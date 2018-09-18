package it.objectmethod.world.dao;

import java.util.List;

import it.objectmethod.world.domain.Country;

public interface CountryDao {
	
	public List<Country> getAllCountries();
	public List<String> getAllContinents();
	public List<Country> getCountriesByContinent(String continent);
	public Country getCountryByCode(String code);
}
