/**
 * 
 */
package com.adevguide.restbuddy.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author PraBhu
 *
 */

@FeignClient(name = "selfFeign", url = "https://restbuddy.herokuapp.com")
public interface SelfFeignService {

	@GetMapping("/")
	public void selfHit();

}
