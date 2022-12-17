package com.example.calculatorlockdemo.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.calculatorlockdemo.databinding.FragmentCurrencyConverterBinding;
import com.example.calculatorlockdemo.utils.Constants;

public class CurrencyConverterFragment extends Fragment {


    private FragmentCurrencyConverterBinding binding;

    public CurrencyConverterFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCurrencyConverterBinding.inflate(inflater,container,false);
        //disable default keyboard when clicked edittext -----------------------
        binding.etOne.setShowSoftInputOnFocus(false);
        binding.fabAllClearCC.setImageBitmap(Constants.textAsBitmap("AC", 40, Color.parseColor("#ffffff")));
        return binding.getRoot();


    }
}