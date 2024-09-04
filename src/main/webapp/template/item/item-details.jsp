<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<c:import url="../common/header.jsp" />
	<link href="../static/css/style.css" rel=stylesheet>
</head>
<style>
.detail-color{
		background-color :#bdbdbd;
		
	
}

body{
		background-color :#bdbdbd;
		width: 100%;
	}
</style>
<body>
<!-- nav bar -->
	<c:import url="../common/nav.jsp" />
	
      
	<!-- item details card -->
	 
	<div class="container mt-3 ">
	<div class="card mb-3" style="max-width:1200px;">
	<div class="row g-0  ">
    <div class="col-md-6 ">
       <img src="${item.image }" class="card-img-top ist-card-image" alt="${item.title }">
    </div>
    <div class="col-md-6 detail-color ">
      <div class="card-body ">
     <c:forEach var="category" items="${categoryList}">
   		<c:if test="${item.category_id == category.id }"> ${category.title}</c:if>
   	</c:forEach>
        <h2 class="card-title">${item.title}</h2>
        <p class="card-text">Price: $ ${item.price}</p>
        
      	<p class="card-text">
<select class="form-select" aria-label="Default select example" id="itemId" name="itemColor">
  <option selected>Color</option>
  <c:forEach var="color" items="${itemColors}">
    <option value="${color}">${color}</option>
  </c:forEach> 
</select>
</p>

        
        <p class="card-text" >Free delivery for all orders from $99</p>
     
      <c:choose>
      		<c:when test="${user.role == 'admin'}">
      		<p>
  			<a class="btn btn-primary" data-bs-toggle="collapse" href="#update" role="button" aria-expanded="false" aria-controls="collapseExample">
  			  Update
  			</a>
  			
 			 <c:url var="deleteLink" value="item">
 			 <c:param name="mode" value="DELETE"/>
 			  <c:param name="itemId" value="${item.id }"/>
			  </c:url>

 			 <a href="${deleteLink}" class="btn btn-danger">Delete</a>
			</p>
      		</c:when>
      		<c:otherwise>
      			
	<p>
  <a class="btn btn-primary" data-bs-toggle="collapse" href="#order" role="button" aria-expanded="false" aria-controls="collapseExample">
     <button class="btn btn-primary " type="button" data-bs-toggle="collapse" data-bs-target="#order" aria-expanded="false" aria-controls="collapseExample">
    Order
  </button>
  </a>
</p>
 </c:otherwise>
  </c:choose>

<%-- <div class="collapse" id="order">
  <div class="card card-body detail-color ">
   		<form action="order" method ="post"> 
		<input type="hidden" name="mode" value="ORDER">
		<input type="hidden" name="itemId" value="${item.id }">
		<input type="hidden" name="userId" value="${user.id }">
<c:if test="${user.role != 'user' }">
 <p> You have no account.</p>
</c:if>
<div class="mb-3">
   <label for="color" class="form-label">Color</label>
    <select class="form-select" aria-label="Default select example" id="itemId" name="itemColor">
  <option selected>Color</option>
  <c:forEach var="color" items="${itemColors}">
    <option value="${color}">${color}</option>
  </c:forEach> 
</select>
    </div>
<div class="mb-3">
   <label for="phone" class="form-label">Phone</label>
    <input type="text" class="form-control" id="phone" name="phone" required="required">
    </div>
   <div class="mb-3">
   <label for="address" class="form-label">Address</label>
    <input type="text" class="form-control" id="address" name="address" required="required">
    </div>
    <div class="mb-3">
   <label for="quantity" class="form-label">Quantity</label>
    <input type="text" class="form-control" id="quantity" name="quantity" required="required">
    </div>
  <button type="submit" class="btn btn-primary float-end">Submit</button>
</form>
  </div>
