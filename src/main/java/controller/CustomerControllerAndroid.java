package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.CustomerDao;
import dao.CustomerDaoAndroid;

@Controller
@RequestMapping(value = { "/", })
public class CustomerControllerAndroid {
	
	CustomerDaoAndroid loadcus = new CustomerDaoAndroid();
	CustomerDao loadcusweb = new CustomerDao();
	
	@RequestMapping(value = "/api/checkidcus")
	public @ResponseBody Object loginAPI( String cusid) {
		return loadcus.checkcustomerid(cusid);
	}
	@RequestMapping(value = "/api/addcus")
	public @ResponseBody Object addcusAPI(String cusid, String cusphone) {
		loadcus.addcustomeraccount(cusid, cusphone);
		return "Success";
	}
	
	@RequestMapping(value = "/api/checkstatus")
	public @ResponseBody Object checkstatuscusAPI( String cusid) {
		int status = loadcusweb.getstatuscustomer(cusid);
		if(status == 1){
			return true;
		}else{
			return false;
		}
	}
	
}