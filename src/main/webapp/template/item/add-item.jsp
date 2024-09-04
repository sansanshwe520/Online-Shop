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
	<div class="container-md mt-5 ">
		<div class="col-md-6 mx-auto" >
		<div class="card md-3 alert alert-primary" role="alert">
			<div class="card-header">
				<h3>New Item Form</h3>
			</div>
			<div class="card-body alert alert-primary" role="alert">
		<c:if test="${not empty insertOk }" >
		<c:choose>
			<c:when test="${insertOk }">
			<div class="alert alert-success" role="alert">
  				Successfully created item.
			</div>
			
			</c:when>
			<c:otherwise>
			<div class="alert alert-danger" role="alert">
  			Item creation is failed
			</div>
				
			</c:otherwise>
		</c:choose>
		</c:if>
	<form action="item" method ="post"> 
	<input type="hidden" name="mode" value="CREATE">
  <div class="mb-3">
    <label for="title" class="form-label">Title</label>
    <input type="text" class="form-control" id="title" name="title" required="required">
    
  </div>
  <div class="mb-3">
   <label for="price" class="form-label">Price</label>
    <input type="text" class="form-control" id="price" name="price" required="required">
    
  </div>
 <div class="mb-3">
   <label for="color" class="form-label">Color</label>
    <input type="text" class="form-control" id="color" name="color" required="required">
  </div>
   <div class="mb-3">
   <label for="image" class="form-label">Image</label>
    <input type="url" class="form-control" id="image" name="image" required="required">
  </div>
  <select class="form-select" aria-label="Default select example" id="category" name="category">
  <option selected>Category</option>
 <c:forEach var="category" items="${categoryList}">
  <option value="${category.id }"> ${category.title}</option>
  </c:forEach>
</select>
<div class="mb-3">
   <label for="description" class="form-label">Description</label>
    <textarea rows="10" class="form-control" id="description" name="description" required="required"></textarea>
  </div>
  <button type="submit" class="btn btn-primary float-end">Create</button>
</form>
			</div>
		</div>
		</div>
	</div>
	<!-- form -->
	

	<!-- footer  -->

<c:import url="../common/footer.jsp" />
</body>
</html>