<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<script src="resources/js/jquery.min.js">
	
</script>
<script src="resources/js/restaurant.js">
	
</script>
<link rel="stylesheet" type="text/css" href="resources/css/paging.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/Searching.css" />
<link rel="stylesheet" type="text/css" href="resources/css/style.css" />
</head>
<body>
	<div class="Searchingnhahang">
		<select name="Tinh" id="Tinh" onchange="loaddistrict(this.value)">
			<option value="" disabled selected>Thành Phố</option>
			<c:forEach items="${LoadCity}" var="listcity" varStatus="status">
				<option value="${listcity.city_id}">${listcity.city_name}</option>
			</c:forEach>
		</select> <select name="Quan" id="quan" onchange="searchdistrict(this.value)">
			<option value="" disabled selected>Quận</option>
		</select> <select name="category" id="category"
			onchange="loadcategory(this.value)">
			<option value="" disabled selected>Danh Mục</option>
			<c:forEach items="${LoadCategory}" var="listcategory"
				varStatus="status">
				<option value="${listcategory.category_id}">${listcategory.category_name}</option>
			</c:forEach>
		</select> <select name="country" id="country"
			onchange="loadcountry(this.value)">
			<option value="" disabled selected>Ẩm Thực</option>
			<c:forEach items="${LoadCountry}" var="listcountry"
				varStatus="status">
				<option value="${listcountry.country_id}">${listcountry.country_name}</option>
			</c:forEach>
		</select> <select id="status" name="Trangthai"
			onchange="loadstatus(this.value)">
			<option value="" disabled selected>Trạng Thái</option>
			<option value="1">Kích hoạt</option>
			<option value="2">Thêm Mới</option>
			<option value="0">Vô Hiệu</option>
		</select> <input id="input" type="text" name="restaurantname"
			placeholder="Nhập vào tên nhà hàng">
		<button class="button buttonsearch" onclick="loadres()">
			<img src="resources/images/search.png">
		</button>

		<div id="register-account">
			<input id="nuttaotk" type="image" src="resources/images/newRes.png"
				alt="Submit" />

		</div>
		</ul>
	</div>

	<table class="Table-Last" border="1" bordercolor="#A2B5CD">


		<tr class="title">
			<th>Hình Đại Diện</th>
			<th>Tên Nhà Hàng</th>
			<th>Email</th>
			<th>Số Điện Thoại</th>
			<th>Địa Chỉ</th>
			<th>Danh Mục</th>
			<th>Ẩm Thực</th>
			<th>Tên Tài Khoản</th>
			<th>Trạng Thái</th>
		</tr>
		<tr>
			<c:forEach items="${listRes}" var="listRes" varStatus="status">
				<tr>
					<td style="color: black; height: 50px; width: 50px"><img
						style="height: 50px; width: 50px"
						src="${listRes.restaurant_image}"></td>
					<td style="color: black; text-align: center;"><c:out
							value="${listRes.restaurant_name}"></c:out></td>
					<td style="color: black"><c:out
							value="${listRes.restaurant_email}"></c:out></td>
					<td style="color: black"><c:out
							value="${listRes.restaurant_phone}"></c:out></td>
					<td style="color: black"><c:out
							value="${listRes.restaurant_address}, ${listRes.city_name}, ${listRes.district_name}"></c:out></td>
					<td style="color: black"><c:out
							value="${listRes.category_name}"></c:out></td>
					<td style="color: black"><c:out
							value="${listRes.country_name}"></c:out></td>
					<td style="color: black"><c:out
							value="${listRes.restaurant_username}"></c:out></td>
					<td style="color: white; text-align: center;"><input
						id="${listRes.restaurant_id}" class="newRes-managed" type="button"
						value="${listRes.restaurant_status == '1' ? 'Vô Hiệu' : listRes.restaurant_status == '0' ? 'Kích Hoạt':'Thêm mới'}"></td>

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

</body>