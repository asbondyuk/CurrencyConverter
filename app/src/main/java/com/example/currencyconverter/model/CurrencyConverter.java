package com.example.currencyconverter.model;

import com.example.currencyconverter.pojo.CurrencyDTO;

public class CurrencyConverter {
    private CurrencyConverter() {
    }

    public static String convert(double convertibleRublesAmount, CurrencyDTO toCurrency) {
        double roundedResult = Math.ceil(convertibleRublesAmount / toCurrency.getValue() * toCurrency.getNominal() * 100) /100 ;

        return String.valueOf(roundedResult);
    }
}
