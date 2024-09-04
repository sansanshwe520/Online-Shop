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
body{
		background-color :#bdbdbd;
		width: 100%;
	}
	
</style>
<body>
<!-- nav bar -->
	<c:import url="../common/nav.jsp" />
<!-- container -->
	<div class="container-md ">
		<div class="col-md-6 mx-auto ">
		<div class="card md-3 mt-5 alert alert-primary" role="alert">
			<div class="card-header">
				<h3>Create Account</h3>
			</div>
			<div class="card-body alert alert-primary" role="alert">
		<c:if test="${not empty signupOk }" >
		<c:choose>
			<c:when test="${signupOk }">
			<div class="alert alert-success" role="alert">
  				Successfully created Account.
			</div>
			
			</c:when>
			<c:otherwise>
			<div class="alert alert-danger" role="alert">
  			Account creation is failed
			</div>
				
			</c:otherwise>
		</c:choose>
		</c:if>
	<form action="user" method ="post"> 
		<input type="hidden" name="mode" value="SIGNUP">
  <div class="mb-3">
    <label for="name" class="form-label">Name</label>
    <input type="text" class="form-control" id="name" name="name" required="required">
    
  </div>
  <div class="mb-3">
    <label for="email" class="form-label"> Email</label>
    <input type="text" class="form-control" id="email" name="email" required="required">
    
  </div>
  <div class="mb-3">
   <label for="password" class="form-label">Password</label>
    <input type="text" class="form-control" id="password" name="password" required="required">
  </div>
 <div class="mb-3">
   <label for="image" class="form-label">Image</label>
    <input type="url" class="form-control" id="image" name="image" required="required">
  </div>
  <button type="submit" class="btn btn-primary float-end">Create</button>
  <p class="clear-float">Already has an account? sign in <a href="login" class="text-decoration-none">Here</a></p>
  
</form>
			</div>
		</div>
		</div>
	</div>
<!-- footer -->
<c:import url="../common/footer.jsp" />

</body>
</html>