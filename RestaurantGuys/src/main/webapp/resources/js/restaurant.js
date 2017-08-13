// Run after the ROM tree is constructed.
$(document).ready(function() {
	$(".newRes-managed").click(function() {
		var value = $(this).attr('value');
		var id = $(this).attr('id');
		if (value == "Thêm mới") {
			$.ajax({
				url : "get-newRes/"+id,
				success : function(result) {
					$("#content").html(result);
				}
			});
		} else {
			$.ajax({
				url : "disable-restaurant",
				data : "id=" + id,
				success : function(response) {
					if (response != null && value == "Vô Hiệu") {
						document.getElementById(id).value = "Kích Hoạt";
						alert("Vô hiệu hóa nhà hàng thành công!");
					} else if (response != null && value == "Kích Hoạt") {
						document.getElementById(id).value = "Vô Hiệu";
						alert("Kích hoạt nhà hàng thành công!");
					} 
					$("#content").html(response);
				}
			});
		}
	});
	restaurentPaging = function(page) {
		$.ajax({
			url : "get-restaurants?page="+page,
			success : function(result) {
				$("#content").html(result);
			}
		});
	};
	$(".nutluu").click(function() {
		var id = $('#id').val();
		var ten = $('#ten').val();
		var diachi = $('#diachi').val();
		var tennhahang = $('#tennhahang').val();
		var email = $('#email').val();
		var sdt = $('#sdt').val();
		var tinh = $('#Tinh option:selected').val();
		var quan = $('#quan option:selected').val();
		var danhmuc = $('#category option:selected').val();
		var amthuc = $('#country option:selected').val();
		if (confirm("Xác nhận thực hiện thao tác?") == true) {
		if(ten){
		$.ajax({
			url : "checkuser",
			data : {
				username : encodeURI(ten),
			},
			success : function(result) {
				if (result) {
					alert("Tên đăng nhập đã tồn tại!");
				} else {
					if (id) {
						if(diachi&& tinh && quan && danhmuc && amthuc){
						$.ajax({
							url : "update-Res",
							data : {
								id : id,
								username : encodeURI(ten),
								address : encodeURI(diachi),
								city : tinh,
								district : quan,
								category : danhmuc,
								country : amthuc
							},
							success : function(result) {
								alert("Cập nhật nhà hàng thành công!");
								$("#content").html(result);
							}
						});}
						else{
							alert("Vui lòng nhập đầy đủ thông tin!");
						}
					} else {
						if(tennhahang &&email &&sdt &&diachi&& tinh && quan && danhmuc && amthuc){
							$.ajax({
								url : "checkadd",
								data : {
									name : encodeURI(tennhahang),
									email : email,
									phone : sdt,
								},
								success : function(result) {
									if(result){
										alert("Thông tin nhập vào đã tồn tại!");
									}else{
										$.ajax({
											url : "add-Res",
											data : {
												restaurant_username : encodeURI(ten),
												restaurant_name : encodeURI(tennhahang),
												restaurant_address : encodeURI(diachi),
												restaurant_email : email,
												restaurant_phone : sdt,
												city_id : tinh,
												district_id : quan,
												category_id : danhmuc,
												country_id : amthuc
											},
											success : function(result) {
												alert("Tạo đăng ký nhà hàng thành công! ");
												$("#content").html(result);
											}
										});
									}
								}
						});
						}else{
							alert("Vui lòng nhập đầy đủ thông tin!");
						}
					}
				}
			}
		});
		}else{
			alert("Bạn chưa nhập tên đăng nhập!");
		}
		}else {
		}

	});
	$("#register-account").click(function() {
		$.ajax({
			url : "set-restaurantsinfor",
			success : function(result) {
				$("#content").html(result);
			}
		});
	});
});

