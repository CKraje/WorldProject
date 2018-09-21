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
import it.objectmethod.world.dao.CountryDao;
import it.objectmethod.world.dao.impl.CityDaoImpl;
import it.objectmethod.world.dao.impl.CountryDaoImpl;
import it.objectmethod.world.domain.City;
import it.objectmethod.world.domain.Country;
@WebServlet("/cities/create-modify")
public class ModifyCreateCityServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected void doGet (HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException  {
		HttpSession session = req.getSession();
		int id=Integer.parseInt(req.getParameter("idCity"));	
		String code= (String)session.getAttribute("country_code");
		CityDao cityDao=new CityDaoImpl();
		CountryDao countryDao=new CountryDaoImpl();
		List<Country> listCountries = countryDao.getAllCountries();
		Country country = countryDao.getCountryByCode(code); 
		City city = null;
		if(id!=0) {
			city= cityDao.getCityById(id);
			session.setAttribute("city", city);
		}
		else {
			city = new City();
			city.setId(0);
		}
		req.setAttribute("city", city);
		req.setAttribute("paese", country.getCode()); 
		req.setAttribute("lista_Countriees", listCountries);
		req.getRequestDispatcher("../create-modify-city.jsp").forward(req, resp);
	}
}
