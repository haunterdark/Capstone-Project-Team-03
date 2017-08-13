<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<title>Restaurant Guys - FORM TẠO TÀI KHOẢN</title>
<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/restaurant.js"></script>
<link rel="stylesheet" type="text/css"
	href="resources/css/updateprofiles.css" />
</head>
<div id="update-Profiles">
	<body>
		<h1 class="h1">Cập nhật thông tin tài khoản</h1>
		<form class="dangkithongtin" name="xuly">
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<input id="id" type="hidden" name=""
					value="${Restaurant.restaurant_id}" class="signup" />
				<input id="ten" type="text" name=""
					value="${Restaurant.restaurant_username}" class="signup"
					placeholder="Tên tài khoản đăng nhập" disabled />
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>

				<input id="tennhahang" type="text" name=""
					value="${Restaurant.restaurant_name}" class="signup"
					placeholder="Tên nhà hàng" />
				<input id="tennhahang1" type="text" name=""
					value="${Restaurant.restaurant_name}" class="signup"
					placeholder="Tên nhà hàng" style="display: none" />
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
					placeholder="Số điện thoại" />
				<input id="sdt1" type="text" name=""
					value="${Restaurant.restaurant_phone}" class="signup"
					placeholder="Số điện thoại" style="display: none" />
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<input id="diachi" type="text" name=""
					value="${Restaurant.restaurant_address}" class="signup"
					placeholder="Địa chỉ" />
				<input id="diachi1" type="text" name=""
					value="${Restaurant.restaurant_address}" class="signup"
					placeholder="Địa chỉ" style="display: none" />
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<select name="Tinh" id="Tinh" class="signup"
					onchange="loadquan(this.value)">
					<option value="${Restaurant.city_id}">${Restaurant.city_name}</option>
					<c:forEach items="${LoadCity}" var="listcity" varStatus="status">
						<option value="${listcity.city_id}">${listcity.city_name}</option>
					</c:forEach>
				</select>
				<select name="Tinh1" id="Tinh1" class="signup" style="display: none">
					<option value="${Restaurant.city_id}">${Restaurant.city_name}</option>
				</select>
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<select name="Quan" id="quan" class="signup">
					<option value="${Restaurant.district_id}">${Restaurant.district_name}</option>
				</select>
				<select name="Quan1" id="quan1" class="signup" style="display: none">
					<option value="${Restaurant.district_id}">${Restaurant.district_name}</option>
				</select>
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<select name="Country" id="country" class="signup">
					<option value="${Restaurant.country_id}">${Restaurant.country_name}</option>
					<c:forEach items="${LoadCountry}" var="listcountry"
						varStatus="status">
						<option value="${listcountry.country_id}">${listcountry.country_name}</option>
					</c:forEach>
				</select>
				<select name="Country1" id="country1" class="signup"
					style="display: none">
					<option value="${Restaurant.country_id}">${Restaurant.country_name}</option>
				</select>
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<select name="Category" id="category" class="signup">
					<option value="${Restaurant.category_id}">${Restaurant.category_name}</option>
					<c:forEach items="${LoadCategory}" var="listcategory"
						varStatus="status">
						<option value="${listcategory.category_id}">${listcategory.category_name}</option>
					</c:forEach>
				</select>
				<select name="Category1" id="category1" class="signup"
					style="display: none">
					<option value="${Restaurant.category_id}">${Restaurant.category_name}</option>
				</select>
			</ul>
		</form>
		<div id="luuvanhaplai">
			<input class="nutluuprofiles" type="button" value="Lưu" name="luu"
				onclick="updateprofiles()"> <input class="nutreset"
				type="reset" value="Nhập lại" name="nhaplai" onclick="reset()">
			<input class="nutthaydoimatkhau" type="button"
				value="Thay đổi mật khẩu" name="nhaplai" onclick="changpass()">
		</div>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
</div>
</body>


</head>

</html>
<script>
	function updateprofiles() {
		var id = $('#id').val();
		var ten = $("#tennhahang").val();
		var diachi = $('#diachi').val();
		var sdt = $('#sdt').val();
		var tinh = $('#Tinh option:selected').val();
		var quan = $('#quan option:selected').val();
		var country = $('#country option:selected').val();
		var category = $('#category option:selected').val();
		if (confirm("Thực hiện cập nhật thông tin nhà hàng?") == true) {
		if (ten && diachi && sdt) {
			$.ajax({
				url : "checkupdateprofile",
				data : {
					name : encodeURI(ten),
					address : encodeURI(diachi),
					phone : sdt,
				},
				success : function(result) {
					if(result == 1){
						alert("Tên Nhà Hàng không hợp lệ!");
					}else if(result == 2) {
						alert("Địa chỉ không hợp lệ!");
					}else if(result == 3){
						alert("Số Điện Thoại không hợp lệ!");
					}else if(result == 0){
						$.ajax({
							url : "update-Profiles",
							data : {
								restaurant_id : id,
								restaurant_address : encodeURI(diachi),
								restaurant_phone : sdt,
								city_id : tinh,
								district_id : quan,
								country_id : country,
								category_id : category,
								restaurant_name : encodeURI(ten)
							},
							success : function(result) {
								alert("Cập nhật thành công! ");
								loadprofiles();
							}
						});
					}
				}
			});
		} else {
			alert("Hủy lệnh thực hiện");
		}
		
		}else{
			alert("Vui lòng nhập đầy đủ thông tin");
		}
	};
</script>
