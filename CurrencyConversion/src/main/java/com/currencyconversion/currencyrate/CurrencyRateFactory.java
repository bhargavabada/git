package com.currencyconversion.currencyrate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.currencyconversion.config.CurrencyPropertyLoader;


/**
 ** 
 *  Currency Rate Factory to get one of the currency rate implementations and get the Currency rate between Source currencya nd destination 
 *  currency . 
 *  
 *  
 * @author Bhargava
 * @date 11-07-2015 
 *
 * 
 */

@Component
public class CurrencyRateFactory {
	@Autowired
	private UnityCurrencyRate unityCurrencyRate;
	
	@Autowired
	private InverseCurrencyRate inverseCurrencyRate;
	
	@Autowired
	private DirectCurrencyRate directCurrencyRate;
	
	@Autowired
	private CCYCurrencyRate ccyCurrencyRate;
	
	@Autowired
	private CurrencyPropertyLoader currencyPropertyLoader;
	
	
	/* this factory method is used to identify the  appropriate currency rate implementation */ 
	
	public CurrencyRate getCurrencyRate(String srcCurrency,String destCurrency){
		
		if(srcCurrency.equals(destCurrency)){
			return unityCurrencyRate;
		}
		if(currencyPropertyLoader.getCurrencyConfigData().isCurrencypairExists(srcCurrency,destCurrency)){
			return directCurrencyRate;
		}
		
		if(currencyPropertyLoader.getCurrencyConfigData().isCurrencypairExists(destCurrency,srcCurrency)){
			
			return inverseCurrencyRate;
		}
		
		
		return ccyCurrencyRate;
	}
	
	

}
