package com.adevguide.restbuddy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author PraBhu
 *
 */
@Data
@Entity
@Table(name="employee")
public class EmployeeEntity {

    private @Id
    @GeneratedValue
    Long id;
    private String name;
    private String role;

    public EmployeeEntity(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public EmployeeEntity() {

    }

}
