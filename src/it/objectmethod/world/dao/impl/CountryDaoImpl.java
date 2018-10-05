package it.objectmethod.world.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import it.objectmethod.world.dao.CountryDao;
import it.objectmethod.world.domain.Country;
import it.objectmethod.world.domain.mapper.CountryMapper;

public class CountryDaoImpl extends NamedParameterJdbcDaoSupport implements CountryDao { 

	private final String SQL_GET_ALL_CONTINENTS="SELECT DISTINCT Continent FROM country co";
	private final String SQL_GET_COUNTRIES_BY_CONTINENT="SELECT * FROM country WHERE Continent = :cont";
	private final String SQL_GET_ALL_COUNTRIES = "SELECT DISTINCT Name,Code,Continent"
			+ ",Population FROM country co ";
	private final String SQL_GET_COUNTRY_BY_CODE= "SELECT * FROM country co WHERE Code=?";

	public List<Country> getAllCountries() {
		return getJdbcTemplate().query(SQL_GET_ALL_COUNTRIES, new CountryMapper());
	}

	public List<String> getAllContinents() {
		List<String> listContinents=getJdbcTemplate().queryForList(SQL_GET_ALL_CONTINENTS, String.class);
		return listContinents;
	}

	public List<Country> getCountriesByContinent(String continent) {
		List<Country> ret = null;
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("cont", continent);
		BeanPropertyRowMapper<Country> rm = new BeanPropertyRowMapper<>(Country.class);
		ret = getNamedParameterJdbcTemplate().query(SQL_GET_COUNTRIES_BY_CONTINENT, map, rm);
		return ret;
	}

	public Country getCountryByCode(String code) {
		return getJdbcTemplate().queryForObject(SQL_GET_COUNTRY_BY_CODE,
				new Object[] { code }, new CountryMapper());
	}

}
