package com.example.currencyconverter.model;

import com.example.currencyconverter.util.DTOCurrency;

public class CurrencyConverter {
    private CurrencyConverter() {
    }

    public static float convert(double convertibleRublesAmount, DTOCurrency toCurrency) {
        return (float) Math.ceil(convertibleRublesAmount / toCurrency.getValue() * toCurrency.getNominal() * 100) / 100;
    }
}
