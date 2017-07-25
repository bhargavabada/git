package com.currencyconversion.service;

import com.currencyconversion.exception.CurrencyConversionException;

public interface ValidationService {
	
	public void validate(String [] tokens) throws CurrencyConversionException;


}
