package com.example.calculatorlockdemo.ui.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.calculatorlockdemo.R;
import com.example.calculatorlockdemo.databinding.FragmentBMIBinding;
import com.example.calculatorlockdemo.ui.dialogfragments.BMIDialogFragment;


public class BMIFragment extends Fragment {

    private FragmentBMIBinding binding;
    private boolean isFemaleSelect = true;
    private boolean isMaleSelect = true;
    private Fragment f = this;

    public BMIFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //set status bar color -------------------------------------------------------------
        //requireActivity().getWindow().setStatusBarColor(Color.parseColor("#E91E63"));
        binding = FragmentBMIBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        binding.fabArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getFragmentManager().popBackStack();
            }
        });

        binding.cvMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.cvMale.setCardBackgroundColor(Color.parseColor("#E91E63"));
                binding.cvFemale.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                binding.ivMale.setColorFilter(Color.parseColor("#FFFFFFFF"));
                binding.ivFemale.setColorFilter(Color.parseColor("#E91E63"));
                isMaleSelect = true;
                isFemaleSelect = false;

            }
        });
        binding.cvFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.cvFemale.setCardBackgroundColor(Color.parseColor("#E91E63"));
                binding.cvMale.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                binding.ivFemale.setColorFilter(Color.parseColor("#FFFFFFFF"));
                binding.ivMale.setColorFilter(Color.parseColor("#605C5C"));
                isFemaleSelect = true;
                isMaleSelect = false;
            }
        });


        binding.fabIncrease.setOnClickListener(v -> {
            int temp = 0;
            String weightString = binding.etWeight.getText().toString();
            if (!weightString.isEmpty()) {
                int weight = Integer.parseInt(weightString);
                temp = weight + 10;
                binding.etWeight.setText(String.valueOf(temp));
            } else {
                binding.etWeight.setText(String.valueOf(temp + 10));
            }
        });
        binding.fabDecrease.setOnClickListener(v -> {
            int temp = 0;
            String weightString = binding.etWeight.getText().toString();
            if (!weightString.isEmpty()) {
                int weight = Integer.parseInt(weightString);
                if (weight > 0) {
                    temp = weight - 1;
                    binding.etWeight.setText(String.valueOf(temp));
                }
            } else {
                Toast.makeText(v.getContext(), "Your weight is empty", Toast.LENGTH_SHORT).show();
            }

        });
        binding.fabIncreaseAge.setOnClickListener(v -> {
            int temp = 0;
            String ageString = binding.etAge.getText().toString();
            if (!ageString.isEmpty()) {
                int age = Integer.parseInt(ageString);
                temp = age + 10;
                binding.etAge.setText(String.valueOf(temp));
            } else {
                binding.etAge.setText(String.valueOf(temp + 10));
            }
        });
        binding.fabDecreaseAge.setOnClickListener(v -> {
            int temp = 0;
            String ageString = binding.etAge.getText().toString();
            if (!ageString.isEmpty()) {
                int age = Integer.parseInt(ageString);
                if (age > 0) {
                    temp = age - 1;
                    binding.etAge.setText(String.valueOf(temp));
                }
            } else {
                Toast.makeText(v.getContext(), "Your age is empty", Toast.LENGTH_SHORT).show();
            }

        });


        binding.btnBMICalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String feet = binding.etFeet.getText().toString();
                String inches = binding.etInches.getText().toString();
                String weight = binding.etWeight.getText().toString();
                String age = binding.etAge.getText().toString();

                Log.e("TAG", "weight " + weight);

                if ((isFemaleSelect && isMaleSelect)) {
                    Toast.makeText(v.getContext(), "Please select your gender first", Toast.LENGTH_LONG).show();
                    return;
                }

                if (feet.isEmpty()) {
                    Toast.makeText(v.getContext(), "Please enter your feet of height", Toast.LENGTH_LONG).show();
                    return;
                }
                if (inches.isEmpty()) {
                    Toast.makeText(v.getContext(), "Please enter your inches of height", Toast.LENGTH_LONG).show();
                    return;
                }
                if (weight.isEmpty()) {
                    Toast.makeText(v.getContext(), "Please enter your weight", Toast.LENGTH_LONG).show();
                    return;
                }
                if (age.isEmpty()) {
                    Toast.makeText(v.getContext(), "Please enter your age", Toast.LENGTH_LONG).show();
                    return;
                }

                if (isMaleSelect) {
                    BMIDialogFragment dialogFragment = new BMIDialogFragment("Male", feet, inches, weight, age);
                    dialogFragment.show(getParentFragmentManager(), "dialog");
                    binding.etFeet.setText("");
                    binding.etInches.setText("");
                    binding.etWeight.setText("");
                    binding.etAge.setText("");


                } else if (isFemaleSelect) {
                    BMIDialogFragment dialogFragment = new BMIDialogFragment("Female", feet, inches, weight, age);
                    dialogFragment.show(getParentFragmentManager(), "dialog");
                    binding.etFeet.setText("");
                    binding.etInches.setText("");
                    binding.etWeight.setText("");
                    binding.etAge.setText("");

                }
            }
        });


    }
}