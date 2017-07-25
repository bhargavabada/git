package com.currencyconversion.formatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.currencyconversion.service.CurrencyMetaDataService;

/**
 ** 
 * DecimalFormatterFactory  Factory to identify the right Formatter class to format the output . 
 *  
 * @author Bhargava
 * @date 11-07-2015 
 *
 * 
 */

@Component
public class DecimalFormatterFactory {
	
	private static Logger logger = LoggerFactory.getLogger(DecimalFormatterFactory.class);
	
	@Autowired
	private ZeroDecimalFormatter zeroDecimalFormatter;
	
	@Autowired
	private TwoDecimalFormatter twoDecimalFormatter;
	
	@Autowired
	private CurrencyMetaDataService currencyMetaDataService;
	
	public DecimalFormatter getFormatter(String currecyCode){
		logger.debug("decimal formatter factory for the currency code",currecyCode);
		int formatedDecimals=currencyMetaDataService.getdecimalformatterDegits(currecyCode);
		if(formatedDecimals==0){
			return zeroDecimalFormatter;
		}
		return twoDecimalFormatter;
		
	}
	

}
