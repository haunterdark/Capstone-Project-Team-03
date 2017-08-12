<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="resources/css/style.css" />
<link rel="stylesheet" type="text/css" href="resources/css/campaign.css" />
<link rel="stylesheet" type="text/css" href="resources/css/paging.css" />
<link rel="stylesheet" type="text/css" href="resources/css/Searching.css" />
<script src="resources/js/jquery.min.js">
	
</script>
<script src="resources/js/campaign.js">
	
</script>
<script src="resources/js/coupon.js">
	
</script>
<script type="text/javascript">
	$(document).ready(function() {
		hidecolum();
	});
</script>

<input id="newcampaign-managed" 
	value="Tạo mới campaign" onclick="addnewcampaign()" type="image"
		src="resources/images/chienluoc.png" alt="Submit"/>

<div class="Searchingcampaign">
	<select id="status" name="Trangthai"
		onchange="searchstatuscam(this.value)">
		<option value="" disabled selected>Trạng Thái</option>
		<option value="1">Còn Hạn</option>
		<option value="0">Hết Hạn</option>
		<option value="2">Mới Tạo</option>
	</select> <input id="idcoupon" style="width: 150px;" type="text"
		name="campaignid" placeholder="Nhập mã coupon "> <input
		id="idcus" style="width: 150px;" type="text" name="customerid"
		placeholder="Nhập mã khách hàng ">
	<button class="button buttonredem" id="redemcoupon">Kiểm tra mã giảm giá</button>

</div>

<div class="campaignadmin">
	<table class="Table-Last" border="1" bordercolor="#A2B5CD">
		<tr class="title">
			<th>Hình Ảnh</th>
			<th>Nội dung campaign</th>
			<th>Giá trị được giảm</th>
			<th>Ngày bắt đầu</th>
			<th>Ngày kết thúc</th>
			<th>Nhà Hàng</th>
			<th>Trạng thái</th>

		</tr>
		<tr>

			<c:forEach items="${listCam}" var="listCam" varStatus="status">
				<tr>
					<td style="color: black; height: 50px; width: 50px"><img
						style="height: 50px; width: 50px"
						src="${listCam.campaign_image}"></td>
					<td style="color: black" hidden="hide"><c:out
							value="${listCam.campaign_id}"></c:out></td>
					<td style="color: black"><c:out
							value="${listCam.campaign_name}"></c:out></td>
					<td style="color: black"><c:out
							value="${listCam.campaign_value}%"></c:out></td>
					<td style="color: black"><c:out
							value="${listCam.campaign_start}"></c:out></td>
					<td style="color: black"><c:out
							value="${listCam.campaign_end}"></c:out></td>
					<td style="color: black"><c:out
							value="${listCam.restaurant_name}"></c:out></td>
					<td style="color: black; text-align: center;"><c:out
							value="${listCam.campaign_status == '1' ? 'Còn Hạn' : listCam.campaign_status == '0' ? 'Hết Hạn':'Mới Tạo'}"></c:out></td>
				</tr>
			</c:forEach>
		</tr>
	</table>
	<%--For displaying Previous link except for the 1st page --%>
	<c:if test="${currentPage != 1}">
		<td><a id="previous"
			href="javascript:campaignPaging('${currentPage - 1}');">Previous</a></td>
	</c:if>
	<%--For displaying Next link --%>
	<c:if test="${currentPage lt noOfPages}">
		<td><a id="next"
			href="javascript:campaignPaging('${currentPage + 1}');">Next</a></td>
	</c:if>
	<%--For displaying Page numbers. 
    The when condition does not display a link for the current page--%>
	<table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<c:forEach begin="1" end="${noOfPages}" var="i">
				<c:choose>
					<c:when test="${currentPage eq i}">
						<td>${i}</td>
					</c:when>
					<c:otherwise>
						<td><a href="javascript:campaignPaging('${i}');">${i}</a></td>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>
	</table>

</div>
<div class="campaigncustomer">
	<table class="Table-Last" border="1" bordercolor="#A2B5CD">
		<tr class="title">
			<th>Hình Ảnh</th>
			<th>Nội dung campaign</th>
			<th>Giá trị được giảm</th>
			<th>Ngày bắt đầu</th>
			<th>Ngày kết thúc</th>
			<th>Trạng thái</th>
			<th>Chỉnh sửa</th>
		</tr>
		<tr>

			<c:forEach items="${listCamRe}" var="listCamRe" varStatus="status">
				<tr>
					<td style="color: black; height: 50px; width: 50px"><img
						style="height: 50px; width: 50px"
						src="${listCamRe.campaign_image}"></td>
					<td style="color: black" hidden="hide"><c:out
							value="${listCamRe.campaign_id}"></c:out></td>
					<td style="color: black"><c:out
							value="${listCamRe.campaign_name}"></c:out></td>
					<td style="color: black"><c:out
							value="${listCamRe.campaign_value}%"></c:out></td>
					<td style="color: black"><c:out
							value="${listCamRe.campaign_start}"></c:out></td>
					<td style="color: black"><c:out
							value="${listCamRe.campaign_end}"></c:out></td>
					<td style="color: black"><c:out
							value="${listCamRe.campaign_status == '1' ? 'Còn Hạn' : listCamRe.campaign_status == '0' ? 'Hết Hạn':'Mới Tạo'}"></c:out></td>
					<td style="color: black;text-align: center"><input id="${listCamRe.campaign_id}"
						class="edit-campaign" type="button" value="Sửa"><input
						id="${listCamRe.campaign_id}" class="delete-campaign"
						type="button" value="Xóa"></td>
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

