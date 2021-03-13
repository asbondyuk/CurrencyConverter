package com.example.currencyconverter.model;

public class Currency {
    private String id;
    private String numCode;
    private String charCode;
    private int nominal;
    private String name;
    private float value;
    private float previousDateValue;

    public Currency(String id, String numCode, String charCode, int nominal, String name, float value, float previousDateValue) {
        this.id = id;
        this.numCode = numCode;
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
        this.previousDateValue = previousDateValue;
    }

    public String getId() {
        return id;
    }

    public String getNumCode() {
        return numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public int getNominal() {
        return nominal;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public double getPreviousDateValue() {
        return previousDateValue;
    }
}
