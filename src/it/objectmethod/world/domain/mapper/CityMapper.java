package it.objectmethod.world.domain.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.objectmethod.world.domain.City;
import it.objectmethod.world.domain.Country;

public class CityMapper implements RowMapper<City>{

	@Override
	public City mapRow(ResultSet rs, int rowNum) throws SQLException {
		City city = new City();
		city.setId(rs.getInt("ID"));
		city.setName(rs.getString("Name"));
		city.setCode(rs.getString("CountryCode"));
		city.setDistrict(rs.getString("District"));
		city.setPopulation(rs.getInt("Population"));
		return city;
	}

}
