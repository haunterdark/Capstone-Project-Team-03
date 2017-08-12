package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.CampaignDaoAndroid;

@Controller
@RequestMapping(value = { "/", })
public class CampaignControllerAndroid {
	CampaignDaoAndroid loadCam = new CampaignDaoAndroid();
	
	@RequestMapping(value = "/api/loadlistcam")
	public @ResponseBody Object loadlistadsAPI() {
		return loadCam.PrintList();
	}
	@RequestMapping(value = "/api/loadcamclick")
	public @ResponseBody Object loadadsclickAPI(String id) {
		return loadCam.PrintCamClicked(id);
	}
}
