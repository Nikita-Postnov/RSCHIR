<?php
$fileName = $_FILES['uploadedFile']['name'];
$fileNameCmps = explode(".", $fileName);
$fileExtension = strtolower(end($fileNameCmps));

$blob = addslashes(file_get_contents($_FILES['uploadedFile']['tmp_name']));
$con = new mysqli("MYSQL", "user", "toor", "appDB");
$con->query("INSERT INTO files (content, author, title, `type`) VALUES ('". $blob . "','" . $_COOKIE["name"] . "','" . $_POST["title"] . "','" . $fileExtension . "');");
$con->close();
header("Location: index.php");
?>