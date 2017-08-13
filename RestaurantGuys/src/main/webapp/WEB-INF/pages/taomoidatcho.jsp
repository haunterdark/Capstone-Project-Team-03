<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<link rel="stylesheet" type="text/css"
	href="resources/css/taodatcho.css" />
<link href='http://fonts.googleapis.com/css?family=Lobster'
	rel='stylesheet'>
<meta charset="utf-8">
<title>Restaurant Guys - FORM TẠO MỚI ĐẶT CHỖ</title>
<script type="text/javascript">
	
</script>
</head>
<div id="taodatcho">
	<body>
		<h1 class="h1">Tạo Đặt Chỗ Mới Cho Nhà Hàng</h1>
		<form class="dangkithongtin" name="xuly" action="" method="post">
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<input id="name" type="text" name="" value="" class="signup"
					placeholder="Tên khách hàng" />
			</ul>

			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>

				<input id="phone" type="text" name="" value="" class="signup"
					placeholder="Số điện thoại" />
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>

				<input id="contents" type="text" name="" value="" class="signup"
					placeholder="Nội dung đặt chỗ" />
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<input id="time" type="text" name="" value="" class="signup"
					placeholder="Thời gian đến" />
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>

				<div class="signup">
					<select id="statusdatcho">
						<option value="" disabled selected>Trạng Thái Đặt Chỗ</option>
						<option value="1">Đã tiếp nhận</option>
						<option value="2">Đã xác nhận</option>
						<option value="3">Đã dùng</option>
						<option value="4">Đã hủy</option>
						<option value="5">Đã không đến</option>
					</select>
				</div>

			</ul>
		</form>
		<input class="nutluudatcho" type="button" value="Lưu" name="luu">
		<input class="nutresetdatcho" type="reset" value="Nhập lại"
			name="nhaplai">
	</body>

	<script type="text/javascript">
		$(".nutluu").click(
				function() {
					var name = $('#name').val();
					var phone = $('#phone').val();
					var contents = $('#contents').val();
					var time = $('#time').val();
					$.ajax({
						url : "add-Reser",
						data : "reservation_cusname=" + name
								+ "&reservation_cusphone=" + phone
								+ "&reservation_contents=" + contents
								+ "&reservation_time=" + time,

						success : function(result) {
							$("#content").html(result);
							$('#name').val('');
							$('#phone').val('');
							$('#contents').val('');
							$('#time').val('');
						}
					});
				});
	</script>
</div>
</head>

</html>