<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="../common/header.jsp" />
<link href="../static/css/style.css" rel=stylesheet>
</head>
<style>
h4{
text-align:center;
}


</style>
<body class="alert alert-primary" role="alert">
	<!-- nav bar -->
	<c:import url="../common/nav.jsp" />
		<form action="order" method="post" class="alert alert-primary" role="alert">
		<input type="hidden" name="mode" value="PAYMENTLIST"> <input
			type="hidden" name="orderId" value="${order.id }">

		<table id="example" class="table table-striped" style="width: 100%">
		<h4>Confirmed Payment</h4><hr>
			<thead>
				<tr>
					<th>Item name</th>
					<th>Color</th>
					<th>User ID</th>
					<th>Name</th>
					<th>Email</th>
					<th>Phone</th>
					<th>Address</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Sub Total</th>
					<th>Date</th>

				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty orderList}">
					<c:forEach var="order" items="${orderList}">
						<c:if test="${order.payment=='0'}">

						<tr>
							<c:forEach var="item" items="${itemList}">
								<c:if test="${order.item_id == item.id}">
									<td>${item.title}</td>
								</c:if>
							</c:forEach>
							<td>${order.color}</td>
							<c:forEach var="user" items="${userList}">
								<c:if test="${order.user_id== user.id }">
									<td>${user.id}</td>
									<td>${user.name}</td>
									<td>${user.email}</td>
								</c:if>

							</c:forEach>
							<td>${order.phone}</td>
							<td>${order.address}</td>
							<c:forEach var="item" items="${itemList}">
								<c:if test="${order.item_id == item.id}">
									<td>${item.price}</td>
								</c:if>
							</c:forEach>

							<td>${order.quantity}</td>
							<td>${order.subTotal}</td>
							<td>${order.issuedDate}</td>

						</tr>
						</c:if>
					</c:forEach>
				</c:if>



				<c:if test="${empty orderList}">
					<tr>
						<td colspan="12">No orders found.</td>
					</tr>
				</c:if>
			</tbody>


			<tfoot>
				<tr>
					<th>Item name</th>
					<th>Color</th>
					<th>User ID</th>
					<th>Name</th>
					<th>Email</th>
					<th>Phone</th>
					<th>Address</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Sub Total</th>
					<th>Date</th>

				</tr>
			</tfoot>
		</table>

	</form>

	<!-- footer -->
	<%-- <c:import url="../common/footer.jsp" /> --%>
</body>
</html>