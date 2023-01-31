package com.vat.rates.standardreduced.main.model;

import java.math.BigDecimal;

public record CountriesRates (String country, BigDecimal standard_rate, BigDecimal reduced_rate) {}
