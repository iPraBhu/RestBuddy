/**
 * 
 */
package com.adevguide.restbuddy.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.adevguide.restbuddy.entity.CityEntity;

/**
 * @author PraBhu
 *
 */
public interface CityService {

	List<CityEntity> getAllCities();

	CityEntity findCityById(Long id);

	CityEntity addNewCity(CityEntity city);

	void deleteByCityId(Long id);

	CityEntity updateCity(Long id, CityEntity city);

	CityEntity updateCompleteCity(CityEntity city);

	List<CityEntity> findByCityname(String cityname);

	List<CityEntity> findByPopulationGreaterThan(Long population);

	List<CityEntity> findByZipcodeLike(String zipcode);

	ResponseEntity<String> checkAuthDetails(String email, String password);

}
