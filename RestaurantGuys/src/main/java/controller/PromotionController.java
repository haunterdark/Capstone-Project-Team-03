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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import dao.PromotionDao;
import data.CrunchifyGetPropertyValues;
import entities.FileBucker;
import entities.Promotion;
import utils.Checkrole;
import utils.FileValidator;
import utils.Util;

@Controller
@RequestMapping(value = { "/", })
public class PromotionController {
	PromotionDao loadPro = new PromotionDao();
	Checkrole check = new Checkrole();
	Util util = new Util();
	int page = 1;
	int recordsPerPage = 5;
	int noOfRecords = loadPro.getNoOfRecords();
	int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

	@RequestMapping(value = { "/get-promotions" })
	public ModelAndView printKhuyenmai(HttpServletRequest request) {
		if (request.getParameter("page") != null) {
			page = Integer.valueOf(request.getParameter("page"));

		}
		ModelAndView mv = new ModelAndView("quanlikhuyenmai");
		if (check.PrintRole().equals("ROLE_ADMIN")) {
			mv.addObject("listProAd", loadPro.Print((page - 1) * recordsPerPage, recordsPerPage));
			mv.addObject("noOfPages", noOfPages);
			mv.addObject("currentPage", page);
		} else if (check.PrintRole().equals("ROLE_USER")) {
			mv.addObject("listProRe", loadPro.PrintPro((page - 1) * recordsPerPage, recordsPerPage, check.PrintUser()));
			mv.addObject("noOfPages", noOfPages);
			mv.addObject("currentPage", page);
		}
		return mv;
	}

	@RequestMapping(value = { "/get-newPro" })
	public ModelAndView newPro() {
		ModelAndView mv = new ModelAndView("taomoikhuyenmai");
		return mv;

	}

	@RequestMapping(value = { "/edit-newPro" })
	public ModelAndView editPro(String id) {
		ModelAndView mv = new ModelAndView("suakhuyenmai");
		mv.addObject("Promotion", loadPro.Printproedit(id));
		return mv;
	}

	@RequestMapping(value = { "/disable-promotion/{id}" })
	public ModelAndView disable(@PathVariable("id") String id) {
		ModelAndView mv = new ModelAndView("quanlikhuyenmai");
		loadPro.Updatestatus(id);
		if (check.PrintRole().equals("ROLE_ADMIN")) {
			mv.addObject("listProAd", loadPro.Print((page - 1) * recordsPerPage, recordsPerPage));
		} else if (check.PrintRole().equals("ROLE_USER")) {
			mv.addObject("listProRe", loadPro.PrintPro((page - 1) * recordsPerPage, recordsPerPage, check.PrintUser()));
			mv.addObject("noOfPages", noOfPages);
			mv.addObject("currentPage", page);
		}
		return mv;

	}

	@RequestMapping(value = { "/changePro-resname" })
	public @ResponseBody String changeresname_pro() {
		String role = check.PrintRole();
		return role;
	}

	@RequestMapping(value = { "/search-Prostatus" })
	public ModelAndView Searchfromstatus(int status) {
		ModelAndView mv = new ModelAndView("quanlikhuyenmai");
		if (check.PrintRole().equals("ROLE_ADMIN")) {
			mv.addObject("listProAd", loadPro.SearchProfromstatusAD(status));
		} else if (check.PrintRole().equals("ROLE_USER")) {
			mv.addObject("listProRe", loadPro.SearchProfromstatusRe(status, check.PrintUser()));
		}
		return mv;
	}

	@RequestMapping(value = { "/search-promotion" })
	public ModelAndView Searchfrompro(String string) {
		ModelAndView mv = new ModelAndView("quanlikhuyenmai");
		if (check.PrintRole().equals("ROLE_ADMIN")) {
			mv.addObject("listProAd", loadPro.SearchfromproAD(string));
		} else if (check.PrintRole().equals("ROLE_USER")) {
			mv.addObject("listProRe", loadPro.SearchfromproRe(string, check.PrintUser()));
		}
		return mv;
	}

