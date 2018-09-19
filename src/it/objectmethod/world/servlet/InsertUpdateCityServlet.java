package it.objectmethod.world.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.objectmethod.world.dao.CityDao;
import it.objectmethod.world.dao.impl.CityDaoImpl;
@WebServlet("/insertmodify")
public class InsertUpdateCityServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected void doGet (HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
		String name= req.getParameter("city_name");
		String district = req.getParameter("district_name");
		int population= Integer.parseInt(req.getParameter("population"));
		String countryCode= req.getParameter("theCountries");
		int idCity = Integer.parseInt(req.getParameter("city_iD"));
		CityDao cityDao=new CityDaoImpl();
		if(idCity ==0) {										
			cityDao.createCity(name, countryCode, population, district);
			int idLastCity= cityDao.getIdFromLastCity();
			req.setAttribute("definitionCreate", " Hai creato una città con ID:");
			req.setAttribute("definitionUpdate", "");
			req.setAttribute("identificativo", idLastCity);
		}
		else if(idCity>=0){
			cityDao.updateCity(idCity, name, district, population, countryCode);
			req.setAttribute("definitionUpdate", " Hai modificato una città con ID:");
			req.setAttribute("definitionCreate", "");
			req.setAttribute("identificativo",idCity );
		}
		req.setAttribute("message", "Operazione avvenuta con succeso:");
		req.getRequestDispatcher("create-modify-city.jsp").forward(req, resp);
	}
}
