<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<meta charset="utf-8">
<title>Restaurant Guys - FORM Chỉnh Sửa Quảng Cáo</title>
<link rel="stylesheet" type="text/css" href="resources/css/style.css" />
<link rel="stylesheet" type="text/css" href="resources/css/campaign.css" />

<link rel="stylesheet" type="text/css"
	href="resources/css/jquery-ui.css">

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

<div id="editcampaign">
	<body>
		<h1 class="h1">Chỉnh Sửa Thông Tin Chiến Lược</h1>
		<form:form id="myForm" class="dangkithongtin" method="POST"
			modelAttribute="fileBucker" enctype="multipart/form-data"
			action="./edit-campaign">
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<input id="file" type="file" path="file" class="signup"
					name="file" placeholder="Hình ảnh"
					value="${Campaign.campaign_image}"
					accept="image/gif, image/jpg, image/png, image/jpeg"
					max-size="32154" />
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<input id="id" type="hidden" name="campaign_id" value="${Campaign.campaign_id}"
					class="signup" />
				<input id="description" type="text" name="campaign_description"
					value="${Campaign.campaign_name}" class="signup"
					placeholder="Mô Tả Chiến Lược" />
				<input id="description1" type="text" name=""
					value="${Campaign.campaign_name}" class="signup"
					placeholder="Mô Tả Chiến Lược" style="display: none" />
			</ul>

			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>

				<input id="value" type="text" name="campaign_value"
					value="${Campaign.campaign_value}" class="signup"
					placeholder="Giá Trị Được Giảm" />
				<input id="value1" type="text" name=""
					value="${Campaign.campaign_value}" class="signup"
					placeholder="Giá Trị Được Giảm" disable style="display: none" />

			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>

				<input id="start" type="text" name="campaign_start"
					value="${Campaign.campaign_start}" class="signup"
					placeholder="Ngày bắt đầu chiến lược" />
				<input id="start1" type="text" name=""
					value="${Campaign.campaign_start}" class="signup"
					placeholder="Ngày bắt đầu chiến lược" style="display: none" />
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<input id="end" type="text" name="campaign_end"
					value="${Campaign.campaign_end}" class="signup"
					placeholder="Ngày kết thúc chiến lược" />
				<input id="end1" type="text" name=""
					value="${Campaign.campaign_end}" class="signup"
					placeholder="Ngày kết thúc chiến lược" style="display: none" />
			</ul>

<div id="editcampaign">
			<input class="nutluu" type="submit" name="luu" value="Lưu">
				<input class="nutreseteditkm"  id = "resetedit"type="reset" name="nhaplai"
				value="Nhập lại" onclick="resetadd()">
			<input class="nuthuy" type="reset" name="huy" value="Hủy"
				onclick="huy()">
</div>
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
				alert("Chào mừng quay về chỉnh sửa campaign!");
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
								if (ten && start && end) {
									if (value.match(regex) && value <= 100) {
										if (st > to && en > to && en >= st) {
											if (confirm("Thực hiện chỉnh sửa campaign?") == true) {
												alert("Chỉnh sửa campaign thành công!");
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
	</script>
</div>


