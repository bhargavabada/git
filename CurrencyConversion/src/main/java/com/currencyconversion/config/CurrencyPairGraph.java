package com.currencyconversion.config;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 ** 
 *  CurrencyPairGraph is prepared while starting the application and disposed after preparing the metadata
 *  this is used to calculate the currency rate  in case of cross currencies 
 * @author Bhargava
 * @date 11-07-2015 
 *
 * 
 */


public class CurrencyPairGraph
{
	private static Logger logger = LoggerFactory.getLogger(CurrencyPairGraph.class);
	private Map<String, LinkedHashSet<String>> map = new HashMap<String, LinkedHashSet<String>>();
    private static boolean flag;
 
    /**
     ** 
     *  This method is used to add the edge into a graph 
     *
     * 
     */
    
    public void addEdge(String node1, String node2)
    {
        LinkedHashSet<String> adjacent = map.get(node1);
        if (adjacent == null)
        {
            adjacent = new LinkedHashSet<String>();
            map.put(node1, adjacent);
        }
        adjacent.add(node2);
    }
    
    public static void resetFlag(){
    	flag = false;
    }
    
    
    /**
     ** 
     *  This method is used to get the adjacent nodes for any node
     *
     * 
     */
    public LinkedList<String> adjacentNodes(String last)
    {
        LinkedHashSet<String> adjacent = map.get(last);
        if (adjacent == null)
        {
            return new LinkedList<String>();
        }
        return new LinkedList<String>(adjacent);
    }
 
   
    /**
     ** 
     *  This method is used add edges in by directional way 
     *
     * 
     */
    public void addTwoWayVertex(String node1, String node2)
    {
        addEdge(node1, node2);
        addEdge(node2, node1);
    }
    
    /**
     ** 
     *  This method is used to construct a graph for all the currency pair values 
     *
     * 
     */
    public CurrencyPairGraph(CurrencyConfigData currencyConfigData){
        
    	logger.debug("Preparing the Currency conversion Graph to identify Sross currencies ");
    	for(String srcCurrency:currencyConfigData.getCurrencyList()){
    		for(String dstCurrency:currencyConfigData.getCurrencyList()){
    			if(currencyConfigData.isCurrencypairExists(srcCurrency,dstCurrency)){
    				addTwoWayVertex(srcCurrency,dstCurrency);
    			}
    		}
    	}
    	
    	logger.debug("Building the graph is completed ");
    	
    }
    
    /**
     ** 
     *  This method is used to find the cross currency path from source currency to destination currency 
     *
     * 
     */
    public void breadthFirst(final String  start,final String  end ,LinkedList<String> visited)
    {
    	logger.debug("Finding the Cross currency path between ",start,end);
    	
    	LinkedList<String> nodes = adjacentNodes(visited.getLast());
 
        for (String node : nodes)
        {
            if (visited.contains(node))
            {
                continue;
            }
            if (node.equals(end))
            {
                visited.add(node);
                flag = true;
                break;
            }
        }
 
        if(!flag){
        	for (String node : nodes){
        		if(flag) break; 	
        		if (visited.contains(node) || node.equals(end)){
        				continue;
        		}
        		visited.addLast(node);
        		breadthFirst(start,end,visited);
        		if(!flag)
        		visited.removeLast();
        	}
        }
        
        
        
 
    }
 
   
}