package controller;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.CouponDao;
import utils.Checkrole;


@Controller
@RequestMapping(value = { "/", })
public class CouponController {
	CouponDao loadCoupon = new CouponDao();
	Checkrole check = new Checkrole();
	
	@RequestMapping(value = { "/checkcouponcode" })
	public @ResponseBody boolean checkcodecoupon(@RequestParam("id") String id) {
		return loadCoupon.checkcodecoupon(id, check.PrintUser());

	}
	@RequestMapping(value = { "/checkdatecode" })
	public @ResponseBody boolean checkdatecoupon(@RequestParam("id") String id,@DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
		return loadCoupon.checkdate(id, date);

	}
	@RequestMapping(value = { "/checkcustomerid" })
	public @ResponseBody boolean checkcustomer(@RequestParam("id") String id, String couponid) {
		return loadCoupon.checkcustomer(couponid, id);

	}
	@RequestMapping(value = { "/checkcouponstatus" })
	public @ResponseBody boolean checkstatuscoupon(@RequestParam("id") String id) {
		return loadCoupon.checkstatuscoupon(id);

	}
	@RequestMapping(value = { "/get-coupon-value" })
	public @ResponseBody int getvaluecoupon(@RequestParam("id") String id) {
		return loadCoupon.getcouponvalue(id);

	}
}
