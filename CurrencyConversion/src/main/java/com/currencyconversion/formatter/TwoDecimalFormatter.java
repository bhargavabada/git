package com.currencyconversion.formatter;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.springframework.stereotype.Component;

/**
 ** 
 *  TwoDecimalFormatter is used format the data into two decimals 
 *  
 * @author Bhargava
 * @date 11-07-2015 
 *
 * 
 */

@Component("twoDecimalFormatter")
public class TwoDecimalFormatter implements DecimalFormatter {

	@Override
	public BigDecimal format(double value) {
		//value=Math.ceil(value/0.05)*0.05;
		return new BigDecimal(new DecimalFormat("##.00").format(value)); 
		
	}

}
