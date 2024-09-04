<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
	<c:import url="../common/header.jsp" />
	<link href="../static/css/style.css" rel=stylesheet>
</head>
<style>
        body {
            background-image: url('https://png.pngtree.com/thumb_back/fw800/back_our/20190619/ourmid/pngtree-taobao-vector-creative-technology-online-shopping-illustration-computer-finger-poster-image_131803.jpg');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            background-attachment: fixed;
        }
        .card-color{
        background-color:#EEE8AA;
        }
    </style>

<body>
<!-- nav bar -->
	<c:import url="../common/nav.jsp" />
	
	
	
	<div class="container mt-5">
	<form action="order" method ="post"> 
		<input type="hidden" name="mode" value="ORDER_RETURN">
		<input type="hidden" name="orderId" value="${order.id }">
		<div class="col-md-6 ">
		<div class="card md-3 card-color">
		
		
    	
    	<c:forEach var="order" items="${orderList}">
    	
    	<c:if test="${order.user_id == user.id &&  order.stated=='0'}">
    		
   <h3 > Your Order</h3><hr>
	<div class="mb-3 row">
    <label for="title" class="col-sm-2 col-form-label">Product</label>
    <div class="col-sm-10">
    <c:forEach var="item" items="${itemList}"> 
    <p class="card-text"><c:if test="${order.item_id == item.id }">${item.title}</c:if></p>
    </c:forEach>
    </div>
    </div>
    <div class="mb-3 row">
    <label for="price" class="col-sm-2 col-form-label">Price</label>
    <div class="col-sm-10">
    <c:forEach var="item" items="${itemList}"> 
    <p class="card-text"><c:if test="${order.item_id == item.id }">${item.price}</c:if></p>
    </c:forEach>
    </div>
    </div>
     <div class="mb-3 row">
    <label for="color" class="col-sm-2 col-form-label">Color</label>
    <div class="col-sm-10">
    <p class="card-text">${order.color}</p>
    </div>
    </div>
   
    <div class="mb-3 row">
    <label for="quantity" class="col-sm-2 col-form-label">Quantity</label>
    <div class="col-sm-10">
    <p class="card-text">${order.quantity}</p>
    </div>
    </div>
    <div class="mb-3 row">
    <label for="subTotal" class="col-sm-2 col-form-label">SubTotal</label>
   <div class="col-sm-10">
    <p class="card-text">${order.subTotal}</p>
    </div>
    </div>
    <div class="mb-3 row">
    <label for="address" class="col-sm-2 col-form-label">Address:</label>
   <div class="col-sm-10">
    <p class="card-text">${order.address}</p>
    </div>
    </div>
   
     <div class="mb-3 row">
    <label for="email" class="col-sm-2 col-form-label">Your email:</label>
   <div class="col-sm-10">
    <p class="card-text"><c:if test="${order.user_id == user.id }">${user.email}</c:if></p>
    </div>
    </div>
    <div class="mb-3 row">
    <label for="" class="col-sm-2 col-form-label">Payment:</label>
   <div class="col-sm-10">
    <p class="card-text">KPay Account: 09-998765345</p>
    <p class="card-text">Wave Account: 09-998765345</p>
    </div>
    </div>
      <p class="card-text"><c:if test="${order.user_id == user.id }">User Code :SM${user.id}</c:if> (Enter this code for your payment node!)</p>
 
     </c:if>
  
    	</c:forEach> 
    	
    	<c:if test="${order.user_id != user.id }">
    		<form>
    		<h3 > Your Order</h3><hr>
    		 <div style="text-align:center"> <h3>You have no order.</h3></div>
    		</form>
    	</c:if> 
			</div>
			</div>
		 </form>
	</div>
	

</body>
</html> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    h3 {
        text-align: center;
    }
</style>

<body>
 <!-- nav bar -->
    <c:import url="../common/nav.jsp" />
    
    <div class="container mt-5">
<div class="card mb-3" style="max-width: 540px;">
  <div class="row g-0">
    <c:forEach var="item" items="${itemList}">
        <c:if test="${order.item_id == item.id}">
            <div class="col-md-4">
              <img src="${item.image}" class="card-img-top ist-card-image" alt="${item.title}">
           </div>
           <div class="col-md-8">
           <div class="card-body">
                                        <h5 class="card-title">${item.title}</h5>
                                        <p class="card-text">Price: $${item.price}</p>
                                        <p class="card-text">Color: ${order.color}</p>
                                        <p class="card-text">Quantity: ${order.quantity}</p>
                                        <p class="card-text">Subtotal: $${order.subTotal}</p>
                                        <p class="card-text">Address: ${order.address}</p>
                                        <p class="card-text">Your Email: ${user.email}</p>
                                        <p class="card-text">Please enter user code for payment node.<br>User Code: SM${user.id}</p>
                                       
        </div>
     	</div>
       </c:if>
    </c:forEach>
  </div>
</div>
</div>









   <%--  <!-- nav bar -->
    <c:import url="../common/nav.jsp" />

    <div class="container mt-5">
        <div class="col-md-10">
            <div class="row ms-10px"> 
                <div class="col-xs-12 col-sm-10 col-md-6 col-lg-3 col-xl-4 col-xxl-3 ms-5">
                    <div class="card mb-3 mt-3">
                        <div class="card list-card">
                            <c:forEach var="item" items="${itemList}">
                                <c:if test="${order.item_id == item.id}">
                                <div class="col-md-4">
                                    <img src="${item.image}" class="card-img-top ist-card-image" alt="${item.title}">
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body">
                                        <h5 class="card-title">${item.title}</h5>
                                        <p class="card-text">Price: $${item.price}</p>
                                        <p class="card-text">Color: ${order.color}</p>
                                        <p class="card-text">Quantity: ${order.quantity}</p>
                                        <p class="card-text">Subtotal: $${order.subTotal}</p>
                                        <p class="card-text">Address: ${order.address}</p>
                                        <p class="card-text">Your Email: ${user.email}</p>
                                        <p class="card-text">User Code: SM${user.id}</p>
                                    </div>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div> 
        </div>
    </div> --%>
</body>
</html>
