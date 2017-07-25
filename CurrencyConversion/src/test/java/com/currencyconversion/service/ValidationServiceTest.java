package com.currencyconversion.service;

import static org.powermock.reflect.Whitebox.setInternalState;


import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import com.currencyconversion.exception.CurrencyConversionException;
import com.currencyconversion.service.impl.CurrencyMetaDataServiceImpl;
import com.currencyconversion.service.impl.ValidationServiceImpl;

public class ValidationServiceTest {
	
	@Test
	public void testValidationService(){
		
		try{
			CurrencyMetaDataService currencyMetaDataServiceMock = EasyMock.createMock(CurrencyMetaDataServiceImpl.class);
			ValidationService validationService=new  ValidationServiceImpl();
			EasyMock.expect(currencyMetaDataServiceMock.isCurrencyCodeExists("AUD","USD")).andReturn(true);
			setInternalState(validationService, CurrencyMetaDataService.class, currencyMetaDataServiceMock);
			EasyMock.replay(currencyMetaDataServiceMock);
		
			String tokens[]={"AUD","100.00","in","USD"};
			validationService.validate(tokens);
		}catch(CurrencyConversionException ex){
			Assert.fail("Problem while validating currency converion input");
		}
		
		
	}
	
	@Test
	public void test1ValidationServiceForInvalidInput(){
		
		try{
			CurrencyMetaDataService currencyMetaDataServiceMock = EasyMock.createMock(CurrencyMetaDataServiceImpl.class);
			ValidationService validationService=new  ValidationServiceImpl();
			EasyMock.expect(currencyMetaDataServiceMock.isCurrencyCodeExists("AUD","USD")).andReturn(true);
			setInternalState(validationService, CurrencyMetaDataService.class, currencyMetaDataServiceMock);
			EasyMock.replay(currencyMetaDataServiceMock);
			String tokens[]={"AUD","100.00"};
			validationService.validate(tokens);
			Assert.fail("Problem while validating currency converion input");
		}catch(CurrencyConversionException ex){
			
		}
		
	}
	
	@Test
	public void test2ValidationServiceForInvalidInput(){
		
		try{
			CurrencyMetaDataService currencyMetaDataServiceMock = EasyMock.createMock(CurrencyMetaDataServiceImpl.class);
			ValidationService validationService=new  ValidationServiceImpl();
			EasyMock.expect(currencyMetaDataServiceMock.isCurrencyCodeExists("AUD","USD")).andReturn(true);
			setInternalState(validationService, CurrencyMetaDataService.class, currencyMetaDataServiceMock);
			EasyMock.replay(currencyMetaDataServiceMock);
			String tokens[]={"AUD","abcd","in","USD"};
			validationService.validate(tokens);
			Assert.fail("Problem while validating currency converion input");
		}catch(CurrencyConversionException ex){
			
		}
	}
		
		@Test
		public void test3ValidationServiceForInvalidInput(){
			
			try{
				CurrencyMetaDataService currencyMetaDataServiceMock = EasyMock.createMock(CurrencyMetaDataServiceImpl.class);
				ValidationService validationService=new  ValidationServiceImpl();
				EasyMock.expect(currencyMetaDataServiceMock.isCurrencyCodeExists("ABC","XYZ")).andReturn(false);
				setInternalState(validationService, CurrencyMetaDataService.class, currencyMetaDataServiceMock);
				EasyMock.replay(currencyMetaDataServiceMock);
				String tokens[]={"ABC","100.00","in","XYZ"};
				validationService.validate(tokens);
				Assert.fail("Problem while validating currency converion input");
			}catch(CurrencyConversionException ex){
				
			}

	}

	
}
