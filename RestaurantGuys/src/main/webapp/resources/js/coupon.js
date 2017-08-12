// Run after the ROM tree is constructed.
$(document)
		.ready(
				function() {
					$("#redemcoupon")
							.click(
									function() {
										var id = $("#idcoupon").val();
										var today = new Date().toISOString()
												.slice(0, 10);
										var cusid = $("#idcus").val();
										var value = getcouponid();
										var valueresponse = value.responseText;
										if (id && cusid) {
											$
													.ajax({
														url : "checkcouponcode",
														data : {
															id : id
														},
														dataType : "json",
														type : 'GET',
														contentType : "application/json",
														success : function(
																result) {
															if (result) {
																$
																		.ajax({
																			url : "checkdatecode",
																			data : {
																				id : id,
																				date : today
																			},
																			dataType : "json",
																			type : 'GET',
																			contentType : "application/json",
																			success : function(
																					response) {
																				if (response) {
																					$
																							.ajax({
																								url : "checkcustomerid",
																								data : {
																									couponid : id,
																									id : cusid
																								},
																								dataType : "json",
																								type : 'GET',
																								contentType : "application/json",
																								success : function(
																										result1) {
																									if (result1) {
																										$
																												.ajax({
																													url : "checkcouponstatus",
																													data : {
																														id : id
																													},
																													dataType : "json",
																													type : 'GET',
																													contentType : "application/json",
																													success : function(
																															result2) {
																														if (result2) {
																															alert("Xác nhận coupon đã sử dụng thành công! Bạn được giảm giá "
																																	+ valueresponse
																																	+ "%");
																														} else {
																															alert("Coupon đã sử dụng!");
																														}
																													},
																													error : function(
																															result2) {
																														alert("Lỗi không thể kiểm tra trạng thái coupon");
																													}
																												});
																									} else {
																										alert("Mã Khách Hàng không đúng!");
																									}
																								},
																								error : function(
																										result1) {
																									alert("Lỗi không thể kiểm tra mã khách hàng");
																								}
																							});
																				} else {
																					alert("Coupon hết hạn hoặc chưa đến ngày sử dụng!");
																				}
																			},
																			error : function(
																					response) {
																				alert("Lỗi không thể kiểm tra thời hạn coupon");
																			}
																		});
															} else {
																alert("Coupon không tồn tại hoặc không thuộc nhà hàng này!");
															}
														},
														error : function(
																respone) {
															alert("Lỗi không kiểm tra được mã coupon");
														}
													});
										} else {
											alert("Vui lòng nhập mã coupon và khách hàng!");
										}
									});
				});
function getcouponid() {
	var id = $("#idcoupon").val();
	return $.ajax({
		url : 'get-coupon-value',
		data : {
			id : id
		},
		dataType : "json",
		type : 'GET',
		contentType : "application/json",
		async : !1,
		error : function() {
			alert('Lỗi lấy giá trị');
		}
	});
}