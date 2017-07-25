package com.currencyconversion.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.currencyconversion.exception.CurrencyConversionException;
import com.currencyconversion.service.CurrencyMetaDataService;
import com.currencyconversion.service.ValidationService;

/**
 ** 
 *  ValidationServiceImpl is used validate inout data and if the data is invalid it will throw CurrencyConversionException
 *     
 * @author Bhargava
 * @date 11-07-2015 
 *
 * 
 */

@Service("validationService")
public class ValidationServiceImpl  implements ValidationService{

	@Autowired
	private CurrencyMetaDataService currencyMetaDataService;
	
	
	/* this method is sued to validate input data . if user is passed invalid data it will throw CurrencyConversionException exception */
	@Override
	public void validate(String[] tokens) throws CurrencyConversionException {
		
		if(tokens.length!=4){
			throw new CurrencyConversionException("Invalid input . please pass the input in <ccy1> <amount1> in <ccy2> format");
		}
		
		if( !currencyMetaDataService.isCurrencyCodeExists(tokens[0].trim(),tokens[3].trim())){
			throw new CurrencyConversionException("Unable to find rate for  " +tokens[0]+"/"+tokens[3]);
		}
		
		try{
			Double.parseDouble(tokens[1]);
		}catch(NumberFormatException ex){
			throw new CurrencyConversionException("Invalid Amount. please pass the input in <ccy1> <amount1> in <ccy2> format");
		}
		
		
	}

	
	
}
