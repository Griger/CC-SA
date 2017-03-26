<link rel="stylesheet" href="style.css" type="text/css">
<body>
<?php
$enlace =  mysql_connect('localhost', 'root', '');

 if(isset($_POST['buscar'])){
    $post = $_POST['posicion'];

    if ($post != 'TODAS')
      $query = "SELECT * FROM lol.campeon WHERE posicionPpal = '" . $_POST['posicion'] ."'";
    else
      $query = "SELECT * FROM lol.campeon";
 }
 else
    $query = "SELECT * FROM lol.campeon";
?>

<form action = "show.php" method = "post">
<select name = "posicion">
     <option value="TOP">TOP</option>
     <option value="JNG">JNG</option>
     <option value="MID">MID</option>
     <option value="ADC">ADC</option>
     <option value="SUP">SUP</option>
     <option value="TODAS">TODAS</option>
</select>
<input type="submit" name="buscar" value="Filtrar Pos.">
<input type="submit" name="buscar" formaction="index.php" style="float: right;" value="INICIO"><br><br>
<table>
<thead>
<tr><td>Nombre</td><td>Posici&oacute;n principal</td><td>Rol principal</td><td>Rol secundario</td><td>P</td><td>Q</td><td>W</td><td>E</td><td>R</td></tr>
</thead>
<tbody>

<?php
$campeones = mysql_query($query);

while ($campeon = mysql_fetch_assoc($campeones)){
    echo '<tr>';
    echo "<td>" . $campeon['nombre'] . "</td><td>" . $campeon['posicionPpal'] . "</td><td>" . $campeon['rolPpal'] . "</td><td>" . $campeon['rolSecundario'] . "</td><td>" . $campeon['P'] . "</td><td>" . $campeon['Q'] . "</td><td>" . $campeon['W'] . "</td><td>" . $campeon['E'] . "</td><td>" . $campeon['R'] . "</td>";
    echo "</tr>";
}
?>

</table>
</tbody>
</table>
</form>
</body>
