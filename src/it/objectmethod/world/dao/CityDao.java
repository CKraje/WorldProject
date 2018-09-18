package it.objectmethod.world.dao;

import java.util.List;

import it.objectmethod.world.domain.City;

public interface CityDao {
	public List<City> getAllCities();
	public List<City>getCitiesByCountry(String country);
	public void deleteCity(int id);
	public List<City> getCitiesByName(String name);
	public void createCity(String name,String code, int pop,String district);
	public City getCityById(int id);
	public void updateCity(int id,String name,String district,int population,String code);
	public int getIdFromLastCity();
}
