<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Restaurant Guys-Trang Quan Ly</title>
<script src='https://www.google.com/recaptcha/api.js'></script>
<script src="resources/js/register.js">
	
</script>
<script src="resources/js/jquery.min.js">
	
</script>
<link rel="stylesheet" type="text/css"
	href="resources/css/loginstyle.css" />
	<link rel="stylesheet" type="text/css"
	href="resources/css/fitweb.css" />
	<link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet'>

</head>
<body>
	<form name='loginForm'
		action="<c:url value='/j_spring_security_check'/>" method="POST">
		<h1 id="dangnhap">Restaurant Guys</h1>
		<div class="login">
			<div class="username">
				<div class="label">Tên đăng nhập:</div>
				<div class="input">
					<input type="text" name="username">
				</div>
			</div>
			<div class="password">
				<div class="label">Mật khẩu :</div>
				<div class="input">
					<input type="password" name="password">
				</div>
				<input type="submit" class="button buttondangnhap" value="Đăng nhập" />
			</div>
			<div class="remember-me">
				<input name="rememberMe" type="checkbox" value="true" />Ghi nhớ
				đăng nhập.
			</div>
			<div class="mess">
				<c:if test="${not empty error}">
					<div class="error">${error}</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="msg">${msg}</div>
				</c:if>
			</div>

		</div>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<form>
		<h1 id="dangky">Đăng Ký Tài Khoản</h1>

		<ul class="dangkyform">
			<br></br>
			<li class="label">Tên nhà hàng:</li>
			<input id="name" type="text" size="50" name="restaurant_name" />
			<br></br>
			<li class="label">Email:</li>
			<input id="email" type="text" size="50" name="restaurant_email" />
			<br></br>
			<li class="label">Điện thoại liên lạc:</li>
			<input id="phone" type="text" size="50" name="restaurant_phone" />
			<p>
			<div id="g-recaptcha-response" class="g-recaptcha"
				data-sitekey="6LeRjBUTAAAAAI5etot7KUs-qN156uoNTMu21cMw"></div>
			</p>
			<p>
			<div class="error1">
				<div class="erroru" style="display: none;"></div>
				<div class="success" style="display: none;"></div>
			</div>
			</p>
		</ul>
		<p>
			<input id="dangki1" type="button" class="button buttondangky"
				value="Đăng Ký" onclick="dangky()" />
		</p>


		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />

	</form>


</body>
</html>
<script type="text/javascript">
	function dangky() {
		var ten = $('#name').val();
		var email = $('#email').val();
		var phone = $('#phone').val();
		var captchaResponse = $("#g-recaptcha-response").val();
		if (ten) {
			if (email) {
				if (phone) {
					if(captchaResponse){
					$.ajax({
						url : "checkinput",
						data : {
							name : encodeURI(ten),
							email : email,
							phone : phone,
						},
						success : function(result) {
							if(result == 1){
								$('.erroru').text("Tên Nhà Hàng không hợp lệ!");
								$('.success').hide();
								$('.erroru').show();
							}else if(result == 2) {
								$('.erroru').text("Email không hợp lệ!");
								$('.success').hide();
								$('.erroru').show();
							}else if(result == 3){
								$('.erroru').text("Số Điện Thoại không hợp lệ!");
								$('.success').hide();
								$('.erroru').show();
							}else if(result == 0){
								$.ajax({
									url : "databasedangky",
									data : {
										name : encodeURI(ten),
										email : email,
										phone : phone,
									},
									success : function(data){
										if(data ==true){
											$('.erroru').text("Dữ Liệu đã tồn tại!");
											$('.success').hide();
											$('.erroru').show();
										}
										else{
											$.ajax({
												url : "register",
												data : {
													restaurant_name : encodeURI(ten),
													restaurant_email : email,
													restaurant_phone : phone,
													recaptcha : captchaResponse,
												},
												success : function(result) {
													$("#content").html(result);
													$('.erroru').hide();
													$('.success').text("Đăng ký thành công!");
													$('.success').show();
												},
												error : function(result) {
													$('.erroru').text("Lỗi không đăng ký thành công");
													$('.success').hide();
													$('.erroru').show();
												}
											});
										}
									}
								});
								
							}
						},
						error : function(result) {
							$('.erroru').text("Lỗi không kiểm tra dữ liệu nhập vào");
							$('.success').hide();
							$('.erroru').show();
						}
					});
					}else{
						$('.erroru').text("Bạn chưa xác nhận captcha");
						$('.success').hide();
						$('.erroru').show();
					}
				}else{
					$('.erroru').text("Số Điện Thoại không được để trống");
					$('.success').hide();
					$('.erroru').show();
				}
			}else{
				$('.erroru').text("Email không được để trống");
				$('.success').hide();
				$('.erroru').show();
			}
		}else{
			$('.erroru').text("Tên nhà hàng không được để trống");
			$('.success').hide();
			$('.erroru').show();
		}
	}


	
</script>
