package com.currencyconversion.currencyrate;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.currencyconversion.util.CurrencyConverUtil;

/**
 ** 
 * UnityCurrencyRate  which is used to find the return currency rate as 1 because source currency and destination currency is same . 
 *  
 * @author Bhargava
 * @date 11-07-2015 
 *
 * 
 */

@Component("unityCurrencyRate")
public class UnityCurrencyRate implements CurrencyRate{

	@Override
	public BigDecimal getCurrencyRate(String srcCurrecny, String destCurrecny) {
		
		return CurrencyConverUtil.getRoundValue(1);
	}
	
	

}
