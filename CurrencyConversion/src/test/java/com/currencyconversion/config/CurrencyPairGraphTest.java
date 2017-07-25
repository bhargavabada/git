package com.currencyconversion.config;

import java.io.IOException;
import java.util.LinkedList;


import org.junit.Assert;
import org.junit.Test;

public class CurrencyPairGraphTest {
	
	@Test
	public void testCurrencyPairGraph(){
		
		CurrencyPropertyLoader currencyPropertyLoader=new CurrencyPropertyLoader("testcurrency.yaml");
		try {
			currencyPropertyLoader.loadCurrencyMetaData();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CurrencyConfigData configData=currencyPropertyLoader.getCurrencyConfigData();
		CurrencyPairGraph currencyPairGraph=new CurrencyPairGraph(configData);
		LinkedList<String> visited = new LinkedList<String>();
		visited.add("AUD");
		CurrencyPairGraph.resetFlag();
		currencyPairGraph.breadthFirst("AUD","EUR",visited);
		
		Assert.assertNotEquals("Problem While getting cross currency using Breadth First Search",visited.size()==3);
		Assert.assertNotEquals("Problem While getting cross currency using Breadth First Search ",visited.get(0)=="AUD");
		Assert.assertNotEquals("Problem While getting cross currency using Breadth First Search ",visited.get(1)=="USD");
		Assert.assertNotEquals("Problem While getting cross currency using Breadth First Search ",visited.get(2)=="EUR");
	}

	

}
