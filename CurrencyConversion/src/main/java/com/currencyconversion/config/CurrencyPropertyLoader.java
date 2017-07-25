package com.currencyconversion.config;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.BeanAccess;

/**
 ** 
 *  CurrencyPropertyLoader is used to load the currenyc.yaml and is used to prepare currency config data.
 *  
 * @author Bhargava
 * @date 11-07-2015 
 *
 * 
 */

public class CurrencyPropertyLoader {
	
	private static Logger logger = LoggerFactory.getLogger(CurrencyPropertyLoader.class);
	String currencyMetaDataResourcepath;
	private CurrencyConfigData currencyConfigData;
	
	/* this method is used to assign the currency.yaml from  the class path */
	public CurrencyPropertyLoader(String currencyMetaDataResourcepath) {
		logger.debug("Currency Meta Data Resorce File Path ", currencyMetaDataResourcepath);
		this.currencyMetaDataResourcepath=currencyMetaDataResourcepath;
	}
	
	/* this post construct method is used to load the currency.yaml file and generate currencyConfigData */
	
	@PostConstruct
	public void loadCurrencyMetaData() throws IOException{
		logger.debug("Before Laoding the Currency Metadata from currency.Yaml");
		Resource resource = new ClassPathResource(this.currencyMetaDataResourcepath);
		Yaml yaml = new Yaml();
		yaml.setBeanAccess(BeanAccess.FIELD); // no setters, directly set value to private variables.
		this.currencyConfigData = yaml.loadAs(resource.getInputStream(), CurrencyConfigData.class);
		logger.debug("Currency MetaData Laoding completed successfully",currencyConfigData);
	}

	public CurrencyConfigData getCurrencyConfigData() {
		return currencyConfigData;
	}
}
