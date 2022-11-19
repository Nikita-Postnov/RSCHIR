<?php

$ar = htmlspecialchars($_GET["code"]);
$a = explode(',', $ar);

function quick_sort($my_array)
 {
	$loe = $gt = array();
	if(count($my_array) < 2)
	{
		return $my_array;
	}
	$pivot_key = key($my_array);
	$pivot = array_shift($my_array);
	foreach($my_array as $val)
	{
		if($val <= $pivot)
		{
			$loe[] = $val;
		}elseif ($val > $pivot)
		{
			$gt[] = $val;
		}
	}
	return array_merge(quick_sort($loe),array($pivot_key=>$pivot),quick_sort($gt));
}
 
echo "Original Array : ".implode(',',$a). "\n";
$my_array = quick_sort($a);
echo 'Sorted Array : '.implode(',',$my_array);
?>
