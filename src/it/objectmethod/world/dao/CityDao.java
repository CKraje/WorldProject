package it.objectmethod.world.dao;

import java.util.List;

import it.objectmethod.world.domain.City;

public interface CityDao {
	public List<City> getAllCities();
	public List<City>getCitiesByCountry(String country);
}
