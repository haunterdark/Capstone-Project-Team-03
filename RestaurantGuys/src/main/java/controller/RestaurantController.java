package controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import dao.CategoryDao;
import dao.CityDao;
import dao.CountryDao;
import dao.DistrictDao;
import dao.RestaurantDao;
import dao.UserDao;
import data.CrunchifyGetPropertyValues;
import entities.District;
import entities.FileBucker;
import entities.Restaurant;
import utils.Checkrole;
import utils.FileValidator;
import utils.Util;

@Controller
@RequestMapping(value = { "/", })
public class RestaurantController {
	RestaurantDao loadRes = new RestaurantDao();
	CityDao loadCity = new CityDao();
	DistrictDao loadDistrict = new DistrictDao();
	UserDao loadUser = new UserDao();
	CountryDao loadCountry = new CountryDao();
	CategoryDao loadCategory = new CategoryDao();
	Util util = new Util();
	Checkrole checkrole = new Checkrole();

	int page = 1;
	int recordsPerPage = 6;
	int noOfRecords = loadRes.getNoOfRecords();
	int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

	// SendEmailService s = new SendEmailService();
	//
	//
	@RequestMapping(value = { "/get-restaurants" })
	public ModelAndView printNhahang(HttpServletRequest request) {
		if (request.getParameter("page") != null) {
			page = Integer.valueOf(request.getParameter("page"));
		}
		ModelAndView mv = new ModelAndView("quanlinhahang");

		mv.addObject("listRes", loadRes.Print((page - 1) * recordsPerPage, recordsPerPage));

		mv.addObject("LoadCity", loadCity.loadlistCity());
		mv.addObject("LoadCategory", loadCategory.loadlistCategory());
		mv.addObject("LoadCountry", loadCountry.loadlistCountry());
		mv.addObject("noOfPages", noOfPages);
		mv.addObject("currentPage", page);

		return mv;
	}

	@RequestMapping(value = { "/get-newRes/{id}" })
	public ModelAndView newRes(@PathVariable("id") String id) {
		ModelAndView mv = new ModelAndView("taomoitaikhoan");
		mv.addObject("Restaurant", loadRes.Printres(id));
		mv.addObject("LoadCity", loadCity.loadlistCity());
		mv.addObject("LoadCategory", loadCategory.loadlistCategory());
		mv.addObject("LoadCountry", loadCountry.loadlistCountry());
		return mv;
	}

	@RequestMapping(value = { "/get-profiles" })
	public ModelAndView newRes() {
		ModelAndView mv = new ModelAndView("updateprofiles");
		mv.addObject("Restaurant", loadRes.Printall(checkrole.PrintUser()));
		mv.addObject("LoadCity", loadCity.loadlistCity());
		mv.addObject("LoadCountry", loadCountry.loadlistCountry());
		mv.addObject("LoadCategory", loadCategory.loadlistCategory());
		return mv;
	}

