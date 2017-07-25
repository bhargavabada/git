package com.currencyconversion.formatter;

import java.math.BigDecimal;

/**
 ** 
 * DecimalFormatter  interfce and it is having multiple implementations to format the final output  . 
 *  
 * @author Bhargava
 * @date 11-07-2015 
 *
 * 
 */

public interface DecimalFormatter {
	
	public BigDecimal format(double value);

}
