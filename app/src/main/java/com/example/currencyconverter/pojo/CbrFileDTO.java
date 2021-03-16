package com.example.currencyconverter.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CbrFileDTO implements Parcelable {
    private java.util.Date Date;
    private Date PreviousDate;
    private String PreviousURL;
    private Date Timestamp;
    private Map<String, CurrencyDTO> Valute;

    public CbrFileDTO(java.util.Date date, java.util.Date previousDate, String previousURL, java.util.Date timestamp, HashMap<String, CurrencyDTO> valute) {
        Date = date;
        PreviousDate = previousDate;
        PreviousURL = previousURL;
        Timestamp = timestamp;
        Valute = valute;
    }

    protected CbrFileDTO(Parcel in) {
        PreviousURL = in.readString();
    }

    public static final Creator<CbrFileDTO> CREATOR = new Creator<CbrFileDTO>() {
        @Override
        public CbrFileDTO createFromParcel(Parcel in) {
            return new CbrFileDTO(in);
        }

        @Override
        public CbrFileDTO[] newArray(int size) {
            return new CbrFileDTO[size];
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

    public Map<String, CurrencyDTO> getValute() {
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
