package com.currencyconversion.service.impl;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.currencyconversion.exception.CurrencyConversionException;
import com.currencyconversion.formatter.DecimalFormatter;
import com.currencyconversion.formatter.DecimalFormatterFactory;
import com.currencyconversion.service.CurrencyConversionService;
import com.currencyconversion.service.CurrencyMetaDataService;
import com.currencyconversion.service.ValidationService;
import com.currencyconversion.util.CurrencyConverUtil;
import com.currencyconversion.vo.CurrencyVO;

/**
 ** 
 *  CurrencyConversionServiceImpl is used convert source currency to destination currency b using currency rate 
 *  which is  prepared while loading the application . 
 *  
 * @author Bhargava
 * @date 11-07-2015 
 *
 * 
 */

@Service("currencyConversionService")
public class CurrencyConversionServiceImpl implements CurrencyConversionService  {
	
	private static Logger logger = LoggerFactory.getLogger(CurrencyConversionServiceImpl.class);
	
	@Autowired
	private DecimalFormatterFactory decimalFormatterFactory;
	@Autowired
	private ValidationService validationService;
	
	@Autowired
	private CurrencyMetaDataService currencyMetaDataService;
	
	/* this method is  used to convert source currency to destination currency . */ 
	
	@Override
	public CurrencyVO convert(String input) throws CurrencyConversionException{
		
		logger.debug("Currency Conversion for the input string",input);
		String[] tokens= CurrencyConverUtil.getTokens(input);
		logger.debug("tokenizing completed successfully ",Arrays.toString(tokens));
		validationService.validate(tokens);
		logger.debug("Toekns validation completed ",Arrays.toString(tokens));
		DecimalFormatter decimalFormatter =decimalFormatterFactory.getFormatter(tokens[3]);
		Double units=Double.parseDouble(tokens[1]);
		Double convertedValue=units*currencyMetaDataService.getCurrecyRate(tokens[0], tokens[3]).doubleValue();
		logger.debug("Currency Conversion successful for",input,convertedValue);
		return new CurrencyVO(tokens[0],decimalFormatter.format(units),tokens[3],decimalFormatter.format(convertedValue));
	}
	
	

}
