/**
 * 
 */
package com.syed.rest.microservices.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syed.rest.microservices.limitsservice.bean.LimitConfiguration;

/**
 * @author sammar
 *
 */
@RestController
public class LimitsConfigurationController {

	@Autowired
	private Configuration config;
	
	@GetMapping("/limits")
	public LimitConfiguration retrieveLimitsFromConfigurations() {
		return new LimitConfiguration(config.getMaximum(), config.getMinimum());
	}
}
