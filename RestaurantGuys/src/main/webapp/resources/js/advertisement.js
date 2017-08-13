// Run after the ROM tree is constructed.
$(document).ready(function() {
	$("#newAds-managed").click(function() {
		$.ajax({
			url : "get-newAds",
			success : function(result) {
				$("#content").html(result);
			}
		});
	});
	advertisementPaging = function(page) {
		$.ajax({
			url : "get-advertisements?page="+page,
			success : function(result) {
				$("#content").html(result);
			}
		});
	};
	$(".edit-adsvertisement").click(function() {
		var id = $(this).attr('id');
		$.ajax({
			url : "editads",
			data : "id=" + id,
			success : function(result) {
				$("#content").html(result);
			}
		});
	});
	$(".nutreset").click(function() {

		var id = $('#id').val();
		var hinhanh = $('#hinhanh').val("");
		var tenquangcao = $('#tenquangcao').val();
		var start = $('#start').val("");
		var end = $('#end').val("");
		alert("Khôi phục thành công!");

		/*
		 * $(".Ads-managed").click(function() { var id = $(this).attr('id'); var
		 * val = $(this).attr('value'); $.ajax({ url : "disable-advertisement/" +
		 * id, success : function(response) { if (response != null && val ==
		 * "Disable") { document.getElementById(id).value = "Enable";
		 * alert("Disable customer successful"); } else if (response != null &&
		 * val == "Enable") { document.getElementById(id).value = "Disable";
		 * alert("Enable customer successful"); } else { alert("Error"); }
		 * $("#content").html(response); } });
		 */
	});

	/*$(".nutluu").click(function() {

		var id = $('#id').val();
		var hinhanh = $('#hinhanh').val();
		var tenquangcao = $('#tenquangcao').val();
		var start = $('#start').val();
		var end = $('#end').val();
		var today = new Date().toISOString().slice(0, 10);
		var st = new Date(start);
		var en = new Date(end);
		var to = new Date(today);
		if (confirm("Xác nhận thực hiện thao tác?") == true) {
		if (tenquangcao == '') {
			alert(" Tên quảng cáo không được bỏ trống");
		} else if (tenquangcao != '') {
			if (st > to && en > to && en > st) {
				$.ajax({
					url : "update-ads",
					data : {
						ads_id : id,
						ads_image : hinhanh,
						ads_name : encodeURI(tenquangcao),
						ads_start : start,
						ads_end : end,
					},
					success : function(result) {

						alert("Cập nhật quảng cáo thành công! ");
						$("#content").html(result);

					}
				
				 
				// } else {
				
				 * url : "checkadsname", data : { ads_name :
				 * encodeURI(tenquangcao), },
				 
				});
			} else {
				alert("Vui lòng chọn lại ngày bắt đầu và ngày kết thúc!");
			}
		}
		}else{
			
		}
	});*/

});

function hidecolum() {
	$.ajax({
		url : "change-resname",
		success : function(result) {
			if (result == 'ROLE_ADMIN') {
				$(".quanliquangcao_customer").hide();
				$("#newAds-managed").hide();
			} else {
				$(".quanliquangcao_admin").hide();
			}
		},
		error : function(result) {
			alert("error");
		}
	});

}
// Run after the ROM tree is constructed.
$(document).ready(function() {
	$(".Ads-managed").click(function() {
		var ads = $(this).attr('id');
		var val = $(this).attr('value');
		var url = "disable-advertisement/" + ads;
		$.get(url, function(response) {
			if (response != null && val == "Kích Hoạt") {
				document.getElementById(ads).value = "Vô Hiệu";
				alert("Vô hiệu hóa quảng cáo thành công!");
			} else if (response != null && val == "Vô Hiệu") {
				document.getElementById(ads).value = "Kích Hoạt";
				alert("Kích hoạt quảng cáo thành công");
			} else {
				alert("Lỗi không thể kích hoạt hay vô hiệu!");
			}
		});
	});

});

function loadfromstatuspro(clicked_id) {
	$.ajax({
		url : "search-Adsstatus",
		data : "status=" + clicked_id,
		success : function(result) {
			$("#content").html(result);
			$("#status").val(clicked_id);
		},
		error : function(result) {
			alert("Lỗi không tìm kiếm được!");
		}
	});
}
function loadfrompro() {
	var val = $("#input").val();
	var sta = $("#status").val();
	if (val != null && sta != null) {
		$.ajax({
			url : "search-allAds",
			data : "string=" + val + "&status=" + sta,
			success : function(result) {
				$("#content").html(result);
				$("#input").val(val);
				$("#status").val(sta);
			},
			error : function(result) {
				alert("Lỗi không tìm kiếm được!");
			}
		});
	} else if (val != null && sta == null) {
		$.ajax({
			url : "search-advertisement",
			data : "string=" + val,
			success : function(result) {
				$("#content").html(result);
				$("#input").val(val);
			},
			error : function(result) {
				alert("Lỗi không tìm kiếm được!");
			}
		});
	}
	// check dublicate ten quang cao

}
function huy() {
	if (confirm("Bạn muốn hủy thao tác đang thực hiện không?") == true) {
		$.ajax({
			url : "get-advertisements",
			success : function(result) {
				$("#content").html(result);
			}
		});
	} else {

	}
}