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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import dao.CampaignDao;
import data.CrunchifyGetPropertyValues;
import entities.Campaign;
import entities.FileBucker;
import utils.Checkrole;
import utils.FileValidator;

@Controller
@RequestMapping(value = { "/", })
public class CampaignController {
	CampaignDao loadCam = new CampaignDao();
	Checkrole check = new Checkrole();
	int page = 1;
	int recordsPerPage = 5;
	int noOfRecords = loadCam.getNoOfRecords();
	int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

	@RequestMapping(value = { "/get-campaign" })
	public ModelAndView printcampaign(HttpServletRequest request) {
		if (request.getParameter("page") != null) {
			page = Integer.valueOf(request.getParameter("page"));
		}
		ModelAndView mv = new ModelAndView("quanlicampaign");
		if (check.PrintRole().equals("ROLE_ADMIN")) {
			mv.addObject("listCam", loadCam.Print((page - 1) * recordsPerPage, recordsPerPage));
			mv.addObject("noOfPages", noOfPages);
			mv.addObject("currentPage", page);
		} else if (check.PrintRole().equals("ROLE_USER")) {
			mv.addObject("listCamRe",
					loadCam.PrintCouponRes((page - 1) * recordsPerPage, recordsPerPage, check.PrintUser()));
			mv.addObject("noOfPages", noOfPages);
			mv.addObject("currentPage", page);
		}

		return mv;
	}

	@RequestMapping(value = { "/get-new-campaign" })
	public ModelAndView getformaddnewcampaign() {
		ModelAndView mv = new ModelAndView("taomoicampaign");
		return mv;
	}

	@RequestMapping(value = { "/get-campaign-to- edit" })
	public ModelAndView getformeditcampaign(String id) {
		ModelAndView mv = new ModelAndView("editcampaign");
		mv.addObject("Campaign", loadCam.getcampaign(id));
		return mv;
	}

	@RequestMapping(value = { "/change-resname-cam" })
	public @ResponseBody String changeresname() {
		String role = check.PrintRole();
		return role;
	}

	@RequestMapping(value = { "/search-status-cam" })
	public ModelAndView Searchfromstatuscam(int status) {
		ModelAndView mv = new ModelAndView("quanlicampaign");
		if (check.PrintRole().equals("ROLE_ADMIN")) {
			mv.addObject("listCam", loadCam.SearchfromstatusAD(status));
		} else if (check.PrintRole().equals("ROLE_USER")) {
			mv.addObject("listCamRe", loadCam.SearchfromstatusRe(status, check.PrintUser()));
		}
		return mv;
	}

	@RequestMapping(value = { "/check-status-cam" })
	public @ResponseBody boolean checkcam(String id) {
		return loadCam.checkstatuscampaign(id);
	}

	@RequestMapping(value = { "/delete-campaign" })
	public ModelAndView deletecampaign(String id) {
		ModelAndView mv = new ModelAndView("quanlicampaign");
		loadCam.DeleteCampaign(id);
		mv.addObject("listCamRe",
				loadCam.PrintCouponRes((page - 1) * recordsPerPage, recordsPerPage, check.PrintUser()));
		mv.addObject("noOfPages", noOfPages);
		mv.addObject("currentPage", page);
		return mv;
	}

	@RequestMapping(value = { "/update-campaign" })
	public ModelAndView updatecampaign(Campaign campaign) {
		ModelAndView mv = new ModelAndView("quanlicampaign");
		loadCam.UpdateCampaign(campaign);
		mv.addObject("listCamRe",
				loadCam.PrintCouponRes((page - 1) * recordsPerPage, recordsPerPage, check.PrintUser()));
		mv.addObject("noOfPages", noOfPages);
		mv.addObject("currentPage", page);
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

	@RequestMapping(value = "/add-campaign")
	public @ResponseBody ModelAndView singleSave(@RequestPart("file") MultipartFile file,
			MultipartHttpServletRequest request) throws IOException {
		String path = new CrunchifyGetPropertyValues().getPropValues("upload.image.path.save");
		String fileName = null;
		String des = request.getParameter("campaign_name");
		String value = request.getParameter("campaign_value");
		String start = request.getParameter("campaign_start");
		String end = request.getParameter("campaign_end");
		ModelAndView mv = new ModelAndView("redirect:welcome?ref=quanlicampaign");

		try {
			// get file attach
			fileName = "upload/campaign/" + file.getOriginalFilename();
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

			Campaign cam = new Campaign();
			cam.setCampaign_image(fileName);
			cam.setCampaign_name(des);
			cam.setCampaign_value(Integer.parseInt(value));
			cam.setCampaign_start(start);
			cam.setCampaign_end(end);

			// insert database
			loadCam.insertCampaign(cam	, check.PrintUser());
			mv.addObject("listCamRe",
					loadCam.PrintCouponRes((page - 1) * recordsPerPage, recordsPerPage, check.PrintUser()));
			mv.addObject("noOfPages", noOfPages);
			mv.addObject("currentPage", page);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	@RequestMapping(value = "/edit-campaign")
	public @ResponseBody ModelAndView EditCampaign(@RequestPart("file") MultipartFile file,
			MultipartHttpServletRequest request) throws IOException {
		String path = new CrunchifyGetPropertyValues().getPropValues("upload.image.path.save");
		String fileName = null;
		String id = request.getParameter("campaign_id");
		String des = request.getParameter("campaign_name");
		String value = request.getParameter("campaign_value");
		String start = request.getParameter("campaign_start");
		String end = request.getParameter("campaign_end");
		ModelAndView mv = new ModelAndView("redirect:welcome?ref=quanlicampaign");

		try {
			// get file attach
			fileName = "upload/campaign/" + file.getOriginalFilename();
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

			Campaign cam = new Campaign();
			cam.setCampaign_id(id);
			cam.setCampaign_name(des);
			cam.setCampaign_value(Integer.parseInt(value));
			cam.setCampaign_start(start);
			cam.setCampaign_end(end);
			cam.setCampaign_image(fileName);

			// insert database
			loadCam.UpdateCampaign(cam);
			mv.addObject("listCamRe",
					loadCam.PrintCouponRes((page - 1) * recordsPerPage, recordsPerPage, check.PrintUser()));
			mv.addObject("noOfPages", noOfPages);
			mv.addObject("currentPage", page);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
}
