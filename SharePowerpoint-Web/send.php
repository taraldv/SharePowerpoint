<?php
	//error_reporting(E_ALL);
	//ini_set('display_errors','on');
	$keyString = $_POST['key'];
	$url = "www.tarves.no";
	$fields = ['btnSubmit' => $keyString];
	//url-ify the data for the POST
	$fields_string = http_build_query($fields);

	//open connection
	$ch = curl_init();

	//set the url, number of POST vars, POST data
	curl_setopt($ch,CURLOPT_PORT , 3322);
	curl_setopt($ch,CURLOPT_URL, $url);
	curl_setopt($ch,CURLOPT_POST, true);
	curl_setopt($ch,CURLOPT_POSTFIELDS, $fields_string);

	//So that curl_exec returns the contents of the cURL; rather than echoing it
	curl_setopt($ch,CURLOPT_RETURNTRANSFER, false); 

	//execute post
	$result = curl_exec($ch);
?>