</div>
	 --%>
	 
	 <div class="collapse" id="order">
  <div class="card card-body detail-color">
    <c:choose>
      <c:when test="${user.role == 'user'}">
        <!-- User has an account, display the form -->
        <form action="order" method="post">
          <input type="hidden" name="mode" value="ORDER">
          <input type="hidden" name="itemId" value="${item.id}">
          <input type="hidden" name="userId" value="${user.id}">

          <div class="mb-3">
            <label for="color" class="form-label">Color</label>
            <select class="form-select" aria-label="Default select example" id="itemId" name="itemColor">
              <option selected>Color</option>
              <c:forEach var="color" items="${itemColors}">
                <option value="${color}">${color}</option>
              </c:forEach>
            </select>
          </div>

          <div class="mb-3">
            <label for="phone" class="form-label">Phone</label>
            <input type="text" class="form-control" id="phone" name="phone" required="required">
          </div>

          <div class="mb-3">
            <label for="address" class="form-label">Address</label>
            <input type="text" class="form-control" id="address" name="address" required="required">
          </div>

          <div class="mb-3">
            <label for="quantity" class="form-label">Quantity</label>
            <input type="text" class="form-control" id="quantity" name="quantity" required="required">
          </div>

          <button type="submit" class="btn btn-primary float-end">Submit</button>
        </form>
      </c:when>
      <c:otherwise>
        <!-- User does not have an account, display a message -->
        <p>You have no account.</p>
      </c:otherwise>
    </c:choose>
  </div>
</div>
	 

<!-- update -->
<div class="collapse" id="update">
  <div class="card card-body detail-color">
   <div class="container-md">
		<div class="alert alert-primary" role="alert">
		
				<h3 style="text-align:center">Update Item Form</h3><hr>
			
			<div class="card-body alert alert-primary" role="alert">
		<c:if test="${not empty updateOk and not updateok }">
			<div class="alert alert-danger" role="alert">
  			Your updating item is fail.
			</div>
		</c:if>
	<form action="item" method ="post"> 
		<input type="hidden" name="mode" value="UPDATE">
		<input type="hidden" name="itemId" value="${item.id }">
  <div class="mb-3">
    <label for="title" class="form-label">Title</label>
    <input type="text" class="form-control" id="title" name="title" required="required" value="${ item.title}">
    
  </div>
  <div class="mb-3">
   <label for="price" class="form-label">Price</label>
    <input type="text" class="form-control" id="price" name="price" required="required" value="${item.price }">
    
  </div>
 <div class="mb-3">
   <label for="color" class="form-label">Color</label>
    <input type="text" class="form-control" id="color" name="color" required="required" value="${item.color }">
  </div>
   <div class="mb-3">
   <label for="image" class="form-label">Image</label>
    <input type="url" class="form-control" id="image" name="image" required="required" value="${item.image }">
  </div>
   <select class="form-select" aria-label="Default select example" id="category" name="category">
  <option selected>Category</option>
 <c:forEach var="category" items="${categoryList}">
  <option value="${category.id }"> ${category.title}</option>
  </c:forEach>
</select>
<div class="mb-3">
   <label for="description" class="form-label">Description</label>
    <textarea rows="10" class="form-control" id="description" name="description" required="required" >${item.description}</textarea>
  </div>
  <button type="submit" class="btn btn-primary float-end">Update</button>
</form>
			</div>
		</div>
		</div>
	</div>
  </div>
</div>


        
      </div>
    </div>
  </div>
  </div>
  </div>
		
	 
 
	<div class="container">
	<div class="row mx-auto" >
			<div class="col-12">
	<p>
  <a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    DESCRIPTION
  </a>
  <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#sizechat" aria-expanded="false" aria-controls="collapseExample">
    SIZE CHAT
  </button>
</p>
<div class="collapse" id="collapseExample">
  <div class="card card-body ">
    <p class="card-text"> ${item.description}</p>
  </div>
</div>

