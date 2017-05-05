// 4. ¿Cómo podríamos obtener adicionalmente la cantidad de parejas de ciudades evaluadas para cada país consultado?.

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
	var contador = 0;
	var city1 = { "name": "" };
	var city2 = { "name": "" };
	var c1;
	var c2;
	var d2;

	for (var i in reduced.data) {
		for (var j in reduced.data) {
			if (i>=j) continue;
			contador++;
			c1 = reduced.data[i];
			c2 = reduced.data[j];
			d2 = (c1.lat-c2.lat)*(c1.lat-c2.lat)+(c1.lon-c2.lon)*(c1.lon-c2.lon);

			if (d2 < min_dist && d2 > 0) {
				min_dist = d2;
				city1 = c1;
				city2 = c2;
			}
		}
	}
	return {"city1": city1.name, "city2": city2.name, "dist": Math.sqrt(min_dist), "numCiudadesEvaluadas" : contador};
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
