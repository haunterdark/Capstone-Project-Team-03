package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = { "/", })
public class CouponControllerAndroid {
	@RequestMapping(value = "/api/getcoupon")
	public @ResponseBody Object getcoupon(String id) {
		return id;
		
	}
}
