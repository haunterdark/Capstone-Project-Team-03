<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/promotion.js"></script>

<link rel="stylesheet" type="text/css"
	href="resources/css/taoquangcao.css"/>
<link href='http://fonts.googleapis.com/css?family=Lobster'
	rel='stylesheet'>
<link rel="stylesheet" type="text/css"
	href="resources/css/jquery-ui.css">
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
<title>Restaurant Guys - FORM TẠO MỚI QUẢNG CÁO</title>

</head>
<div id="taoquangcao">
	<body>
		<h1 class="h1">Tạo Quảng Cáo Mới Cho Nhà Hàng</h1>
		<form:form id="myForm" class="dangkithongtin" method="POST"
			modelAttribute="fileBucker" enctype="multipart/form-data"
			action="./save-advertisement">

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

				<input id="tenquangcao" type="text" name="advertisement_name" value=""
					class="signup" placeholder="Tên quảng cáo" />
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>

				<input id="start" type="text" name="advertisement_start" value=""
					class="signup" placeholder="Ngày bắt đầu" />
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<input id="end" type="text" name="advertisement_end" value="" class="signup"
					placeholder="Ngày kết thúc" />
			</ul>
			<div id="nutthaotacqc">
				<input class="nutluuqc" type="submit" name="luu" value="Lưu">
				<input class="nutresetqc" type="reset" name="nhaplai"
					value="Nhập lại" onclick="resetadd()"> <input
					class="nuthuyqc" type="reset" name="huy" value="Hủy"
					onclick="huy()">
			</div>
		</form:form>
	</body>

	<script type="text/javascript">
		function huy() {
			if (confirm("Bạn muốn hủy thao tác đang thực hiện không?") == true) {
				$.ajax({
					url : "get-advertisements",
					success : function(result) {
						$("#content").html(result);
					}
				});
			} else {
				alert("Chào mừng quay về tạo quảng cáo!");
			}
		}
		$("#myForm").submit(function(e) {
			var tenquangcao = $('#tenquangcao').val();
			var start = $('#start').val();
			var end = $('#end').val();
			var today = new Date().toISOString().slice(0, 10);
			var st = new Date(start);
			var en = new Date(end);
			var to = new Date(today);
				if (tenquangcao && start && end) {
					if (st > to && en > to && en >= st) {
						if (confirm("Thực hiện tạo quảng cáo?") == true) {
							alert("Tạo quảng cáo thành công!");
							$('#tenquangcao').val(encodeURI(tenquangcao));
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
			$('#tenquangcao').val('');
			$('#start').val('');
			$('#end').val('');
		}
	</script>
</div>
</head>

</html>
