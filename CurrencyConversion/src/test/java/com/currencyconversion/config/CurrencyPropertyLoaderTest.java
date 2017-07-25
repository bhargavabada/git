package com.currencyconversion.config;

import java.io.IOException;




import org.junit.Assert;
import org.junit.Test;

public class CurrencyPropertyLoaderTest {
	
	@Test
	public void testCurrencyPropertyLoader(){
		
		
		CurrencyPropertyLoader currencyPropertyLoader=new CurrencyPropertyLoader("currency.yaml");
		try {
			currencyPropertyLoader.loadCurrencyMetaData();
			Assert.assertTrue("Problem while laoding currency.yaml", currencyPropertyLoader.getCurrencyConfigData() != null);
			
		} catch (IOException e) {
			Assert.fail("Problem while laoding currency.yaml");
		}
		
	}

}
