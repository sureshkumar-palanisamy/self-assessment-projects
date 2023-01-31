package com.vat.rates.standardreduced.main.dto;

import java.math.BigDecimal;

public record CountryRateDTO (String country, BigDecimal rate) {}