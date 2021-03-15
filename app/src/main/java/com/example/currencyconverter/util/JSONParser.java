package com.example.currencyconverter.util;

import com.example.currencyconverter.pojo.DTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONParser {
    private JSONParser() {
    }

    public static DTO parse(String jsonFile) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(jsonFile, DTO.class);
    }
}