function loaddistrict(clicked_id) {
	var s = '';
	var id = clicked_id;
	$.ajax({
		url : "search-city",
		data : "Restaurant_city_id=" + clicked_id,
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
function loadcategory(clicked_id) {
	var s = '';
	var id = clicked_id;
	$.ajax({
		url : "search-category-re",
		data : "category_id=" + clicked_id,
		success : function(result) {
			$("#content").html(result);
			$("#category").val(id);
		},

	});
}
function loadcountry(clicked_id) {
	var s = '';
	var id = clicked_id;
	$.ajax({
		url : "search-country-re",
		data : "country_id=" + clicked_id,
		success : function(result) {
			$("#content").html(result);
			$("#country").val(id);
		},

	});

}
function searchdistrict(clicked_id) {
	var s = '';
	var id = clicked_id;
	var tinh = $('#Tinh option:selected').val();
	$.ajax({
		url : "search-city-district",
		data : "Restaurant_city_id=" + tinh + "&Restaurant_district_id="
				+ clicked_id,
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

function loadstatus(clicked_id) {
	var s = '';
	var val = $("#input").val();
	var tinh = $('#Tinh option:selected').val();
	var quan = $('#quan option:selected').val();
	var danhmuc = $('#category option:selected').val();
	var amthuc = $('#country option:selected').val();
	var id = clicked_id;
	// if (tinh == quan == danhmuc == amthuc == 0 && val.equal(null) ) {
	$.ajax({
		url : "search-status-re",
		data : "status=" + id,
		success : function(result) {
			$("#content").html(result);
			$("#status").val(id);
		},
		error : function(result) {
			alert("Lỗi!");
		}
	});
	/*
	 * else if(tinh !=0 && quan !=0 && val ==null && danhmuc ==0 && amthuc ==0){
	 * $.ajax({ url : "search-status-city-district-re", data : "status=" +
	 * clicked_id + "&Restaurant_city_id=" + tinh + "&Restaurant_district_id=" +
	 * quan, success : function(result) { $("#content").html(result);
	 * $("#Tinh").val(tinh);
	 * 
	 * $.ajax({ url : "loadlistdistrict", data : "id=" + tinh, dataType :
	 * "json", type : 'GET', contentType : "application/json", success :
	 * function(result) { for (var i = 0; i < result.length; i++) { s += '<option
	 * value=' + result[i].district_id + '>' + result[i].district_name + '</option>';
	 * $('#quan').html(s); $("#quan").val(quan); } } });
	 * 
	 * $("#status").val(clicked_id); }, error : function(result) {
	 * alert("Error"); } }); }
	 */
}
function loadquan(clicked_id) {
	var s = '';
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

		},
		error : function(result) {
			alert(JSON.stringify(result));
		}
	});

}
function loadres() {
	var s = '';
	var val = $("#input").val();
	var sta = $("#status").val();
	var tinh = $('#Tinh option:selected').val();
	var quan = $('#quan option:selected').val();
	if (val != null && sta != null && tinh == 0 && quan == 0) {
		$.ajax({
			url : "search-resname-status-re",
			data : "string=" + val + "&status=" + sta,
			success : function(result) {
				$("#content").html(result);
				$("#input").val(val);
				$("#status").val(sta);
			},
			error : function(result) {
				alert("Lỗi!");
			}
		});
	} else if (val != null && sta == null && tinh == 0 && quan == 0) {
		$.ajax({
			url : "search-resname-re",
			data : "string=" + val,
			success : function(result) {
				$("#content").html(result);
				$("#input").val(val);
			},
			error : function(result) {
				alert("Lỗi!");
			}
		});
	} else if (val != null && sta == null && tinh != 0 && quan != 0) {
		$.ajax({
			url : "search-resname-city-re",
			data : "string=" + val + "&city=" + tinh + "&district=" + quan,
			success : function(result) {
				$("#content").html(result);
				$("#input").val(val);
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

					},
					error : function(result) {
						alert(JSON.stringify(result));
					}
				});

			},
			error : function(result) {
				alert("Lỗi!");
			}
		});
	} else if (val != null && sta != null && tinh != 0 && quan != 0) {
		$.ajax({
			url : "search-all-of-res",
			data : "string=" + val + "&city=" + tinh + "&district=" + quan
					+ "&status=" + sta,
			success : function(result) {
				$("#content").html(result);
				$("#input").val(val);
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

					},
					error : function(result) {
						alert(JSON.stringify(result));
					}
				});
				$("#status").val(sta);

			},
			error : function(result) {
				alert("Lỗi!");
			}
		});
	}
}
function reset() {
	var ten = $('#tennhahang1').val();
	var sdt = $('#sdt1').val();
	var diachi = $("#diachi1").val();
	var tinh = $('#Tinh1').val();
	var quan = $('#quan1').val();
	var country = $('#country1').val();
	var category = $('#category1').val();
	$('#diachi').val(diachi);
	$('#sdt').val(sdt);
	$('#tennhahang').val(ten);
	$('#Tinh').val(tinh);
	$('#quan').val(quan);
	$('#country').val(country);
	$('#category').val(category);
}
function resetedit() {
	$('#diachi').val('');
	$('#ten').val('');
	$('#Tinh').val('');
	$('#quan').val('');
	$('#category').val('');
	$('#country').val('');
}
function huy() {
	if (confirm("Bạn muốn hủy thao tác đang thực hiện không?") == true) {
		$.ajax({
			url : "get-restaurants",
			success : function(result) {
				$("#content").html(result);
			}
		});
	} else {

	}
}
function changpass(){
	$.ajax({
		url : "get-formchangepass",
		success : function(result) {
			$("#content").html(result);
		}
	});
}