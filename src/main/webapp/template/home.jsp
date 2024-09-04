<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<c:import url="common/header.jsp"/>
	<link href="static/css/style.css" rel="stylesheet">
	<style>
	body{
		background-color :#bdbdbd;
		width: 100%;
	}
	.list-card{
	width: 18rem;
	height: 35rem;
	
	
}
	</style>
</head>
<body>
<!-- Nav -->
	<c:import url="common/nav.jsp"/>
<!-- End Nav -->

<div class="container">
  <div class="row">
    <div class="col mx-auto mt-3 "> 
  
  
      	
   <%--  <form action="item" method ="post"> 
		<input type="hidden" name="mode" value="SEARCH_ITEM">
		<input type="hidden" name="categoryId" value="${category.id }"> --%>
		
    <%--   
     <ul class="nav flex-column">
     
  <li class="nav-item">
 
    <a class="nav-link active" aria-current="page" href="${searchlink }" >MEN</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="${searchlink }">WOMEN</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="${searchlink }">OUTLET</a>
  </li>
  
</ul> --%>
<form action="category" method ="post"> 
		<input type="hidden" name="mode" value="CATEGORY_LIST">
		<input type="hidden" name="categoryId" value="${category.id }">
 <ul class="nav flex-column mt-5">
     <c:forEach var="category" items="${categoryList}">
  <li class="nav-item" >
    <a class="nav-link active" aria-current="page" href="item?mode=SEARCH_ITEM&categoryId=${category.id}">${category.title}</a>
  </li>
 </c:forEach>
</ul>
</form>
    </div>
    <!-- Second Column -->
    <div class="col-md-10">
      	<div class= "row ms-10px"> 
      	<c:forEach var="item" items="${itemList}">
      
      	<c:url var="detailslink" value="item">
      		<c:param name="mode" value="SINGLE"/>
      		<c:param name="itemId" value="${item.id}"/>
      	</c:url>
      	 <div class="col-xs-12 col-sm-10 col-md-6 col-lg-3 col-xl-4 col-xxl-3 ">
      	<div class="card  mb-3 mt-3   ">
      		<div class="card list-card ">
  			<img src="${item.image }" class="card-img-top ist-card-image" alt="${item.title }">
  			<div class="card-body">
   			 <h5 class="card-title">${item.title }</h5>
   			 <c:forEach var="category" items="${categoryList}">
   			<p><c:if test="${item.category_id == category.id }"> ${category.title}</c:if></p>
   			</c:forEach>	
   			 <p class="card-text">Price :$ ${item.price}</p>
   			  <p class="card-text">Color :${item.color}</p>
  			  <a href="${detailslink}" class="btn btn-info ">View</a>
  		</div>
		</div>
      	</div>
      </div>
      </c:forEach>
      	

     </div> 
      </div>
      
    
  </div>
 </div>
	<c:import url="common/footer.jsp" />
</body>
</html>