package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dao.CityDao;
import dao.CustomerDao;
import dao.DistrictDao;
import utils.Checkrole;

@Controller
@RequestMapping(value = { "/", })
public class CustomerController {
	CustomerDao LoadCus = new CustomerDao();
	Checkrole check = new Checkrole();
	CityDao loadCity = new CityDao();
	DistrictDao loadDistrict = new DistrictDao();
	int page = 1;
	int recordsPerPage = 6;
	int noOfRecords = LoadCus.getNoOfRecords();
	int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

	@RequestMapping(value = { "/get-customers" })
	public ModelAndView printKhachhang(HttpServletRequest request) {
		if (request.getParameter("page") != null) {
			page = Integer.valueOf(request.getParameter("page"));
		}
		ModelAndView mv = new ModelAndView("quanlikhachhang");
		if (check.PrintRole().equals("ROLE_ADMIN")) {
			mv.addObject("LoadCity", loadCity.loadlistCity());
			mv.addObject("listCusAd", LoadCus.Print((page - 1) * recordsPerPage, recordsPerPage));
			mv.addObject("noOfPages", noOfPages);
			mv.addObject("currentPage", page);
		} else if (check.PrintRole().equals("ROLE_USER")) {
			mv.addObject("LoadCity", loadCity.loadlistCity());
			mv.addObject("listCusRe",
					LoadCus.PrintCustomer((page - 1) * recordsPerPage, recordsPerPage, check.PrintUser()));
			mv.addObject("noOfPages", noOfPages);
			mv.addObject("currentPage", page);
		}
		return mv;
	}

	@RequestMapping(value = { "/disable-customers/{id}" })
	public ModelAndView disable(@PathVariable("id") String id) {
		ModelAndView mv = new ModelAndView("quanlikhachhang");
		LoadCus.Updatestatus(id);
		if (check.PrintRole().equals("ROLE_ADMIN")) {
			mv.addObject("LoadCity", loadCity.loadlistCity());
			mv.addObject("listCusAd", LoadCus.Print((page - 1) * recordsPerPage, recordsPerPage));
			mv.addObject("noOfPages", noOfPages);
			mv.addObject("currentPage", page);
		} else if (check.PrintRole().equals("ROLE_USER")) {
			mv.addObject("LoadCity", loadCity.loadlistCity());
			mv.addObject("listCusRe",
					LoadCus.PrintCustomer((page - 1) * recordsPerPage, recordsPerPage, check.PrintUser()));
			mv.addObject("noOfPages", noOfPages);
			mv.addObject("currentPage", page);
		}
		return mv;
	}

	@RequestMapping(value = { "/change-resname" })
	public @ResponseBody String changeresname() {
		String role = check.PrintRole();
		return role;
	}

	@RequestMapping(value = { "/search-status" })
	public ModelAndView Searchfromstatus(int status) {
		ModelAndView mv = new ModelAndView("quanlikhachhang");
		if (check.PrintRole().equals("ROLE_ADMIN")) {
			mv.addObject("LoadCity", loadCity.loadlistCity());
			mv.addObject("listCusAd", LoadCus.SearchfromstatusAD(status));
		} else if (check.PrintRole().equals("ROLE_USER")) {
			mv.addObject("LoadCity", loadCity.loadlistCity());
			mv.addObject("listCusRe", LoadCus.SearchfromstatusRe(status, check.PrintUser()));
		}
		return mv;
	}

	@RequestMapping(value = { "/search-city_cus" })
	public ModelAndView Searchfromcity(int city_id) {
		ModelAndView mv = new ModelAndView("quanlikhachhang");
		if (check.PrintRole().equals("ROLE_ADMIN")) {
			mv.addObject("LoadCity", loadCity.loadlistCity());
			mv.addObject("listCusAd", LoadCus.SearchfromcityAD(city_id));
		} else if (check.PrintRole().equals("ROLE_USER")) {
			mv.addObject("LoadCity", loadCity.loadlistCity());
			mv.addObject("listCusRe", LoadCus.SearchfromcityRe(city_id, check.PrintUser()));
		}
		return mv;
	}

