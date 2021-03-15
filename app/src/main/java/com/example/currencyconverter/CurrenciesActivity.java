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
import com.example.currencyconverter.pojo.DTO;
import com.example.currencyconverter.pojo.DTOCurrency;
import com.example.currencyconverter.util.DateFormat;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//https://stackoverflow.com/questions/21335129/android-open-downloaded-file
public class CurrenciesActivity extends AppCompatActivity implements RecyclerViewOnClickInterface {
    public static final String TAG = "CurrenciesActivity";
    private static final int PERMISSION_STORAGE_CODE = 1000;

    private CurrencyAdapter currencyAdapter;

    private List<DTOCurrency> currencies;
    private DTO dto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currencies);

        initRecyclerView();

        download(findViewById(R.id.currenciesLayout));

        Log.d(TAG, "File downloading");
    }

    private void initRecyclerView() {
        RecyclerView currenciesRecyclerView = findViewById(R.id.recyclerview);
        currenciesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        currencyAdapter = new CurrencyAdapter(this);
        currenciesRecyclerView.setAdapter(currencyAdapter);

        Log.d(TAG, "RecyclerView init finish");
    }

    public void onClickGoToConverter(DTOCurrency dtoCurrency) {
        Intent intent = new Intent(CurrenciesActivity.this, ConverterActivity.class);
        intent.putExtra("currency for convert", dtoCurrency);
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
        Call<DTO> call = apiService.getCurrencies();

        call.enqueue(new Callback<DTO>() {
            @Override
            public void onResponse(Call<DTO> call, Response<DTO> response) {
                dto = response.body();

                TextView date = findViewById(R.id.textCurrenciesDate);
                date.setText(DateFormat.dateFormat(dto.getDate()));

                currencies = new ArrayList<>(dto.getValute().values());
                currencyAdapter.setItems(currencies);
            }

            @Override
            public void onFailure(Call<DTO> call, Throwable t) {
            }
        });
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

//    public void save(View v) {
//        String text = Config.JSON_FILE;
//        FileOutputStream outputStream = null;
//
//        try {
//            outputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);
//            outputStream.write(text.getBytes());
//
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (outputStream != null) {
//                try {
//                    outputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public void load(View v) {
//        FileInputStream inputStream = null;
//
//        try {
//            inputStream = openFileInput(FILE_NAME);
//            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//            StringBuilder stringBuilder = new StringBuilder();
//
//            String text;
//            while ((text = bufferedReader.readLine()) != null) {
//                stringBuilder.append(text).append("\n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (inputStream != null) {
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

}