<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/promotion.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/jquery-ui.css">
<link rel="stylesheet" type="text/css"
	href="resources/css/taokhuyenmai.css" />
<link href='http://fonts.googleapis.com/css?family=Lobster'
	rel='stylesheet'>
<script type="text/javascript" src="resources/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
<script type="text/javascript">
	$(function() {
		$('#start').datepicker({
			dateFormat : "yy-mm-dd",
			showOtherMonths : true,
			selectOtherMonths : false,
			showAnim : "slide"
		});
		$('#end').datepicker({
			dateFormat : "yy-mm-dd",
			showOtherMonths : true,
			selectOtherMonths : false,
			showAnim : "slide"
		});
	});
</script>
<html>
<head>
<meta charset="utf-8">
<title>Restaurant Guys - FORM TẠO MỚI KHUYẾN MÃI</title>

</head>
<div id="taokhuyenmai">
	<body>
		<h1 class="h1">Tạo Khuyến Mãi Mới Cho Nhà Hàng</h1>
		<form:form id="myForm" class="dangkithongtin" method="POST"
			modelAttribute="fileBucker" enctype="multipart/form-data"
			action="./save-promotions">

			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<form:input id="file" type="file" path="file" class="signup"
					placeholder="Hình ảnh"
					accept="image/gif, image/jpg, image/png, image/jpeg"
					max-size="32154" />
			</ul>

			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>

				<input id="tenkhuyenmai" type="text" name="promotion_name" value=""
					class="signup" placeholder="Tên khuyến mãi" />
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>

				<input id="start" type="text" name="promotion_start" value=""
					class="signup" placeholder="Ngày bắt đầu" />
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<input id="end" type="text" name="promotion_end" value=""
					class="signup" placeholder="Ngày kết thúc" />
			</ul>
			<div id="nutthaotackm">
				<input class="nutluukm" type="submit" name="luu" value="Lưu">
				<input class="nutresetkm" type="reset" name="nhaplai"
					value="Nhập lại" onclick="resetadd()"> <input
					class="nuthuykm" type="reset" name="huy" value="Hủy"
					onclick="huy()">
			</div>
		</form:form>
	</body>

	<script type="text/javascript">
		function huy() {
			if (confirm("Bạn muốn hủy thao tác đang thực hiện không?") == true) {
				$.ajax({
					url : "get-promotions",
					success : function(result) {
						$("#content").html(result);
					}
				});
			} else {
				alert("Chào mừng quay về tạo khuyến mãi!");
			}
		}
		$("#myForm").submit(function(e) {
			var img = $('#file').val();
			var ten = $('#tenkhuyenmai').val();
			var start = $('#start').val();
			var end = $('#end').val();
			var today = new Date().toISOString().slice(0, 10);
			var st = new Date(start);
			var en = new Date(end);
			var to = new Date(today);
				if (ten && start && end) {
					if (st > to && en > to && en >= st) {
						if (confirm("Thực hiện tạo khuyến mãi?") == true) {
							alert("Tạo khuyến mãi thành công!");
							$('#tenkhuyenmai').val(encodeURI(ten));
							$('#form').submit({
								submit : true
							});
						} else {
							e.preventDefault();
							alert("Hủy lệnh thực hiện");
						}
					} else {
						e.preventDefault();
						alert("Ngày không hợp lệ!");
					}
				} else {
					e.preventDefault();
					alert("Vui lòng nhập đầy đủ thông tin!");
				}
			
		});

		function resetadd() {
			$('#file').val('');
			$('#tenkhuyenmai').val('');
			$('#start').val('');
			$('#end').val('');
		}
	</script>
</div>
</head>

</html>
