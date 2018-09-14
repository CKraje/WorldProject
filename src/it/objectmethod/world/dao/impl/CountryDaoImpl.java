package it.objectmethod.world.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.objectmethod.world.config.ConnectionFactory;
import it.objectmethod.world.dao.CountryDao;
import it.objectmethod.world.domain.Country;

public class CountryDaoImpl implements CountryDao {

	@Override
	public List<Country> getAllCountries() { 
		Connection conn = ConnectionFactory.getConnection();
		Statement stmt=null;
		List<Country> list = new ArrayList<Country>(0);
		try {
			stmt = conn.createStatement();
			String sql="SELECT DISTINCT Name,Code,Continent,Population FROM country co ";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				Country country = new Country();
				String code= rs.getString("co.Code");
				String name= rs.getString("co.Name");
				String continent= rs.getString("co.Continent");
				int population = rs.getInt("co.Population");
				country.setCode(code);
				country.setName(name);
				country.setContinent(continent);
				country.setPopulation(population);
				list.add(country);
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
	public List<String> getAllContinents() {
		Connection conn = ConnectionFactory.getConnection();
		Statement stmt;
		List<String> list = new ArrayList<String>(0);
		try {
			stmt = conn.createStatement();
			String sql="SELECT DISTINCT Continent FROM country co";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				String continent= rs.getString("co.Continent");
				list.add(continent);
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
	public List<Country> getCountriesByContinent(String continent) {

		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt=null;
		List<Country> list = new ArrayList<Country>(0);
		try {
			String sql="SELECT DISTINCT Name,Code,Population FROM country co WHERE Continent=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, continent);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Country country = new Country();
				String code= rs.getString("co.Code");
				String name= rs.getString("co.Name");
				int population = rs.getInt("co.Population");
				country.setCode(code);
				country.setName(name);
				country.setContinent(continent);
				country.setPopulation(population);
				list.add(country);
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
