package com.example.currencyconverter.request;

import com.example.currencyconverter.pojo.CbrFileDTO;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("daily_json.js")
    Call<CbrFileDTO> getCurrencies();
}
