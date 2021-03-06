package it.objectmethod.world.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.objectmethod.world.dao.CountryDao;
import it.objectmethod.world.dao.impl.CountryDaoImpl;
@WebServlet("/continents")
public class ContinentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet (HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException  {
		CountryDao countryDao = new CountryDaoImpl();
		HttpSession session = req.getSession();
		if(!session.isNew()) {
			session.invalidate();
		}
		List<String> list = countryDao.getAllContinents();
		req.setAttribute("listaContinenti",list); 
		req.getRequestDispatcher("Continents.jsp").forward(req, resp);
	}
}
