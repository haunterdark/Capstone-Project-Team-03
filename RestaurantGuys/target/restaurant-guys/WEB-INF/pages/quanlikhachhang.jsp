<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<link rel="stylesheet" type="text/css"
	href="resources/css/Searching.css" />
<link rel="stylesheet" type="text/css" href="resources/css/style.css" />
<script src="resources/js/jquery.min.js">
	
</script>
<script src="resources/js/customer.js">
	
</script>
<script type="text/javascript">
	$(document).ready(function() {
		hidecolum();
	});
</script>
<link rel="stylesheet" type="text/css" href="resources/css/paging.css" />
</head>
<div class="Searchingkhachhang">
	<select name="Tinh" id="Tinh" onchange="loaddistrictcus(this.value)">
		<option value="" disabled selected>Thành Phố</option>
		<c:forEach items="${LoadCity}" var="listcity" varStatus="status">
			<option value="${listcity.city_id}">${listcity.city_name}</option>
		</c:forEach>
	</select> <select name="Quan" id="quan" onchange="searchdistrict(this.value)">
		<option value="" disabled selected>Quận</option>
	</select> <select id="status" name="Trangthai"
		onchange="loadfromstatus(this.value)">
		<option value="status" disabled selected>Trạng Thái</option>
		<option value="1">Kích Hoạt</option>
		<option value="0">Vô Hiệu</option>
	</select> <input id="input" style="width: 350px" type="text"
		name="restaurantname"
		placeholder="Nhập vào tên khách hàng hoặc số điện thoại">
	<button class="button buttonsearchcustomer" onclick="loadfromcus()">
		<img src="resources/images/search.png">
	</button>
</div>

<div class="followadmin">
	<table class="Table-Last" border="1">
		<tr class="title">
			<th>Hình Đại Diện</th>
			<th>Tên Khách Hàng</th>
			<th>Email</th>
			<th>Số Điện Thoại</th>
			<th>Địa Chỉ</th>
			<th>Trạng Thái</th>
		</tr>
		<tr>

			<c:forEach items="${listCusAd}" var="listCusAd" varStatus="status">
				<tr>
					<td style="color: black; height: 50px; width: 50px"><img
						style="height: 50px; width: 50px"
						src="${listCusAd.customer_image}"></td>
					<td style="color: black"><c:out
							value="${listCusAd.customer_name}"></c:out></td>
					<td style="color: black"><c:out
							value="${listCusAd.customer_email}"></c:out></td>
					<td style="color: black"><c:out
							value="${listCusAd.customer_phone}"></c:out></td>
					<td style="color: black"><c:out
							value="${listCusAd.customer_address},${listCusAd.city_name},${listCusAd.district_name}"></c:out></td>
					<td style="color: black; text-align: center"><input
						id="${listCusAd.customer_id}" class="discoup" type="button"
						name="discuss"
						value="${listCusAd.customer_status == '0' ? 'Kích Hoạt' : 'Vô Hiệu'}"></td>
				</tr>
			</c:forEach>


		</tr>
	</table>
	<nav class="pagination">

		<%--For displaying Previous link except for the 1st page --%>
		<c:if test="${currentPage != 1}">
			<a id="previous"
				href="javascript:customerPaging('${currentPage - 1}');">Previous</a>
		</c:if>
		<%--For displaying Next link --%>
		<c:if test="${currentPage lt noOfPages}">
			<a id="next" href="javascript:customerPaging('${currentPage + 1}');">Next</a>
		</c:if>
		<%--For displaying Page numbers. 
    <%-- The when condition does not display a link for the current page--%>
		<a class="number"> <c:forEach begin="1" end="${noOfPages}" var="i">
				<c:choose>
					<c:when test="${currentPage eq i}">
						<a class=" number">${i}</a>
					</c:when>
					<c:otherwise>
						<a class="number" href="javascript:customerPaging('${i}');">${i}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</a>
	</nav>

</div>

<div class="followcustomer">
	<table class="Table-Last" border="1">
		<tr class="title">
			<th>Hình Đại Diện</th>
			<th>Tên Khách Hàng</th>
			<th>Email</th>
			<th>Số Điện Thoại</th>
			<th>Địa Chỉ</th>
			<th>Trạng Thái</th>
		</tr>
		<tr>

			<c:forEach items="${listCusRe}" var="listCusRe" varStatus="status">
				<tr>
					<td style="color: black; height: 50px; width: 50px"><img
						style="height: 50px; width: 50px"
						src="${listCusRe.customer_image}"></td>
					<td style="color: black"><c:out
							value="${listCusRe.customer_name}"></c:out></td>
					<td style="color: black"><c:out
							value="${listCusRe.customer_email}"></c:out></td>
					<td style="color: black"><c:out
							value="${listCusRe.customer_phone}"></c:out></td>
					<td style="color: black"><c:out
							value="${listCusRe.customer_address},${listCusRe.city_name},${listCusRe.district_name}"></c:out></td>
					<td style="color: black; text-align: center"><input
						id="${listCusRe.customer_id}" class="discuss" type="button"
						name="discuss"
						value="${listCusRe.customer_status == '1' ? 'Vô Hiệu' : 'Kích Hoạt'}"></td>
				</tr>
			</c:forEach>


		</tr>
	</table>
	<nav class="pagination">

		<%--For displaying Previous link except for the 1st page --%>
		<c:if test="${currentPage != 1}">
			<a id="previous"
				href="javascript:customerPaging('${currentPage - 1}');">Previous</a>
		</c:if>
		<%--For displaying Next link --%>
		<c:if test="${currentPage lt noOfPages}">
			<a id="next" href="javascript:customerPaging('${currentPage + 1}');">Next</a>
		</c:if>
		<%--For displaying Page numbers. 
    <%-- The when condition does not display a link for the current page--%>
		<a class="number"> <c:forEach begin="1" end="${noOfPages}" var="i">
				<c:choose>
					<c:when test="${currentPage eq i}">
						<a class=" number">${i}</a>
					</c:when>
					<c:otherwise>
						<a class="number" href="javascript:customerPaging('${i}');">${i}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</a>
	</nav>
</div>