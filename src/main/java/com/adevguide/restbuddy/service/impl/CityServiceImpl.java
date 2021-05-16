/**
 * 
 */
package com.adevguide.restbuddy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.adevguide.restbuddy.dao.CityJpaRepository;
import com.adevguide.restbuddy.entity.CityEntity;
import com.adevguide.restbuddy.exception.CityNotFoundException;
import com.adevguide.restbuddy.service.CityService;

/**
 * @author PraBhu
 *
 */
@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityJpaRepository cityRepository;

	@Override
	public List<CityEntity> getAllCities() {
		return cityRepository.findAll();
	}

	@Override
	public CityEntity findCityById(Long id) {
		return cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(id));
	}

	@Override
	public CityEntity addNewCity(CityEntity city) {
		return cityRepository.save(city);
	}

	@Override
	public void deleteByCityId(Long id) {
		cityRepository.deleteById(id);

	}

	@Override
	public CityEntity updateCity(Long id, CityEntity city) {

		return cityRepository.findById(id).map(updatedCity -> {
			if (null != city.getDensity()) {
				updatedCity.setDensity(city.getDensity());
			}
			if (null != city.getPopulation()) {
				updatedCity.setPopulation(city.getPopulation());
			}
			if (null != city.getCityname()) {
				updatedCity.setCityname(city.getCityname());
			}
			if (null != city.getLatitude()) {
				updatedCity.setLatitude(city.getLatitude());
			}
			if (null != city.getLongitude()) {
				updatedCity.setLongitude(city.getLongitude());
			}
			if (null != city.getStatecode()) {
				updatedCity.setStatecode(city.getStatecode());
			}
			if (null != city.getStatename()) {
				updatedCity.setStatename(city.getStatename());
			}
			if (null != city.getZipcode()) {
				updatedCity.setZipcode(city.getZipcode());
			}
			return cityRepository.save(updatedCity);
		}).orElseGet(() -> {
			city.setId(id);
			return cityRepository.save(city);
		});
	}

	@Override
	public CityEntity updateCompleteCity(CityEntity city) {
		return cityRepository.save(city);
	}

	@Override
	public List<CityEntity> findByCityname(String cityname) {
		return cityRepository.findByCityname(cityname);
	}

	@Override
	public List<CityEntity> findByPopulationGreaterThan(Long population) {
		return cityRepository.findByPopulationGreaterThan(population);
	}

	@Override
	public List<CityEntity> findByZipcodeLike(String zipcode) {
		return cityRepository.findByZipcodeLike("%" + zipcode + "%");
	}

	@Override
	public ResponseEntity<String> checkAuthDetails(String email, String password) {
		if (email.equals("prabhu.sites@gmail.com") && password.equals("123")) {
			return new ResponseEntity<>("Login Successful", HttpStatus.OK);
		} else if (email.equals("prabhu@gmail.com") && password.equals("123")) {
			return new ResponseEntity<>("Login Successful, Role Unauthorized.", HttpStatus.FORBIDDEN);
		} else {
			return new ResponseEntity<>("Login Unsuccessful, Please check your credentials.", HttpStatus.UNAUTHORIZED);
		}
	}

}
