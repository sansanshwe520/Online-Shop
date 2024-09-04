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
				<h3>Login Here</h3>
			</div>
			<div class="card-body alert alert-primary" role="alert">
		<c:if test="${not empty loginOk and not loginOk}" >
			<div class="alert alert-danger" role="alert">
  			Username or Password is incorrect.
			</div>
		</c:if>
	<form action="login" method ="post"> 
		<input type="hidden" name="mode" value="LOGIN">
  <div class="mb-3">
    <label for="name" class="form-label">UserName or Email</label>
    <input type="text" class="form-control" id="name" name="name" required="required">
    
  </div>
  
  <div class="mb-3">
   <label for="inputpassword" class="form-label">Password</label>
    <input type="password" class="form-control" id="inputpassword" name="password" required="required">
  </div>
 
  <button type="submit" class="btn btn-primary float-end">LogIn</button>
  <p class="clear-float">Does not have an account? sign up <a href="user" class="text-decoration-none">Here</a></p>
</form>
			</div>
		</div>
		</div>
	</div>
<!-- footer -->
<c:import url="../common/footer.jsp" />

</body>
</html>