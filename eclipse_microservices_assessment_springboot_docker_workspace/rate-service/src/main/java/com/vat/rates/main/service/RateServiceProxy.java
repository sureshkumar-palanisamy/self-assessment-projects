package com.vat.rates.main.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import feign.Response;

@FeignClient(name="${spring.application.name}", url="${endpoint.rates}")
public interface RateServiceProxy {

	@GetMapping
	Response loadRatesJsonData();
}