	//
	@RequestMapping(value = { "/add-Res" }, produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public ModelAndView add(Restaurant res) {
		ModelAndView model = new ModelAndView("quanlinhahang");
		// if (util.checkname(res.getRestaurant_username()) &&
		// util.checkname(res.getRestaurant_name())
		// && util.checkname(res.getRestaurant_address()) &&
		// util.checkphone(res.getRestaurant_phone())
		// && util.checkemail(res.getRestaurant_email())) {
		try {
			loadUser.insertuser(util.decode(res.getRestaurant_username()));
			loadRes.insertdata(res);
			loadUser.insertuserrole(util.decode(res.getRestaurant_username()));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addObject("listRes", loadRes.Print((page - 1) * recordsPerPage, recordsPerPage));
		model.addObject("noOfPages", noOfPages);
		model.addObject("currentPage", page);
		// s.sendMail1(res.getRestaurant_username(), res.getRestaurant_email());
		// model.addObject("msg", "You've been add new account successfully.");

		// } else {
		// model.addObject("msg", "failllllllllllllllllllllllllll");
		// }
		return model;

	}

	//
	@RequestMapping(value = { "/update-Res" }, produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public ModelAndView update(@RequestParam("id") String id, @RequestParam("username") String username,
			@RequestParam("address") String address, @RequestParam("city") int city,
			@RequestParam("district") int district, @RequestParam("category") int category,
			@RequestParam("country") int country) {
		ModelAndView model = new ModelAndView("quanlinhahang");
		// if (util.checkname(res.getRestaurant_username()) &&
		// util.checkname(res.getRestaurant_address())) {
		String name, add;
		try {
			name = util.decode(username);
			add = util.decode(address);
			loadUser.insertuser(name);
			loadRes.updatedata(id, name, add, city, district, category, country);
			loadUser.insertuserrole(name);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addObject("listRes", loadRes.Print((page - 1) * recordsPerPage, recordsPerPage));
		model.addObject("noOfPages", noOfPages);
		model.addObject("currentPage", page);
		// s.sendMail1(res.getRestaurant_username(), res.getRestaurant_email());
		// }
		return model;

	}

	@RequestMapping(value = { "/update-Profiles" })
	public ModelAndView updateprofiles(Restaurant res) {
		 ModelAndView model = new ModelAndView("updateprofiles");
		// if (util.checkname(res.getRestaurant_username()) &&
		// util.checkname(res.getRestaurant_address())) {
		loadRes.updateprofiles(res);
		// s.sendMail1(res.getRestaurant_username(), res.getRestaurant_email());
		// }
		 return model;

	}
	@RequestMapping(value = { "/update-pass" })
	public ModelAndView updatepassword(String password) {
		ModelAndView model = new ModelAndView("updateprofiles");
		try {
			String decodepass = util.decode(password);
			String oldpass = util.MD5(decodepass);
			loadRes.updatepass(checkrole.PrintUser(), oldpass);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return model;
	}
	@RequestMapping(value = { "/search-city" })
	public ModelAndView Searchfromstatus(int Restaurant_city_id) {
		ModelAndView mv = new ModelAndView("quanlinhahang");
		mv.addObject("LoadCity", loadCity.loadlistCity());
		mv.addObject("LoadCategory", loadCategory.loadlistCategory());
		mv.addObject("LoadCountry", loadCountry.loadlistCountry());
		mv.addObject("listRes", loadRes.Searchfromcity(Restaurant_city_id));
		return mv;
	}

	@RequestMapping(value = { "/search-city-district" })
	public ModelAndView Searchfromcitydistrict(int Restaurant_city_id, int Restaurant_district_id) {
		ModelAndView mv = new ModelAndView("quanlinhahang");
		mv.addObject("LoadCity", loadCity.loadlistCity());
		mv.addObject("LoadCategory", loadCategory.loadlistCategory());
		mv.addObject("LoadCountry", loadCountry.loadlistCountry());
		mv.addObject("listRes", loadRes.Searchfromcitydistrict(Restaurant_city_id, Restaurant_district_id));
		return mv;
	}

	@RequestMapping(value = { "/set-restaurantsinfor" })
	public ModelAndView printdanhsach1() {
		ModelAndView mv = new ModelAndView("taomoidangky");
		mv.addObject("LoadCity", loadCity.loadlistCity());
		mv.addObject("LoadCategory", loadCategory.loadlistCategory());
		mv.addObject("LoadCountry", loadCountry.loadlistCountry());
		return mv;
	}

	@RequestMapping(value = { "/search-status-re" })
	public ModelAndView Searchstatusre(int status) {
		ModelAndView mv = new ModelAndView("quanlinhahang");
		mv.addObject("LoadCity", loadCity.loadlistCity());
		mv.addObject("LoadCategory", loadCategory.loadlistCategory());
		mv.addObject("LoadCountry", loadCountry.loadlistCountry());
		mv.addObject("listRes", loadRes.SearchfromstatusRe(status));
		return mv;
	}

	@RequestMapping(value = { "/search-category-re" })
	public ModelAndView Searchcategory(int category_id) {
		ModelAndView mv = new ModelAndView("quanlinhahang");
		mv.addObject("LoadCity", loadCity.loadlistCity());
		mv.addObject("LoadCategory", loadCategory.loadlistCategory());
		mv.addObject("LoadCountry", loadCountry.loadlistCountry());
		mv.addObject("listRes", loadRes.Searchfromcategory(category_id));
		return mv;
	}

	@RequestMapping(value = { "/search-country-re" })
	public ModelAndView Searchcountry(int country_id) {
		ModelAndView mv = new ModelAndView("quanlinhahang");
		mv.addObject("LoadCity", loadCity.loadlistCity());
		mv.addObject("LoadCategory", loadCategory.loadlistCategory());
		mv.addObject("LoadCountry", loadCountry.loadlistCountry());
		mv.addObject("listRes", loadRes.Searchfromcountry(country_id));
		return mv;
	}

	@RequestMapping(value = { "/search-category-country-re" })
	public ModelAndView Searchcategorycountry(int category_id, int country_id) {
		ModelAndView mv = new ModelAndView("quanlinhahang");
		mv.addObject("LoadCity", loadCity.loadlistCity());
		mv.addObject("LoadCategory", loadCategory.loadlistCategory());
		mv.addObject("LoadCountry", loadCountry.loadlistCountry());
		mv.addObject("listRes", loadRes.Searchfromcategorycountry(category_id, country_id));
		return mv;
	}

	@RequestMapping(value = { "/search-resname-re" })
	public ModelAndView Searchresname(String string) {
		ModelAndView mv = new ModelAndView("quanlinhahang");
		mv.addObject("LoadCity", loadCity.loadlistCity());
		mv.addObject("LoadCategory", loadCategory.loadlistCategory());
		mv.addObject("LoadCountry", loadCountry.loadlistCountry());
		mv.addObject("listRes", loadRes.Searchfromname(string));
		return mv;
	}

	@RequestMapping(value = { "/search-resname-status-re" })
	public ModelAndView Searchresnamestatus(String string, int status) {
		ModelAndView mv = new ModelAndView("quanlinhahang");
		mv.addObject("LoadCity", loadCity.loadlistCity());
		mv.addObject("LoadCategory", loadCategory.loadlistCategory());
		mv.addObject("LoadCountry", loadCountry.loadlistCountry());
		mv.addObject("listRes", loadRes.Searchfromnamestatus(string, status));
		return mv;
	}

	@RequestMapping(value = { "/search-resname-city-re" })
	public ModelAndView Searchresnamecity(String string, int city, int district) {
		ModelAndView mv = new ModelAndView("quanlinhahang");
		mv.addObject("LoadCity", loadCity.loadlistCity());
		mv.addObject("LoadCategory", loadCategory.loadlistCategory());
		mv.addObject("LoadCountry", loadCountry.loadlistCountry());
		mv.addObject("listRes", loadRes.Searchfromnamecity(string, city, district));
		return mv;
	}

	@RequestMapping(value = { "/search-all-of-res" })
	public ModelAndView Searchall(String string, int city, int district, int status) {
		ModelAndView mv = new ModelAndView("quanlinhahang");
		mv.addObject("LoadCity", loadCity.loadlistCity());
		mv.addObject("LoadCategory", loadCategory.loadlistCategory());
		mv.addObject("LoadCountry", loadCountry.loadlistCountry());
		mv.addObject("listRes", loadRes.Searchall(string, city, district, status));
		return mv;
	}

	@RequestMapping(value = { "/search-status-city-district-re" })
	public ModelAndView Searchcitydistrictstatus(int Restaurant_city_id, int Restaurant_district_id, int status) {
		ModelAndView mv = new ModelAndView("quanlinhahang");
		mv.addObject("LoadCity", loadCity.loadlistCity());
		mv.addObject("LoadCategory", loadCategory.loadlistCategory());
		mv.addObject("LoadCountry", loadCountry.loadlistCountry());
		mv.addObject("listRes",
				loadRes.Searchfromcitydistrictstatus(Restaurant_city_id, Restaurant_district_id, status));
		return mv;
	}

	@RequestMapping(value = { "/disable-restaurant" })
	public ModelAndView disable(String id) {
		ModelAndView mv = new ModelAndView("quanlinhahang");
		loadRes.Updatestatus(id);
		loadUser.Updatestatus(id);
		mv.addObject("listRes", loadRes.Print((page - 1) * recordsPerPage, recordsPerPage));
		mv.addObject("LoadCity", loadCity.loadlistCity());
		mv.addObject("LoadCategory", loadCategory.loadlistCategory());
		mv.addObject("LoadCountry", loadCountry.loadlistCountry());
		mv.addObject("noOfPages", noOfPages);
		mv.addObject("currentPage", page);
		return mv;

	}

	@RequestMapping(value = { "/loadlistdistrict" })
	public @ResponseBody List<District> printDistrict(@RequestParam("id") int id) {
		return loadDistrict.loadlistDistrict(id);

	}

	@RequestMapping(value = { "/checkuser" })
	public @ResponseBody boolean checkuser(@RequestParam("username") String username) {
		return loadUser.checkusername(username);

	}

	@RequestMapping(value = { "/load-avatar" })
	public @ResponseBody String loadavatar() {
		String s = null;
		if (checkrole.PrintRole().equals("ROLE_ADMIN")) {
			s = "upload/admin.png";
		} else if (checkrole.PrintRole().equals("ROLE_USER")) {
			s = loadRes.loadavatarres(checkrole.PrintUser());
		}
		return s;
	}
	@RequestMapping(value = { "/display- camera" })
	public @ResponseBody boolean displaycamera() {
		boolean found=false;
		if (checkrole.PrintRole().equals("ROLE_ADMIN")) {
			found = true;
		} else if (checkrole.PrintRole().equals("ROLE_USER")) {
			found = false;
		}
		return found;
	}

	@RequestMapping(value = { "/load-restaurant-name" }, produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public @ResponseBody String loadname() {
		String s = null;
		if (checkrole.PrintRole().equals("ROLE_ADMIN")) {
			s = "Admin";
		} else if (checkrole.PrintRole().equals("ROLE_USER")) {
			s = loadRes.loadresname(checkrole.PrintUser());
		}
		return s;
	}

	@RequestMapping(value = { "/checkadd" })
	public @ResponseBody boolean checkadd(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("phone") String phone) {
		boolean exists = false;
		try {
			if (loadRes.checkresname(util.decode(name)) && loadRes.checkemail(email) && loadRes.checkphone(phone)) {
				exists = true;
			} else {
				exists = false;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exists;
	}

	@Autowired
	FileValidator fileValidator;

	@InitBinder("fileBucker")
	protected void initBinderFileBucket(WebDataBinder binder) {
		binder.setValidator(fileValidator);
	}

	@ModelAttribute("fileBucker")
	public FileBucker createModel() {
		return new FileBucker();
	}

	@RequestMapping(value = "/update-avatar")
	public @ResponseBody ModelAndView singleSave(@RequestPart("file") MultipartFile file,
			MultipartHttpServletRequest request) throws IOException {
		String path = new CrunchifyGetPropertyValues().getPropValues("upload.image.path.save");
		String fileName = null;
		ModelAndView model = new ModelAndView("redirect:welcome");

		try {
			// get file attach
			fileName = "upload/restaurant_avatar/" + file.getOriginalFilename();
			// get full path of file
			String fullNameFile = path + fileName;
			File fileNew = new File(fullNameFile);
			System.out.println(fileNew.getAbsolutePath());
			if (!fileNew.exists()) {
				fileNew.createNewFile();
			}
			// System.out.println(fileNew.getAbsolutePath());
			byte[] bytes = file.getBytes();
			// write file to the hard disk
			BufferedOutputStream buffStream = new BufferedOutputStream(
					new FileOutputStream(new File(fileNew.getAbsolutePath()), true));
			buffStream.write(bytes);
			buffStream.close();
			loadRes.updateimage(fileName, checkrole.PrintUser());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = { "/get-formchangepass" })
	public ModelAndView newpass() {
		ModelAndView mv = new ModelAndView("thaydoimatkhau");
		return mv;
	}

	@RequestMapping(value = { "/check-pass" })
	public @ResponseBody boolean checkpassword(String password) {
		boolean found = false;
		String old = loadUser.checkpassword(checkrole.PrintUser());
		try {
			String decodepass = util.decode(password);
			String oldpass = util.MD5(decodepass);
			if (oldpass.equals(old)) {
				found= true;
			} 
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return found;
		
	}
	@RequestMapping(value = { "/checkupdateprofile" })
	public @ResponseBody int checkinput(String name, String address, String phone) throws UnsupportedEncodingException {
		int noti =0;
		String ten = util.decode(name).trim();
		String dc= util.decode(address).trim();
		if(util.checkvietnamese(ten)&& ten.length()>=6 && ten.length()<=100){
			if(util.checkvietnamese(dc)&& dc.length()>=3 && dc.length()<=100){
				if(util.checkphone(phone)){
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
}