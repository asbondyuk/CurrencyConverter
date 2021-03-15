package com.example.currencyconverter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.currencyconverter.pojo.DTOCurrency;
import com.example.currencyconverter.model.CurrencyConverter;

public class ConverterActivity extends AppCompatActivity {
    public static final String TAG = "ConverterActivity";

    private DTOCurrency currency;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        findViewById(R.id.textEndedConvert).setVisibility(View.INVISIBLE);

        TextView charCode = findViewById(R.id.textCurrencyCode);
        TextView value = findViewById(R.id.textCurrencyValue);
        DTOCurrency currency = getIntent().getParcelableExtra("currency for convert");

        charCode.setText(currency.getCharCode());
        value.setText(String.valueOf(currency.getValue()));
    }

    public void convert(View view) {
        Log.i(TAG, "Convert button pushed");

        Log.i(TAG, "CurrencyConverter is running");
        String converterResult = CurrencyConverter.convert(100, currency);
        Log.i(TAG, "CurrencyConverter finished");

        TextView textResult = findViewById(R.id.textEndedConvert);
        textResult.setVisibility(View.VISIBLE);

        TextView editTextView = findViewById(R.id.textViewConvertedResult);
        editTextView.setText("100");
        Log.i(TAG, "Show result converting");
    }
}
