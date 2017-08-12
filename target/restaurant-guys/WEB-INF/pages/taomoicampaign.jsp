<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<meta charset="utf-8">
<title>Restaurant Guys - Tạo Mới Campaign</title>
<link rel="stylesheet" type="text/css" href="resources/css/style.css" />
<link rel="stylesheet" type="text/css" href="resources/css/campaign.css" />

<link rel="stylesheet" type="text/css"
	href="resources/css/jquery-ui.css">
<link href='http://fonts.googleapis.com/css?family=Lobster'
	rel='stylesheet'>
<script src="resources/js/jquery.min.js">
	
</script>
<script src="resources/js/campaign.js">
	
</script>
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

<div id="addcampaign">
	<body>
		<h1 class="h1">Tạo Mới Chiến Lược Cho Nhà Hàng</h1>
		<form:form id="myForm" class="dangkithongtin" method="POST"
			modelAttribute="fileBucker" enctype="multipart/form-data"
			action="./add-campaign">
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<input id="file" type="file" path="file" class="signup"
					name="file" placeholder="Hình ảnh" value=""
					accept="image/gif, image/jpg, image/png, image/jpeg"
					max-size="32154" />
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<input id="id" type="hidden" name="" value="" class="signup" />
				<input id="description" type="text" name="campaign_name"
					value="" class="signup" placeholder="Mô Tả Chiến Lược" />
			</ul>

			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>

				<input id="value" type="text" name="campaign_value" value=""
					class="signup" placeholder="Giá Trị Được Giảm" disable />
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>

				<input id="start" type="text" name="campaign_start" value=""
					class="signup" placeholder="Ngày bắt đầu chiến lược" />
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<input id="end" type="text" name="campaign_end" value=""
					class="signup" placeholder="Ngày kết thúc chiến lược" />
			</ul>

		
		<input class="nutluu" type="submit" value="Lưu">
			<input class="nutreset" type="reset" name="nhaplai"
				value="Nhập lại" onclick="resetadd()">
			<input class="nuthuy" type="reset" name="huy" value="Hủy"
				onclick="huy()">
	
			</form:form>
	</body>
	<script type="text/javascript">
		function huy() {
			if (confirm("Bạn muốn hủy thao tác đang thực hiện không?") == true) {
				$.ajax({
					url : "get-campaign",
					success : function(result) {
						$("#content").html(result);
					}
				});
			} else {
				alert("Chào mừng quay về tạo mới campaign!");
			}
		}
		$("#myForm")
				.submit(
						function(e) {
							var img = $('#file').val();
							var ten = $('#description').val();
							var value = $('#value').val();
							var start = $('#start').val();
							var end = $('#end').val();
							var today = new Date().toISOString().slice(0, 10);
							var st = new Date(start);
							var en = new Date(end);
							var to = new Date(today);
							var regex = /^[0-9]+$/;
						
								if (ten && value && start && end) {
									if (value.match(regex) && value <= 100) {
										if (st > to && en > to && en >= st) {
											if (confirm("Thực hiện tạo mới campaign?") == true) {
												alert("Tạo campaign thành công!");
												$('#description').val(
														encodeURI(ten));
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
										alert("Yêu cầu nhập giá trị số hoặc nhập giá trị nhỏ hơn hoặc bằng 100!");
									}
								} else {
									e.preventDefault();
									alert("Vui lòng nhập đầy đủ thông tin!");
								}
							
						});

		function resetadd() {
			$('#file').val('');
			$('#description').val('');
			$('#value').val('');
			$('#start').val('');
			$('#end').val('');
		}
	</script>
</div>


