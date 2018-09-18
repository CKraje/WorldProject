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

public class ModifyCreateCityServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet (HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException  {
		int id=Integer.parseInt(req.getParameter("idCity"));	// id dice se precompilare oppure no
		String code= req.getParameter("countryCode");
		CityDao cityDao=new CityDaoImpl();
		CountryDao countryDao=new CountryDaoImpl();
		List<Country> listCountries = countryDao.getAllCountries();
		int zero = 0;
		HttpSession session = req.getSession();
		Country country = countryDao.getCountryByCode(code);
		if(id!=0) {
			City city= cityDao.getCityById(id);
			req.setAttribute("district", city.getDistrict());
			req.setAttribute("population", city.getPopulation());
			req.setAttribute("name", city.getName());
			session.setAttribute("cityId", id);
		}
		req.setAttribute("paese", country.getName());
		session.setAttribute("cityId", zero);
		req.setAttribute("lista_Countriees", listCountries);
		req.getRequestDispatcher("create-modify-list.jsp").forward(req, resp);
	}
}
