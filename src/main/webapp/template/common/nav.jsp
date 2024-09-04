<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<style>
.nav-link{
	color: white;
}

</style>
<nav class="navbar navbar-expand-lg " style="background-color:#757575; color:#fff;">
  <div class="container-fluid">
    <a class="navbar-brand nav-link" href="item" ><img alt="" src="https://img.freepik.com/premium-vector/motorcycle-logo-vector_628306-40.jpg" width="70rem" height="50rem">    MoMoDesign</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="item">Home</a>
        </li>
        <c:if test="${user.role == 'admin'}">
         <li class="nav-item">
          <a class="nav-link" href="item?mode=ITEM_FORM">Add Item</a>
        </li>
        </c:if>
        
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="item?mode=LOGIN_FORM" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            LogIn
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
           <li><a class="dropdown-item" href="login?mode=LOGIN_FORM">LogIn</a></li>
            <li><a class="dropdown-item" href="user?mode=SIGNUP_FORM">Create Account</a></li>
          </ul>
        </li>
        <c:if test="${user.role == 'admin'}">
         <li class="nav-item">
          <a class="nav-link" href="admin">AdminDashboard</a>
        </li>
        </c:if>
          <c:if test="${user.role == 'admin' || user.role =='user'}">
         	<li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="useraccount?mode=EDIT_FORM" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Account
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
           <li><a class="dropdown-item" href="useraccount?mode=EDIT_FORM">My Account</a></li>
			<li><a class="dropdown-item" href="login?mode=LOGOUT">Delete Account</a></li>
            <li><a class="dropdown-item" href="order?mode=ORDER_CONFIRM">Your Order</a></li>
          </ul>
        </li>
        </c:if>
        
      </ul>
     
      	<form class="d-flex" action="item" method="get">
       	<input type="hidden" name="mode" value="SEARCH">
        <input class="form-control me-2" name="query" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>