<div class="collapse" id="sizechat">
  <div class="card card-body">
   <p class="card-text"> <strong>MOTORBIKE SUITS : MAN</strong></p>
  <table class="table">
  	<thead>
  		<tr>
  		<th>Euro</th>
  		<th>44</th>
  		<th>46</th>
  		<th>48</th>
  		<th>50</th>
  		<th>52</th>
  		<th>54</th>
  		<th>56</th>
  		<th>58</th>
  		<th>60</th>
  		<th>62</th>
  		<th>64</th>
  		</tr>
  	</thead>
  	<tbody>
  		<tr>
  		<td>Alpha</td>
  		<td>S</td>
  		<td>S</td>
  		<td>M</td>
  		<td>M</td>
  		<td>L</td>
  		<td>L</td>
  		<td>XL</td>
  		<td>XL</td>
  		<td>2XL</td>
  		<td>3XL</td>
  		<td>4XL</td>
  		</tr>
  		<tr>
  		<td>USA-UK</td>
  		<td>34</td>
  		<td>36</td>
  		<td>38</td>
  		<td>40</td>
  		<td>42</td>
  		<td>44</td>
  		<td>46</td>
  		<td>48</td>
  		<td>50</td>
  		<td>52</td>
  		<td>54</td>
  		</tr>
  		<tr>
  		<td>Height</td>
  		<td>166-169</td>
  		<td>169-172</td>
  		<td>172-175</td>
  		<td>175-178</td>
  		<td>178-181</td>
  		<td>181-184</td>
  		<td>184-187</td>
  		<td>187-188</td>
  		<td>188-189</td>
  		<td>189-190</td>
  		<td>190-191</td>
  		</tr>
  		<tr>
  		<td>Chest</td>
  		<td>86-90</td>
  		<td>90-94</td>
  		<td>94-98</td>
  		<td>98-102</td>
  		<td>102-106</td>
  		<td>106-110</td>
  		<td>110-114</td>
  		<td>114-118</td>
  		<td>118-122</td>
  		<td>122-126</td>
  		<td>126-130</td>
  		</tr>
  		<tr>
  		<td>Arm</td>
  		<td>56-57</td>
  		<td>57-58</td>
  		<td>58-59</td>
  		<td>59-60</td>
  		<td>60-61</td>
  		<td>61-62</td>
  		<td>62-63</td>
  		<td>62-63</td>
  		<td>63-64</td>
  		<td>63-64</td>
  		<td>64-65</td>
  		</tr>
  		<tr>
  		<td>Neck</td>
  		<td>36.5-36.7</td>
  		<td>37.5-38.5</td>
  		<td>38.5-39.5</td>
  		<td>39.5-40.5</td>
  		<td>40.5-41.5</td>
  		<td>41.5-42.5</td>
  		<td>42.5-43.5</td>
  		<td>43.5-44.5</td>
  		<td>44.5-45.5</td>
  		<td>45.5-46.5</td>
  		<td>46.5-47.5</td>
  		</tr>
  		<tr>
  		<td>Waist</td>
  		<td>74-78</td>
  		<td>78-82</td>
  		<td>82-86</td>
  		<td>86-90</td>
  		<td>90-94</td>
  		<td>94-98</td>
  		<td>98-102</td>
  		<td>102-106</td>
  		<td>106-110</td>
  		<td>110-114</td>
  		<td>114-118</td>
  		</tr>
  		<tr>
  		<td>Hips</td>
  		<td>88-92</td>
  		<td>92-96</td>
  		<td>96-100</td>
  		<td>100-104</td>
  		<td>104-108</td>
  		<td>108-112</td>
  		<td>112-116</td>
  		<td>116-120</td>
  		<td>120-124</td>
  		<td>124-128</td>
  		<td>128-132</td>
  		</tr>
  		<tr>
  		<td>Inseam</td>
  		<td>77-79</td>
  		<td>79-80</td>
  		<td>80-81</td>
  		<td>81-82</td>
  		<td>82-83</td>
  		<td>83-84</td>
  		<td>84-85</td>
  		<td>85-86</td>
  		<td>86-87</td>
  		<td>87-88</td>
  		<td>88-89</td>
  		</tr>
  	</tbody>
  </table><br><br>
  <p class="card-text"> <strong>MOTORBIKE :</strong> GLOVES</p>
  
  <table class="table">
  	<thead>
  		<tr>
  		<th>Size</th>
  		<th>XXXS</th>
  		<th>XXS</th>
  		<th>XS</th>
  		<th>S</th>
  		<th>M</th>
  		<th>L</th>
  		<th>XL</th>
  		<th>XXL</th>
  		<th>XXXL</th>
  		</tr>
  	</thead>
  	<tbody>
  		<tr>
  		<td>INCHES</td>
  		<td>6.5</td>
  		<td>7</td>
  		<td>7.5</td>
  		<td>8</td>
  		<td>8.5</td>
  		<td>9</td>
  		<td>9.5</td>
  		<td>10</td>
  		<td>10.5</td>
  		</tr>
  		<tr>
  		<td>CENTIMETRES</td>
  		<td>16.5</td>
  		<td>17.8</td>
  		<td>19.1</td>
  		<td>20.3</td>
  		<td>21.6</td>
  		<td>22.9</td>
  		<td>24.1</td>
  		<td>25.4</td>
  		<td>26.7</td>
  		</tr>
  	</tbody>
  </table>

  </div>
</div>

</div>
</div>
</div>

	 
<!-- footer -->
<c:import url="../common/footer.jsp" />
</body>
</html>