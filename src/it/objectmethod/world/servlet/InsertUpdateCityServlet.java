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
import it.objectmethod.world.domain.City;
@WebServlet("/cities/insert_modify")
public class InsertUpdateCityServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected void doGet (HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
		String name= req.getParameter("city_name");
		String district = req.getParameter("district_name");
		int population= Integer.parseInt(req.getParameter("population"));
		String countryCode= req.getParameter("theCountries");
		int idCity = Integer.parseInt(req.getParameter("city_id"));
		CityDao cityDao=new CityDaoImpl();
		City city = new City();
		city.setName(name);
		city.setCode(countryCode);
		city.setPopulation(population);
		city.setDistrict(district);
		city.setId(idCity);
		if(city.getId() ==0	) {										
			int rowsCreate=cityDao.createCity( city); 
			req.setAttribute("message", "Creazione avvenuta con successo!");
		}
		else if(city.getId()!=0){
			city.setId(idCity);
			int rowsUpdate =cityDao.updateCity( city); 
			req.setAttribute("message", "Modifica avvenuta con successo!");
		}
		req.getRequestDispatcher("/create-modify-city.jsp").forward(req, resp);
	}
}
