<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="resources/css/taokhuyenmai.css" />
<link rel="stylesheet" type="text/css" href="resources/css/style.css" />
<link rel="stylesheet" type="text/css" href="resources/css/Searching.css" />
<link rel="stylesheet" type="text/css" href="resources/css/taodatcho.css" />
<div class="Searchingdatcho">
	<select id="status"name="Trangthai">
		<option value="" disabled selected>Trạng Thái</option>
		<option value="1">Kích Hoạt</option>
		<option value="3">Vô Hiệu</option>
	</select>
	 <input id ="input"type="text" name="reservationname"
		placeholder="Nhập vào tên khách hàng">
	<button class="button buttonsearch">
		<img src="resources/images/search.png">
	</button>
</div>

<input id="newReser-managed" type="image"
		src="resources/images/reservation-icon.png" alt="Submit" />

<table class="Table-Last" border="1" bordercolor="#A2B5CD">
	<tr class="title">
		<th>Tên khách hàng</th>
		<th>Số điện thoại</th>
		<th>Nội dung đặt chỗ</th>
		<th>Thời gian đến</th>
		<th>Trạng Thái</th>
	</tr>
	<tr>
		<c:forEach items="${listReser}" var="listReser" varStatus="status">

			<tr>
				<td style="color: black"><c:out
						value="${listReser.reservation_cusname}"></c:out></td>
				<td style="color: black"><c:out
						value="${listReser.reservation_cusphone}"></c:out></td>
				<td style="color: black"><c:out
						value="${listReser.reservation_contents}"></c:out></td>
				<td style="color: black"><c:out
						value="${listReser.reservation_time}"></c:out></td>
				<td style="color: black"><c:out
						value="${listReser.reservation_status}"></c:out></td>
		</c:forEach>
	</tr>
</table>

<script type="text/javascript">
	$("#newReser-managed").click(function() {
		$.ajax({
			url : "get-newReser",
			success : function(result) {
				$("#content").html(result);
			}
		});
	});
</script>