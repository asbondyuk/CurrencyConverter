package com.example.currencyconverter.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DTO implements Parcelable {
    private java.util.Date Date;
    private Date PreviousDate;
    private String PreviousURL;
    private Date Timestamp;
    private Map<String, DTOCurrency> Valute;

    public DTO(java.util.Date date, java.util.Date previousDate, String previousURL, java.util.Date timestamp, HashMap<String, DTOCurrency> valute) {
        Date = date;
        PreviousDate = previousDate;
        PreviousURL = previousURL;
        Timestamp = timestamp;
        Valute = valute;
    }

    protected DTO(Parcel in) {
        PreviousURL = in.readString();
    }

    public static final Creator<DTO> CREATOR = new Creator<DTO>() {
        @Override
        public DTO createFromParcel(Parcel in) {
            return new DTO(in);
        }

        @Override
        public DTO[] newArray(int size) {
            return new DTO[size];
        }
    };

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

    public Map<String, DTOCurrency> getValute() {
        return Valute;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(PreviousURL);
    }
}
