<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
			<ul class="menu">
				<li id="restaurant-managed"> <img src="resources/images/restaurant19.png">
					<a  href="#">Quản Lý Nhà Hàng</a>
				</li> 
				<li id = "customer-managed"><img src="resources/images/restaurant19.png">
					<a href="#">Quản Lý Khách Hàng</a>
				</li>
				<li id = "advertisement-managed"><img src="resources/images/restaurant19.png">
					<a href="#">Quản Lý Quảng Cáo</a>
				</li>
				<li id = "promotion-managed"><img src="resources/images/restaurant19.png">
					<a href="#">Quản Lý Khuyến Mãi</a>
				</li>
				<li id = "reservation-managed"><img src="resources/images/restaurant19.png">
					<a href="#">Quản Lý Đặt Chỗ</a>
				</li>
			</ul>
			
			
<% 
if(request.getParameter("ref")!=null && request.getParameter("ref").equals("quanlykhuyenmai")){
%>
	<script>
		loadKhuyenMai();
	</script>
<%
}
%>