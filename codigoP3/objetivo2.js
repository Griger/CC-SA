// 1. Nº total de clientes.

db.pedidos.aggregate(
	[
		{$count : "nClientes"}
	]
);

// 2. No total de clientes de Jaén.

db.pedidos.aggregate(
	[
		{$match : {Localidad : "Jaen"}},
		{$count : "nClientes"}
	]
);

// 3. Facturación total de clientes por localidad.

db.pedidos.aggregate(
	[
		{$group : {
			_id : {localidad : "$Localidad"},
			facturacionTotal : {$sum : "$Facturacion"}
		}}
	]
);

// 4. Facturación media de clientes por localidad para las localidades distintas a "Jaen" con facturación media mayor de 5000. Ordenación por Localidad descendente. Eliminar el _id y poner el nombre en mayúsculas.

db.pedidos.aggregate(
	[
		{$match : {Localidad : {$not : /Jaen/}}},
		{$group : {
			_id : "$Localidad",
			facturacionMedia : {$avg : "$Facturacion"}
		}},
		{$match : {facturacionMedia : {$gt : 5000}}},
		{$sort : {_id : -1}},
		{$project : {nombreLocalidad : {$toUpper : "$_id"}, _id : false, facturacionMedia : true}}
	]
);

// 5. Calcula la cantidad total facturada por cada cliente (uso de “unwind”).

db.pedidos.aggregate(
	[
		{$project : {id_cliente : true, Nombre : true, Pedidos : true}},
		{$unwind : "$Pedidos"},
		{$unwind : "$Pedidos.Productos"},
		{$group : {
			_id : {id_cliente : "$id_cliente", nombre : "$Nombre"},
			facturacionTotal : {$sum : {$multiply : ["$Pedidos.Productos.Precio_unidad", "$Pedidos.Productos.Cantidad"]}}
		}}
	]
);
