package com.vat.main.webservice.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vat.main.webservice.model.CountryRates;

@Service
public class RateService {
	
	private static final Logger logger = LoggerFactory.getLogger(RateService.class);

	@Value("${endpoint.rates}")
	private String RATES_ENDPOINT_URL;

	@Value("${rates}")
	private String RATES;

	@Value("${country}")
	private String COUNTRY;

	@Value("${standard.rate}")
	private String STANDARD_RATE;

	@Value("${reduced.rate}")
	private String REDUCED_RATE;
	
	/**
	 * getJsonData() method is used to load the entire json data by requesting endpoint service and 
	 * filters only countries and rates
	 * @return List<CountryRates> - list of countries and rates
	 */
	public List<CountryRates> getJsonData() {
		logger.info("Entered inside getJsonData() method in RateService ");

		ObjectMapper objectMapper = new ObjectMapper();
		List<CountryRates> countryRatesList = new ArrayList<CountryRates>();
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList((MediaType.APPLICATION_JSON)));
			HttpEntity<String> entity = new HttpEntity<String>(headers);

			JsonNode jsonNode = objectMapper.readTree(
					new RestTemplate().exchange(RATES_ENDPOINT_URL, HttpMethod.GET, entity, String.class).getBody());

			JsonNode jsonRates = jsonNode.get(RATES);

			Iterator<Entry<String, JsonNode>> fields = jsonRates.fields();
			while (fields.hasNext()) {
				Map.Entry<String, JsonNode> field = fields.next();
				CountryRates countryRates = new CountryRates();
				countryRates.setCountry(field.getValue().get(COUNTRY).toString());
				countryRates.setStandard_rate(field.getValue().get(STANDARD_RATE).doubleValue());
				countryRates.setReduced_rate(field.getValue().get(REDUCED_RATE).doubleValue());
				countryRatesList.add(countryRates);
			}
			logger.info("Ended inside getJsonData() method in RateService");

		} catch (JsonProcessingException jexception) {
			logger.error("Exception in getJsonData() method in RateService:: " + jexception.getMessage());
		}
		return countryRatesList;
	}

}
