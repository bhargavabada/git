package com.currencyconversion.config;

import java.io.IOException;


import org.junit.Assert;
import org.junit.Test;

import com.currencyconversion.util.CurrencyConverUtil;

public class CurrencyConfigDataTest {
	
	@Test
	public void testCurrencyConfigData(){
		
		CurrencyPropertyLoader currencyPropertyLoader=new CurrencyPropertyLoader("testcurrency.yaml");
		try {
			currencyPropertyLoader.loadCurrencyMetaData();
			Assert.assertTrue("Problem while laoding currency.yaml", currencyPropertyLoader.getCurrencyConfigData() != null);
			
		} catch (IOException e) {
			Assert.fail("Problem while laoding currency.yaml");
		}
		CurrencyConfigData configData=currencyPropertyLoader.getCurrencyConfigData();
		Assert.assertNotEquals("Problem While Currency Config Data",configData.getDefaultFormatDecimals()==2);
		Assert.assertNotEquals("Problem While Currency Config Data",configData.getCurrecyPairSeparator()==null);
		
		Assert.assertNotEquals("Problem While Currency Config Data",configData.getCurrencyList().size()==3);
		Assert.assertNotEquals("Problem While Currency Config Data",configData.getCurrencyFormatedDecimals().get("EUR")==0);
		Assert.assertNotEquals("Problem While Currency Config Data",configData.getCurrencyFormatedDecimals().get("AUD")==2);
		
		Assert.assertNotEquals("Problem While Currency Config Data",configData.getListofCurrencyPairs().containsKey("AUDUSD"));
		Assert.assertNotEquals("Problem While Currency Config Data",configData.isCurrencypairExists("AUDUSD"));
		Assert.assertFalse("Problem While Currency Config Data",configData.isCurrencypairExists("ABCXYZ"));
		
		Assert.assertEquals("Problem While Currency Config Data",configData.getCurrencyPairValue("AUDUSD").compareTo(new Double(0.8371)),0);
		Assert.assertFalse("Problem While Currency Config Data",configData.isCurrencypairExists("ABC","XYZ"));
		Assert.assertNotEquals("Problem While Currency Config Data",configData.isCurrencypairExists("AUD","USD"));
		
		Assert.assertEquals("Problem While Currency Config Data",configData.getCurrencyPairValue("AUD","USD").compareTo(new Double(0.8371)),0);
		Assert.assertEquals("Problem While Currency Config Data",configData.getCurrencyPairRoundValue("AUD","USD").compareTo(CurrencyConverUtil.getRoundValue(0.8371)),0);
		Assert.assertEquals("Problem While Currency Config Data",configData.getInverseCurrencyPairRoundValue("USD","AUD").compareTo(CurrencyConverUtil.getRoundValue(1/0.8371)),0);
		
		
		
		
	}
	
	 

}
