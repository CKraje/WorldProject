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
import org.springframework.stereotype.Component;

import it.objectmethod.world.config.ConnectionFactoryContext;
import it.objectmethod.world.dao.CountryDao;
import it.objectmethod.world.domain.Country;
import it.objectmethod.world.domain.mapper.CountryMapper;

@Component
public class CountryDaoImpl implements CountryDao { 

	JdbcTemplate jdbcTemplateCountryDao;
	private DataSource dataSource;
	private final String SQL_GET_ALL_CONTINENTS="SELECT DISTINCT Continent FROM country co";
	private final String SQL_GET_COUNTRIES_BY_CONTINENT="SELECT * FROM country WHERE Continent=?";
	private final String SQL_GET_ALL_COUNTRIES = "SELECT DISTINCT Name,Code,Continent"
			+ ",Population FROM country co ";
	private final String SQL_GET_COUNTRY_BY_CODE= "SELECT * FROM country co WHERE Code=?";

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateCountryDao = new JdbcTemplate(dataSource);
	}

	public List<Country> getAllCountries() {
		return jdbcTemplateCountryDao.query(SQL_GET_ALL_COUNTRIES, new CountryMapper());
	}
	public List<String> getAllContinents() {
		List<String> listContinents=jdbcTemplateCountryDao.queryForList(SQL_GET_ALL_CONTINENTS, String.class);
		return listContinents;
	}
	public List<Country> getCountriesByContinent(String continent) {
		return jdbcTemplateCountryDao.query(SQL_GET_COUNTRIES_BY_CONTINENT,
				new Object[] { continent }, new CountryMapper());
	}
	public Country getCountryByCode(String code) {
		return jdbcTemplateCountryDao.queryForObject(SQL_GET_COUNTRY_BY_CODE,
				new Object[] { code }, new CountryMapper());
	}
}
