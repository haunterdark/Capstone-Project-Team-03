
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<title>Restaurant Guys - FORM TẠO TÀI KHOẢN</title>
<link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
<link rel="stylesheet" type="text/css"
	href="resources/css/addnewtaikhoan.css" />
<link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet'>
<script src="resources/js/jquery.min.js">
	
</script>
<script src="resources/js/restaurant.js">
	
</script>
</head>
<div id="taotaikhoan">
	<body>
		<h1 class="h1">Tạo Tài Khoản Mới Cho Nhà Hàng</h1>
		<form class="dangkithongtin" name="xuly" action="" method="post">
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<input id="id" type="hidden" name=""
					value="${Restaurant.restaurant_id}" class="signup" />
				<input id="ten" type="text" name="" value="" class="signup"
					placeholder="Tên tài khoản đăng nhập" />
				<c:if test="${not empty erroru}">
					<div class="erroru">${erroru}</div>
				</c:if>
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>

				<input id="tennhahang" type="text" name=""
					value="${Restaurant.restaurant_name}" class="signup"
					placeholder="Tên nhà hàng" disabled />
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>

				<input id="email" type="text" name=""
					value="${Restaurant.restaurant_email}" class="signup"
					placeholder="Email" disabled />
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<input id="sdt" type="text" name=""
					value="${Restaurant.restaurant_phone}" class="signup"
					placeholder="Số điện thoại" disabled />
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<input id="diachi" type="text" name="" value="" class="signup"
					placeholder="Địa chỉ" value="" />
				<c:if test="${not empty errora}">
					<div class="errora">${errora}</div>
				</c:if>
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<select name="Tinh" id="Tinh" class="signup"
					onchange="loadquan(this.value)">
					<option value="" disabled selected>Thành Phố</option>
					<c:forEach items="${LoadCity}" var="listcity" varStatus="status">
						<option value="${listcity.city_id}">${listcity.city_name}</option>
					</c:forEach>
				</select>
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<select name="Quan" id="quan" class="signup">
					<option value="" disabled selected>Quận</option>
				</select>
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<select name="category" id="category" class="signup">>
					<option value="" disabled selected>Danh Mục</option>
					<c:forEach items="${LoadCategory}" var="listcategory"
						varStatus="status">
						<option value="${listcategory.category_id}">${listcategory.category_name}</option>
					</c:forEach>
				</select>
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<select name="country" id="country" class="signup">>
					<option value="" disabled selected>Ẩm Thực</option>
					<c:forEach items="${LoadCountry}" var="listcountry"
						varStatus="status">
						<option value="${listcountry.country_id}">${listcountry.country_name}</option>
					</c:forEach>
				</select>
			</ul>
		</form>
	<div class = "3nutcuoi 3nutedit">
		<input class="nutluu" type="button" value="Lưu" name="luu">
		<input class="nuttaotk nutreset" type="reset" value="Nhập lại"
			name="nhaplai" onclick="resetedit()">
		<input class="nuttaotk nuthuy" type="button" value="Hủy"
			name="huy" onclick="huy()">
	</div>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
</div>
</body>


</head>

</html>