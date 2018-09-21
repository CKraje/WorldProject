package it.objectmethod.world.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.objectmethod.world.config.ConnectionFactoryContext;
import it.objectmethod.world.dao.CityDao;
import it.objectmethod.world.domain.City;

public class CityDaoImpl implements CityDao {

	@Override
	public List<City> getAllCities() {
		Connection conn = ConnectionFactoryContext.getConnection();
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
		Connection conn = ConnectionFactoryContext.getConnection();
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
	public int deleteCity(int id) { 
		Connection conn = ConnectionFactoryContext.getConnection();
		PreparedStatement stmt = null;
		int rows=0;
		try {
			String sql="DELETE  FROM city  WHERE ID=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rows=stmt.executeUpdate();
			stmt.close();
			conn.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public List<City> getCitiesByName(String name) { 
		Connection conn = ConnectionFactoryContext.getConnection();
		PreparedStatement stmt = null;
		List<City> list = new ArrayList<City>(0);
		try {
			String sql="SELECT Name,Population,ID,CountryCode,District FROM city ci WHERE Name LIKE ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,name + "%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				City city = new City();
				String nameFromRs= rs.getString("ci.Name"); 
				int population = rs.getInt("ci.Population");
				int id = rs.getInt("ci.ID");
				String code = rs.getString("ci.CountryCode");
				String district = rs.getString("ci.District");
				city.setName(nameFromRs);
				city.setPopulation(population);
				city.setId(id);
				city.setDistrict(district);
				city.setCode(code);
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
	public int createCity(City city) {  
		Connection conn = ConnectionFactoryContext.getConnection();
		PreparedStatement stmt = null;
		int rows=0;
		try {
			String sql="INSERT INTO city (Name,CountryCode,Population,District)VALUES(?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, city.getName());
			stmt.setString(2, city.getCode());
			stmt.setInt(3, city.getPopulation());
			stmt.setString(4, city.getDistrict());
			//stmt.execute();
			rows=stmt.executeUpdate();
			//City city=new City();
			stmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public City getCityById(int id) {
		Connection conn = ConnectionFactoryContext.getConnection();
		PreparedStatement stmt = null;
		City city = new City();
		try {
			String sql="SELECT * FROM city ci WHERE ID=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				String nameFromRs= rs.getString("ci.Name");
				String code=rs.getString("ci.CountryCode");
				String district = rs.getString("ci.District");
				int population = rs.getInt("ci.Population");
				int idFromRs = rs.getInt("ci.ID");
				city.setName(nameFromRs);
				city.setPopulation(population);
				city.setId(idFromRs);
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
	public int updateCity(City city) { 
		Connection conn = ConnectionFactoryContext.getConnection();
		PreparedStatement stmt = null;
		int rows=0;
		try {
			String sql="UPDATE city ci SET Name=?,CountryCode=?,District=?,Population=? WHERE ID=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, city.getName());
			stmt.setString(2, city.getCode());
			stmt.setString(3, city.getDistrict());
			stmt.setInt(4, city.getPopulation());
			stmt.setInt(5, city.getId());
			rows=stmt.executeUpdate();
			stmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rows;
	}
}

