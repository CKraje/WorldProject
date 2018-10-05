package it.objectmethod.world.dao;

import java.util.List;

import it.objectmethod.world.domain.City;

public interface CityDao {
	public List<City>getCitiesByCountry(String country);
	public int deleteCity(int id);  
	public List<City> getCitiesByName(String name); 
	public int createCity(City city); 
	public City getCityById(int id); 
	public int updateCity(City city); 
}
