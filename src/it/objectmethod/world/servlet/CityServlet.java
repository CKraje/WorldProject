package it.objectmethod.world.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.objectmethod.world.dao.CityDao;
import it.objectmethod.world.dao.impl.CityDaoImpl;
import it.objectmethod.world.domain.City;
@WebServlet("/cities")
public class CityServlet extends  HttpServlet{
	private static final long serialVersionUID = 1L;
	protected void doGet (HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException  {
		HttpSession session = req.getSession();
		String countryCode= req.getParameter("country_code");
		CityDao cityDao = new CityDaoImpl();
		List<City> list = cityDao.getCitiesByCountry(countryCode);		
		req.setAttribute("lista_cities", list);
		session.setAttribute("countryCode", countryCode);
		req.getRequestDispatcher("city-list.jsp").forward(req, resp);
	}
}
