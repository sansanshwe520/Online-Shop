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
<body>
<!-- Nav -->
	<c:import url="../common/nav.jsp"/>
	
	<div class="collapse" id="userlist">
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

</body>
</html>