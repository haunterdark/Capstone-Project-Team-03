package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.AdvertisementDaoAndroid;

@Controller
@RequestMapping(value = { "/", })
public class AdvertisementControllerAndroid {
	AdvertisementDaoAndroid loadAds = new AdvertisementDaoAndroid();
	
	@RequestMapping(value = "/api/loadlistads")
	public @ResponseBody Object loadlistadsAPI() {
		return loadAds.PrintList();
	}
	@RequestMapping(value = "/api/loadadsclick")
	public @ResponseBody Object loadadsclickAPI(String id) {
		return loadAds.PrintAdsClicked(id);
	}
}
