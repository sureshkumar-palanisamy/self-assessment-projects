package com.vat.rates.standardreduced.main.rest.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vat.rates.standardreduced.main.model.CountriesRates;
import com.vat.rates.standardreduced.main.rest.service.CountriesRatesServiceProxy;

@RestController
@RequestMapping("${application.path}")
public class ReducedRateRestService {
	
private static final Logger logger = LoggerFactory.getLogger(ReducedRateRestService.class);
	
	@Autowired
	private CountriesRatesServiceProxy countriesRatesServiceProxy;

	@Value("${reduced.rate.count}")
	private long REDUCED_RATE_COUNT;
	
	/**
	 * getHighestThreeCountriesStandardRates() method is used to load rates json data by injecting rate service proxy interface
	 * and apply the filter to get top three countries with highest standard rates
	 * @return JSONObject
	 */
	@GetMapping(path="${reduced.rate.path}")
	public ResponseEntity<List<String>> getLowestThreeCountriesReducedRates() {
		logger.info("Entered inside getLowestThreeCountriesReducedRates() method in ReducedRateRestService");

		List<String> countryList = new ArrayList<String>();
		try {

			List<CountriesRates> countriesList = countriesRatesServiceProxy.loadRatesJsonData().stream()
					.sorted(Comparator.comparing(CountriesRates::getReduced_rate))
					.limit(REDUCED_RATE_COUNT).collect(Collectors.toList());

			for (CountriesRates cr : countriesList) {
				countryList.add(cr.getCountry());
				countryList.add(String.valueOf(cr.getReduced_rate()));
			}

			logger.info("Ended inside getLowestThreeCountriesReducedRates() method in ReducedRateRestService");
		} catch (Exception ex) {
			logger.error(
					"Exception in getLowestThreeCountriesReducedRates() method in ReducedRateRestService :: "
							+ ex.getMessage());
		}
		return ResponseEntity.ok(countryList);
	}
}
