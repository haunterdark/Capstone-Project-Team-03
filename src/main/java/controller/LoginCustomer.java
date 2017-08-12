package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.AdvertisementDao;
import services.LoginAPI;

@Controller
public class LoginCustomer {
	LoginAPI login = new LoginAPI();
	AdvertisementDao loadAds = new AdvertisementDao();

	@RequestMapping(value = "/api/login")
	public @ResponseBody Object loginAPI(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		boolean found = login.loadcustomer(username, password);
		boolean status = login.checkstatus(username);
		if (found && status) {
			return true;
		} else {
			return false;
		}
	}



}
