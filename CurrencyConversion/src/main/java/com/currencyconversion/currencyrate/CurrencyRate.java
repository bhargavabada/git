package com.currencyconversion.currencyrate;

import java.math.BigDecimal;

/**
 ** 
 * CurrencyRate Interface and provided various implementation to get the currency rate 
 *  
 * @author Bhargava
 * @date 11-07-2015 
 *
 * 
 */
public interface CurrencyRate {
	
	public BigDecimal getCurrencyRate(String srcCurrecny,String destCurrecny); 

}
