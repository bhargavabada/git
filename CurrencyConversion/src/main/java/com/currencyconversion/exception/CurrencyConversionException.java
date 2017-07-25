package com.currencyconversion.exception;

/**
 ** 
 *  CurrencyConversionException is used throw validatin exceptions and currency conversion exceptions
 *  
 * @author Bhargava
 * @date 11-07-2015 
 *
 * 
 */


public class CurrencyConversionException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String message;
	
	public CurrencyConversionException() {
		super();
	}
	
	public CurrencyConversionException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
}
