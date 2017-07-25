package com.currencyconversion.formatter;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.springframework.stereotype.Component;

/**
 ** 
 *  ZeroDecimalFormatter is used format the data into zero decimals 
 *  
 * @author Bhargava
 * @date 11-07-2015 
 *
 * 
 */

@Component("zeroDecimalFormatter")
public class ZeroDecimalFormatter implements DecimalFormatter {

	@Override
	public BigDecimal format(double value) {
		return new BigDecimal(new DecimalFormat("##").format(value)); 
	}

}
