package com.example.currencyconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ConverterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
    }

    public void convert (View view){
//        float  convertedRublesAmount = (float) view.findViewById(R.id.editTextConvertedRublesAmount).toString();

        TextView editTextView  = findViewById(R.id.textViewConvertedResult);
        editTextView.setText("clicked");
    }
}
