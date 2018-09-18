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
			String sql="SELECT Name,Population,ID FROM city ci WHERE CountryCode=?"
					+ "ORDER BY Name ASC";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, countryCode);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				City city = new City();
				String name= rs.getString("ci.Name");
				int population = rs.getInt("ci.Population");
				int id = rs.getInt("ci.ID");
				city.setName(name);
				city.setPopulation(population);
				city.setId(id);
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
	public void deleteCity(int id) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try {
			String sql="DELETE  FROM city  WHERE ID=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<City> getCitiesByName(String name) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		List<City> list = new ArrayList<City>(0);
		try {
			String sql="SELECT Name,Population,ID FROM city ci WHERE Name LIKE ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,name + "%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				City city = new City();
				String namE= rs.getString("ci.Name");
				int population = rs.getInt("ci.Population");
				int id = rs.getInt("ci.ID");
				city.setName(namE);
				city.setPopulation(population);
				city.setId(id);
				list.add(city);
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void createCity(String name, String code, int pop,String district) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try {
			String sql="INSERT INTO city (Name,CountryCode,Population,District)VALUES(?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, code);
			stmt.setInt(3, pop);
			stmt.setString(4, district);
			stmt.execute();
			//City city=new City();
			stmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public City getCityById(int id) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		City city = new City();
		try {
			String sql="SELECT * FROM city ci WHERE ID=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				String namE= rs.getString("ci.Name");
				String code=rs.getString("ci.CountryCode");
				String district = rs.getString("ci.District");
				int population = rs.getInt("ci.Population");
				int iD = rs.getInt("ci.ID");
				city.setName(namE);
				city.setPopulation(population);
				city.setId(iD);
				city.setCode(code);
				city.setDistrict(district);
			}
			stmt.close();
			conn.close();
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return city;
	}

	@Override
	public void updateCity(int id,String name,String district,int population,String code) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try {
			String sql="UPDATE city ci SET Name=?,CountryCode=?,District=?,Population=? WHERE ID=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, code);
			stmt.setString(3, district);
			stmt.setInt(4, population);
			stmt.setInt(5, id);
			stmt.executeUpdate();
			stmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getIdFromLastCity() {
		Connection conn = ConnectionFactory.getConnection();
		Statement stmt = null;
		int id=0;
		try {
			stmt = conn.createStatement();
			String sql="SELECT MAX(ID) AS Max FROM city";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				id = rs.getInt("Max");
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return id;
	}
}

