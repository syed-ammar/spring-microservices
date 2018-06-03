/**
 * 
 */
package com.syed.rest.webservices.restfulwebservices.filter;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

/**
 * @author sammar
 *
 */
@RestController
public class FilterController {

	@GetMapping("/filterData")
	public MappingJacksonValue getFilterData() {
		FilterData filterData = new FilterData("value1","value2","value3");
		//Dynamic filtering
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("feild1","feild3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("FilterDataFilter", filter );
		MappingJacksonValue mapping = new MappingJacksonValue(filterData);
		mapping.setFilters(filters);
		return mapping;
	}
}
