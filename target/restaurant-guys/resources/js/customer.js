// Run after the ROM tree is constructed.
$(document).ready(function() {
	$(".discuss").click(function() {
		var cus = $(this).attr('id');
		var val = $(this).attr('value');
		$.ajax({
			url : "disable-customers/" + cus,
			data : "id=" + cus,
			success : function(response) {
				if (response != null && val == "Disable") {
					document.getElementById(cus).value = "Enable";
					alert("Vô hiệu hóa khách hàng thành công!");
				} else if (response != null && val == "Enable") {
					document.getElementById(cus).value = "Disable";
					alert("Kích hoạt nhà hàng thành công!");
				} else {
					alert("Lỗi!");
				}
				$("#content").html(response);
			}
		});
	});
	customerPaging = function(page) {
		$.ajax({
			url : "get-customers?page=" + page,
			success : function(result) {
				$("#content").html(result);
			}
		});
	};
	/*
	 * function loadfromstatus(clicked_id) { alert(clicked_id); $.ajax({ url :
	 * "loadlistdistrict", data : "id=" + clicked_id, dataType: "json", type:
	 * 'GET', contentType: "application/json", success : function(result) { for
	 * (var i = 0; i < result.length; i++) { s+= '<option
	 * value='+result[i].district_id +'>'+result[i].district_name+'</option>';
	 * $('#quan').html(s); } }, error:function(result){
	 * alert(JSON.stringify(result)); } }); }
	 */

});
function loadfromstatus(clicked_id) {
	var s = '';
	var tinh = $("#Tinh").val();
	var quan = $("#quan").val();
	if (tinh == null && quan == null && clicked_id != null) {
		$.ajax({
			url : "search-status",
			data : "status=" + clicked_id,
			success : function(result) {
				$("#content").html(result);
				$("#status").val(clicked_id);
			},
			error : function(result) {
				alert("Lỗi!");
			}
		});
	} else if (tinh != null && quan != null) {
		$.ajax({
			url : "search-city-district-status_cus",
			data : "enable=" + clicked_id + "&city_id=" + tinh
					+ "&district_id=" + quan,
			success : function(result) {
				$("#content").html(result);
				$("#Tinh").val(tinh);
				$.ajax({
					url : "loadlistdistrict",
					data : "id=" + clicked_id,
					dataType : "json",
					type : 'GET',
					contentType : "application/json",
					success : function(result) {
						for (var i = 0; i < result.length; i++) {
							s += '<option value=' + result[i].district_id + '>'
									+ result[i].district_name + '</option>';
							$('#quan').html(s);
							$("#quan").val(quan);
						}
					}
				});

				$("#status").val(clicked_id);
			},
			error : function(result) {
				alert("Lỗi!");
			}
		});
	}
}
function loadfromcus() {
	var s = '';
	var val = $("#input").val();
	var sta = $("#status").val();
	var tinh = $("#Tinh").val();
	var quan = $("#quan").val();
	if (val != null && sta != null && tinh == 0 && quan == 0) {
		$.ajax({
			url : "search-name-status",
			data : "string=" + val + "&status=" + sta,
			success : function(result) {
				$("#content").html(result);
				$("#input").val(val);
				$("#status").val(sta);
			},
			error : function(result) {
				alert("Lỗi !");
			}
		});
	} else if (val != null && sta == null && tinh == 0 && quan == 0) {
		$.ajax({
			url : "search-customer",
			data : "string=" + val,
			success : function(result) {
				$("#content").html(result);
				$("#input").val(val);
			},
			error : function(result) {
				alert("Lỗi!");
			}
		});
	} else if (val != null && sta != null && tinh != 0 && quan != 0) {
		$.ajax({
			url : "search-all",
			data : "string=" + val + "&status=" + sta + "&city=" + tinh
					+ "&district=" + quan,
			success : function(result) {
				$("#content").html(result);
				$("#Tinh").val(tinh);
				$.ajax({
					url : "loadlistdistrict",
					data : "id=" + tinh,
					dataType : "json",
					type : 'GET',
					contentType : "application/json",
					success : function(result) {
						for (var i = 0; i < result.length; i++) {
							s += '<option value=' + result[i].district_id + '>'
									+ result[i].district_name + '</option>';
							$('#quan').html(s);
							$("#quan").val(quan);
						}
					}
				});
				$("#status").val(sta);
				$("#input").val(val);
			},
			error : function(result) {
				alert("Lỗi!");
			}
		});
	}/*
		 * else if (tinh != 0 && quan != 0 && val != null && sta == null) {
		 * $.ajax({ url : "search-city-district-name_cus", data : "string=" +
		 * val + "&city_id=" + tinh + "&district_id=" + quan, success :
		 * function(result) { $("#content").html(result); $("#Tinh").val(tinh);
		 * $.ajax({ url : "loadlistdistrict", data : "id=" + tinh, dataType :
		 * "json", type : 'GET', contentType : "application/json", success :
		 * function(result) { for (var i = 0; i < result.length; i++) { s += '<option
		 * value=' + result[i].district_id + '>' + result[i].district_name + '</option>';
		 * $('#quan').html(s); $("#quan").val(quan); } } });
		 * $("#input").val(val); }, error : function(result) { alert("Error"); }
		 * });
		 */
}
function loaddistrictcus(clicked_id) {
	var s = '';
	var id = clicked_id;
	$.ajax({
		url : "search-city_cus",
		data : "city_id=" + clicked_id,
		success : function(result) {
			$("#content").html(result);
			$("#Tinh").val(id);
			$.ajax({
				url : "loadlistdistrict",
				data : "id=" + clicked_id,
				dataType : "json",
				type : 'GET',
				contentType : "application/json",
				success : function(result) {
					for (var i = 0; i < result.length; i++) {
						s += '<option value=' + result[i].district_id + '>'
								+ result[i].district_name + '</option>';
						$('#quan').html(s);
					}
				}
			});
		},

	});

}
function searchdistrict(clicked_id) {
	var s = '';
	var id = clicked_id;
	var tinh = $('#Tinh option:selected').val();
	$.ajax({
		url : "search-city-district_cus",
		data : "city_id=" + tinh + "&district_id=" + clicked_id,
		success : function(result) {
			$("#content").html(result);
			$("#Tinh").val(tinh);
			$.ajax({
				url : "loadlistdistrict",
				data : "id=" + tinh,
				dataType : "json",
				type : 'GET',
				contentType : "application/json",
				success : function(result) {
					for (var i = 0; i < result.length; i++) {
						s += '<option value=' + result[i].district_id + '>'
								+ result[i].district_name + '</option>';
						$('#quan').html(s);
						$("#quan").val(id);
					}
				}
			});

		},

	});
}
function hidecolum() {
	$.ajax({
		url : "change-resname",
		success : function(result) {
			if (result == 'ROLE_ADMIN') {
				$(".followcustomer").hide();
			} else {
				$(".followadmin").hide();
			}
		},
		error : function(result) {
			alert("Lỗi!");
		}
	});

}