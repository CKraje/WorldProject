package it.objectmethod.world.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.objectmethod.world.dao.impl.CountryDaoImpl;
import it.objectmethod.world.domain.Country;

@Controller
public class CountryController {
	
	@Autowired
	CountryDaoImpl countryDao;

	@RequestMapping("/continents")
	public String continentList(ModelMap map, HttpServletRequest req) {
		List<String> list = countryDao.getAllContinents();
		map.addAttribute("listaContinenti", list);
		HttpSession session = req.getSession();
		if(!session.isNew()) {
			session.invalidate();
		}
		return "Continents";
	}
	
	@RequestMapping("/countries")
	public String countriesListByContinent(@RequestParam("continent") String continent, 
			ModelMap map,Model model, HttpServletRequest req) {
		List<Country> list = countryDao.getCountriesByContinent(continent);
		map.addAttribute("countries", list);
		HttpSession session = req.getSession();
		session.removeAttribute("country_code");
		session.setAttribute("continent", continent); 
		return "Countries";
	}

}
