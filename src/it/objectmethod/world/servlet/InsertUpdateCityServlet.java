package it.objectmethod.world.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.objectmethod.world.dao.CityDao;
import it.objectmethod.world.dao.impl.CityDaoImpl;

public class InsertUpdateCityServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet (HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
		String name= req.getParameter("city_name");
		HttpSession session = req.getSession();
		String district = req.getParameter("district_name");
		int population= Integer.parseInt(req.getParameter("population"));
		String countryCode= req.getParameter("theCountries");
		int check= (int) session.getAttribute("cityId");		
		CityDao cityDao=new CityDaoImpl();
		if(check ==0) {										// id dice se modificare oppure creare
			// creo citta
			cityDao.createCity(name, countryCode, population, district);
			int idLastCity= cityDao.getIdFromLastCity();
			req.setAttribute("definitionCreate", " Hai creato una città con ID:");
			req.setAttribute("definitionUpdate", "");
			req.setAttribute("identificativo", idLastCity);
		}
		else if(check>=0){
			// faccio update città esistente
			cityDao.updateCity(check, name, district, population, countryCode);
			req.setAttribute("definitionUpdate", " Hai modificato una città con ID:");
			req.setAttribute("definitionCreate", "");
			req.setAttribute("identificativo",check );
		}
		req.setAttribute("message", "Operazione avvenuta con succeso:");
		req.getRequestDispatcher("create-modify-list.jsp").forward(req, resp);
	}
}
