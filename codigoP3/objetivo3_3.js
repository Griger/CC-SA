// 3. ¿Qué ocurre si en un país hay dos parejas de ciudades que están a la misma distancia mínima? ¿Cómo harías para que aparecieran todas?

var mapCode = function MapCode() {
	emit(this.CountryID,
		{ "data":
			[
				{
					"name": this.City,
					"lat": this.Latitude,
					"lon": this.Longitude
				}
			]
	});
};

var reduceCode = function ReduceCode(key, values) {
	var reduced = {"data":[]};
	for (var i in values) {
		var inter = values[i];
		for (var j in inter.data) {
			reduced.data.push(inter.data[j]);
		}
	}
	return reduced;
};

var finalize = function Finalize(key, reduced) {
	if (reduced.data.length == 1) {
		return { "message" : "Este pais solo contiene una ciudad" };
	}

	var min_dist = 999999999999;
	var pair_list = {};
	var contador = 0;
	var c1;
	var c2;
	var d2;

	for (var i in reduced.data) {
		for (var j in reduced.data) {
			if (i>=j) continue;
			c1 = reduced.data[i];
			c2 = reduced.data[j];
			d2 = (c1.lat-c2.lat)*(c1.lat-c2.lat)+(c1.lon-c2.lon)*(c1.lon-c2.lon);

			if (d2 < min_dist && d2 > 0) {
				pair_list = {};
				contador = 0;
				min_dist = d2;
				pair_list["pair" + contador] = {"city1": c1.name, "city2": c2.name, "dist": Math.sqrt(min_dist)};
			}
			else if (d2 == min_dist) {
				contador++;
				pair_list["pair" + contador] = {"city1": c1.name, "city2": c2.name, "dist": Math.sqrt(min_dist)};
			}
		}
	}
	return pair_list;
};

db.runCommand({
	mapReduce : "cities",
	map : mapCode,
	reduce : reduceCode,
	finalize : finalize,
	query : { "CountryID" : { "$ne" : 254 } },
	out: { merge: "ciudades_proximas" }
});

db.ciudades_proximas.find();
