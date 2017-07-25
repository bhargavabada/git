package com.currencyconversion.config;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.currencyconversion.util.CurrencyConverUtil;


/**
 * 
 * CurrencyConfigData.java
 * This is configuration value object which is loaded from currency.yaml
 * this value object prepared while starting the application
 * this contains Currency pairs and decimal values
 *  
 * 
 * @author Bhargava
 * @since 11-107-2017
 */

public class CurrencyConfigData {
	
	private Integer defaultFormatDecimals;
	private String currecyPairSeparator;
	private List<String> currencyList=new ArrayList<String>();
	private Map<String,Integer> currencyFormatedDecimals=new HashMap<String,Integer>();
	private Map<String,Double> listofCurrencyPairs=new HashMap<String,Double>();
	
	
	public Integer getDefaultFormatDecimals() {
		return defaultFormatDecimals;
	}
	public String getCurrecyPairSeparator() {
		return currecyPairSeparator;
	}
	public List<String> getCurrencyList() {
		return currencyList;
	}
	public Map<String, Integer> getCurrencyFormatedDecimals() {
		return currencyFormatedDecimals;
	}
	public Map<String, Double> getListofCurrencyPairs() {
		return listofCurrencyPairs;
	}
	
	public boolean isCurrencypairExists(String CurrencyPair){
		
		return listofCurrencyPairs.containsKey(CurrencyPair);
	}
	
	public Double getCurrencyPairValue(String currencyPair){
		
		return listofCurrencyPairs.get(currencyPair);
	}
	
	public boolean isCurrencypairExists(String srcCurrency,String DestCurrency){
		
		return listofCurrencyPairs.containsKey(CurrencyConverUtil.getCurrencyPair(srcCurrency,DestCurrency,currecyPairSeparator));
	}
	
	public Double getCurrencyPairValue(String srcCurrency,String DestCurrency){
		
		return listofCurrencyPairs.get(CurrencyConverUtil.getCurrencyPair(srcCurrency,DestCurrency,currecyPairSeparator));
	}
	
	public BigDecimal getCurrencyPairRoundValue(String srcCurrency,String DestCurrency){
		
		return CurrencyConverUtil.getRoundValue(
				listofCurrencyPairs.get(CurrencyConverUtil.getCurrencyPair(srcCurrency,DestCurrency,currecyPairSeparator)));
	}

	public BigDecimal getInverseCurrencyPairRoundValue(String srcCurrency,String DestCurrency){
		
		return CurrencyConverUtil.getInverseRoundValue(
				listofCurrencyPairs.get(CurrencyConverUtil.getCurrencyPair(DestCurrency,srcCurrency,currecyPairSeparator)));
	}
	
	
	@Override
	public String toString() {
		return "CurrencyMetaData [defaultFormatDecimals="
				+ defaultFormatDecimals + ", currecyPairSeparator="
				+ currecyPairSeparator + ", currencyList=" + currencyList
				+ ", currencyFormatedDecimals=" + currencyFormatedDecimals
				+ ", listofCurrencyPairs=" + listofCurrencyPairs + "]";
	}	
	
	
}
