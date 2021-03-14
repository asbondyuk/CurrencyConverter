package com.example.currencyconverter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currencyconverter.R;
import com.example.currencyconverter.RecyclerViewOnClickInterface;
import com.example.currencyconverter.dto.DTOCurrency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


//https://www.google.com/search?q=recyclerview+onclick+item&oq=RecyclerView+onClick&aqs=chrome.3.69i57j0l6j69i60.3101j0j7&sourceid=chrome&ie=UTF-8#kpvalbx=_6CdOYKvMC8uyqwHVqbyAAw11
// https://www.youtube.com/watch?v=AkiltTv0CjA
// https://devcolibri.com/unit/%D1%83%D1%80%D0%BE%D0%BA-10-%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D0%B0-%D1%81-recyclerview-%D0%BD%D0%B0-%D0%BF%D1%80%D0%B8%D0%BC%D0%B5%D1%80%D0%B5-tweetsrecyclerview-2/
public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder> {
    private List<DTOCurrency> currencyList = new ArrayList<>();
    private RecyclerViewOnClickInterface recyclerViewOnClickInterface;
    private List<DTOCurrency> currencies;

    public CurrencyAdapter(RecyclerViewOnClickInterface recyclerViewOnClickInterface) {
        this.recyclerViewOnClickInterface = recyclerViewOnClickInterface;
    }

    class CurrencyViewHolder extends RecyclerView.ViewHolder {
        private TextView charCodeView;
        private TextView nameView;
        private TextView valueView;
        private TextView nominalView;
        private OnClickListener onClickListener;

        public CurrencyViewHolder(View itemView) {
            super(itemView);

            charCodeView = itemView.findViewById(R.id.charCode);
            nameView = itemView.findViewById(R.id.name);
            valueView = itemView.findViewById(R.id.value);
            nominalView = itemView.findViewById(R.id.nominal);

            itemView.setOnClickListener(v -> recyclerViewOnClickInterface.onItemClick(getAdapterPosition()));
        }

        public void bind(DTOCurrency dtoCurrency) {
            charCodeView.setText(dtoCurrency.getCharCode());
            nameView.setText(dtoCurrency.getName());
            nominalView.setText("Номинал: " + dtoCurrency.getNominal());
            valueView.setText(String.valueOf(dtoCurrency.getValue()));
        }
    }

    public void setItems(List<DTOCurrency> currencies) {
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
