package com.adevguide.restbuddy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adevguide.restbuddy.entity.EmployeeEntity;

/**
 * @author PraBhu
 *
 */
public interface EmployeeJpaRepository extends JpaRepository<EmployeeEntity, Long> {

}
