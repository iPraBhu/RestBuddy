package com.adevguide.restbuddy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author PraBhu
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CityNotFoundException extends RuntimeException {
    
    
    private static final long serialVersionUID = 2838325588667943877L;

    public CityNotFoundException(Object id) {
        super("City not found with ID:"+id);
    }

}
