package com.currencyconversion.service;

import static org.powermock.reflect.Whitebox.setInternalState;

import java.io.IOException;


import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import com.currencyconversion.config.CurrencyConfigData;
import com.currencyconversion.config.CurrencyPropertyLoader;
import com.currencyconversion.currencyrate.CCYCurrencyRate;
import com.currencyconversion.currencyrate.CurrencyRateFactory;
import com.currencyconversion.currencyrate.DirectCurrencyRate;
import com.currencyconversion.currencyrate.InverseCurrencyRate;
import com.currencyconversion.currencyrate.UnityCurrencyRate;
import com.currencyconversion.service.impl.CurrencyMetaDataServiceImpl;
import com.currencyconversion.util.CurrencyConverUtil;

public class CurrencyMetaDataServiceTest {
	
	@Test
	public void testCurrencyMetaDataService(){
		
		
		CurrencyPropertyLoader currencyPropertyLoaderMock=EasyMock.createMock(CurrencyPropertyLoader.class);
		CurrencyRateFactory currencyRateFactoryMock=EasyMock.createMock(CurrencyRateFactory.class);
		UnityCurrencyRate unityCurrencyRateMock=EasyMock.createMock(UnityCurrencyRate.class);
		InverseCurrencyRate inverseCurrencyRateMock=EasyMock.createMock(InverseCurrencyRate.class);
		DirectCurrencyRate  directCurrencyRateMock=EasyMock.createMock(DirectCurrencyRate.class);
		CCYCurrencyRate		ccyCurrencyRateMock=EasyMock.createMock(CCYCurrencyRate.class);
		CurrencyPropertyLoader currencyPropertyLoader=new CurrencyPropertyLoader("testcurrency.yaml");
		try {
			currencyPropertyLoader.loadCurrencyMetaData();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CurrencyMetaDataServiceImpl currencyMetaDataService=new CurrencyMetaDataServiceImpl();
		setInternalState(currencyMetaDataService, CurrencyPropertyLoader.class, currencyPropertyLoaderMock);
		setInternalState(currencyMetaDataService, CurrencyRateFactory.class, currencyRateFactoryMock);
		
		CurrencyConfigData configData=currencyPropertyLoader.getCurrencyConfigData();
		EasyMock.expect(currencyPropertyLoaderMock.getCurrencyConfigData()).andReturn(configData);
		EasyMock.expect(currencyRateFactoryMock.getCurrencyRate("AUD", "AUD")).andReturn(unityCurrencyRateMock).anyTimes();
		EasyMock.expect(currencyRateFactoryMock.getCurrencyRate("AUD", "USD")).andReturn(directCurrencyRateMock).anyTimes();
		EasyMock.expect(currencyRateFactoryMock.getCurrencyRate("AUD", "EUR")).andReturn(ccyCurrencyRateMock).anyTimes();
		EasyMock.expect(currencyRateFactoryMock.getCurrencyRate("USD", "AUD")).andReturn(inverseCurrencyRateMock).anyTimes();
		EasyMock.expect(currencyRateFactoryMock.getCurrencyRate("USD", "USD")).andReturn(unityCurrencyRateMock).anyTimes();
		EasyMock.expect(currencyRateFactoryMock.getCurrencyRate("USD", "EUR")).andReturn(inverseCurrencyRateMock).anyTimes();
		EasyMock.expect(currencyRateFactoryMock.getCurrencyRate("EUR", "EUR")).andReturn(unityCurrencyRateMock).anyTimes();
		EasyMock.expect(currencyRateFactoryMock.getCurrencyRate("EUR", "AUD")).andReturn(ccyCurrencyRateMock).anyTimes();
		EasyMock.expect(currencyRateFactoryMock.getCurrencyRate("EUR", "USD")).andReturn(directCurrencyRateMock).anyTimes();
		
		EasyMock.expect(unityCurrencyRateMock.getCurrencyRate("AUD", "AUD")).andReturn(CurrencyConverUtil.getRoundValue(1)).anyTimes();
		EasyMock.expect(unityCurrencyRateMock.getCurrencyRate("USD", "USD")).andReturn(CurrencyConverUtil.getRoundValue(1)).anyTimes();
		EasyMock.expect(unityCurrencyRateMock.getCurrencyRate("EUR", "EUR")).andReturn(CurrencyConverUtil.getRoundValue(1)).anyTimes();
		
		EasyMock.expect(directCurrencyRateMock.getCurrencyRate("AUD", "USD")).andReturn(CurrencyConverUtil.getRoundValue(0.8371)).anyTimes();
		EasyMock.expect(directCurrencyRateMock.getCurrencyRate("EUR", "USD")).andReturn(CurrencyConverUtil.getRoundValue(1.2315)).anyTimes();
		
		EasyMock.expect(ccyCurrencyRateMock.getCurrencyRate("AUD", "EUR")).andReturn(null).anyTimes();
		EasyMock.expect(ccyCurrencyRateMock.getCurrencyRate("EUR", "AUD")).andReturn(CurrencyConverUtil.getRoundValue(1)).anyTimes();
		
		EasyMock.expect(inverseCurrencyRateMock.getCurrencyRate("USD", "AUD")).andReturn(CurrencyConverUtil.getRoundValue(1/0.8371)).anyTimes();
		EasyMock.expect(inverseCurrencyRateMock.getCurrencyRate("USD", "EUR")).andReturn(CurrencyConverUtil.getRoundValue(1/1.2315)).anyTimes();
		
		EasyMock.replay(inverseCurrencyRateMock, ccyCurrencyRateMock,directCurrencyRateMock,
				unityCurrencyRateMock,currencyRateFactoryMock,currencyPropertyLoaderMock);
		
		currencyMetaDataService.initCurrencyMetaData();
		
		Assert.assertNotEquals("Problem While doing executing MetaData service",currencyMetaDataService.getdecimalformatterDegits("AUD")==2);
		Assert.assertNotEquals("Problem While doing executing MetaData service",currencyMetaDataService.getdecimalformatterDegits("EUR")==0);
		
		Assert.assertEquals("Problem While doing executing MetaData service",currencyMetaDataService.getCurrecyRate("AUD","AUD").compareTo(CurrencyConverUtil.getRoundValue(1)),0);
		Assert.assertEquals("Problem While doing executing MetaData service",currencyMetaDataService.getCurrecyRate("AUD","USD").compareTo(CurrencyConverUtil.getRoundValue(0.8371)),0);
		Assert.assertEquals("Problem While doing executing MetaData service",currencyMetaDataService.getCurrecyRate("USD","EUR").compareTo(CurrencyConverUtil.getRoundValue(1/1.2315)),0);
		Assert.assertEquals("Problem While doing executing MetaData service",currencyMetaDataService.getCurrecyRate("AUD","EUR").compareTo(CurrencyConverUtil.getRoundValue(0.6797)),0);
		
		Assert.assertNotEquals("Problem While doing executing MetaData service",currencyMetaDataService.isSrcCurrencyCodeExists("EUR"));
		Assert.assertNotEquals("Problem While doing executing MetaData service",currencyMetaDataService.isSrcCurrencyCodeExists("USD"));
		Assert.assertNotEquals("Problem While doing executing MetaData service",currencyMetaDataService.isSrcCurrencyCodeExists("AUD"));
		
		Assert.assertNotEquals("Problem While doing executing MetaData service",currencyMetaDataService.isCurrencyCodeExists("EUR","AUD"));
		Assert.assertNotEquals("Problem While doing executing MetaData service",currencyMetaDataService.isCurrencyCodeExists("AUD","USD"));
		
		
	}
	
	
  }
