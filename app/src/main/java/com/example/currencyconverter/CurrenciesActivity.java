package com.example.currencyconverter;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currencyconverter.adapter.CurrencyAdapter;
import com.example.currencyconverter.adapter.RecyclerViewOnClickInterface;
import com.example.currencyconverter.cache.SharedPreferencesManager;
import com.example.currencyconverter.pojo.CbrFileDTO;
import com.example.currencyconverter.pojo.CurrencyDTO;
import com.example.currencyconverter.request.ApiClient;
import com.example.currencyconverter.request.ApiInterface;
import com.example.currencyconverter.util.DateFormat;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CurrenciesActivity extends AppCompatActivity implements RecyclerViewOnClickInterface {
    public static final String TAG = "CurrenciesActivity";
    private static final int PERMISSION_STORAGE_CODE = 1000;

    private CurrencyAdapter currencyAdapter;

    private List<CurrencyDTO> currencies;
    private CbrFileDTO cbrFileDto;

    private SharedPreferencesManager sharedPreferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currencies);

        sharedPreferencesManager = new SharedPreferencesManager(this);
        initRecyclerView();

        if (!useCache()) {
            download(findViewById(R.id.currenciesLayout));
        }
    }

    private void initRecyclerView() {
        RecyclerView currenciesRecyclerView = findViewById(R.id.recyclerview);
        currenciesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        currencyAdapter = new CurrencyAdapter(this);
        currenciesRecyclerView.setAdapter(currencyAdapter);

        Log.d(TAG, "RecyclerView init finish");
    }

    public void onClickGoToConverter(CurrencyDTO currencyDTO) {
        Intent intent = new Intent(CurrenciesActivity.this, ConverterActivity.class);
        intent.putExtra("currency for convert", currencyDTO);
        startActivity(intent);

        Log.d(TAG, "Goto ConverterActivity");
    }

    @Override
    public void onItemClick(int position) {
        onClickGoToConverter(currencies.get(position));

        Log.d(TAG, "RecyclerViewItem clicked");
    }

    public void download(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, PERMISSION_STORAGE_CODE);
            } else {
                startDownloading();
            }
        } else {
            startDownloading();
        }
    }

    private void startDownloading() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<CbrFileDTO> call = apiService.getCurrencies();

        call.enqueue(new Callback<CbrFileDTO>() {
            @Override
            public void onResponse(Call<CbrFileDTO> call, Response<CbrFileDTO> response) {
                cbrFileDto = response.body();
                Log.d(TAG, "Response received");

                fillActivityData(cbrFileDto);
            }

            @Override
            public void onFailure(Call<CbrFileDTO> call, Throwable t) {
            }
        });

        Toast.makeText(this, "Downloading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_STORAGE_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startDownloading();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void fillActivityData(CbrFileDTO cbrFileDto) {
        TextView date = findViewById(R.id.textCurrenciesDate);
        date.setText(DateFormat.dateFormatToString(cbrFileDto.getDate()));

        currencies = new ArrayList<>(cbrFileDto.getValute().values());
        currencyAdapter.setItems(currencies);

        Log.d(TAG, "CurrenciesActivity was filled data");
    }

    private boolean useCache() {
        if (sharedPreferencesManager.isContainData()) {
            fillActivityData(loadCache());

            Log.d(TAG, "Cache was used");
            return true;
        }

        return false;
    }

    private CbrFileDTO loadCache() {
        Gson gson = new Gson();
        String cache = sharedPreferencesManager.loadData();
        return gson.fromJson(cache, CbrFileDTO.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Gson gson = new Gson();
        String dataToCaching = gson.toJson(cbrFileDto);
        sharedPreferencesManager.saveData(dataToCaching);
    }
}