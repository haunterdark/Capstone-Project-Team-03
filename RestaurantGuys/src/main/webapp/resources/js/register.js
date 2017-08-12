function dangky() {
	var ten = $('#name').val();
	var email = $('#email').val();
	var phone = $('#phone').val();
	var captchaResponse = $("#g-recaptcha-response").val();
	$.ajax({

		url : "register",
		data : {
			restaurant_name : encodeURI(ten),
			restaurant_email : email,
			restaurant_phone : phone,
			recaptcha : captchaResponse,
		},
		success : function(result) {
			$("#content").html(result);
		}
	});
};

