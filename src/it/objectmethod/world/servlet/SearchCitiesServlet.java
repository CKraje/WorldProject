package it.objectmethod.world.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.objectmethod.world.dao.CityDao;
import it.objectmethod.world.dao.impl.CityDaoImpl;
import it.objectmethod.world.domain.City;
@WebServlet("/cities/search")
public class SearchCitiesServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected void doGet (HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException  {
		String name= req.getParameter("city_name");
		if(name!=null && !(name.equals(""))) {
			req.setAttribute("home_page", name);
		}
		CityDao cityDao = new CityDaoImpl();
		List<City> list = cityDao.getCitiesByName(name);
		req.setAttribute("lista_cities", list);
		req.getRequestDispatcher("../city-list.jsp").forward(req, resp);
	}
}
