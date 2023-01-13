package com.vat.rate.main.proxy;

import com.vat.rate.main.model.CountriesRates;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RegisterRestClient(baseUri = "com.vat.rate.main.proxy.RatesServiceProxy/mp-rest/url")
@Produces(MediaType.APPLICATION_JSON)
public interface RatesServiceProxy {

    @GET
    List<CountriesRates> loadRatesJsonData();
}
