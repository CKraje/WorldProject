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
@WebServlet("/delete") //TODO cambiare con ad esempio /cities/delete
public class DeleteCityServlet extends  HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doGet (HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException  {
		CityDao cityDao = new CityDaoImpl();
		int id = Integer.parseInt(req.getParameter("idCity"));
		String code = (String) req.getParameter("countryCode"); 
		cityDao.deleteCity(id);
		//TODO aggiungere attribute alla request con messaggio esito in base al numero di righe (>0)
//		List<City> listCitiesByCountryCode = cityDao.getCitiesByCountry(code);
//		req.setAttribute("lista_cities", listCitiesByCountryCode);
		req.getRequestDispatcher("/cities").forward(req, resp);
	}
}

