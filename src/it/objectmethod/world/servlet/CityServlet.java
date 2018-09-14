package it.objectmethod.world.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.objectmethod.world.dao.CityDao;
import it.objectmethod.world.dao.impl.CityDaoImpl;
import it.objectmethod.world.domain.City;

public class CityServlet extends  HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doGet (HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException  {
		String countryCode= req.getParameter("country_code");
		CityDao cityDao = new CityDaoImpl();
		List<City> list = cityDao.getCitiesByCountry(countryCode);
		req.setAttribute("lista_cities", list);
		String x = (String) req.getAttribute("indietro");
		req.setAttribute("paesi_prima", x);
		req.getRequestDispatcher("city-list.jsp").forward(req, resp);
	}
}
