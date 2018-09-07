/**
 * 
 */
package com.syed.rest.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author sammar
 *
 */
@RestController
public class CurrencyConverstionController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CurrencyExchangeProxy proxy;
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConverstionBean convertCurrency(@PathVariable String from,@PathVariable String to, @PathVariable BigDecimal quantity) {
		
		Map<String,String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CurrencyConverstionBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
				CurrencyConverstionBean.class, uriVariables );
		
		CurrencyConverstionBean response = responseEntity.getBody();
		response.setQuantity(quantity);
		response.setCalculatedAmount(quantity.multiply(response.getConversionMultiple()));
		return response;
	}
	
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConverstionBean convertCurrencyFeign(@PathVariable String from,@PathVariable String to, @PathVariable BigDecimal quantity) {
		
		CurrencyConverstionBean response = proxy.retrieveExchange(from, to);
		response.setQuantity(quantity);
		response.setCalculatedAmount(quantity.multiply(response.getConversionMultiple()));
		logger.info("{}",response);
		return response;
	}
}
