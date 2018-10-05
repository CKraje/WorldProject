package it.objectmethod.world.domain.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.objectmethod.world.domain.Country;

public class CountryMapper implements RowMapper<Country>{

	@Override
	public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
		Country country = new Country();
		country.setCode(rs.getString("Code"));
		country.setContinent(rs.getString("Continent"));
		country.setName(rs.getString("Name"));
		country.setPopulation(rs.getInt("Population"));
		return country;
	}

}
