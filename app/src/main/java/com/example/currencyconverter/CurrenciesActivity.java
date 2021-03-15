package com.example.currencyconverter;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currencyconverter.adapter.CurrencyAdapter;
import com.example.currencyconverter.dto.DTO;
import com.example.currencyconverter.dto.DTOCurrency;
import com.example.currencyconverter.util.DateFormat;
import com.example.currencyconverter.util.JSONParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CurrenciesActivity extends AppCompatActivity implements RecyclerViewOnClickInterface {
    private static final int PERMISSION_STORAGE_CODE = 1000;
    private static final String FILE_NAME = "daily_json.js";
    private static final String URL = "https://www.cbr-xml-daily.ru/daily_json.js";

    private CurrencyAdapter currencyAdapter;
    private RecyclerView currenciesRecyclerView;
    private RecyclerViewOnClickInterface recyclerViewOnClickInterface;
    private List<DTOCurrency> currencies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currencies);

        initRecyclerView();

        DTO dto = JSONParser.parse(Config.JSON_FILE);

        TextView date = findViewById(R.id.textCurrenciesDate);
        date.setText(DateFormat.dateFormat(dto.getDate()));

        currencies = new ArrayList<>(dto.getValute().values());
        currencyAdapter.setItems(currencies);
    }

    private void initRecyclerView() {
        currenciesRecyclerView = findViewById(R.id.recyclerview);
        currenciesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        currencyAdapter = new CurrencyAdapter(this);
        currenciesRecyclerView.setAdapter(currencyAdapter);
    }

    public void onClickGoToConverter(DTOCurrency dtoCurrency) {
        Intent intent = new Intent(CurrenciesActivity.this, ConverterActivity.class);
        intent.putExtra("currency for convert", dtoCurrency);
        startActivity(intent);
    }

    @Override
    public void onItemClick(int position) {

        onClickGoToConverter(currencies.get(position));
    }


    //https://www.youtube.com/watch?v=c-SDbITS_R4
    public void downloadFile(View view) {
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
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(URL));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);

        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "cbr");

        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_STORAGE_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startDownloading();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    public void save(View v) {
        String text = Config.JSON_FILE;
        FileOutputStream outputStream = null;

        try {
            outputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);
            outputStream.write(text.getBytes());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void load(View v) {
        FileInputStream inputStream = null;

        try {
            inputStream = openFileInput(FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            String text;
            while ((text = bufferedReader.readLine()) != null) {
                stringBuilder.append(text).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}