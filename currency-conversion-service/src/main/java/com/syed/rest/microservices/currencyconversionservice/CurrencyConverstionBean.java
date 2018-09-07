/**
 * 
 */
package com.syed.rest.microservices.currencyconversionservice;

import java.math.BigDecimal;

/**
 * @author sammar
 *
 */
public class CurrencyConverstionBean {

	private Long id;

	private String from;
	
	private String to;
	
	private BigDecimal conversionMultiple;
	
	private BigDecimal quantity;
	
	private BigDecimal calculatedAmount;

	private int port;
	
	public CurrencyConverstionBean() {}
	

	/**
	 * @param id
	 * @param from
	 * @param to
	 * @param conversionMultiple
	 * @param quantity
	 * @param calculatedAmount
	 * @param port
	 */
	public CurrencyConverstionBean(Long id, String from, String to, BigDecimal conversionMultiple, BigDecimal quantity,
			BigDecimal calculatedAmount, int port) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversionMultiple = conversionMultiple;
		this.quantity = quantity;
		this.calculatedAmount = calculatedAmount;
		this.port = port;
	}


	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}


	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return the conversionMultiple
	 */
	public BigDecimal getConversionMultiple() {
		return conversionMultiple;
	}

	/**
	 * @param conversionMultiple the conversionMultiple to set
	 */
	public void setConversionMultiple(BigDecimal conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}

	/**
	 * @return the quantity
	 */
	public BigDecimal getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the calculatedAmount
	 */
	public BigDecimal getCalculatedAmount() {
		return calculatedAmount;
	}

	/**
	 * @param calculatedAmount the calculatedAmount to set
	 */
	public void setCalculatedAmount(BigDecimal calculatedAmount) {
		this.calculatedAmount = calculatedAmount;
	}
	
	
	
	
}
