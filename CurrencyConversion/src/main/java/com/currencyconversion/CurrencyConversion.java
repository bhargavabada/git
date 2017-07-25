package com.currencyconversion;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.currencyconversion.config.CurrencyPropertyLoader;
import com.currencyconversion.exception.CurrencyConversionException;
import com.currencyconversion.service.CurrencyConversionService;
import com.currencyconversion.vo.CurrencyVO;


/**
 * 
 * CurrencyConversion.java
 * Currency Conversion Spring boot application . additional logic is provided to
 * test console based currency converter . if we call it as a service  
 * additional code is not required .  
 * 
 * @author Bhargava
 * @since 11-107-2017
 */

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@Configuration
@ComponentScan("com.currencyconversion")
public class CurrencyConversion {
	
	private static Logger logger = LoggerFactory.getLogger(CurrencyConversion.class);
	
	/* main method for Currency Conversion application which initialize the application context */
	
	public static void main(String args[]) {
	    logger.warn("Starting Currency Conversion.......");
	    ApplicationContext ctx=SpringApplication.run(CurrencyConversion.class, args);
	    System.out.println("Enter the input ");
	    
	    Scanner sc = new Scanner(System.in);
	    String  input=null;
	    while (!"exit".equalsIgnoreCase(input)){
	    	 logger.debug("Enter the input . enter exit to exit the program ");
	    	input=sc.nextLine();
	    	try{
	    		CurrencyConversionService currencyConversionService= (CurrencyConversionService) ctx.getBean("currencyConversionService");
	    		CurrencyVO currencyVO=currencyConversionService.convert(input);
	    		System.out.println(currencyVO);
	    	}catch(CurrencyConversionException ex){
	    		System.out.println(ex.getMessage());
	    	}
	    }
	    sc.close();	
	    SpringApplication.exit(ctx, () -> 1);
	 }
	
	
	/* Creating the currencyPropertyLoader bean to load the currency configurations from currency.yaml */
	 @Bean
	 CurrencyPropertyLoader currencyPropertyLoader() {
		 CurrencyPropertyLoader currencyPropertyLoader = new CurrencyPropertyLoader("/currency.yaml");
	    return currencyPropertyLoader;
	  }
	 
	
	
}
