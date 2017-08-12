package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dao.ReservationDao;
import entities.Reservation;

@Controller
@RequestMapping(value = { "/", })
public class ReservationController {
	
	ReservationDao loadReser = new ReservationDao();

	@RequestMapping(value = { "/get-reservations" })
	public ModelAndView printDatcho() {
		ModelAndView mv = new ModelAndView("quanlidatcho");
		mv.addObject("listReser", loadReser.Print());
		return mv;
	}
	@RequestMapping(value = { "/get-newReser" })
	public ModelAndView newPro() {
		ModelAndView mv = new ModelAndView("taomoidatcho");
		return mv;

	}

	@RequestMapping(value = { "/add-Reser" })
	public ModelAndView add(Reservation reser) {
		loadReser.insertdata(reser);
		ModelAndView model = new ModelAndView("taomoidatcho");
		return model;
	
	}
}