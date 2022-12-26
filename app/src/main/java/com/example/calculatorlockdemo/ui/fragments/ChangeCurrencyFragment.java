package com.example.calculatorlockdemo.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.calculatorlockdemo.R;
import com.example.calculatorlockdemo.callbacks.CurrencyCallback;
import com.example.calculatorlockdemo.databinding.FragmentChangeCurrencyBinding;
import com.example.calculatorlockdemo.utils.Constants;

import java.util.List;


public class ChangeCurrencyFragment extends Fragment implements CurrencyCallback {


    private FragmentChangeCurrencyBinding binding;
    private List<Integer> countryFlags;
    private String[] currencyNames;
    private String[] currencyAbbreviations;
    private int id;

    public ChangeCurrencyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChangeCurrencyBinding.inflate(inflater, container, false);
        binding.fabArrowBackChangeCurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.actionChangeCFToCCF);
            }
        });
        countryFlags = Constants.countryFlagList();
        currencyNames = getResources().getStringArray(R.array.CurrencyNames);
        currencyAbbreviations = getResources().getStringArray(R.array.CurrencyCodes);
        if(getArguments()!=null){
            id = getArguments().getInt("currency_id");
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      /*  CurrencyNameAdapter adapter = new CurrencyNameAdapter(this,countryFlags, currencyNames, currencyAbbreviations);
        binding.rvCurrencyNames.setLayoutManager(new LinearLayoutManager(view.getContext()));
        binding.rvCurrencyNames.setAdapter(adapter);*/

    }

    @Override
    public void onCurrencyItemClickListener(int position) {
        final  Bundle bundle = new Bundle();
        bundle.putInt("position",position);
        bundle.putInt("id",id);
        Navigation.findNavController(binding.getRoot()).navigate(R.id.actionChangeCFToCCF,bundle);
        Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();
    }
}