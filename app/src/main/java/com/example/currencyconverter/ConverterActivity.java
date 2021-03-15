package com.example.currencyconverter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.currencyconverter.model.CurrencyConverter;
import com.example.currencyconverter.pojo.DTOCurrency;

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
        EditText editText = findViewById(R.id.editTextConvertedRublesAmount);

        if(editText.getText()!= null){
            String converterResult = CurrencyConverter.convert(Double.parseDouble(editText.getText().toString()), currency);
            TextView textResult = findViewById(R.id.textEndedConvert);
            textResult.setVisibility(View.VISIBLE);

            TextView editTextView = findViewById(R.id.textViewConvertedResult);
        }
    }
}
