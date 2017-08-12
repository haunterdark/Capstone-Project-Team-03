package controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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

import dao.AdvertisementDao;
import data.CrunchifyGetPropertyValues;
import entities.Advertisement;
import entities.FileBucker;
import utils.Checkrole;
import utils.FileValidator;
import utils.Util;

@Controller
@RequestMapping(value = { "/", })
public class AdvertisementController {
	AdvertisementDao loadAds = new AdvertisementDao();
	Util util = new Util();
	Checkrole check = new Checkrole();
	int page = 1;
	int recordsPerPage = 6;
	int noOfRecords = loadAds.getNoOfRecords();
	int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

	@RequestMapping(value = { "/get-advertisements" })
	public ModelAndView printQuangcao(HttpServletRequest request) {
		if (request.getParameter("page") != null) {
			page = Integer.valueOf(request.getParameter("page"));
		}
		ModelAndView mv = new ModelAndView("quanliquangcao");
		if (check.PrintRole().equals("ROLE_ADMIN")) {
			mv.addObject("listAd", loadAds.Print((page - 1) * recordsPerPage, recordsPerPage));
			mv.addObject("noOfPages", noOfPages);
			mv.addObject("currentPage", page);
		} else if (check.PrintRole().equals("ROLE_USER")) {
			mv.addObject("listRe",
					loadAds.PrintAdvertisement((page - 1) * recordsPerPage, recordsPerPage, check.PrintUser()));
			mv.addObject("noOfPages", noOfPages);
			mv.addObject("currentPage", page);
		}
		return mv;

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

	@RequestMapping(value = "/save-advertisement")
	public @ResponseBody ModelAndView singleSave(@RequestPart("file") MultipartFile file,
			MultipartHttpServletRequest request) throws IOException {
		String path = new CrunchifyGetPropertyValues().getPropValues("upload.image.path.save");
		String fileName = null;
		String ten = request.getParameter("advertisement_name");
		String start = request.getParameter("advertisement_start");
		String end = request.getParameter("advertisement_end");
		ModelAndView model = new ModelAndView("redirect:welcome?ref=quanlyquangcao");

		try {
			// get file attach
			fileName = "upload/advertisement/" + file.getOriginalFilename();
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

			Advertisement ads = new Advertisement();
			ads.setAdvertisement_image(fileName);
			ads.setAdvertisement_name(ten);
			ads.setAdvertisement_start(start);
			ads.setAdvertisement_end(end);

			// insert database
			loadAds.insertAds(ads, check.PrintUser());
			model.addObject("listProRe",
					loadAds.PrintAdvertisement((page - 1) * recordsPerPage, recordsPerPage, check.PrintUser()));
			model.addObject("noOfPages", noOfPages);
			model.addObject("currentPage", page);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/edit-update-advertisement")
	public @ResponseBody ModelAndView update(@RequestPart("file") MultipartFile file,
			MultipartHttpServletRequest request) throws IOException {
		String path = new CrunchifyGetPropertyValues().getPropValues("upload.image.path.save");
		String fileName = null;
		String id = request.getParameter("advertisement_id");
		String ten = request.getParameter("advertisement_name");
		String start = request.getParameter("advertisement_start");
		String end = request.getParameter("advertisement_end");
		ModelAndView model = new ModelAndView("redirect:welcome?ref=quanlyquangcao");

		try {
			// get file attach
			fileName = "upload/advertisement/" + file.getOriginalFilename();
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

			Advertisement ads = new Advertisement();
			ads.setAdvertisement_id(id);
			ads.setAdvertisement_image(fileName);
			ads.setAdvertisement_name(ten);
			ads.setAdvertisement_start(start);
			ads.setAdvertisement_end(end);

			// insert database
			loadAds.updateAds(ads);
			model.addObject("listProRe",
					loadAds.PrintAdvertisement((page - 1) * recordsPerPage, recordsPerPage, check.PrintUser()));
			model.addObject("noOfPages", noOfPages);
			model.addObject("currentPage", page);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = { "/editads" })
	public ModelAndView updateQuangcao(String id) {

		ModelAndView mv = new ModelAndView("editquangcao");

		mv.addObject("Advertisement", loadAds.Printrads(id));

		return mv;
	}

	@RequestMapping(value = { "/update-ads" })
	public ModelAndView update(Advertisement Ad) {
		ModelAndView model = new ModelAndView("quanliquangcao");

		loadAds.updateAds(Ad);
		model.addObject("listRe",
				loadAds.PrintAdvertisement((page - 1) * recordsPerPage, recordsPerPage, check.PrintUser()));
		model.addObject("noOfPages", noOfPages);
		model.addObject("currentPage", page);
		// s.sendMail1(res.getRestaurant_username(), res.getRestaurant_email());
		// }
		return model;

	}

	@RequestMapping(value = { "/get-newAds" })
	public ModelAndView newAds() {
		ModelAndView mv = new ModelAndView("taomoiquangcao");
		return mv;

	}

	@RequestMapping(value = { "/add-Ads" })
	public ModelAndView add(Advertisement ads) {
		ModelAndView model = new ModelAndView("quanliquangcao");
		if (check.PrintRole().equals("ROLE_USER")) {
			// if (util.checkname(ads.getAds_name())) {
			loadAds.insertAds(ads, check.PrintUser());
			model.addObject("listRe",
					loadAds.PrintAdvertisement((page - 1) * recordsPerPage, recordsPerPage, check.PrintUser()));
			model.addObject("noOfPages", noOfPages);
			model.addObject("currentPage", page);
		}
		return model;

		// } else {
		// ModelAndView model1 = new ModelAndView("taomoiquangcao");
		// return model1;
		// }
	}

	@RequestMapping(value = { "/changeAds-resname" })
	public @ResponseBody String changeresname_pro() {
		String role = check.PrintRole();
		return role;
	}

	@RequestMapping(value = { "/search-Adsstatus" })
	public ModelAndView Searchfromstatus(int status) {
		ModelAndView mv = new ModelAndView("quanliquangcao");
		if (check.PrintRole().equals("ROLE_ADMIN")) {
			mv.addObject("listAd", loadAds.SearchAdsfromstatusAD(status));
		} else if (check.PrintRole().equals("ROLE_USER")) {
			mv.addObject("listRe", loadAds.SearchAdsfromstatusRe(status, check.PrintUser()));
		}
		return mv;
	}

	@RequestMapping(value = { "/search-advertisement" })
	public ModelAndView Searchfrompro(String string) {
		ModelAndView mv = new ModelAndView("quanliquangcao");
		if (check.PrintRole().equals("ROLE_ADMIN")) {
			mv.addObject("listAd", loadAds.SearchfromadsAD(string));
		} else if (check.PrintRole().equals("ROLE_USER")) {
			mv.addObject("listRe", loadAds.SearchfromadsRe(string, check.PrintUser()));
		}
		return mv;
	}

	@RequestMapping(value = { "/search-allAds" })
	public ModelAndView SearchfromAll(String string, int status) {
		ModelAndView mv = new ModelAndView("quanliquangcao");
		if (check.PrintRole().equals("ROLE_ADMIN")) {
			mv.addObject("listAd", loadAds.SearchAllAdsAD(string, status));
		} else if (check.PrintRole().equals("ROLE_USER")) {
			mv.addObject("listRe", loadAds.SearchAllAdsRe(string, status, check.PrintUser()));
		}
		return mv;
	}

	@RequestMapping(value = { "/disable-advertisement/{id}" })
	public ModelAndView disable(@PathVariable("id") String id) {
		ModelAndView mv = new ModelAndView("quanliquangcao");

		loadAds.Updatestatus(id);
		if (check.PrintRole().equals("ROLE_ADMIN")) {
			mv.addObject("listAd", loadAds.Print((page - 1) * recordsPerPage, recordsPerPage));
			mv.addObject("noOfPages", noOfPages);
			mv.addObject("currentPage", page);
		} else if (check.PrintRole().equals("ROLE_USER")) {
			mv.addObject("listRe",
					loadAds.PrintAdvertisement((page - 1) * recordsPerPage, recordsPerPage, check.PrintUser()));
			mv.addObject("noOfPages", noOfPages);
			mv.addObject("currentPage", page);
		}
		return mv;
	}

	@RequestMapping(value = { "/checkadsname" })
	public @ResponseBody boolean printDistrict(@RequestParam("ads_name") String ads_name) {
		return loadAds.checkvalidateadsname(ads_name);

	}
}
