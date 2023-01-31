package com.vat.rates.main.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vat.rates.main.model.CountryRates;
import com.vat.rates.main.service.RateService;

@RestController
@RequestMapping("${application.path}")
public class RatesWebService {
	
	private static final Logger logger = LoggerFactory.getLogger(RatesWebService.class);

	@Value("${rates}")
	private String RATES;

	@Value("${country}")
	private String COUNTRY;

	@Value("${standard.rate}")
	private String STANDARD_RATE;

	@Value("${reduced.rate}")
	private String REDUCED_RATE;
	
	@Autowired
	RateService rateService;

	/**
	 * loadRatesJsonData() call rateService and get data from the end point 
	 * 
	 * @return List<CountryRates> - list of countries and rates as ResponseEntity
	 * @throws IOException 
	 */
	@GetMapping(path = "${rate.path}")
	public ResponseEntity<List<CountryRates>> loadRatesJsonData() throws IOException {
		
		var countryRatesList = rateService.loadCountryRatesData();
		
		return ResponseEntity.ok(countryRatesList);
	}
}
