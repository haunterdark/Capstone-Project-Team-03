<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<ul class="menu">
	<li id="customer-managed"><img
		src="resources/images/restaurant19.png"> <a href="#">Quản Lý
			Khách Hàng</a></li>
	<li id="advertisement-managed"><img
		src="resources/images/restaurant19.png"> <a href="#">Quản Lý
			Quảng Cáo</a></li>
	<li id="promotion-managed"><img
		src="resources/images/restaurant19.png"> <a href="#">Quản Lý
			Khuyến Mãi</a></li>
	<li id="campaign-managed"><img
		src="resources/images/restaurant19.png"> <a href="#">Quản Lý
			Campaign</a></li>
	<li id="reservation-managed"><img
		src="resources/images/restaurant19.png"> <a href="#">Quản Lý
			Đặt Chỗ</a></li>
	<li id="profiles-managed"><img
		src="resources/images/restaurant19.png"> <a href="#">Quản Lý
			Tài Khoản</a></li>

</ul>
<%
	if (request.getParameter("ref") != null && request.getParameter("ref").equals("quanlykhuyenmai")) {
%>
<script>
	loadpromotion();
</script>
<%
	} else if (request.getParameter("ref") != null && request.getParameter("ref").equals("quanlyquangcao")) {
%>
<script>
	loadadvertisement();
</script>
<%
	} else if (request.getParameter("ref") != null && request.getParameter("ref").equals("quanlicampaign")) {
%>
<script>
	loadcampaign();
</script>
<%
	}
%>
