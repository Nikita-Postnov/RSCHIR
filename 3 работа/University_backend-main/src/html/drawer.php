<?php

$ar = htmlspecialchars($_GET["code"]);
$as = str_split(decbin($ar));

$a = array_sum($as);
$draw = new \ImagickDraw();

// Create ImagickPixel object
$strokeColor = new \ImagickPixel('Red');
$fillColor = new \ImagickPixel('Green');
  
// Set the color, opacity of image
$draw->setStrokeOpacity(1*$a);
$draw->setStrokeColor('Red');
$draw->setFillColor('Green');
  
// Set the width and height of image
$draw->setStrokeWidth(1*$a);
$draw->setFontSize(72*$a);
   
// Function to draw circle 
if($a % 2 == 0){
    $draw->rectangle(25*$a, 25*$a, 10*$a, 15*$a);
}
else {
    $draw->circle(25*$a, 25*$a, 10*$a, 15*$a);
}
   
$imagick = new \Imagick();
$imagick->newImage(1100, 1100, 'White');
$imagick->setImageFormat("png");
$imagick->drawImage($draw);
   
// Display the output image
header("Content-Type: png");
echo $imagick->getImageBlob();
?>
