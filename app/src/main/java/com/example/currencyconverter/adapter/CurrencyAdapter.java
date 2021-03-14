package com.example.currencyconverter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currencyconverter.R;
import com.example.currencyconverter.dto.DTOCurrency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder> {

    private List<DTOCurrency> currencyList = new ArrayList<>();

    class CurrencyViewHolder extends RecyclerView.ViewHolder {
        private TextView charCodeView;
        private TextView nameView;
        private TextView valueView;
        private TextView nominalView;

        public CurrencyViewHolder(View itemView) {
            super(itemView);

            charCodeView = itemView.findViewById(R.id.charCode);
            nameView = itemView.findViewById(R.id.name);
            valueView = itemView.findViewById(R.id.value);
            nominalView = itemView.findViewById(R.id.nominal);
        }

        public void bind(DTOCurrency dtoCurrency) {
            charCodeView.setText(dtoCurrency.getCharCode());
            nameView.setText(dtoCurrency.getName());
            nominalView.setText("Номинал: " + dtoCurrency.getNominal());
            valueView.setText(String.valueOf(dtoCurrency.getValue()));
        }
    }

    public void setItems(Collection<DTOCurrency> currencies) {
        currencyList.addAll(currencies);
        notifyDataSetChanged();
    }

    public void clearItems() {
        currencyList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new CurrencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder holder, int position) {
        holder.bind(currencyList.get(position));
    }

    @Override
    public int getItemCount() {
        return currencyList.size();
    }
}