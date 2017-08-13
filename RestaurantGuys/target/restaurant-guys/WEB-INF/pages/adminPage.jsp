<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="Cache-Control"
	content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title>Restaurant Guys-Trang Quan Ly</title>
<link rel="stylesheet" type="text/css" href="resources/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/taotaikhoan.css" />
<link href='http://fonts.googleapis.com/css?family=Sansita+One'
	rel='stylesheet'>
<link href='http://fonts.googleapis.com/css?family=Noto+Serif'
	rel='stylesheet'>
<link href='http://fonts.googleapis.com/css?family=Lobster'
	rel='stylesheet'>
<script src="resources/js/jquery.min.js">
	
</script>
<script src="resources/js/loadmenu.js">
	
</script>
<script type="text/javascript">
	$(document).ready(function() {
		loadavatarandname();
	});
</script>
</head>
<body>

	<div id="main">
		<div id="head"></div>

		<form:form id="avatar" method="POST" modelAttribute="fileBucker"
			enctype="multipart/form-data" action="./update-avatar">
			
			<img id="hinhdaidien" >
			<input id="camera" type="image" src="upload/Camera.png">


			<input id="file" type="file" path="file" name="file"
				placeholder="Hình ảnh"
				accept=" image/jpg, image/png, image/jpeg"
				max-size="32154" style="display: none" onchange="update()" />
			<input class="nutluuavtar" type="submit" name="luu" value="Lưu"
				style="display: none">
		</form:form>

		<div>
			<c:url value="/j_spring_security_logout" var="logoutUrl" />
			<form action="${logoutUrl}" method="post" id="logoutForm">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
			<script>
				function formSubmit() {
					document.getElementById("logoutForm").submit();
				}
			</script>

			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<h2>
					<a href="javascript:formSubmit()"> <input class="buttonlogout"
						type="image" src="resources/images/logout.png" alt="Submit"></input></a>

				</h2>
			</c:if>
		</div>
		<div id="head-link">

			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<div id="welcome-div" style="float: left;">
					<h2 id="name" class="usernamelogin"></h2>
				</div>
			</c:if>
			<!-- <div id="header-menu" style="float:right;"></div> -->
		</div>
		<!--left page -->
		<div id="left">
			<jsp:include page="${leftPage}.jsp" />
		</div>
		<div id="content"></div>

		<div id="footer">
			
		</div>
	</div>

</body>
</html>
<script>
$('#camera').click(function(e){
	e.preventDefault();
	$('#file').click();
});
function update(){
	$('.nutluuavtar').click();
}
</script>
<!--  fit screen resolution -->
<script>
$(document).ready(function(){
    $(window).resize();
});
$(window).resize(function(){
    // your code
    var windowWidth=$(window).width();
    var mainContainerWidth=windowWidth-100; // For example
    $("#yourMainContainer").css({"width":mainContainerWidth+"px"});
});
</script>