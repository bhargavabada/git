package com.currencyconversion.service;

import java.math.BigDecimal;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import com.currencyconversion.formatter.DecimalFormatterFactory;
import com.currencyconversion.formatter.TwoDecimalFormatter;
import com.currencyconversion.service.impl.CurrencyConversionServiceImpl;
import com.currencyconversion.service.impl.CurrencyMetaDataServiceImpl;
import com.currencyconversion.service.impl.ValidationServiceImpl;
import com.currencyconversion.vo.CurrencyVO;

import static org.powermock.reflect.Whitebox.setInternalState;

public class CurrencyConversionServiceTest {
	
	@Test
	public void testCurrencyConversionService(){

		CurrencyMetaDataService currencyMetaDataServiceMock = EasyMock.createMock(CurrencyMetaDataServiceImpl.class);
		DecimalFormatterFactory decimalFormatterFactoryMock= EasyMock.createMock(DecimalFormatterFactory.class);
		ValidationService validationServiceMock = EasyMock.createMock(ValidationServiceImpl.class);
		CurrencyConversionService currencyConversionServiceImpl=new CurrencyConversionServiceImpl();
		setInternalState(currencyConversionServiceImpl, ValidationService.class, validationServiceMock);
		setInternalState(currencyConversionServiceImpl, CurrencyMetaDataService.class, currencyMetaDataServiceMock);
		setInternalState(currencyConversionServiceImpl, DecimalFormatterFactory.class, decimalFormatterFactoryMock);
		EasyMock.expect(currencyMetaDataServiceMock.getCurrecyRate("AUD","USD")).andReturn(new BigDecimal(0.8371));
		TwoDecimalFormatter twoDecimalFormatter=new TwoDecimalFormatter();
		EasyMock.expect(decimalFormatterFactoryMock.getFormatter(EasyMock.anyString())).andReturn(twoDecimalFormatter);
		
		validationServiceMock.validate(EasyMock.anyObject());
		EasyMock.expectLastCall();
		EasyMock.replay(currencyMetaDataServiceMock, validationServiceMock,decimalFormatterFactoryMock);
		CurrencyVO currencyVo= currencyConversionServiceImpl.convert("AUD 100.00 in USD");
		Assert.assertTrue("Currency Value object should not be null", currencyVo != null);
		Assert.assertNotEquals("Problem While doing currency conversion",currencyVo.getSrcCurrencyCode().equals("AUD"));
		Assert.assertNotEquals("Problem While doing currency conversion",currencyVo.getDestCurrencyCode().equals("USD"));
		Assert.assertEquals("Problem While doing currency conversion", currencyVo.getSrcValue().compareTo(new BigDecimal("100.00")),0);
		Assert.assertEquals("Problem While doing currency conversion", currencyVo.getDestValue().compareTo(new BigDecimal("83.71")),0);
		
	}
	
	
	

}
