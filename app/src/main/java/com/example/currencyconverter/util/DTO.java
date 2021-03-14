package com.example.currencyconverter.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class DTO implements Serializable {
    private java.util.Date Date;
    private Date PreviousDate;
    private String PreviousURL;
    private Date Timestamp;
    private HashMap<String, DTOCurrency> Valute;

    public DTO(java.util.Date date, java.util.Date previousDate, String previousURL, java.util.Date timestamp, HashMap<String, DTOCurrency> valute) {
        Date = date;
        PreviousDate = previousDate;
        PreviousURL = previousURL;
        Timestamp = timestamp;
        Valute = valute;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public java.util.Date getPreviousDate() {
        return PreviousDate;
    }

    public String getPreviousURL() {
        return PreviousURL;
    }

    public java.util.Date getTimestamp() {
        return Timestamp;
    }

    public HashMap<String, DTOCurrency> getValute() {
        return Valute;
    }
}
