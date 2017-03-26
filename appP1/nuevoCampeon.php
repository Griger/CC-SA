<link rel="stylesheet" href="style.css" type="text/css">
<body>
  <?php
  if(isset($_POST['crear'])){
    if (empty($_POST['nombre']) || empty($_POST['posicionPpal']) || empty($_POST['rolPpal'])
    || empty($_POST['rolSecundario']) || empty($_POST['P']) || empty($_POST['Q'])
    || empty($_POST['W']) || empty($_POST['E']) || empty($_POST['R']))
     echo "Has de rellenar todos los espacios";
    else {
      $enlace =  mysql_connect('localhost', 'root', '');
      $qr = "INSERT INTO lol.campeon VALUES('" . implode("', '", array_slice($_POST,0,-1)) . "')";
      mysql_query($qr);
      mysql_close($enlace);
    }
  }
   ?>
  <div align = "center">
    <form method = "post" action="nuevoCampeon.php">
      <table>
        <tr><td> Nombre: </td><td> <input type="text" name="nombre" value=""></td></tr>
        <tr><td> Posición: </td><td> <select name = "posicionPpal">
             <option value="TOP">TOP</option>
             <option value="JNG">JNG</option>
             <option value="MID">MID</option>
             <option value="ADC">ADC</option>
             <option value="SUP">SUP</option>
        </select> </td></tr>
        <tr><td> Rol principal: </td><td> <input type="text" name="rolPpal" value=""></td></tr>
        <tr><td> Rol secundario: </td><td> <input type="text" name="rolSecundario" value=""></td></tr>
        <tr><td> P: </td><td> <input type="text" name="P" value=""></td></tr>
        <tr><td> Q: </td><td> <input type="text" name="Q" value=""></td></tr>
        <tr><td> W: </td><td> <input type="text" name="W" value=""></td></tr>
        <tr><td> E: </td><td> <input type="text" name="E" value=""></td></tr>
        <tr><td> R: </td><td> <input type="text" name="R" value=""></td></tr>
      </table>
      <input type="submit" name="crear" value="CREAR"> <input type="submit" name="buscar" formaction="index.php" value="SALIR"><br><br>



    </form>
  </div>
</body>
