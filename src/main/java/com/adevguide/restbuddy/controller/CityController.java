package com.adevguide.restbuddy.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adevguide.restbuddy.entity.CityEntity;
import com.adevguide.restbuddy.service.CityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * @author PraBhu
 *
 */
@Slf4j
@RestController
@RequestMapping("/city")
@Api(value = "CityApis")
public class CityController {

	@Autowired
	CityService service;

	@GetMapping("/allcities")
	@ApiOperation(value = "get all available cities in JSON array.", produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list") })
	public ResponseEntity<List<CityEntity>> getAllCities() {
		List<CityEntity> cities = service.getAllCities();
		if (!cities.isEmpty()) {
			return new ResponseEntity<>(cities, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(cities, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<CityEntity> getCityById(@PathVariable Long id) {
		return new ResponseEntity<>(service.findCityById(id), HttpStatus.OK);
	}

	@PostMapping("/addcity")
	@ApiOperation(value = "Add new city in the database.")
	public ResponseEntity<CityEntity> addNewCity(@RequestBody @Valid CityEntity city) {
		log.info("Adding New City::" + city);
		return new ResponseEntity<>(service.addNewCity(city), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete particular city by ID.")
	public ResponseEntity<String> deleteCity(@PathVariable Long id) {
		try {
			log.info("Deleting City with ID:" + id);
			service.deleteByCityId(id);
			return new ResponseEntity<>("City successfully deleted with ID:" + id, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			log.error(e.toString());
			return new ResponseEntity<>("City not found with ID:" + id, HttpStatus.NO_CONTENT);
		}

	}

	@PatchMapping("/{id}")
	@ApiOperation(value = "Update particular city's some information fields by ID")
	public ResponseEntity<CityEntity> updateCity(@RequestBody CityEntity city, @PathVariable Long id) {
		return new ResponseEntity<>(service.updateCity(id, city), HttpStatus.ACCEPTED);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Update particular city's complete information by ID")
	public ResponseEntity<CityEntity> updateCompleteCity(@RequestBody CityEntity city, @PathVariable Long id) {
		city.setId(id);
		return new ResponseEntity<>(service.updateCompleteCity(city), HttpStatus.ACCEPTED);
	}

	@GetMapping("/cityname/{cityname}")
	@ApiOperation(value = "Get a particular city by cityname")
	public ResponseEntity<List<CityEntity>> getCityByCityname(@PathVariable String cityname) {
		List<CityEntity> cities = service.findByCityname(cityname);
		if (!cities.isEmpty()) {
			return new ResponseEntity<>(cities, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(cities, HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping("/population/{population}")
	@ApiOperation(value = "Get list of cities whose population is greater than passed value.")
	public ResponseEntity<List<CityEntity>> findByPopulationGreaterThan(@PathVariable Long population) {
		List<CityEntity> cities = service.findByPopulationGreaterThan(population);
		;
		if (!cities.isEmpty()) {
			return new ResponseEntity<>(cities, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(cities, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/zipcode/{zipcode}")
	@ApiOperation(value = "Get city/cities whose zipcode consists passed value.")
	public ResponseEntity<List<CityEntity>> findByZipcodeLike(@PathVariable String zipcode) {
		List<CityEntity> cities = service.findByZipcodeLike(zipcode);
		;
		if (!cities.isEmpty()) {
			return new ResponseEntity<>(cities, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(cities, HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/login")
	@ApiOperation(value = "Get city/cities whose zipcode consists passed value.")
	public ResponseEntity<String> doAuthentication(@RequestHeader("email") String email,
			@RequestHeader("password") String password) {

		return service.checkAuthDetails(email, password);

		
	}

}
