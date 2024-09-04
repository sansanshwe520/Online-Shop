<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://code.jquery.com/jquery-3.7.0.js"
  type="text/javascript"></script>
<c:import url="../common/header.jsp" />
<link
  href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css"
  rel="stylesheet">
<link
  href="https://cdn.datatables.net/1.13.7/css/dataTables.bootstrap5.min.css"
  rel="stylesheet">
<script
  src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"
  type="text/javascript"></script>
</head>
<style>
.mag{
 margin-right:10rem;
}
body {
            background-image: url('https://wallpapercave.com/wp/wp10701711.jpg');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            background-attachment: fixed;
        }
</style>
<body>
<!-- Nav -->
	<c:import url="../common/nav.jsp"/>
<!-- End Nav -->

 <div class="container mt-5">
   <a class="btn btn-primary mag btn btn-primary" data-bs-toggle="collapse" href="#useralllist" role="button" aria-expanded="false" aria-controls="collapseExample">
    User List
  </a>
  <button class="btn btn-primary mag btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#categoryupdate" aria-expanded="false" aria-controls="collapseExample">
   Category
  </button>
  
  
  
  
  
<c:url var="orderlistlink" value="order">
      		<c:param name="mode" value="ORDERLIST"/>
      		<c:param name="orderId" value="${order.id}"/>
      	</c:url>
  <a href="${orderlistlink}" class=" mag btn btn-primary">Order List</a>
  
  <c:url var="orderlistlink" value="order">
      		<c:param name="mode" value="PAYMENTLIST"/>
      		<c:param name="orderId" value="${order.id}"/>
      	</c:url>
  <a href="${orderlistlink}" class=" mag btn btn-primary">Confirmed Payment</a>
  
 
 <div class="collapse mt-3" id="useralllist">
  <div class="card card-body">
   
   	<table id="example" class="table table-striped" style="width:100%">
   <thead>
   		<tr>
   			<th>ID</th>
   			<th>Name</th>
   			<th>Email</th>
   			<th>CreatedAt</th>
   			<th>Action</th>
   		</tr>
   </thead>
   <tbody>
   		<tr>
   			<c:forEach var="user" items="${userList }">
   				<tr>
   					<td>${user.id }</td>
   					<td>${user.name }</td>
   					<td>${user.email }</td>
   					<td>${user.createdAt }</td>
   					<td>
   						<c:choose>
   							<c:when test="${user.enable }">
   								<c:url var="disableLink" value="admin">
   									<c:param name="mode" value="DISABLE"/>
   									<c:param name="userId" value="${user.id }"/>
   								</c:url>
   									<a class="btn btn-danger" href="${disableLink }">Disable</a>
   							</c:when>
   							<c:otherwise>
   								<c:url var="enableLink" value="admin">
   									<c:param name="mode" value="ENABLE"/>
   									<c:param name="userId" value="${user.id }"/>
   								</c:url>
   									<a class="btn btn-success" href="${enableLink }">Enable</a>
   							</c:otherwise>
   						</c:choose>
   					</td> 
   				</tr>
   			</c:forEach>
   		</tr>
   </tbody>
   <tfoot>
   	<tr>
   			<th>ID</th>
   			<th>Name</th>
   			<th>Email</th>
   			<th>CreatedAt</th>
   			<th>Action</th>
   		</tr>
   </tfoot>
   </table>
   
</div>
</div> 
<div class="collapse mt-3" id="categoryupdate">
  <div class="card card-body">
   <table id="example" class="table table-striped" style="width:100%">
    <thead>
    	<tr>
    		<th>Title</th>
    		<th>Action</th>
    	</tr>
    </thead>
    <tbody>
    	
    	<c:forEach var="category" items="${categoryList}">
    			<tr>
   					<td>${category.title}</td>
   					<td><c:url var="deleteLink" value="admin">
   						<c:param name="mode" value="DELETE"/>
   						<c:param name="categoryId" value="${category.id }"/>
   						</c:url>
   					<a class="btn btn-danger" href="${deleteLink }">Delete</a>
   					</td> 
   					</tr>
   		</c:forEach>
   		<tr>
   	<td>
  <a class="btn btn-primary" data-bs-toggle="collapse" href="#addcategory" role="button" aria-expanded="false" aria-controls="collapseExample">
    <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#addcategory" aria-expanded="false" aria-controls="collapseExample">
   Add Category
  </button>
  </a>

<div class="collapse mt-3" id="addcategory">
  <div class="card card-body">
  <form action="admin" method ="post"> 
<input type="hidden" name="mode" value="CREATE">
  <div class="mb-3">
    <label for="title" class="form-label">New Category</label>
    <input type="text" class="form-control" id="title" name="title"  aria-describedby="title" required="required">
  </div>
  <button type="submit" class="btn btn-primary">Submit</button>
</form>
  </div>
</div>
   			</td> 
   		</tr>
   				
    </tbody>
   </table>
</div>
  </div>
  </div><hr>
  <!-- <div class="col-md-12 body-image">
  
  </div> -->
<!-- footer -->
<script type="text/javascript">
	new DataTable('#example');
</script>


</body>
</html>