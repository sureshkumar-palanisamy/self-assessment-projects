package com.vat.rates.main.model;

import java.math.BigDecimal;

public record CountryRates (String country, BigDecimal standard_rate, BigDecimal reduced_rate) {}