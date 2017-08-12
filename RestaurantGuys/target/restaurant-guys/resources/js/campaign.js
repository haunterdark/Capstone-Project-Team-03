// Run after the ROM tree is constructed.
$(document)
		.ready(
				function() {
					$(".edit-campaign")
							.click(
									function() {
										var id = $(this).attr('id');
										$
												.ajax({
													url : "check-status-cam",
													data : {
														id : id
													},
													success : function(response) {
														if (response) {
															$
																	.ajax({
																		url : "get-campaign-to- edit",
																		data : {
																			id : id
																		},
																		success : function(
																				result) {
																			$(
																					"#content")
																					.html(
																							result);
																		},
																		error : function(
																				result) {
																			alert("Lỗi! Không thể chuyển qua form edit!");
																		}
																	});
														} else {
															alert("Chiến lược đang thực thi! Không thể chỉnh sửa hay xóa chiến lược!");
														}
													}
												});
									});
					campaignPaging = function(page) {
						$.ajax({
							url : "get-campaign?page=" + page,
							success : function(result) {
								$("#content").html(result);
							}
						});
					};
					$(".delete-campaign")
							.click(
									function() {
										var id = $(this).attr('id');
										if (confirm("Xác nhận xóa?") == true) {
											$
													.ajax({
														url : "check-status-cam",
														data : {
															id : id
														},
														success : function(
																response) {
															if (response) {
																$
																		.ajax({
																			url : "delete-campaign",
																			data : {
																				id : id
																			},
																			success : function(
																					result) {
																				$(
																						"#content")
																						.html(
																								result);
																			},
																			error : function(
																					result) {
																				alert("Lỗi! Không thể xóa chiến lược này!");
																			}
																		});
															} else {
																alert("Chiến lược đang thực thi! Không thể chỉnh sửa hay xóa chiến lược!");
															}
														}
													});
										} else {

										}
									});
					$("#resetedit").click(function() {
						var descrip = $('#description1').val();
						var value = $('#value1').val();
						var start = $('#start1').val();
						var end = $('#end1').val();
						$('#description').val(descrip);
						$('#value').val(value);
						$('#start').val(start);
						$('#end').val(end);
					});
					

				});

function hidecolum() {
	$.ajax({
		url : "change-resname-cam",
		success : function(result) {
			if (result == 'ROLE_ADMIN') {
				$("#newcampaign-managed").hide();
				$("#idcoupon").hide();
				$("#redemcoupon").hide();
				$(".campaigncustomer").hide();
			} else {
				$(".campaignadmin").hide();
			}
		},
		error : function(result) {
			alert("Lỗi!");
		}
	});
}
function searchstatuscam(id) {
	$.ajax({
		url : "search-status-cam",
		data : {
			status : id
		},
		success : function(result) {
			$("#content").html(result);
			$("#status").val(id);
		},
		error : function(result) {
			alert("Lỗi không tìm kiếm được!");
		}
	});
}
function addnewcampaign() {
	$.ajax({
		url : "get-new-campaign",
		success : function(result) {
			$("#content").html(result);
		},
		error : function(result) {
			alert("Lỗi không chuyển qua form đăng ký được!");
		}
	});
}
