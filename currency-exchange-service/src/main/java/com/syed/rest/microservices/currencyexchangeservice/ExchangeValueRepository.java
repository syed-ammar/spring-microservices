/**
 * 
 */
package com.syed.rest.microservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sammar
 *
 */
public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long>{

	public ExchangeValue findByFromAndTo(String from, String to);
}
