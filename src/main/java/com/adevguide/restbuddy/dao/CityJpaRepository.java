package com.adevguide.restbuddy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adevguide.restbuddy.entity.CityEntity;

/**
 * @author PraBhu
 *
 */
public interface CityJpaRepository extends JpaRepository<CityEntity, Long>{
    
    
    public List<CityEntity> findByCityname(String cityname); 
    
    public List<CityEntity> findByPopulationGreaterThan(Long population);
    
    public List<CityEntity> findByZipcodeLike(String zipcode);
    
    public CityEntity findByCitynameAndStatecode(String cityname, String statecode);

}
