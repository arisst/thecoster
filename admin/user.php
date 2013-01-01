<?php
include 'conf.php';
$query=mysql_query("select uid,name,email,mobile from users");
?>

<h2 class="well">List User</h2>
<div class="row">
<div class="span2">
<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
<!-- Button to trigger modal -->
<a href="#addNew" role="button" class="btn" data-toggle="modal">Add User</a>
<!-- Modal -->
<div id="addNew" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
    <h3 id="myModalLabel" align="center">Add New User</h3>
  </div>
  <form action="../add.php" method="post" class="form-horizontal">
  <div class="modal-body">
  
  <div class="control-group">
	<label class="control-label" for="nama">Nama</label>
	<div class="controls">
		<input type="text" id="nama" name="nama" placeholder="Nama" required autofocus>
	</div>
	<label class="control-label" for="email">Email</label>
	<div class="controls">
		<input type="email" id="email" name="email" placeholder="Email" required>
	</div>
	<label class="control-label" for="mobile">Mobile</label>
	<div class="controls">
		<input type="tel" id="mobile" name="mobile" placeholder="Mobile" required>
	</div>
	<label class="control-label" for="pass1">Password</label>
	<div class="controls">
	<input type="password" id="pass1" name="pass1" placeholder="Password" required>
	</div>
	<label class="control-label" for="pass2">Password Confirm</label>
	<div class="controls">
	<input type="password" id="pass2" name="pass2" placeholder="Password" required>
	</div>
	<div class="controls">
	<!--button class="btn" data-dismiss="modal" aria-hidden="true">Close</button-->
	<button type="reset" class="btn">Clear</button>
    <button type="submit" class="btn btn-primary">Submit</button>
	</div>
  </div>
 
  </div>
  <div class="modal-footer">
    <p>* ) Required</p>
  </div>
  </form>
</div>
</div>
<div class="span4">
<form class="form-search">
  <div class="input-append">
    <input type="text" class="span2 search-query">
    <button type="submit" class="btn">Search</button>
  </div>
</form>
</div>
<div class="span2">
<?php
if(isset($_GET['status'])&&$_GET['status']!=''){
	$status=$_GET['status'];
	if($status=='success'){echo '<h5 class="text-success">Add User Success ... </h5>';}
	else if($status=='exist'){echo '<span class="text-error">Email Already Exist ... </span>';}
	else if($status=='pass'){echo '<span class="text-error">Password Not Match ... </span>';}
	else {echo '<span class="text-error">Error Inserting Data ... </span>';}
}
 ?>
 </div>
</div>

<table class="table table-hover table-bordered table-condensed">
  <thead>
  <tr>
    <th>No</th>
	<th>Name</th>
	<th>Email</th>
	<th>Mobile</th>
	<th>Action</th>
  </tr>
  </thead>
  <tbody class="text-info">
  <?php
  $i=1;
  while($result=mysql_fetch_array($query)){
  ?>
  <tr>
	<td><?=$i?></td>
	<td><?=$result['name'];?></td>
	<td><a href="mailto:<?=$result['email'];?>"><?=$result['email'];?><a></td>
	<td><?=$result['mobile'];?></td>
	<td><a href="#Edit<?=$result['uid']?>" title="Edit" data-toggle="modal"><i class="icon-edit"></i>Edit</a> <a href="?page=del&id=<?=$result['uid']?>" title="Delete"> <i class="icon-remove"></i>Delete</a></td>
	
<!------------------->	
	<!-- Button to trigger modal >
<a href="#Edit" role="button" class="btn" data-toggle="modal">Edit</a>
<!-- Modal -->
<div id="Edit<?=$result['uid']?>" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
    <h3 id="myModalLabel" align="center">Edit <?=$result['name']?></h3>
  </div>
  <form action="../edit.php" method="post" class="form-horizontal">
  <div class="modal-body">
  
  <div class="control-group">
	<label class="control-label" for="nama">Nama</label>
	<div class="controls">
		<input type="text" id="nama" name="nama" value="<?=$result['name']?>" required autofocus>
	</div>
	<label class="control-label" for="email">Email</label>
	<div class="controls">
		<input type="email" id="email" name="email" value="<?=$result['email']?>" required>
	</div>
	<label class="control-label" for="mobile">Mobile</label>
	<div class="controls">
		<input type="tel" id="mobile" name="mobile" value="<?=$result['mobile']?>" required>
	</div>
	<label class="control-label" for="pass1">Password</label>
	<div class="controls">
	<input type="password" id="pass1" name="pass1" placeholder="<unchanged>">
	</div>
	<label class="control-label" for="pass2">Password Confirm</label>
	<div class="controls">
	<input type="password" id="pass2" name="pass2" placeholder="<unchanged>">
	</div>
	<div class="controls">
	<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
	<!--button type="reset" class="btn">Clear</button-->
    <button type="submit" class="btn btn-primary">Submit</button>
	</div>
  </div>
 
  </div>
  <div class="modal-footer">
    <p>* ) Required</p>
  </div>
  </form>
</div>
<!------------------->	
	
  </tr>
  <?php $i++; } ?>
  <tbody>
</table>




