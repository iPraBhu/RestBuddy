package com.adevguide.restbuddy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

import lombok.Data;

/**
 * @author PraBhu
 *
 */

@Entity(name = "CITY")
@Data
@TableGenerator(name = "citySeq", initialValue = 10000, allocationSize = 1)
public class CityEntity {

    private @Id
    @GeneratedValue(generator = "citySeq", strategy = GenerationType.TABLE)
    Long id;
    private String cityname;
    private String statecode;
    private String statename;
    private String latitude;
    private String longitude;
    private Long population;
    private Long density;
    private String zipcode;

    public CityEntity() {

    }

    public CityEntity(String cityname, String statecode, String statename, String latitude, String longitude,
            Long population, Long density, String zipcode) {
        this.cityname = cityname;
        this.statecode = statecode;
        this.statename = statename;
        this.latitude = latitude;
        this.longitude = longitude;
        this.population = population;
        this.density = density;
        this.zipcode = zipcode;
    }

}
