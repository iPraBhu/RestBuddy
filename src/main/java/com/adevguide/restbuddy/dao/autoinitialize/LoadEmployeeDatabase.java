package com.adevguide.restbuddy.dao.autoinitialize;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.adevguide.restbuddy.dao.EmployeeJpaRepository;
import com.adevguide.restbuddy.entity.EmployeeEntity;

import lombok.extern.slf4j.Slf4j;

/**
 * @author PraBhu
 *
 */

@Configuration
@Slf4j
public class LoadEmployeeDatabase {
    
    
    @Bean
    public CommandLineRunner initDatabase(EmployeeJpaRepository repository) {
        
        return args->{
            log.info("Adding "+repository.save(new EmployeeEntity("Pratik", "Manager")));
            log.info("Adding "+repository.save(new EmployeeEntity("Prabhu", "Sr.Manager")));
            
            
        };
    }

}
