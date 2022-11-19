Листинг 2 – Файл download.php
<?php

if (!isset($_GET['id'])){
    header("Location: index.php");
    exit;
}

$con = new mysqli("MYSQL", "user", "toor", "appDB");
$query_result = $con->query("SELECT * FROM files WHERE ID='".$_GET['id']."'");
if (mysqli_num_rows($query_result) == 0)
    die("File doesnt exists");

$row = mysqli_fetch_object($query_result);
header("Content-type: application/" . $row->type);
header('Content-Disposition: attachment; filename="'.$row->title.'.'.$row->type.'"');
echo $row->content;
?>