package it.objectmethod.world.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.objectmethod.world.dao.CityDao;
import it.objectmethod.world.dao.CountryDao;
import it.objectmethod.world.dao.impl.CityDaoImpl;
import it.objectmethod.world.dao.impl.CountryDaoImpl;
import it.objectmethod.world.domain.City;
import it.objectmethod.world.domain.Country;

public class CreateCityServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	protected void doGet (HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException  {
		HttpSession session = req.getSession();
		String continente= req.getParameter("cont");
		String name= req.getParameter("city_name");		// Name
		String district = req.getParameter("district_name");
//		String popu= req.getParameter("population");
//		int population = Integer.parseInt(req.getParameter("population"));	// Population
		CountryDao countryDao = new CountryDaoImpl();
		CityDao cityDao = new CityDaoImpl();
		List<Country> list = countryDao.getAllCountries();
		req.setAttribute("lista_Countriees",list);
//		String countryCode = req.getParameter("theCountriees");		// CountryCode
//		List<City>listOfCities = cityDao.getCitiesByCountry(countryCode);
//		cityDao.createCity(name, countryCode, population, district);
//		List<City> listWithNewCity = cityDao.getCitiesByCountry(countryCode);
//		req.setAttribute("lista_citieees",listWithNewCity );
//		req.setAttribute("list_Of_cities",listOfCities);
		req.getRequestDispatcher("created-city-list.jsp").forward(req, resp);
	}
}
