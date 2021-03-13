package com.example.currencyconverter.model;

public class CurrencyConverter {
    private CurrencyConverter() {
    }

    public static float convert(double convertibleRublesAmount, Currency toCurrency) {
        return (float) Math.ceil(convertibleRublesAmount / toCurrency.getValue() * toCurrency.getNominal() * 100) / 100;
    }
}
