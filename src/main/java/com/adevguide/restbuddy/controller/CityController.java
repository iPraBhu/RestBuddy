package com.adevguide.restbuddy.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adevguide.restbuddy.dao.CityJpaRepository;
import com.adevguide.restbuddy.entity.CityEntity;
import com.adevguide.restbuddy.exception.CityNotFoundException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author PraBhu
 *
 */
@Slf4j
@RestController
@RequestMapping("city")
public class CityController {

    private CityJpaRepository cityRepository;

    public CityController(CityJpaRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @GetMapping("/allcities")
    public ResponseEntity<List<CityEntity>> getAllCities() {
        List<CityEntity> cities = cityRepository.findAll();
        if (!cities.isEmpty()) {
            return new ResponseEntity<>(cities, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(cities, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityEntity> getCityById(@PathVariable
    Long id) {
        return cityRepository.findById(id).map(city -> new ResponseEntity<>(city, HttpStatus.OK))
                .orElseThrow(() -> new CityNotFoundException(id));
    }

    @PostMapping("/addcity")
    public ResponseEntity<CityEntity> addNewCity(@RequestBody
    CityEntity city) {
        log.info("Adding New City::" + city);
        return new ResponseEntity<>(cityRepository.save(city), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCity(@PathVariable
    Long id) {
        try {
            log.info("Deleting City with ID:" + id);
            cityRepository.deleteById(id);
            return new ResponseEntity<>("City successfully deleted with ID:" + id, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>("City not found with ID:" + id, HttpStatus.NO_CONTENT);
        }

    }

    @PatchMapping("/{id}")
    public ResponseEntity<CityEntity> updateCity(@RequestBody
    CityEntity city, @PathVariable
    Long id) {
        return cityRepository.findById(id).map(updatedCity -> {
            updatedCity.setDensity(city.getDensity());
            updatedCity.setPopulation(city.getPopulation());
            return new ResponseEntity<>(cityRepository.save(updatedCity), HttpStatus.ACCEPTED);
        }).orElseGet(() -> {
            city.setId(id);
            return new ResponseEntity<>(cityRepository.save(city), HttpStatus.CREATED);
        });
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityEntity> updateCompleteCity(@RequestBody
    CityEntity city, @PathVariable
    Long id) {
        city.setId(id);
        return new ResponseEntity<>(cityRepository.save(city), HttpStatus.ACCEPTED);
    }

    @GetMapping("/cityname/{cityname}")
    public ResponseEntity<List<CityEntity>> getCityByCityname(@PathVariable
    String cityname) {
        List<CityEntity> cities = cityRepository.findByCityname(cityname);
        if (!cities.isEmpty()) {
            return new ResponseEntity<>(cities, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(cities, HttpStatus.NO_CONTENT);
        }

    }

    @GetMapping("/population/{population}")
    public ResponseEntity<List<CityEntity>> findByPopulationGreaterThan(@PathVariable
    Long population) {
        List<CityEntity> cities = cityRepository.findByPopulationGreaterThan(population);;
        if (!cities.isEmpty()) {
            return new ResponseEntity<>(cities, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(cities, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/zipcode/{zipcode}")
    public ResponseEntity<List<CityEntity>> findByZipcodeLike(@PathVariable
    String zipcode) {
        List<CityEntity> cities = cityRepository.findByZipcodeLike("%" + zipcode + "%");;
        if (!cities.isEmpty()) {
            return new ResponseEntity<>(cities, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(cities, HttpStatus.NO_CONTENT);
        }
    }

}
