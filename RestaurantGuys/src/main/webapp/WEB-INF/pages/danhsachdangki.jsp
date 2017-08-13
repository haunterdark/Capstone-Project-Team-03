<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="resources/css/style.css" />
				
<table class="Table-Last" border="1" bordercolor="#A2B5CD">
		<tr class="title">
			
			<th>Tên Nhà Hàng</th>
			<th >Email</th>
			<th>Số Điện Thoại</th>
			<th> Trạng Thái </th>
			
		</tr>
		<tr>
			<c:forEach items="${list}" var="list" varStatus="status">

				<tr>
				   
					<td style="color: white"></td>
					<td style="color: white"></td>
					<td style="color: white"></td>
					<td style="color: white"><input type="button" value="Tạo Mới"></a></td>
					<td style="color: white"><input type="button" value="Xóa"></a></td>

					
				</tr>
			</c:forEach>
		</tr>
	</table>