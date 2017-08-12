<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" type="text/css" href="resources/css/style.css" />
<link rel="stylesheet" type="text/css" href="resources/css/paging.css" />
<link rel="stylesheet" type="text/css" href="resources/css/taokhuyenmai.css" />
<link rel="stylesheet" type="text/css" href="resources/css/Searching.css" />
<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/promotion.js"></script>

<div id="newPro-managed">
	<input id="nuttaokm" type="image"
		src="resources/images/newpromotion.png" alt="Submit" />
</div>

<script type="text/javascript">
	$(document).ready(function() {
		hidecolumPro();
	});
</script>

<div class="Searchingquangcao">
	<select id="status" name="Trangthai"
		onchange="loadfromstatuspro(this.value)">
		<option value="status" disabled selected>Trạng Thái</option>
		<option value="1">Kích Hoạt</option>
		<option value="0">Vô Hiệu</option>
	</select> <input id="input" type="text" name="promotionname"
		placeholder="Nhập vào tên khuyến mãi">
	<button class="button buttonsearch" onclick="loadfrompro()">
		<img src="resources/images/search.png">
	</button>
</div>

<div class="followadmin">
	<table class="Table-Last" border="1" bordercolor="#A2B5CD">
		<tr class="title">
			<th>Hình Khuyến Mãi</th>
			<th>Tên Khuyến Mãi</th>
			<th>Tên Nhà Hàng</th>
			<th>Ngày bắt đầu</th>
			<th>Ngày kết thúc</th>
			<th>Trạng Thái</th>

		</tr>
		<tr>
			<c:forEach items="${listProAd}" var="listProAd" varStatus="status">

				<tr>

					<td style="color: black; height: 50px; width: 50px"><img
						style="height: 50px; width: 50px"
						src="${listProAd.promotion_image}"></td>
					<td style="color: black"><c:out
							value="${listProAd.promotion_name}"></c:out></td>
					<td style="color: black"><c:out
							value="${listProAd.restaurant_name}"></c:out></td>
					<td style="color: black"><c:out
							value="${listProAd.promotion_start}"></c:out></td>
					<td style="color: black"><c:out
							value="${listProAd.promotion_end}"></c:out></td>
					<td style="color: black;text-align: center"><input id="${listProAd.promotion_id}"
						class="status" type="button"
						value="${listProAd.promotion_status == '1' ? 'Vô Hiệu' : 'Kích Hoạt'}"></td>
				</tr>
			</c:forEach>
		</tr>
	</table>
	<nav class="pagination">

		<%--For displaying Previous link except for the 1st page --%>
		<c:if test="${currentPage != 1}">
			<a id="previous"
				href="javascript:promotionPaging('${currentPage - 1}');">Previous</a>
		</c:if>
		<%--For displaying Next link --%>
		<c:if test="${currentPage lt noOfPages}">
			<a id="next" href="javascript:promotionPaging('${currentPage + 1}');">Next</a>
		</c:if>
		<%--For displaying Page numbers. 
    <%-- The when condition does not display a link for the current page--%>
		<a class="number"> <c:forEach begin="1" end="${noOfPages}" var="i">
				<c:choose>
					<c:when test="${currentPage eq i}">
						<a class=" number">${i}</a>
					</c:when>
					<c:otherwise>
						<a class="number" href="javascript:promotionPaging('${i}');">${i}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</a>
	</nav>
</div>

<div class="followrestaurant">
	<table class="Table-Last" border="1" bordercolor="#A2B5CD">
		<tr class="title">
			<th>Hình Khuyến Mãi</th>
			<th>Tên Khuyến Mãi</th>
			<th>Ngày bắt đầu</th>
			<th>Ngày kết thúc</th>
			<th>Trạng Thái</th>
		</tr>
		<tr>
			<c:forEach items="${listProRe}" var="listProRe" varStatus="status">

				<tr>


					<td style="color: black; height: 50px; width: 50px"><img
						style="height: 50px; width: 50px"
						src="${listProRe.promotion_image}"></td>
					<td style="color: black"><c:out
							value="${listProRe.promotion_name}"></c:out></td>
					<td style="color: black"><c:out
							value="${listProRe.promotion_start}"></c:out></td>
					<td style="color: black"><c:out
							value="${listProRe.promotion_end}"></c:out></td>
					<td style="color: black;text-align: center"><input id="${listProRe.promotion_id}"
						class="status" type="button"
						value="${listProRe.promotion_status == '1' ? 'Vô Hiệu' : 'Kích Hoạt'}">
						<input class="edit" id="${listProRe.promotion_id}" type="button"
						value="Sửa"></td>
				</tr>
			</c:forEach>
		</tr>

	</table>
	<%--For displaying Previous link except for the 1st page --%>
	<nav class="pagination">

		<%--For displaying Previous link except for the 1st page --%>
		<c:if test="${currentPage != 1}">
			<a id="previous"
				href="javascript:promotionPaging('${currentPage - 1}');">Previous</a>
		</c:if>
		<%--For displaying Next link --%>
		<c:if test="${currentPage lt noOfPages}">
			<a id="next" href="javascript:promotionPaging('${currentPage + 1}');">Next</a>
		</c:if>
		<%--For displaying Page numbers. 
    <%-- The when condition does not display a link for the current page--%>
		<a class="number"> <c:forEach begin="1" end="${noOfPages}" var="i">
				<c:choose>
					<c:when test="${currentPage eq i}">
						<a class=" number">${i}</a>
					</c:when>
					<c:otherwise>
						<a class="number" href="javascript:promotionPaging('${i}');">${i}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</a>
	</nav>
</div>


