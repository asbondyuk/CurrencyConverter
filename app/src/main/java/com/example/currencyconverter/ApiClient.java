package com.example.currencyconverter;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// https://howtodoandroid.medium.com/retrofit-android-example-with-recyclerview-870e74e5b2ff
public class ApiClient {
    public static String BASE_URL = "https://www.cbr-xml-daily.ru/";
    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}