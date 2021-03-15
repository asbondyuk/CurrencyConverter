package com.example.currencyconverter;

import com.example.currencyconverter.pojo.DTO;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("daily_json.js")
    Call<DTO> getCurrencies();
}
