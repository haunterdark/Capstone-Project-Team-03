package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.RestaurantDaoAndroid;
import utils.Util;

@Controller
@RequestMapping(value = { "/", })
public class RestaurantControllerAndroid {
	RestaurantDaoAndroid loadRes = new RestaurantDaoAndroid();
	Util util = new Util();

	@RequestMapping(value =  "/api/load-detail-res")
	public @ResponseBody Object loaddetailres(String id) {
		return loadRes.loadlistresapi(id);
	}
	@RequestMapping(value =  "/api/search-loainhahang")
	public @ResponseBody Object searchdanhmucapi(int id, int stt) {
		return loadRes.loadlistresapiforsearch(id,stt);
	}
	@RequestMapping(value =  "/api/checkconnectres")
	public @ResponseBody Object checkconnectresapi(String cusid, String resid) {
		return loadRes.checkconnectres(cusid,resid);
	}
	@RequestMapping(value =  "/api/addconnectres")
	public @ResponseBody Object addconnectresapi(String cusid, String resid) {
		loadRes.addconnectres(cusid,resid);
		return "Đã kết nối với nhà hàng";
	}
	@RequestMapping(value =  "/api/disconnectres")
	public @ResponseBody Object disconnectresapi(String cusid, String resid) {
		loadRes.disconnectres(cusid,resid);
		return "Đã hủy kết nối với nhà hàng";
	}
}