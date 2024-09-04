<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<c:import url="../common/header.jsp" />
	<link href="../static/css/style.css" rel=stylesheet>
</head>
<style>
.user-card-image{
	width: 26rem;
	min-height: 30rem;
	max-height: 30rem;
	object-fit: fill;
}
body{
		background-color :#bdbdbd;
		width: 100%;
	}
	
</style>

<body>
<!-- nav bar -->
	<c:import url="../common/nav.jsp" />
	
	
	
	
	<div class="container mt-5 ">
	<div class="card mb-3"  style="max-width: 1200px;max-height:30rem; min-height:30rem; mx-auto">
  	<div class="row g-0 alert alert-dark" role="alert">
    <div class="  col-md-5  user-card-image">
      <img src="${user.image}" class="user-card-image" alt="${user.image }">
    </div>
    <div class="col-md-7 mx-auto alert alert-dark" role="alert" >
      <div class="card-body">
      <div class="card-header clearfix mb-5" >
		<p class="card-text h3">Name : ${user.name }</p>
        <p class="card-text h3">Email : ${user.email} </p>
		
       <p>
  <button class="btn btn-primary float-end" type="button" data-bs-toggle="collapse" data-bs-target="#setting" aria-expanded="false" aria-controls="collapseExample" >
   Change Settings
  </button>
</p>
</div>
   </div>
      
<div class="collapse" id="setting">
  <div class="card card-body alert alert-dark" role="alert">
  <div class="mb-3 row">
  <form action ="useraccount" method ="post">
  	<input type="hidden" name="mode" value="EDIT">
  	<input type="hidden" name="userId" value="${user.id }">
    <div class="card">
    	<div class="card-header clearfix">
    		<h2>If you want to change your data.</h2>
    	</div>
    	<div class="card-body">
    		<div class="mb-3 row">
     <label for="image" class="col-sm-2 col-form-label">Profile</label>
    <div class="col-sm-10">
      <input type="url" class="form-control" id="image" name="image"  required="required"  value="${user.image }">
    </div>
    </div>
  	<div class="mb-3 row">
    <label for="name" class="col-sm-2 col-form-label">Name</label>
    <div class="col-sm-10">
     <input type="text" class="form-control" id="name" name="name"  required="required"  value="${user.name }">
    </div>
    </div>
      <div class="mb-3 row">
    <label for="email" class="col-sm-2 col-form-label">Email</label>
    <div class="col-sm-10">
     <input type="text" class="form-control" id="email" name="email"  required="required"  value="${user.email }">
    </div>
  </div>
  
    
     <button type="submit" class="btn btn-primary float-end mt-3">Sumbit</button>
  </div>
   </div>
  
    	</form>	
   
    
    
     <form action ="useraccount" method ="post">
  	<input type="hidden" name="mode" value="EDIT_PASSWORD">
  	<input type="hidden" name="userId" value="${user.id }">
  	<input type="hidden" name="image" value="${user.image }">
  	<input type="hidden" name="name" value="${user.name}">
  	<input type="hidden" name="email" value="${user.email }">
  	
  	
    <div class="card">
    	<div class="card-header clearfix">
    		<h2>Change Password</h2>
    	</div>
    	<div class="card-body">
    		<div class="mb-3 row">
    
   
   <div class="mb-3 row">
    <label for="inputpassword" class="col-sm-2 col-form-label">Password</label>
    <div class="col-sm-10">
    <p>New Password</p>
      <input type="password" class="form-control" id="inputpassword" name="newpassword" required="required">
    </div>
    
    <div class="mb-3 row">
    <label for="inputpassword" class="col-sm-2 col-form-label">Password</label>
    <div class="col-sm-10">
    <p> If you want to change password, enter the old password.</p>
     <input type="password" class="form-control" id="inputpassword" name="oldpassword" required="required">
    </div>
  </div>
   </div>
   </div>

   <button type="submit" class="btn btn-primary float-end">Sumbit</button>
   </div>
   </div>
   
    </form>
	</div>
	</div>
	</div>
    </div>
  </div>
</div>
</div>
	<div></div>
	
	
	

	<%-- <p>
  <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#setting" aria-expanded="false" aria-controls="collapseExample">
   Change Settings
  </button>
</p>
<div class="collapse" id="setting">
  <div class="card card-body">
  <div class="mb-3 row">
  <form action ="useraccount" method ="post">
  	<input type="hidden" name="mode" value="EDIT">
  	<input type="hidden" name="userId" value="${user.id }">
    <div class="card">
    	<div class="card-header clearfix">
    		<h2>If you want to change your data.</h2>
    	</div>
    	<div class="card-body">
    		<div class="mb-3 row">
     <label for="image" class="col-sm-2 col-form-label">Profile</label>
    <div class="col-sm-10">
      <input type="url" class="form-control" id="image" name="image"  required="required"  value="${user.image }">
    </div>
    </div>
  	<div class="mb-3 row">
    <label for="name" class="col-sm-2 col-form-label">Name</label>
    <div class="col-sm-10">
     <input type="text" class="form-control" id="name" name="name"  required="required"  value="${user.name }">
    </div>
    </div>
      <div class="mb-3 row">
    <label for="email" class="col-sm-2 col-form-label">Email</label>
    <div class="col-sm-10">
     <input type="text" class="form-control" id="email" name="email"  required="required"  value="${user.email }">
    </div>
  </div>
  
    
    
  </div>
   </div>
   <button type="submit" class="btn btn-primary float-end">Sumbit</button>
    	</form>	
   
    
    
     <form action ="useraccount" method ="post">
  	<input type="hidden" name="mode" value="EDIT_PASSWORD">
  	<input type="hidden" name="userId" value="${user.id }">
  	<input type="hidden" name="image" value="${user.image }">
  	<input type="hidden" name="name" value="${user.name}">
  	<input type="hidden" name="email" value="${user.email }">
  	
  	
    <div class="card">
    	<div class="card-header clearfix">
    		<h2>Change Password</h2>
    	</div>
    	<div class="card-body">
    		<div class="mb-3 row">
    
   
   <div class="mb-3 row">
    <label for="inputpassword" class="col-sm-2 col-form-label">Password</label>
    <div class="col-sm-10">
    <p>New Password</p>
      <input type="password" class="form-control" id="inputpassword" name="newpassword" required="required">
    </div>
    
    <div class="mb-3 row">
    <label for="inputpassword" class="col-sm-2 col-form-label">Password</label>
    <div class="col-sm-10">
    <p> If you want to change password, enter the old password.</p>
     <input type="password" class="form-control" id="inputpassword" name="oldpassword" required="required">
    </div>
  </div>
   </div>
   </div>

   <button type="submit" class="btn btn-primary float-end">Sumbit</button>
   </div>
   </div> --%>
   
    </form>
	</div>
	</div>
	</div>
	
	


</body>
</html>