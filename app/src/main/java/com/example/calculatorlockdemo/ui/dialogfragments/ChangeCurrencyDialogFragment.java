package com.example.calculatorlockdemo.ui.dialogfragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.calculatorlockdemo.R;
import com.example.calculatorlockdemo.adapters.CurrencyNameAdapter;
import com.example.calculatorlockdemo.databinding.FragmentChangeCurrencyDialogBinding;
import com.example.calculatorlockdemo.ui.fragments.CurrencyConverterFragment;
import com.example.calculatorlockdemo.utils.Constants;

import java.util.List;


public class ChangeCurrencyDialogFragment extends DialogFragment {

    private FragmentChangeCurrencyDialogBinding binding;
    private List<Integer> countryFlags;
    private String[] currencyNames;
    private String[] currencyAbbreviations;
    CurrencyConverterFragment fragment;

    public ChangeCurrencyDialogFragment(CurrencyConverterFragment fragment) {
        // Required empty public constructor
        this.fragment = fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChangeCurrencyDialogBinding.inflate(inflater, container, false);
        countryFlags = Constants.countryFlagList();
        currencyNames = getResources().getStringArray(R.array.CurrencyNames);
        currencyAbbreviations = getResources().getStringArray(R.array.CurrencyCodes);

        binding.fabArrBackCCDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        CurrencyNameAdapter adapter = new CurrencyNameAdapter(this, fragment, countryFlags, currencyNames, currencyAbbreviations);
        binding.rvChangeCurrencyDialog.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        binding.rvChangeCurrencyDialog.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

}