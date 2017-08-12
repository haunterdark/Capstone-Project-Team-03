package controller;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dao.RestaurantDao;
import dao.RestaurantDao_Register;
import entities.Restaurant;
import services.SendEmailService;
import utils.Checkrole;
import utils.Util;


@Controller
public class LoginController {
	SendEmailService s = new SendEmailService();
	Checkrole check = new Checkrole();
	RestaurantDao loadres = new RestaurantDao();
	Util utils = new Util();

	@RequestMapping(value = { "/", })
	public ModelAndView login() {

		ModelAndView model = new ModelAndView("/login");

		return model;

	}

	@RequestMapping(value = { "/welcome**" })
	public ModelAndView welcome() {
		String role = check.checkrole();
		ModelAndView model = new ModelAndView("/adminPage");
		model.addObject("leftPage", role);
		return model;
	}

	@RequestMapping(value = { "/register" })
	public ModelAndView send(Restaurant restaurant,  String recaptcha) {

		ModelAndView model = new ModelAndView("/login");
		RestaurantDao_Register register = new RestaurantDao_Register();
			register.insertdata(restaurant);
			s.sendMail(restaurant.getRestaurant_name(),
			restaurant.getRestaurant_email(),
			restaurant.getRestaurant_phone());
		return model;
	}

	@RequestMapping(value = "/admin**")
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		return model;

	}

	@RequestMapping(value = "/usernamelogin")
	public ModelAndView usernamelogin() {
		ModelAndView model = new ModelAndView("/adminPage");
		model.addObject("usernamelogin", loadres.getname(check.PrintRole(), check.PrintUser()));
		return model;

	}

	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Sai tên đăng nhập và mật khẩu!");
		}

		if (logout != null) {
			model.addObject("msg", "Bạn đã đăng xuất thành công!");
		}
		model.setViewName("login");

		return model;

	}
	@RequestMapping(value = { "/checkinput" })
	public @ResponseBody int checkinput(String name, String email, String phone) throws UnsupportedEncodingException {
		int noti =0;
		String ten = utils.decode(name).trim();
		if(utils.checkvietnamese(ten)&& ten.length()>=3 && ten.length()<=100){
			if(utils.checkemail(email)){
				if(utils.checkphone(phone)){
					noti = 0;
				}else{
					noti = 3;
				}
			}else{
				noti = 2;
			}
		}else{
			noti = 1;
		}
		return noti;
	}
	@RequestMapping(value = { "/databasedangky" })
	public @ResponseBody boolean checkdatabasedangky(String name, String email, String phone) throws UnsupportedEncodingException {
		boolean found = false;
		String ten = utils.decode(name);
		if(loadres.checkresname(ten)){
			found = true;
		}else{
			if(loadres.checkemail(email)){
				found = true;
			}else{
				if(loadres.checkphone(phone)){
					found = true;
				}else{
					found = false;
				}
			}
		}
		return found;
	}
	
}