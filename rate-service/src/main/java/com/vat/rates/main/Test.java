package com.vat.rates.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {
		/*
		 * String patternString = "\"path\":\"/management/prometheus\",\"scheme\":"; //
		 * String input = "\"path\":\"/management/prometheus\",\"scheme\":"; String
		 * input =
		 * "{\"@timestamp\":\"2023-04-13T05:22:29.295Z\",\"vin\":\"\",\"message\":\"{\"requestMessage\":{\"origin\":\"remote\",\"type\":\"request\",\"correlation\":\"e83718ab063087b2\",\"protocol\":\"HTTP/1.1\",\"remote\":\"10.198.128.186\",\"method\":\"GET\",\"uri\":\"http://10.198.128.120:8080/management/prometheus\",\"host\":\"10.198.128.120\",\"path\":\"/management/prometheus\",\"scheme\":\"http\",\"port\":\"8080\",\"headers\":{\"accept\":[\"text/plain\"],\"accept-encoding\":[\"gzip\"],\"connection\":[\"keep-alive\"],\"host\":[\"10.198.128.120:8080\"],\"user-agent\":[\"Datadog Agent/7.37.1\"]}},\"responseMessage\":{\"origin\":\"local\",\"type\":\"response\",\"correlation\":\"e83718ab063087b2\",\"duration\":221,\"protocol\":\"HTTP/1.1\",\"status\":200,\"headers\":{\"Cache-Control\":[\"no-cache, no-store, max-age=0, must-revalidate\"],\"Connection\":[\"keep-alive\"],\"Content-Length\":[\"242970\"],\"Content-Type\":[\"text/plain;version=0.0.4;charset=utf-8\"],\"Date\":[\"Thu, 13 Apr 2023 05:22:29 GMT\"],"
		 * ;
		 * 
		 * if(input.contains(patternString)) {
		 * 
		 * System.out.println("matched"); } else { System.out.println("not matched"); }
		 */
		 long count = (long) 1.5;
		    
		if(count > 0) {
			System.out.println("yes");
		} else {
			System.out.println("no");
		}
	    
				 
		
	}

}
