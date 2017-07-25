package com.currencyconversion.vo;

import java.math.BigDecimal;

/**
 ** 
 *  CurrencyVO   is value  object and is used to return the response . 
 * @author Bhargava
 * @date 11-07-2015 
 *
 * 
 */

public final class  CurrencyVO {
	
	private String srcCurrencyCode;
	private BigDecimal srcValue;
	private String destCurrencyCode;
	private BigDecimal destValue;
	
	public CurrencyVO(String srcCurrencyCode,BigDecimal srcValue,String destCurrencyCode,BigDecimal destValue){
		
		this.srcCurrencyCode=srcCurrencyCode;
		this.srcValue=srcValue;
		this.destCurrencyCode=destCurrencyCode;
		this.destValue=destValue;
	}

	public String getSrcCurrencyCode() {
		return srcCurrencyCode;
	}

	public BigDecimal getSrcValue() {
		return srcValue;
	}

	public String getDestCurrencyCode() {
		return destCurrencyCode;
	}

	public BigDecimal getDestValue() {
		return destValue;
	}

	@Override
	public String toString() {
		return srcCurrencyCode +"  "+ srcValue + "=" +destCurrencyCode+" "+destValue;
	}
	
	
	
	
	
	
	

}
