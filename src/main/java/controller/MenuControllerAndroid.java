package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.MenuDaoAndroid;

@Controller
@RequestMapping(value = { "/", })
public class MenuControllerAndroid {
	MenuDaoAndroid loadMenu = new MenuDaoAndroid();

	@RequestMapping(value = { "/api/load-danhmuc" })
	public @ResponseBody Object loaddanhmuc() {
		return loadMenu.loadlistMenu();
	}

	@RequestMapping(value = { "/api/load-city" })
	public @ResponseBody Object loadcityapi() {
		return loadMenu.loadlistcity();
	}

	@RequestMapping(value = { "/api/load-district" })
	public @ResponseBody Object loaddistrictapi(String cityname) {
		return loadMenu.loadlistdistrict(cityname);

	}

}
