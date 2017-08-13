<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/promotion.js"></script>
<link rel="stylesheet" type="text/css"
	href="resources/css/jquery-ui.css">
<link rel="stylesheet" type="text/css"
	href="resources/css/taokhuyenmai.css">
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
<title>Restaurant Guys - FORM CHỈNH SỬA KHUYẾN MÃI</title>

</head>
<div id="taokhuyenmai">
	<body>
		<h1 class="h1">Chỉnh Sửa Khuyến Mãi Cho Nhà Hàng</h1>
		<form:form id="myForm" class="dangkithongtin" method="POST"
			modelAttribute="fileBucker" enctype="multipart/form-data"
			action="./edit-update-promotions">

			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<input id="id" type="label" name="promotion_id"
					value="${Promotion.promotion_id}" class="signup" hidden="hide" />
				<form:input id="file" type="file" path="file" class="signup"
					name="promotion_image" placeholder="Hình ảnh"
					value="${Promotion.promotion_image}"
					accept="image/gif, image/jpg, image/png, image/jpeg"
					max-size="32154" />
			</ul>

			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>

				<input id="tenkhuyenmai" type="text" name="promotion_name"
					value="${Promotion.promotion_name}" class="signup"
					placeholder="Tên khuyến mãi" />
				<input id="tenkhuyenmai1" type="text" name="promotion_name"
					value="${Promotion.promotion_name}" class="signup"
					placeholder="Tên khuyến mãi" style="display: none" />
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>

				<input id="start" type="text" name="promotion_start"
					value="${Promotion.promotion_start}" class="signup"
					placeholder="Ngày bắt đầu" />
				<input id="start1" type="text" name="promotion_start"
					value="${Promotion.promotion_start}" class="signup"
					placeholder="Ngày bắt đầu" style="display: none" />
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<input id="end" type="text" name="promotion_end"
					value="${Promotion.promotion_end}" class="signup"
					placeholder="Ngày kết thúc" />
				<input id="end1" type="text" name="promotion_end"
					value="${Promotion.promotion_end}" class="signup"
					placeholder="Ngày kết thúc" style="display: none" />
			</ul>
			<div id="editkhuyenmai">
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
				alert("Chào mừng quay về chỉnh sửa khuyến mãi!");
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
					if (confirm("Thực hiện chỉnh sửa khuyến mãi?") == true) {
						alert("Chỉnh sửa khuyến mãi thành công!");
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
			var ten = $('#tenkhuyenmai1').val();
			var start = $('#start1').val();
			var end = $('#end1').val();
			$('#file').val('');
			$('#tenkhuyenmai').val(ten);
			$('#start').val(start);
			$('#end').val(end);
		}
	</script>
</div>
</head>

</html>
