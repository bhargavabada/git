package com.currencyconversion.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.springframework.util.StringUtils;

import com.currencyconversion.exception.CurrencyConversionException;

/**
 ** 
 *  CurrencyConverUtil  is used to provide couple of utility methods across the service . 
 * @author Bhargava
 * @date 11-07-2015 
 *
 * 
 */

public class CurrencyConverUtil {
	
	public static String getCurrencyPair(String sourceCurrency, String destCurrency,String separator) {
		return StringUtils.isEmpty(separator)? new StringBuilder(sourceCurrency).append(destCurrency).toString():
			new StringBuilder(sourceCurrency).append("/").append(destCurrency).toString();
	}
	
	public static BigDecimal getRoundValue(double value) {
		//value=Math.ceil(value/0.05)*0.05;
		return new BigDecimal(new DecimalFormat("##.0000").format(value)); 
	}
	
	public static BigDecimal getInverseRoundValue(double value) {
		return getRoundValue(1/value);
		
	}
	
	public  static String[]  getTokens(String input) throws CurrencyConversionException{
		
		if(input==null){
			throw new CurrencyConversionException("Invalid input . please pass the input in <ccy1> <amount1> in <ccy2> format");
		}
		return input.split("\\s+");
	}
	
	

}
