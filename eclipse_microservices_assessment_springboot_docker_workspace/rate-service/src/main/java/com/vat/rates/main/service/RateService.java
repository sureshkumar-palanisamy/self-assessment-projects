package com.vat.rates.main.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.vat.rates.main.model.CountryRates;

import feign.Response;

@Service
public class RateService {

	@Value("${rates}")
	private String RATES;

	@Value("${country}")
	private String COUNTRY;

	@Value("${standard.rate}")
	private String STANDARD_RATE;

	@Value("${reduced.rate}")
	private String REDUCED_RATE;

	@Autowired
	RateServiceProxy rateServiceProxy;

	public List<CountryRates> loadCountryRatesData() throws IOException {

		var countryRateList = new ArrayList<CountryRates>();
		var objMapper = new ObjectMapper().enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS).setNodeFactory(JsonNodeFactory.withExactBigDecimals(true));

		Response feignResponse = rateServiceProxy.loadRatesJsonData();
		
		if (feignResponse != null) {
			
			String feignList = IOUtils.toString(feignResponse.body().asInputStream());
			
	        JsonNode jsonFeignList = objMapper.readTree(feignList);
	        JsonNode jsonRatesList = jsonFeignList.get(RATES);
	        
			if (feignList != null) {
				Iterator<Map.Entry<String, JsonNode>> fields = jsonRatesList.fields();

				if (fields != null) {
					while (fields.hasNext()) {
						Map.Entry<String, JsonNode> field = fields.next();

						String country = field.getValue().get(COUNTRY).asText();
						BigDecimal standardRate = field.getValue().get(STANDARD_RATE).decimalValue();
						BigDecimal reducedRate = field.getValue().get(REDUCED_RATE).decimalValue();

						CountryRates countryRates = new CountryRates(country, standardRate, reducedRate);
						countryRateList.add(countryRates);
						
					}
				}
			}
		}
		return countryRateList;
	}
}