package com.example.currencyconverter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.currencyconverter.model.CurrencyConverter;
import com.example.currencyconverter.pojo.CurrencyDTO;

public class ConverterActivity extends AppCompatActivity {
    public static final String TAG = "ConverterActivity";

    private CurrencyDTO currency;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        findViewById(R.id.textConvertResultHelper).setVisibility(View.INVISIBLE);

        this.currency = getIntent().getParcelableExtra("currency for convert");

        TextView charCode = findViewById(R.id.textCurrencyCode);
        charCode.setText(currency.getCharCode());

        TextView value = findViewById(R.id.textCurrencyValue);
        value.setText(String.valueOf(currency.getValue()));

        TextView nominal = findViewById(R.id.nominal);
        nominal.setText("Номинал: " + currency.getNominal());
    }

    public void convert(View view) {
        EditText editText = findViewById(R.id.editTextConvertAmount);
        String ed_text = editText.getText().toString().trim();

        if (ed_text.isEmpty() || ed_text.length() == 0 || ed_text.equals("") || ed_text == null) {
            Toast.makeText(this, "Need value", Toast.LENGTH_SHORT).show();
        } else {

            TextView textResult = findViewById(R.id.textConvertResultHelper);
            textResult.setVisibility(View.VISIBLE);

            String converterResult = CurrencyConverter.convert(Double.parseDouble(editText.getText().toString()), currency);
            TextView editTextView = findViewById(R.id.textConvertedResult);
            editTextView.setText(converterResult);
            editTextView.setVisibility(View.VISIBLE);
        }

        Log.d(TAG, "Show result converting");
    }
}