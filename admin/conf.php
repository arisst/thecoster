<?php
$dbHost = "localhost";
$dbUser = "root";
$dbPass = "aris123";
$dbName = "theCoster";


	if (!mysql_connect($dbHost, $dbUser, $dbPass)){
		echo "mysql gagal";
	}
	$db = mysql_select_db($dbName);
if (!$db){
	echo "gagal Select db. error: ".mysql_error()."<br>";
}
?>