<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="resources/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/taoquangcao.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/taokhuyenmai.css" />
	<link rel="stylesheet" type="text/css"
	href="resources/css/Searching.css" />
<link rel="stylesheet" type="text/css" href="resources/css/paging.css" />
<div id="newAds-managed">
	<input id="nuttaoqc" type="image"
		src="resources/images/newadvertisement.png" alt="Submit" />
</div>
<script src="resources/js/jquery.min.js">
	
</script>
<script src="resources/js/advertisement.js">
	
</script>
<script type="text/javascript">
	$(document).ready(function() {
		hidecolum();
	});
</script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="Searchingquangcao">
	<select id="status" name="Trangthai"
		onchange="loadfromstatuspro(this.value)">
		<option value="" disabled selected>Trạng Thái</option>
		<option value="1">Kích Hoạt</option>
		<option value="0">Vô Hiệu</option>
	</select> <input id="input" type="text" name="Adsname"
		placeholder="Nhập vào tên quảng cáo">
	<button class="button buttonsearch" onclick="loadfrompro()">
		<img src="resources/images/search.png">
	</button>
</div>
<div class="quanliquangcao_admin">
	<table class="Table-Last" border="1" bordercolor="#A2B5CD">
		<tr class="title">
			<th>Hình Quảng Cáo</th>
			<th>Tên Quảng Cáo</th>
			<th>Tên Nhà Hàng</th>
			<th>Ngày Bắt Đầu</th>
			<th>Ngày Kết Thúc</th>
			<th>Trạng Thái</th>
		</tr>
		<tr>
			<c:forEach items="${listAd}" var="listAd" varStatus="status">

				<tr>
					<td style="color: black; height: 50px; width: 50px"><img
						style="height: 50px; width: 50px" src="${listAd.advertisement_image}"></td>
					<td style="color: black"><c:out value="${listAd.advertisement_name}"></c:out></td>
					<td style="color: black"><c:out
							value="${listAd.restaurant_name}"></c:out></td>
					<td style="color: black"><c:out value="${listAd.advertisement_start}"></c:out></td>
					<td style="color: black"><c:out value="${listAd.advertisement_end}"></c:out></td>
					<td style="color: black ;text-align: center"><input id="${listAd.advertisement_id}"
						class="Ads-managed" type="button"
						value="${listAd.advertisement_status == '1' ? 'Vô Hiệu' : 'Kích Hoạt'}"></td>
			</c:forEach>
		</tr>
	</table>
	<nav class="pagination">

		<%--For displaying Previous link except for the 1st page --%>
		<c:if test="${currentPage != 1}">
			<a id="previous"
				href="javascript:advertisementPaging('${currentPage - 1}');">Previous</a>
		</c:if>
		<%--For displaying Next link --%>
		<c:if test="${currentPage lt noOfPages}">
			<a id="next"
				href="javascript:advertisementPaging('${currentPage + 1}');">Next</a>
		</c:if>
		<%--For displaying Page numbers. 
    <%-- The when condition does not display a link for the current page--%>
		<a class="number"> <c:forEach begin="1" end="${noOfPages}" var="i">
				<c:choose>
					<c:when test="${currentPage eq i}">
						<a class=" number">${i}</a>
					</c:when>
					<c:otherwise>
						<a class="number" href="javascript:advertisementPaging('${i}');">${i}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</a>
	</nav>
</div>
<div class="quanliquangcao_customer">
	<table class="Table-Last" border="1" bordercolor="#A2B5CD">
		<tr class="title">
			<th>Hình Quảng Cáo</th>
			<th>Tên Quảng Cáo</th>

			<th>Ngày Bắt Đầu</th>
			<th>Ngày Kết Thúc</th>
			<th>Trạng Thái</th>
		</tr>
		<tr>
			<c:forEach items="${listRe}" var="listRe" varStatus="status">

				<tr>
					<td style="color: black; height: 50px; width: 50px"><img
						style="height: 50px; width: 50px" src="${listRe.advertisement_image}"></td>
					<td style="color: black; text-align: center;"><c:out
							value="${listRe.advertisement_name}"></c:out></td>

					<td style="color: black; text-align: center;"><c:out
							value="${listRe.advertisement_start}"></c:out></td>
					<td style="color: black; text-align: center;"><c:out
							value="${listRe.advertisement_end}"></c:out></td>
					<td style="color: black; text-align: center;"><input
						id="${listRe.advertisement_id}" class="Ads-managed" type="button"
						value="${listRe.advertisement_status == '1' ? 'Vô Hiệu' : 'Kích Hoạt'}">
						<input class="edit-adsvertisement" id="${listRe.advertisement_id}"
						type="button" value="Sửa"></td>
			</c:forEach>
		</tr>
	</table>
	<nav class="pagination">

		<%--For displaying Previous link except for the 1st page --%>
		<c:if test="${currentPage != 1}">
			<a id="previous"
				href="javascript:advertisementPaging('${currentPage - 1}');">Previous</a>
		</c:if>
		<%--For displaying Next link --%>
		<c:if test="${currentPage lt noOfPages}">
			<a id="next"
				href="javascript:advertisementPaging('${currentPage + 1}');">Next</a>
		</c:if>
		<%--For displaying Page numbers. 
    <%-- The when condition does not display a link for the current page--%>
		<a class="number"> <c:forEach begin="1" end="${noOfPages}" var="i">
				<c:choose>
					<c:when test="${currentPage eq i}">
						<a class=" number">${i}</a>
					</c:when>
					<c:otherwise>
						<a class="number" href="javascript:advertisementPaging('${i}');">${i}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</a>
	</nav>
</div>
