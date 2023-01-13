package com.vat.rate.main.rest;

import com.vat.rate.main.model.CountriesRates;
import com.vat.rate.main.proxy.RatesServiceProxy;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("/standard_reduced_rates_microservice")
public class StandardRateRestAPI {

    @ConfigProperty(name = "standardrate.count")
    long STANDARD_RATE_COUNT;

    @RestClient
    RatesServiceProxy ratesServiceProxy;

    @GET
    @Path("/vat/standardrate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHighestThreeCountriesStandardRates() {

        List<String> countryList = new ArrayList<>();

        List<CountriesRates> countriesList = ratesServiceProxy.loadRatesJsonData().stream()
                .sorted(Comparator.comparing(CountriesRates::getStandard_rate).reversed())
                .limit(STANDARD_RATE_COUNT).toList();

        for (CountriesRates cr : countriesList) {
            countryList.add(cr.getCountry());
            countryList.add(String.valueOf(cr.getStandard_rate()));
        }
        return Response.ok(countryList).build();
    }
}
