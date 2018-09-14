package it.objectmethod.world.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.objectmethod.world.config.ConnectionFactory;
import it.objectmethod.world.dao.CityDao;
import it.objectmethod.world.domain.City;

public class CityDaoImpl implements CityDao {

	@Override
	public List<City> getAllCities() {
		Connection conn = ConnectionFactory.getConnection();
		Statement stmt;

		List<City> list = new ArrayList<City>(0);
		try {
			String sql="SELECT Name,Population FROM city ci ORDER BY Name ASC";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				City city = new City();
				String name= rs.getString("ci.Name");
				int population = rs.getInt("ci.Population");
				city.setName(name);
				city.setPopulation(population);
				list.add(city);
			}
			rs.close();
			stmt.close();
			conn.close();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<City> getCitiesByCountry(String countryCode) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		List<City> list = new ArrayList<City>(0);
		try {
			String sql="SELECT Name,Population FROM city ci WHERE CountryCode=?"
					+ "ORDER BY Name ASC";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, countryCode);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				City city = new City();
				String name= rs.getString("ci.Name");
				int population = rs.getInt("ci.Population");
				city.setName(name);
				city.setPopulation(population);
				list.add(city);
			}
			rs.close();
			stmt.close();
			conn.close();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
