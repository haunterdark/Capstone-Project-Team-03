package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.PromotionDaoAndroid;

@Controller
@RequestMapping(value = { "/", })
public class PromotionControllerAndroid {
	PromotionDaoAndroid loadPro = new PromotionDaoAndroid();

	@RequestMapping(value = "/api/loadlistpro")
	public @ResponseBody Object loadlistproAPI() {
		return loadPro.PrintList();
	}

	@RequestMapping(value = "/api/loadproclick")
	public @ResponseBody Object loadproclickAPI(String id) {
		return loadPro.PrintProClicked(id);
	}
}
