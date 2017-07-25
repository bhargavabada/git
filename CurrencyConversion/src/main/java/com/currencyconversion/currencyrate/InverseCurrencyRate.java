package com.currencyconversion.currencyrate;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.currencyconversion.config.CurrencyPropertyLoader;


/**
 ** 
 * InverseCurrencyRate implementation which is used to find the Inverse Currency rate between Source currency and destination currency 
 *  
 * @author Bhargava
 * @date 11-07-2015 
 *
 * 
 */

@Component("inverseCurrencyRate")
public class InverseCurrencyRate implements CurrencyRate{

	@Autowired
	private CurrencyPropertyLoader currencyPropertyLoader;
	
	@Override
	public BigDecimal getCurrencyRate(String srcCurrecny, String destCurrecny) {
		
		return currencyPropertyLoader.getCurrencyConfigData().getInverseCurrencyPairRoundValue(srcCurrecny,destCurrecny);
	}

}
