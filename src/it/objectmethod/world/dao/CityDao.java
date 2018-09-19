package it.objectmethod.world.dao;

import java.util.List;

import it.objectmethod.world.domain.City;

public interface CityDao {
	public List<City> getAllCities();
	public List<City>getCitiesByCountry(String country);
	public void deleteCity(int id); //TODO ritornare sempre un int 
	public List<City> getCitiesByName(String name); 
	public void createCity(String name,String code, int pop,String district); //TODO ritornare int
	public City getCityById(int id); 
	public void updateCity(int id,String name,String district,int population,String code); //TODO ritornare int
	public int getIdFromLastCity(); //TODO rimuovere plz
}
