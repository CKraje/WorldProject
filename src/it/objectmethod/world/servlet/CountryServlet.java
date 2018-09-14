package it.objectmethod.world.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.objectmethod.world.dao.CountryDao;
import it.objectmethod.world.dao.impl.CountryDaoImpl;
import it.objectmethod.world.domain.Country;

public class CountryServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doGet (HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException  {
		HttpSession session = req.getSession();
		String continent = req.getParameter("continent");
		CountryDao countryDao = new CountryDaoImpl();
		List<Country> list = countryDao.getCountriesByContinent(continent);
		req.setAttribute("countries", list);
		session.setAttribute("indietro", continent);
		req.getRequestDispatcher("Countries.jsp").forward(req, resp);
	}
}
