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
@WebServlet("/cities/delete") //TODO cambiare con ad esempio /cities/delete
public class DeleteCityServlet extends  HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doGet (HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException  {
		CityDao cityDao = new CityDaoImpl();
		HttpSession session = req.getSession();
		int id = Integer.parseInt(req.getParameter("idCity"));
		String code = (String) req.getParameter("countryCode"); 
		int deletedCitiesRows = cityDao.deleteCity(id);
		String resultMsg = "Errore nell'eliminare la city.";
		if(deletedCitiesRows > 0) {
			resultMsg = "City eliminata con successo!";
		}
		req.setAttribute("delete_msg", resultMsg);
		req.getRequestDispatcher("/cities").forward(req, resp);
	}
}

