<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<title>Restaurant Guys - FORM ĐĂNG KÝ THÔNG TIN TÀI KHOẢN</title>
<link rel="stylesheet" type="text/css" href="resources/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/addnewtaikhoan.css" />
<link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet'>
<script src="resources/js/jquery.min.js">
	
</script>
<script src="resources/js/restaurant.js">
	
</script>
</head>
<div id="addnewtaikhoan">

	<body>
		<h1 class="h1">Tạo Đăng Ký Mới Cho Nhà Hàng</h1>
		<form class="dangkithongtin" name="xuly" action="" method="get">

			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<input id="ten" type="text" name="" value="" class="signup"
					placeholder="Tên tài khoản đăng nhập" />
				<div class="mess">
					<c:if test="${not empty erroru}">
						<div class="erroru">${erroru}</div>
					</c:if>

				</div>
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>

				<input id="tennhahang" type="text" name="" value="" class="signup"
					placeholder="Tên nhà hàng" />
				<div class="mess1">
					<c:if test="${not empty errorn}">
						<div class="errorn">${errorn}</div>
					</c:if>
				</div>
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>

				<input id="email" type="text" name="" value="" class="signup"
					placeholder="Email" />
				<div class="mess2">
					<c:if test="${not empty errore}">
						<div class="errore">${errore}</div>
					</c:if>
				</div>
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<input id="sdt" type="text" name="" value="" class="signup"
					placeholder="Số điện thoại" />
				<div class="mess3">
					<c:if test="${not empty errorp}">
						<div class="errorp">${errorp}</div>
					</c:if>
				</div>
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<input id="diachi" type="text" name="" value="" class="signup"
					placeholder="Địa chỉ" />
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
				<select name="category" id="category" class="signup">
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
				<select name="country" id="country" class="signup">
					<option value="" disabled selected>Ẩm Thực</option>
					<c:forEach items="${LoadCountry}" var="listcountry"
						varStatus="status">
						<option value="${listcountry.country_id}">${listcountry.country_name}</option>
					</c:forEach>
				</select>
			</ul>
		</form>
		<div id="3nutcuoi">
		<input class="nutluu" type="button" value="Lưu" name="luu">
		<input class="nuttaotk nutreset" type="reset" value="Nhập lại"
			name="nhaplai" onclick="reset()">
			<input class="nuttaotk nuthuy" type="button" value="Hủy"
			name="huy" onclick="huy()">
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
		</div>
	</body>
	<script type="text/javascript">
		$(".save").click(

		function() {
			var ten = $('#ten').val();
			var diachi = $('#diachi').val();
			var tennhahang = $('#tennhahang').val();
			var email = $('#email').val();
			var sdt = $('#sdt').val();
			var city = $('#Tinh').text();
			var district = $('#Quan').text();

		}

		);
	</script>
	<script type="text/javascript">
		function loaddistrict(clicked_id) {
			var s = '';
			$.ajax({
				url : "loadlistdistrict",
				data : "id=" + clicked_id,
				dataType : "json",
				type : 'GET',
				contentType : "application/json",
				success : function(result) {
					for (var i = 0; i < result.length; i++) {
						s += '<option value='+result[i].district_id +'>'
								+ result[i].district_name + '</option>';
						$('#quan').html(s);
					}
				},
				error : function(result) {
					alert(JSON.stringify(result));
				}
			});
		}
	</script>

</div>
</head>

</html>