<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <c:import url="../common/header.jsp" />
    <link href="../static/css/style.css" rel="stylesheet">
</head>
<style>
body{
		background-color :#bdbdbd;
		width: 100%;
	}
	
	.list-card{
	width: 19rem;
	height: 35rem;
	
	
}
</style>
<body>
    <!-- Nav -->
    <c:import url="../common/nav.jsp" />
    <!-- End Nav -->

    <div class="container">
        <div class="row">
            <c:forEach var="item" items="${itemList}">
                <div class="col-xs-12 col-sm-10 col-md-6 col-lg-3 col-xl-4 col-xxl-3">
                    <div class="card mb-3 mt-3">
                        <div class="card list-card">
                            <img src="${item.image}" class="card-img-top list-card-image" alt="${item.title}">
                            <div class="card-body">
                                <h5 class="card-title">${item.title}</h5>
                                <p class="card-text">Price: $${item.price}</p>
                                <p class="card-text">Color: ${item.color}</p>
                                
                                <!-- View Item Details Link -->
                                <c:url var="detailslink" value="item">
                                    <c:param name="mode" value="SINGLE"/>
                                    <c:param name="itemId" value="${item.id}"/>
                                </c:url>
                                <a href="${detailslink}" class="btn btn-info">View</a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <c:if test="${order.user_id != user.id }">
    		<form>
    		<h3 > Your Order</h3><hr>
    		 <div style="text-align:center"> <h3>You have no order.</h3></div>
    		</form>
    	</c:if> 
        </div>
    </div>
</body>
</html>
 --%>
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
        background-color: #bdbdbd;
        width: 100%;
    }

    .list-card {
        width: 19rem;
        height: 35rem;
    }
</style>
<body>
    <!-- Nav -->
    <c:import url="../common/nav.jsp" />
    <!-- End Nav -->

    <div class="container">
        <div class="row">
            <!-- Check if user has orders -->
            <c:set var="hasOrder" value="false" />

          
            <c:forEach var="order" items="${orderList}">
                <c:if test="${order.user_id == user.id}">
                    <c:set var="hasOrder" value="true" />

                   
                    <c:forEach var="item" items="${itemList}">
                        <c:if test="${order.item_id == item.id}">
                            <div class="col-xs-12 col-sm-10 col-md-6 col-lg-3 col-xl-4 col-xxl-3">
                                <div class="card mb-3 mt-3">
                                    <div class="card list-card">
                                        <img src="${item.image}" class="card-img-top list-card-image" alt="${item.title}">
                                        <div class="card-body">
                                            <h5 class="card-title">${item.title}</h5>
                                            <p class="card-text">Price: $${item.price}</p>
                                           

                                            <!-- View Order Details Link -->
                                            <c:url var="detailslink" value="order">
                                                <c:param name="mode" value="ORDERCOMFIRMVIEW"/>
                                                <c:param name="orderId" value="${order.id}"/>
                                            </c:url>
                                            <a href="${detailslink}" class="btn btn-info">View</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </c:if>
            </c:forEach>

            <!-- Display message if the user has no orders -->
            <c:if test="${!hasOrder}">
                <div style="text-align:center; width: 100%;">
                    <h3>Your Order</h3>
                    <hr>
                    <h3>You have no order.</h3>
                </div>
            </c:if>
        </div>
    </div>
</body>
</html>
 