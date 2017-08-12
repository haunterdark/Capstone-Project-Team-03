
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="resources/css/taotaikhoan.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/thaydoimatkhau.css" />
<html>
<head>
<title>Restaurant Guys - FORM THAY ĐỔI MẬT KHẨU</title>
<script src="resources/js/jquery.min.js">
	
</script>
<script src="resources/js/md5.js">
	
</script>
</head>
<div id="taotaikhoan">
	<body>
		<h1 class="h1">Thay Đổi Mật Khẩu</h1>
		<form class="dangkithongtin" name="xuly" action="" method="post">
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>
				<input id="passold" type="password" name="" value="" class="signup"
					placeholder="Nhập Mật Khẩu Cũ" />
				<c:if test="${not empty erroru}">
					<div class="erroru">${erroru}</div>
				</c:if>
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>

				<input id="passnew" type="password" name="" value="" class="signup"
					placeholder="Nhập Mật Khẩu Mới" />
			</ul>
			<ul class="inputul">
				<div>
					<img class="imginput" src="resources/images/inputicon.png">
				</div>

				<input id="repassnew" type="password" name="" value=""
					class="signup" placeholder="Nhập Lại Mật Khẩu Mới" />
			</ul>
		</form>
		<input class="nutluu" type="button" id="luuchange" value="Lưu"
			name="luu">
		<input class="nutnhaplaimk" id="resetchange" type="reset"
			value="Nhập lại" name="nhaplai">
		<input class="nuthuy" id="huychange" type="button"
			value="Hủy" name="huy">
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
</div>
</body>
</head>
</html>
<script>
	$(document).ready(function() {
		$("#huychange").click(function() {
			$.ajax({
				url : "get-profiles",
				success : function(result) {
					$("#content").html(result);
				}
			});
		});
		$("#resetchange").click(function() {
			$("#passold").val('');
			$("#passnew").val('');
			$("#repassnew").val('');
		});
		$("#luuchange").click(function() {
			var oldpass = $("#passold").val();
			var newpass = $("#passnew").val();
			var renewpass = $("#repassnew").val();
			if (confirm("Thực hiện cập nhật mật khẩu?") == true) {
			if(oldpass && newpass && renewpass){
				if(oldpass != newpass || oldpass != renewpass || (oldpass != newpass && oldpass != renewpass) ){
			$.ajax({
				url : "check-pass",
				data : {
					password : encodeURI(oldpass)
				},
				success : function(result) {
					if(result){
						
						if(newpass == renewpass){
							$.ajax({
								url : "update-pass",
								data : {
									password : encodeURI(newpass)
								},
								success : function(result) {
									alert("Cập nhật mật khẩu thành công! ");
									loadprofiles();
								}
							});
						}else{
							alert("Mật khẩu mới và nhập lại không khớp");
						}
						
					}else{
						alert("Mật khẩu không đúng");
					}
				}
			}); 
				}else{
					alert("Mật khẩu cũ không được trùng với mật khẩu mới");
				}
			}else{
				alert("Vui lòng nhập mật khẩu");
			}
			}else{
				alert("Hủy lệnh thực hiện");
			}
		});
		
	});
</script>