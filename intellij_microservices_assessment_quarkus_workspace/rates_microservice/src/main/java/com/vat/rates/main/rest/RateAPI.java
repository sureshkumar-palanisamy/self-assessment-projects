package com.vat.rates.main.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.vat.rates.main.model.CountryRates;
import com.vat.rates.main.service.RatesServiceProxy;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.*;

@Path("/rates_microservice")
public class RateAPI {

    @ConfigProperty(name = "rates")
    String RATES;

    @ConfigProperty(name = "country")
    String COUNTRY;

    @ConfigProperty(name = "standard")
    String STANDARDRATE;

    @ConfigProperty(name = "reduced")
    String REDUCEDRATE;

    @RestClient
    RatesServiceProxy ratesService;

    @GET
    @Path("/vat/rates")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CountryRates> loadRatesJsonData() {

        List<CountryRates> countryRatesList = new ArrayList<>();

        JsonNode jsonResponse = ratesService.loadRatesJsonData();
        JsonNode jsonRatesList = jsonResponse.get(RATES);

        Iterator<Map.Entry<String, JsonNode>> fields = jsonRatesList.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            CountryRates countryRates = new CountryRates();
            countryRates.setCountry(field.getValue().get(COUNTRY).toString());
            countryRates.setStandard_rate(field.getValue().get(STANDARDRATE).doubleValue());
            countryRates.setReduced_rate(field.getValue().get(REDUCEDRATE).doubleValue());
            countryRatesList.add(countryRates);
        }
        return countryRatesList;
    }
}