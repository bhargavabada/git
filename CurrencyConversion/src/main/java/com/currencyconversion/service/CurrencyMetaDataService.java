package com.currencyconversion.service;

import java.math.BigDecimal;

public interface CurrencyMetaDataService {
	
	  public int getdecimalformatterDegits(String currencyCode);  
      public BigDecimal getCurrecyRate(String srcCurrencyCocde,String destCurrencyCode);
      public boolean isSrcCurrencyCodeExists(String CurrencyCode);
      public boolean isCurrencyCodeExists(String srcCurrencyCode,String destCurrency);
      
      
 }
