package com.vat.main.webservice.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vat.main.webservice.model.CountryRates;
import com.vat.main.webservice.services.RateService;

@RestController
@RequestMapping("/api/vat")
public class RestWebserviceController {

	private static final Logger logger = LoggerFactory.getLogger(RestWebserviceController.class);

	@Autowired
	RateService rateService;
	
	@Value("${standard.rate.count}")
	private long STANDAARD_RATE_COUNT;
	
	@Value("${reduced.rate.count}")
	private long REDUCED_RATE_COUNT;
	
	@GetMapping(path="/getStandardRates")
	public Object loadTopThreeCountriesStandardRate() {
		logger.info("Entered inside loadTopThreeCountriesStandardRate() method in RestWebserviceController");

		List<String> countryList = new ArrayList<String>();
		try {
			List<CountryRates> countryRatesList = rateService.getJsonData();

			List<CountryRates> countriesList = countryRatesList.stream()
					.sorted(Comparator.comparing(CountryRates::getStandard_rate)
					.reversed()).limit(STANDAARD_RATE_COUNT)
					.collect(Collectors.toList());

			for (CountryRates cr : countriesList) {
				countryList.add(cr.getCountry());
				countryList.add(String.valueOf(cr.getStandard_rate()));
			}
			
			logger.info("Ended inside loadTopThreeCountriesStandardRate() method in RestWebserviceController");
		} catch (Exception ex) {
			logger.error("Exception in loadTopThreeCountriesStandardRate() method in RestWebserviceController :: " + ex.getMessage());

		}
		return JSONObject.stringToValue(countryList.toString());
	}
	
	@GetMapping(path="/getReducedRates")
	public Object loadTopThreeCountriesReducedRate() {
		logger.info("Entered inside loadTopThreeCountriesReducedRate() method in RestWebserviceController");

		List<String> countryList = new ArrayList<String>();
		try {
			List<CountryRates> countryRatesList = rateService.getJsonData();

			List<CountryRates> countriesList = countryRatesList.stream()
					.sorted(Comparator.comparing(CountryRates::getReduced_rate))
					.limit(REDUCED_RATE_COUNT)
					.collect(Collectors.toList());
			
			for (CountryRates cr : countriesList) {
				countryList.add(cr.getCountry());
				countryList.add(String.valueOf(cr.getReduced_rate()));
			}
			logger.info("Ended inside loadTopThreeCountriesReducedRate() method in RestWebserviceController");
		} catch (Exception ex) {
			logger.error("Exception in loadTopThreeCountriesReducedRate() method in RestWebserviceController :: " + ex.getMessage());
		}
		return JSONObject.stringToValue(countryList.toString());
	}
}
