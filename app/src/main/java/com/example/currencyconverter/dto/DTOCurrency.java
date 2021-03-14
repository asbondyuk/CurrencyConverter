package com.example.currencyconverter.dto;

import java.io.Serializable;

public class DTOCurrency implements Serializable {
    private String ID;
    private String NumCode;
    private String CharCode;
    private int Nominal;
    private String Name;
    private double Value;
    private double Previous;

    public DTOCurrency(String ID, String numCode, String charCode, int nominal, String name, double value, double previous) {
        this.ID = ID;
        NumCode = numCode;
        CharCode = charCode;
        Nominal = nominal;
        Name = name;
        Value = value;
        Previous = previous;
    }

    public String getID() {
        return ID;
    }

    public String getNumCode() {
        return NumCode;
    }

    public String getCharCode() {
        return CharCode;
    }

    public int getNominal() {
        return Nominal;
    }

    public String getName() {
        return Name;
    }

    public double getValue() {
        return Value;
    }

    public double getPrevious() {
        return Previous;
    }

    @Override
    public String toString() {
        return "{" + CharCode + ": " + Value + "}";
    }
}
