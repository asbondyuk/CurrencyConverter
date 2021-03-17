package com.example.currencyconverter.cache;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {
    private final String KEY_JSON_FILE = "CBR_JSON_FILE";
    private final SharedPreferences sharedPreferences;

    public SharedPreferencesManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences("currencies", Context.MODE_PRIVATE);
    }

    public void saveData(String jsonFile) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_JSON_FILE, jsonFile);
        editor.apply();
    }

    public String loadData() {
        return sharedPreferences.getString(KEY_JSON_FILE, "");
    }

    public boolean isContainData() {
        return loadData().length() != 0;
    }
}
