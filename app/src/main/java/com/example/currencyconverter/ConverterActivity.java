package com.example.currencyconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.currencyconverter.dto.DTOCurrency;

public class ConverterActivity extends AppCompatActivity {
    private DTOCurrency currency;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        TextView charCode = findViewById(R.id.textCurrencyCode);
        TextView value = findViewById(R.id.textCurrencyValue);

        DTOCurrency currency = getIntent().getParcelableExtra("currency for convert");

        charCode.setText(currency.getCharCode());
        value.setText(String.valueOf(currency.getValue()));
    }

    public void convert(View view) {
//        float  convertedRublesAmount = (float) view.findViewById(R.id.editTextConvertedRublesAmount).toString();

        TextView editTextView = findViewById(R.id.textViewConvertedResult);
        editTextView.setText("clicked");
    }
}
