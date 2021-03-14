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
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currencyconverter.adapter.CurrencyAdapter;
import com.example.currencyconverter.dto.DTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CurrenciesActivity extends AppCompatActivity {
    private static final int PERMISSION_STORAGE_CODE = 1000;

    private CurrencyAdapter currencyAdapter;
    private RecyclerView currenciesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currencies);

        initRecyclerView();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        DTO dto = gson.fromJson(Config.JSON_FILE, DTO.class);
        currencyAdapter.setItems(dto.getValute().values());
    }

    private void initRecyclerView() {
        currenciesRecyclerView = findViewById(R.id.recyclerview);
        currenciesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        currencyAdapter = new CurrencyAdapter();
        currenciesRecyclerView.setAdapter(currencyAdapter);
    }

    public void onClickGoToConverter(View view) {
        Intent intent = new Intent(CurrenciesActivity.this, ConverterActivity.class);
        startActivity(intent);
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
        String url = "https://www.cbr-xml-daily.ru/daily_json.js";

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Download");
        request.setDescription("Downloading file ...");

        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "daily_json.js");

        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_STORAGE_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startDownloading();
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}