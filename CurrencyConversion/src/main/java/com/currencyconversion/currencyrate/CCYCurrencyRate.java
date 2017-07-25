package com.currencyconversion.currencyrate;

import java.math.BigDecimal;



/**
 ** 
 *  Cross Currency Rate not implemented because it required additional logic and other currency rate 
 *  this is written to jsut identify give currencies require cross currency rate . so it is returning null . 
 *  
 * @author Bhargava
 * @date 11-07-2015 
 *
 * 
 */

import org.springframework.stereotype.Component;
@Component("ccyCurrencyRate")
public class CCYCurrencyRate implements CurrencyRate{

	@Override
	public BigDecimal getCurrencyRate(String srcCurrecny, String destCurrecny) {
		return null;
	}


}
