<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/promotion.js"></script>
<link rel="stylesheet" type="text/css"
	href="resources/css/jquery-ui.css">
<link rel="stylesheet" type="text/css"
	href="resources/css/taoquangcao.css">
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
<title>Restaurant Guys - FORM CHỈNH SỬA QUẢNG CÁO</title>

</head>
<div id="taoquangcao">
	<body>
		<h1 class="h1">Chỉnh Sửa Quảng Cáo Cho Nhà Hàng</h1>
		<form:form id="myForm" class="dangkithongtin" method="POST"
			modelAttribute="fileBucker" enctype="multipart/form-data"
			action="./edit-update-advertisement">

			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<input id="id" type="label" name="advertisement_id"
					value="${Advertisement.advertisement_id}" class="signup" hidden="hide" />
				<form:input id="file" type="file" path="file" class="signup"
					name="promotion_image" placeholder="Hình ảnh"
					value="${Advertisement.advertisement_image}"
					accept="image/gif, image/jpg, image/png, image/jpeg"
					max-size="32154" />
			</ul>

			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>

				<input id="tenquangcao" type="text" name="advertisement_name"
					value="${Advertisement.advertisement_name}" class="signup"
					placeholder="Tên khuyến mãi" />
				<input id="tenquangcao1" type="text" name="advertisement_name"
					value="${Advertisement.advertisement_name}" class="signup"
					placeholder="Tên khuyến mãi" style="display: none" />
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>

				<input id="start" type="text" name="advertisement_start"
					value="${Advertisement.advertisement_start}" class="signup"
					placeholder="Ngày bắt đầu" />
				<input id="start1" type="text" name="advertisement_start"
					value="${Advertisement.advertisement_start}" class="signup"
					placeholder="Ngày bắt đầu" style="display: none" />
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<input id="end" type="text" name="advertisement_end"
					value="${Advertisement.advertisement_end}" class="signup"
					placeholder="Ngày kết thúc" />
				<input id="end1" type="text" name="advertisement_end"
					value="${Advertisement.advertisement_end}" class="signup"
					placeholder="Ngày kết thúc" style="display: none" />
			</ul>
<div id="editquangcao">
			<input class="nutluuqc" type="submit" name="luu" value="Lưu">
			<input class="nutresetqc" type="reset" name="nhaplai"
				value="Nhập lại" onclick="resetadd()">
			<input class="nuthuyqc" type="reset" name="huy" value="Hủy"
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
				alert("Chào mừng quay về chỉnh sửa khuyến mãi!");
			}
		}
		$("#myForm").submit(function(e) {
			var img = $('#file').val();
			var ten = $('#tenquangcao').val();
			var start = $('#start').val();
			var end = $('#end').val();
			var today = new Date().toISOString().slice(0, 10);
			var st = new Date(start);
			var en = new Date(end);
			var to = new Date(today);
				if (ten && start && end) {
					if (st > to && en > to && en >= st) {
						if (confirm("Thực hiện chỉnh sửa quảng cáo?") == true) {
							alert("Chỉnh sửa quảng cáo thành công!");
							$('#tenquangcao').val(encodeURI(ten));
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
			var ten = $('#tenquangcao1').val();
			var start = $('#start1').val();
			var end = $('#end1').val();
			$('#file').val('');
			$('#tenquangcao').val(ten);
			$('#start').val(start);
			$('#end').val(end);
		}
	</script>
</div>
</head>

</html>
