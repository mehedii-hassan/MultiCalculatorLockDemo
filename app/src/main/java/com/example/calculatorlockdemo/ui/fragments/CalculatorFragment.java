package com.example.calculatorlockdemo.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.calculatorlockdemo.R;
import com.example.calculatorlockdemo.databinding.FragmentCalculatorBinding;

public class CalculatorFragment extends Fragment {

    private FragmentCalculatorBinding binding;
    private boolean showNormalCalculator = true;


    public CalculatorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCalculatorBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //super.onViewCreated(view, savedInstanceState);
        binding.fabScientificCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(showNormalCalculator){
                    binding.scientificCalculator.setVisibility(View.VISIBLE);
                    binding.normalCalculator.setVisibility(View.INVISIBLE);
                    showNormalCalculator = false;
                    binding.fabScientificCalculator.setImageResource(R.drawable.normal_cal_icon);
                }else{
                    binding.scientificCalculator.setVisibility(View.INVISIBLE);
                    binding.normalCalculator.setVisibility(View.VISIBLE);
                    binding.fabScientificCalculator.setImageResource(R.drawable.scientific_cal_icon);
                    showNormalCalculator = true;

                }
            }
        });
    }
}