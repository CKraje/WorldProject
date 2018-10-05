package it.objectmethod.world.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import it.objectmethod.world.config.ConnectionFactoryContext;
import it.objectmethod.world.dao.CityDao;
import it.objectmethod.world.domain.City;
import it.objectmethod.world.domain.mapper.CityMapper;

public class CityDaoImpl implements CityDao {//
	JdbcTemplate jdbcTemplateCitydao;
	private DataSource dataSource;
	private final String SQL_GET_CITIES_BY_COUNTRY="SELECT * FROM city ci WHERE CountryCode=?"
			+ "ORDER BY Name ASC";
	private final String SQL_DELETE_CITY = "DELETE  FROM city  WHERE ID=?";
	private final String SQL_GET_CITIES_BY_NAME="SELECT  * FROM city ci WHERE Name LIKE ?";
	private final String SQL_CREATE_CITY = "INSERT INTO city "
			+ "(Name,CountryCode,Population,District)VALUES(?,?,?,?)";
	private final String SQL_UPDATE_CITY= "UPDATE city ci SET Name=?,CountryCode=?,District=?"
			+ ",Population=? WHERE ID=?";
	private final String SQL_GET_CITIES_BY_ID="SELECT * FROM city ci WHERE ID=?";

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateCitydao = new JdbcTemplate(dataSource);
	}

	public List<City> getCitiesByCountry(String code) {
		return jdbcTemplateCitydao.query(SQL_GET_CITIES_BY_COUNTRY,new Object[] {code},
				new CityMapper());
	}
	public List<City> getCitiesByName(String name) {
		return jdbcTemplateCitydao.query(SQL_GET_CITIES_BY_NAME, 
				new Object[]{name+"%"},new CityMapper());
	}
	public int createCity(City city) {
		return jdbcTemplateCitydao.update(SQL_CREATE_CITY, city.getName(),city.getCode(),
				city.getPopulation(),city.getDistrict());
	}
	public City getCityById(int id) {
		return jdbcTemplateCitydao.queryForObject(SQL_GET_CITIES_BY_ID, 
				new Object[]{id},new CityMapper());
	}
	public int updateCity(City city) {
		return jdbcTemplateCitydao.update(SQL_UPDATE_CITY, city.getName(),city.getCode(),
				city.getDistrict(),city.getPopulation(),city.getId());
	}
	@Override
	public int deleteCity(int id) {
		return jdbcTemplateCitydao.update(SQL_DELETE_CITY, id);
	}
}