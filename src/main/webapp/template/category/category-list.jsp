<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<c:import url="../common/header.jsp" />
	<link href="../static/css/style.css" rel=stylesheet>
</head>
<body>
<!-- nav bar -->
	<c:import url="../common/nav.jsp" />
	<div class="container">
	<input type="hidden" name="mode" value="CATEGORY_LIST">
	<p>
  <a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Category
  </a>
  <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
    Button with data-bs-target
  </button>
</p>
<div class="collapse" id="collapseExample">
  <div class="card card-body">
  <select class="form-select" aria-label="Default select example">
  <option selected>All category list...</option>
  <c:forEach var="category" categorys="${categoryList}">
  <option value="1"> ${category.title}</option>
  </c:forEach>

</select>
    
  </div>
</div>
  </div>
</div>
</div>
</body>
</html>