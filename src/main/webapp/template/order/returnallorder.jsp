
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<c:import url="../common/header.jsp" />
	<link href="../static/css/style.css" rel="stylesheet">
</head>
<style>
    body {
        background-image: url('https://png.pngtree.com/thumb_back/fw800/back_our/20190619/ourmid/pngtree-taobao-vector-creative-technology-online-shopping-illustration-computer-finger-poster-image_131803.jpg');
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        background-attachment: fixed;
    }
    .card-color {
        background-color: #EEE8AA;
    }
    h3{
    text-algin:center;
    }
</style>

<body>
    <!-- nav bar -->
    <c:import url="../common/nav.jsp" />

    <div class="container mt-5">
        <form action="order" method="post">
            <input type="hidden" name="mode" value="ORDER_RETURN">
            <input type="hidden" name="orderId" value="${order.id}">
             <div class="col-md-10">
      	<div class= "row ms-10px"> 
      	 <c:forEach var="order" items="${orderList}">
       <c:if test="${order.user_id == user.id && order.stated == '0'}">
       <c:url var="detailslink" value="order">
      		<c:param name="mode" value="ORDERCOMFIRMVIEW"/>
      		<c:param name="orderId" value="${order.id}"/>
      	</c:url>
      	 <div class="col-xs-12 col-sm-10 col-md-6 col-lg-3 col-xl-4 col-xxl-3 ms-5">
      	<div class="card mb-3 mt-3">
          <div class="card list-card">
      		 <c:forEach var="item" items="${itemList}">
               <c:if test="${order.item_id == item.id}"><img src="${item.image }" class="card-img-top ist-card-image" alt="${item.title }"></c:if>
                </c:forEach>
  			<div class="card-body">
  			<c:forEach var="item" items="${itemList}">
  			 <c:if test="${order.item_id == item.id}">
   			 <h5 class="card-title">${item.title }</h5>
   			  <p class="card-text">Price :$ ${item.price}</p>
   			   <a href="${detailslink}" class="btn btn-info ">View</a>
   			 </c:if>
   			 </c:forEach>
  			 
  		</div>
		</div>
      	</div>
      </div>
       </c:if>
      	
      </c:forEach>
      	

     </div> 
      </div>
            
            
        </form>
    </div>
</body>
</html>
