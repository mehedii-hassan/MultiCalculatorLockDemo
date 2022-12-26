package com.example.calculatorlockdemo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calculatorlockdemo.R;
import com.example.calculatorlockdemo.callbacks.CurrencyCallback;
import com.example.calculatorlockdemo.databinding.CurrencyRowDesignBinding;
import com.example.calculatorlockdemo.ui.dialogfragments.ChangeCurrencyDialogFragment;

import java.util.List;

public class CurrencyNameAdapter extends RecyclerView.Adapter<CurrencyNameAdapter.CurrencyNameViewHolder> {

    private final List<Integer> countryFlags;
    private String[] currencyNames;
    private String[] currencyAbbreviations;
    private CurrencyCallback rowItemPosition;
    ChangeCurrencyDialogFragment dialogFragment;

    /*public CurrencyNameAdapter(Fragment fragment, List<Integer> countryFlags, String[] currencyNames, String[] currencyAbbreviations) {
        this.countryFlags = countryFlags;
        this.currencyNames = currencyNames;
        this.currencyAbbreviations = currencyAbbreviations;
        rowItemPosition = (CurrencyCallback) fragment;
    }*/
    public CurrencyNameAdapter(ChangeCurrencyDialogFragment dialogFragment, Fragment fragment, List<Integer> countryFlags, String[] currencyNames, String[] currencyAbbreviations) {
        this.countryFlags = countryFlags;
        this.currencyNames = currencyNames;
        this.currencyAbbreviations = currencyAbbreviations;
        this.dialogFragment = dialogFragment;
        rowItemPosition = (CurrencyCallback) fragment;
    }

    @NonNull
    @Override
    public CurrencyNameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CurrencyRowDesignBinding binding = CurrencyRowDesignBinding.inflate(LayoutInflater
                .from(parent.getContext()), parent, false);
        return new CurrencyNameViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyNameViewHolder holder, int position) {

        holder.binding.ivCountryFlag.setImageResource(countryFlags.get(position));
        holder.binding.tvCurrencyFullName.setText(currencyNames[position]);
        holder.binding.tvCurrencyAbbreviation.setText(currencyAbbreviations[position]);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowItemPosition.onCurrencyItemClickListener(holder.getAdapterPosition());
                dialogFragment.dismiss();
            }
        });

    }

    @Override
    public int getItemCount() {
        return countryFlags.size();
    }

    class CurrencyNameViewHolder extends RecyclerView.ViewHolder {
        CurrencyRowDesignBinding binding;

        public CurrencyNameViewHolder(CurrencyRowDesignBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