	@RequestMapping(value = { "/search-city-district_cus" })
	public ModelAndView Searchfromcitydistrict(int city_id, int district_id) {
		ModelAndView mv = new ModelAndView("quanlikhachhang");
		if (check.PrintRole().equals("ROLE_ADMIN")) {
			mv.addObject("LoadCity", loadCity.loadlistCity());
			mv.addObject("listCusAd", LoadCus.SearchfromcitydistrictAD(city_id, district_id));
		} else if (check.PrintRole().equals("ROLE_USER")) {
			mv.addObject("LoadCity", loadCity.loadlistCity());
			mv.addObject("listCusRe", LoadCus.SearchfromcitydistrictRe(city_id, district_id, check.PrintUser()));
		}
		return mv;
	}

	@RequestMapping(value = { "/search-city-district-status_cus" })
	public ModelAndView Searchfromcitydistrictstatus(int city_id, int district_id, int enable) {
		ModelAndView mv = new ModelAndView("quanlikhachhang");
		if (check.PrintRole().equals("ROLE_ADMIN")) {
			mv.addObject("LoadCity", loadCity.loadlistCity());
			mv.addObject("listCusAd", LoadCus.SearchfromcitydistrictstatusAD(city_id, district_id, enable));
		} else if (check.PrintRole().equals("ROLE_USER")) {
			mv.addObject("LoadCity", loadCity.loadlistCity());
			mv.addObject("listCusRe",
					LoadCus.SearchfromcitydistrictstatusRe(city_id, district_id, check.PrintUser(), enable));
		}
		return mv;
	}

	@RequestMapping(value = { "/search-city-district-name_cus" })
	public ModelAndView Searchfromcitydistrictname(int city_id, int district_id, String string) {
		ModelAndView mv = new ModelAndView("quanlikhachhang");
		if (check.PrintRole().equals("ROLE_ADMIN")) {
			mv.addObject("LoadCity", loadCity.loadlistCity());
			mv.addObject("listCusAd", LoadCus.SearchfromcitydistrictnameAD(city_id, district_id, string));
		} else if (check.PrintRole().equals("ROLE_USER")) {
			mv.addObject("LoadCity", loadCity.loadlistCity());
			mv.addObject("listCusRe",
					LoadCus.SearchfromcitydistrictnameRe(city_id, district_id, check.PrintUser(), string));
		}
		return mv;
	}

	@RequestMapping(value = { "/search-customer" })
	public ModelAndView Searchfromcus(String string) {
		ModelAndView mv = new ModelAndView("quanlikhachhang");
		if (check.PrintRole().equals("ROLE_ADMIN")) {
			mv.addObject("LoadCity", loadCity.loadlistCity());
			mv.addObject("listCusAd", LoadCus.SearchfromcusAD(string));
		} else if (check.PrintRole().equals("ROLE_USER")) {
			mv.addObject("LoadCity", loadCity.loadlistCity());
			mv.addObject("listCusRe", LoadCus.SearchfromcusRe(string, check.PrintUser()));
		}
		return mv;
	}

	@RequestMapping(value = { "/search-name-status" })
	public ModelAndView Searchfromnamestatus(String string, int status) {
		ModelAndView mv = new ModelAndView("quanlikhachhang");
		if (check.PrintRole().equals("ROLE_ADMIN")) {
			mv.addObject("LoadCity", loadCity.loadlistCity());
			mv.addObject("listCusAd", LoadCus.SearchfromnamestatusAD(string, status));
		} else if (check.PrintRole().equals("ROLE_USER")) {
			mv.addObject("LoadCity", loadCity.loadlistCity());
			mv.addObject("listCusRe", LoadCus.SearchfromnamestatusRE(string, status, check.PrintUser()));
		}
		return mv;
	}

	@RequestMapping(value = { "/search-all" })
	public ModelAndView SearchfromAll(String string, int status, int city, int district) {
		ModelAndView mv = new ModelAndView("quanlikhachhang");
		if (check.PrintRole().equals("ROLE_ADMIN")) {
			mv.addObject("LoadCity", loadCity.loadlistCity());
			mv.addObject("listCusAd", LoadCus.SearchfromallAD(string, status, city, district));
		} else if (check.PrintRole().equals("ROLE_USER")) {
			mv.addObject("LoadCity", loadCity.loadlistCity());
			mv.addObject("listCusRe", LoadCus.SearchfromallRE(string, status, check.PrintUser(), city, district));
		}
		return mv;
	}
}