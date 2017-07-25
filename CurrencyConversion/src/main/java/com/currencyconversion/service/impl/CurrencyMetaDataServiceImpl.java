package com.currencyconversion.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.currencyconversion.config.CurrencyConfigData;
import com.currencyconversion.config.CurrencyPairGraph;
import com.currencyconversion.config.CurrencyPropertyLoader;
import com.currencyconversion.currencyrate.CurrencyRateFactory;
import com.currencyconversion.service.CurrencyMetaDataService;
import com.currencyconversion.util.CurrencyConverUtil;

/**
 ** 
 *  CurrencyMetaDataServiceImpl is used prepare the currency rates for various currencies  
 *  this class contains the core logic to prepare the currency reates for direct,Inverse,unity,and CCY 
 *  currency convertores .  
 *  
 *  Currency rate data is stored in hasMap and it is prepared once . so thread safe is not required .
 *   
 * @author Bhargava
 * @date 11-07-2015 
 *
 * 
 */


@Service("currencyMetaDataService")
public class CurrencyMetaDataServiceImpl implements CurrencyMetaDataService {

	private static Logger logger = LoggerFactory.getLogger(CurrencyMetaDataServiceImpl.class);
	
	@Autowired
	private CurrencyPropertyLoader currencyPropertyLoader;
	
	@Autowired
	private CurrencyRateFactory currencyRateFactory;
	
	private CurrencyConfigData currencyConfigData;
	
	Map<String, Map<String, BigDecimal>> currencyConversionMetaData=new HashMap<String,Map<String,BigDecimal>>();
	
	@PostConstruct
	public void initCurrencyMetaData(){
		logger.debug("Constructing the Creency Conversion meta data");
		this.currencyConfigData=currencyPropertyLoader.getCurrencyConfigData();
		CurrencyPairGraph currencyPairGraph=new CurrencyPairGraph(this.currencyConfigData);
		for(String currency:this.currencyConfigData.getCurrencyList()){
			currencyConversionMetaData.put(currency, getConversionCurrencies(currency,currencyPairGraph));
			logger.debug("Constructing the Creency Conversion meta data for the country ",currency,currencyConversionMetaData.get(currency));
			
		}
		logger.debug("Constructing the Creency Conversion meta data",currencyConversionMetaData);
	}
	
	
	/* this method is used to iterate the source currency list and destination currency list identify the currency conversion rate*/
	
	private Map<String, BigDecimal> getConversionCurrencies(String sourceCurrency,CurrencyPairGraph currencyPairGraph) {
		
		Map<String, BigDecimal>  destCurrencyMap=new HashMap<String,BigDecimal>();
		for(String destCurrency:this.currencyConfigData.getCurrencyList()){
			
			
			BigDecimal value=currencyRateFactory.getCurrencyRate(sourceCurrency,destCurrency).getCurrencyRate(sourceCurrency, destCurrency);
			if(value!=null){
				logger.debug("Currency Conversion value",sourceCurrency,destCurrency,value);
				destCurrencyMap.put(destCurrency, value);
				continue;
			}
			logger.debug("cross currency caluclations started",sourceCurrency,destCurrency);
			LinkedList<String> visited = new LinkedList<String>();
			visited.add(sourceCurrency);
			CurrencyPairGraph.resetFlag();
			currencyPairGraph.breadthFirst(sourceCurrency,destCurrency,visited);
			if(visited.size() ==1){
				destCurrencyMap.put(destCurrency, null);
				continue;
			}
			value=null;
			for(int i=0;i<visited.size()-1;i++){
				value =value==null?currencyRateFactory.getCurrencyRate(visited.get(i),visited.get(i+1)).getCurrencyRate(visited.get(i), visited.get(i+1)):
					CurrencyConverUtil.getRoundValue(value.doubleValue()*currencyRateFactory.
							getCurrencyRate(visited.get(i),visited.get(i+1)).getCurrencyRate(visited.get(i), visited.get(i+1)).doubleValue());
				logger.debug("cross currency caluclations ",visited.get(i),visited.get(i+1),value);
			}
			destCurrencyMap.put(destCurrency, value);
		}
		
		return destCurrencyMap;
	}
	
	@Override
	public int getdecimalformatterDegits(String currencyCode) {
		
		if(currencyConfigData.getCurrencyFormatedDecimals().containsKey(currencyCode)){
			return currencyConfigData.getCurrencyFormatedDecimals().get(currencyCode);
		}
		
		return currencyConfigData.getDefaultFormatDecimals();
	}


	@Override
	public BigDecimal getCurrecyRate(String srcCurrencyCocde,
			String destCurrencyCode) {
		return currencyConversionMetaData.get(srcCurrencyCocde).get(destCurrencyCode);
	}


	@Override
	public boolean isSrcCurrencyCodeExists(String CurrencyCode) {
		return currencyConversionMetaData.containsKey(CurrencyCode);
		
	}
	
	@Override
	public boolean isCurrencyCodeExists(String srcCurrencyCode,String destCurrency) {
		if(isSrcCurrencyCodeExists(srcCurrencyCode)){
				return currencyConversionMetaData.get(srcCurrencyCode).containsKey(destCurrency) && 
						currencyConversionMetaData.get(srcCurrencyCode).get(destCurrency)!=null;
		}
		return false;
	}
	
	

}
