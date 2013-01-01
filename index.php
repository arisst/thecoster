<?php
/**
 * File to handle all API requests
 * Accepts GET and POST
 *
 * Each request will be identified by TAG
 * Response will be JSON data
 
  /**
 * check for POST request
 */
if (isset($_POST['tag']) && $_POST['tag'] != '') {
    // get tag
    $tag = $_POST['tag'];
 
    // include db handler
    require_once 'include/DB_Functions.php';
    $db = new DB_Functions();
 
    // response Array
    $response = array("tag" => $tag, "success" => 0, "error" => 0);
 
    // check for tag type
    if ($tag == 'login') {
        // Request type is check Login
        $email = $_POST['email'];
        $password = $_POST['password'];
 
        // check for user
        $user = $db->getUserByEmailAndPassword($email, $password);
        if ($user != false) {
            // user found
            // echo json with success = 1
	    $log = $db->getLog($email, "CURDATE()");
	if($log!=false){
            for($i=0; $i<count($log); $i++){
		$response["log"]["$i"] = $log["$i"];
            }}
            $response["success"] = 1;
            $response["uid"] = $user["unique_id"];
            $response["user"]["name"] = $user["name"];
            $response["user"]["email"] = $user["email"];
            $response["user"]["mobile"] = $user["mobile"];
	    $response["user"]["start"] = $user["start"];
            $response["user"]["credit"] = $user["credit"];
            $response["user"]["created_at"] = $user["created_at"];
            $response["user"]["updated_at"] = $user["updated_at"];
            echo json_encode($response);
        } else {
            // user not found
            // echo json with error = 1
            $response["error"] = 1;
            $response["error_msg"] = "Incorrect email or password!";
            echo json_encode($response);
        }
    }
	else if ($tag == 'register') {
        // Request type is Register new user
        $name = $_POST['name'];
        $email = $_POST['email'];
        $mobile = $_POST['mobile'];
        $password = $_POST['password'];
 
        // check if user is already existed
        if ($db->isUserExisted($email)) {
            // user is already existed - error response
            $response["error"] = 2;
            $response["error_msg"] = "User already existed";
            echo json_encode($response);
        } else {
            // store user
            $user = $db->storeUser($name, $email, $mobile, $password);
            if ($user) {
            // user stored successfully
            $response["success"] = 1;
            $response["uid"] = $user["unique_id"];
            $response["user"]["name"] = $user["name"];
            $response["user"]["email"] = $user["email"];
            $response["user"]["mobile"] = $user["mobile"];
	    $response["user"]["start"] = $user["start"];
            $response["user"]["credit"] = $user["credit"];
            $response["user"]["created_at"] = $user["created_at"];
            $response["user"]["updated_at"] = $user["updated_at"];
                echo json_encode($response);
            } else {
                // user failed to store
                $response["error"] = 1;
                $response["error_msg"] = "Error occured in Registartion";
                echo json_encode($response);
            }
        }
    }
	else if($tag == 'logout'){
	$email = $_POST['email'];
	$goods = $_POST['goods'];
	$price = $_POST['price'];
	$time = $_POST['time'];
	$log = $db->storeLog($email, $goods, $price, $time);
 		
            

}
else if($tag == 'delete'){
	$email = $_POST['email'];
	$log = $db->deleteLog($email);
 		
            

}
else if($tag == 'sum'){
	$email = $_POST['email'];
	$log = $db->sumToday($email);
 	$response["sum"] = $log["sum"];	
        echo json_encode($response);

}
 else {
        echo "Invalid Request";
    }
} else {
    //echo "Access Denied";
	header('Location: admin');
}
?>