	@RequestMapping(value = { "/search-allPro" })
	public ModelAndView SearchfromAll(String string, int status) {
		ModelAndView mv = new ModelAndView("quanlikhuyenmai");
		if (check.PrintRole().equals("ROLE_ADMIN")) {
			mv.addObject("listProAd", loadPro.SearchAllProAD(string, status));
		} else if (check.PrintRole().equals("ROLE_USER")) {
			mv.addObject("listProRe", loadPro.SearchAllProRe(string, status, check.PrintUser()));
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

	// @RequestMapping(value = { "/add-Pro" })
	// public ModelAndView add(Promotion pro) {
	// ModelAndView model = new ModelAndView("quanlikhuyenmai");
	//
	// if (util.checkvietnamese(pro.getPromotion_name())) {
	// loadPro.insertPro(pro, check.PrintUser(),fileName);
	// model.addObject("listProRe", loadPro.PrintPro(check.PrintUser()));
	// return model;
	// } else {
	// ModelAndView model2 = new ModelAndView("taomoikhuyenmai");
	// return model2;
	// }
	// }

	@RequestMapping(value = "/save-promotions")
	public @ResponseBody ModelAndView singleSave(@RequestPart("file") MultipartFile file,
			MultipartHttpServletRequest request) throws IOException {
		String path = new CrunchifyGetPropertyValues().getPropValues("upload.image.path.save");
		String fileName = null;
		String ten = request.getParameter("promotion_name");
		String start = request.getParameter("promotion_start");
		String end = request.getParameter("promotion_end");
		ModelAndView model = new ModelAndView("redirect:welcome?ref=quanlykhuyenmai");

		try {
			// get file attach
			fileName = "upload/promotion/" + file.getOriginalFilename();
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

			Promotion pro = new Promotion();
			pro.setPromotion_end(end);
			pro.setPromotion_name(ten);
			pro.setPromotion_start(start);

			// insert database
			loadPro.insertPro(pro, check.PrintUser(), fileName);
			model.addObject("listProRe",
					loadPro.PrintPro((page - 1) * recordsPerPage, recordsPerPage, check.PrintUser()));
			model.addObject("noOfPages", noOfPages);
			model.addObject("currentPage", page);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/edit-update-promotions")
	public @ResponseBody ModelAndView updatepromotion(@RequestPart("file") MultipartFile file,
			MultipartHttpServletRequest request) throws IOException {
		String path = new CrunchifyGetPropertyValues().getPropValues("upload.image.path.save");
		String fileName = null;
		String id = request.getParameter("promotion_id");
		String ten = request.getParameter("promotion_name");
		String start = request.getParameter("promotion_start");
		String end = request.getParameter("promotion_end");
		ModelAndView model = new ModelAndView("redirect:welcome?ref=quanlykhuyenmai");

		try {
			// get file attach
			fileName = "upload/promotion/" + file.getOriginalFilename();
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

			Promotion pro = new Promotion();
			pro.setPromotion_id(id);
			pro.setPromotion_image(fileName);
			pro.setPromotion_end(end);
			pro.setPromotion_name(ten);
			pro.setPromotion_start(start);

			// insert database
			loadPro.updatepro(pro);
			// loadPro.insertPro(pro, check.PrintUser(), fileName);
			model.addObject("listProRe",
					loadPro.PrintPro((page - 1) * recordsPerPage, recordsPerPage, check.PrintUser()));
			model.addObject("noOfPages", noOfPages);
			model.addObject("currentPage", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	// @RequestMapping(value = { "/load-Pro" })
	// public ModelAndView trave() {
	// ModelAndView mv = new ModelAndView("quanlikhuyenmai");
	// if (check.PrintRole().equals("ROLE_ADMIN")) {
	// mv.addObject("listProAd", loadPro.Print());
	// } else if(check.PrintRole().equals("ROLE_USER")) {
	// mv.addObject("listProRe", loadPro.PrintPro(check.PrintUser()));
	// }
	// return mv;
	// }
}
