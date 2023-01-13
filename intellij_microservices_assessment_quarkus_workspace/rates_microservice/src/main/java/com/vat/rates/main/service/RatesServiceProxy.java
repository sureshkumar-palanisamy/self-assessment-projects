package com.vat.rates.main.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(baseUri = "com.vat.rates.main.service.RatesServiceProxy/mp-rest/url")
@Produces(MediaType.APPLICATION_JSON)
public interface RatesServiceProxy {

    @GET
    JsonNode loadRatesJsonData();
}
