//1. Visualiza la colección pedidos y familiarízate con ella. Observa los distintos tipos de datos y sus estructuras dispares.

db.pedidos.find();

// 2. Visualiza sólo el primer documento de la colección. Utiliza los métodos .limit() y .findOne()

db.pedidos.find().limit(1);
db.pedidos.findOne();

// 3. Visualiza el cliente con id_cliente = 2222

db.pedidos.find({id_cliente : 2222});

// 4. Visualiza los clientes que hayan pedido algún producto de más de 94 euros

db.pedidos.find(
   {Pedidos :
     {$elemMatch :
       {Productos : {$elemMatch : {Precio_unidad : {$gt : 94} } } }
     }
   },
   {id_cliente : true}
);

// 5. Visualiza los clientes de Jaén o Salamanca (excluye los datos de los pedidos). Utiliza los operadores $or e $in.

db.pedidos.find(
  {$or : [{Localidad : "Jaen"}, {Localidad : "Salamanca"}]},
  {Pedidos : false}
);

db.pedidos.find(
  {Localidad : {$in : ["Jaen", "Salamanca"]}},
  {Pedidos : false}
);

// 6. Visualiza los clientes que no tienen campo pedidos.

db.pedidos.find(
  {Pedidos : {$exists : false}}
);

// 7. Visualiza los clientes que hayan nacido en 1963.

db.pedidos.find(
	{Fnacimiento : {$gte : new Date(1963, 1, 1), $lte : new Date(1963, 12, 31)}	}
);

// 8. Visualiza los clientes que hayan pedido algún producto fabricado por Canon y algún producto cuyo precio sea inferior a 15 euros.

db.pedidos.find(
  {$and : [
		{Pedidos :
			{$elemMatch :
				{Productos : {$elemMatch : {Precio_unidad : {$lt : 15} } } }
			}},
		{Pedidos :
			{$elemMatch :
				{Productos : {$elemMatch : {Fabricante : "Canon" } } }
			}}
		]
	},
	{id_cliente : true}
);

// 9. Datos personales (id_cliente, Nombre, Direccion, Localidad y Fnacimiento) de los clientes cuyo nombre empieza por la cadena "c" (No distinguir entre mayusculas y minúsculas).

db.pedidos.find(
	{Nombre : {$regex : /^c|C/}},
	{id_cliente : true, Nombre : true, Direccion : true, Localidad : true, Fnacimiento : true}
);

// 10. Visualiza los datos personales de los clientes (excluyendo \_id). Limita los documentos a 4.

db.pedidos.find(
	{},
	{_id : false}
).limit(4);

// 11. Ídem anterior pero ordenando los documentos por Localidad (ascendente) e id_cliente (descendente).

db.pedidos.find(
	{},
	{_id : false}
).limit(4).sort({Localidad : 1, id_cliente : -1});
