// Run after the ROM tree is constructed.
$(document).ready(function() {
	$("#restaurant-managed").click(function() {
		loadrestaurant();
	});
	$("#customer-managed").click(function() {
		loadcustomer();
	});
	$("#advertisement-managed").click(function() {
		loadadvertisement();
	});
	$("#promotion-managed").click(function() {
		loadpromotion();
	});
	$("#campaign-managed").click(function() {
		loadcampaign();
	});
	$("#reservation-managed").click(function() {
		loadreservation();
	});
	$("#profiles-managed").click(function() {
		loadprofiles();
	});
});
function loadavatarandname() {
	$.ajax({
		url : "load-avatar",
		success : function(result) {
			$("#hinhdaidien").attr('src', result);
		}
	});
	$.ajax({
		url : "load-restaurant-name",
		success : function(result) {
			$(".usernamelogin").text(result);
		}
	});
	$.ajax({
		url : "display- camera",
		success : function(result) {
			if(result){
				$("#camera").hide();
			}
		}
	});
}

function loadrestaurant() {
	$.ajax({
		url : "get-restaurants",
		success : function(result) {
			$("#content").html(result);
		}
	});
}
function loadcustomer() {
	$.ajax({
		url : "get-customers",
		success : function(result) {
			$("#content").html(result);
		}
	});
}
function loadadvertisement() {
	$.ajax({
		url : "get-advertisements",
		success : function(result) {
			$("#content").html(result);
		}
	});
}
function loadpromotion() {
	$.ajax({
		url : "get-promotions",
		success : function(result) {
			$("#content").html(result);
		}
	});
}
function loadreservation() {
	$.ajax({
		url : "get-reservations",
		success : function(result) {
			$("#content").html(result);
		}
	});
}
function loadcampaign() {
	$.ajax({
		url : "get-campaign",
		success : function(result) {
			$("#content").html(result);
		}
	});
}
function loadprofiles() {
	$.ajax({
		url : "get-profiles",
		success : function(result) {
			$("#content").html(result);
		}
	});
}