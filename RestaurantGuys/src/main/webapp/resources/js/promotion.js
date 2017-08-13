// Run after the ROM tree is constructed.
$(document).ready(function() {
	$(".status").click(function() {
		var pro = $(this).attr('id');
		var val = $(this).attr('value');
		var url = "disable-promotion/" + pro;
		$.get(url, {
			id : pro
		}, function(response) {
			if (response != null && val == "Vô Hiệu") {
				document.getElementById(pro).value = "Kích Hoạt";
				alert("Vô Hiệu hóa khuyến mãi thành công!");
			} else if (response != null && val == "Kích Hoạt") {
				document.getElementById(pro).value = "Vô Hiệu";
				alert("Kích hoạt khuyến mãi thành công!");
			}
		});
	});
	promotionPaging = function(page) {
		$.ajax({
			url : "get-promotions?page=" + page,
			success : function(result) {
				$("#content").html(result);
			}
		});
	};
	$("#newPro-managed").click(function() {
		$.ajax({
			url : "get-newPro",
			success : function(result) {
				$("#content").html(result);
			}
		});
	});

	$(".edit").click(function() {
		var pro = $(this).attr('id');
		$.ajax({
			url : "edit-newPro",
			data : "id=" + pro,
			success : function(result) {
				$("#content").html(result);
			}
		});
	});
});
function loadfromstatuspro(clicked_id) {
	$.ajax({
		url : "search-Prostatus",
		data : "status=" + clicked_id,
		success : function(result) {
			$("#content").html(result);
			$("#status").val(clicked_id);
		},
		error : function(result) {
			alert("Error");
		}
	});
}
function loadfrompro() {
	var val = $("#input").val();
	var sta = $("#status").val();
	if (val != null && sta != null) {
		$.ajax({
			url : "search-allPro",
			data : "string=" + val + "&status=" + sta,
			success : function(result) {
				$("#content").html(result);
				$("#input").val(val);
				$("#status").val(sta);
			},
			error : function(result) {
				alert("Error");
			}
		});
	} else if (val != null && sta == null) {
		$.ajax({
			url : "search-promotion",
			data : "string=" + val,
			success : function(result) {
				$("#content").html(result);
				$("#input").val(val);
			},
			error : function(result) {
				alert("Error");
			}
		});
	}
}

function hidecolumPro() {
	$.ajax({
		url : "changePro-resname",
		success : function(result) {
			if (result == 'ROLE_ADMIN') {
				$(".followrestaurant").hide();
				$("#newPro-managed").hide();
			} else {
				$(".followadmin").hide();
			}
		},
		error : function(result) {
			alert("error");
		}
	});

}
