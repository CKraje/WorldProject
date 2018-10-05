package it.objectmethod.world.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.objectmethod.world.dao.CityDao;
import it.objectmethod.world.dao.CountryDao;
import it.objectmethod.world.dao.impl.CityDaoImpl;
import it.objectmethod.world.dao.impl.CountryDaoImpl;
import it.objectmethod.world.domain.City;
import it.objectmethod.world.domain.Country;

@Controller
public class CityController {
	@Autowired
	CityDaoImpl cityDao; 
	@Autowired
	CountryDaoImpl countryDao;
	@RequestMapping("/cities/list")
	public String citiesList(@RequestParam("country_code") String countryCode, ModelMap map,
			HttpServletRequest req) {
		HttpSession session = req.getSession();
		List<City> list = cityDao.getCitiesByCountry(countryCode);	
		if(countryCode == null) {
			countryCode = (String) session.getAttribute("country_code");
		} else {
			session.setAttribute("country_code", countryCode); 
		}
		map.addAttribute("lista_cities", list);
		map.addAttribute("countryCode", countryCode);
		return "city-list";
	}
	@RequestMapping("cities/delete") 
	public String deleteCity(@RequestParam("idCity") int idCity,
			@RequestParam("countryCode") String code, ModelMap map) {
		int deletedCitiesRows = cityDao.deleteCity(idCity);
		String resultMsg = "Errore nell'eliminare la city.";
		if(deletedCitiesRows > 0) {
			resultMsg = "City eliminata con successo!";
		}
		map.addAttribute("delete_message", resultMsg);
		return "forward:/cities/list?country_code="+code;
	}
	@RequestMapping("cities/create-modify") 
	public String createOrModifyCity(@RequestParam("idCity") int idCity,
			@RequestParam("countryCode") String code, ModelMap map,Model model,
			HttpServletRequest req) {
		HttpSession session = req.getSession();
		List<Country> listCountries = countryDao.getAllCountries();
		Country country = countryDao.getCountryByCode(code);
		City city = null;
		if(idCity!=0) {
			city= cityDao.getCityById(idCity);
			session.setAttribute("city", city);
		}
		else {
			city = new City();
			city.setId(0);
		}
		map.addAttribute("city", city);
		map.addAttribute("paese", country.getCode());
		map.addAttribute("lista_Countriees", listCountries);
		return "create-modify-city";
	}
	@RequestMapping("/cities/insert_modify")
	public String insertOrUpdateCity (@RequestParam("city_name") String name,
			@RequestParam("district_name") String district,
			@RequestParam("population") int population,
			@RequestParam("theCountries") String countryCode, @RequestParam("city_id") int idCity,
			ModelMap map) {
		City city = new City();
		city.setName(name);
		city.setCode(countryCode);
		city.setPopulation(population);
		city.setDistrict(district);
		city.setId(idCity);
		if(city.getId() ==0	) {										
			int rowsCreate=cityDao.createCity( city); 		
			map.addAttribute("message", "Creazione avvenuta con successo!");
		}
		else if(city.getId()!=0){
			city.setId(idCity);
			int rowsUpdate =cityDao.updateCity( city); 
			map.addAttribute("message", "Modifica avvenuta con successo!");
		}
		return "create-modify-city";
	}
	@RequestMapping("/cities/search")
	public String searchCity(@RequestParam("city_Name") String name, ModelMap map,
			HttpServletRequest req) {
		List<City> list = cityDao.getCitiesByName(name);
		map.addAttribute("lista_cities", list);
		HttpSession session = req.getSession();
		session.removeAttribute("continent");
		return "city-list";
	}
}
