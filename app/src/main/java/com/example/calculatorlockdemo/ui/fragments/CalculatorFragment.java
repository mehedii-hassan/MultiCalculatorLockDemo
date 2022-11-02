package com.example.calculatorlockdemo.ui.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.calculatorlockdemo.R;
import com.example.calculatorlockdemo.databinding.FragmentCalculatorBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class CalculatorFragment extends Fragment implements View.OnClickListener {

    private FragmentCalculatorBinding binding;
    private boolean showNormalCalculator = true;
    private String userInputString = "";


    public CalculatorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCalculatorBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //super.onViewCreated(view, savedInstanceState);

        //Change calculator mode from normal to scientific or scientific to normal--------------------------
        binding.fabNormalToScientificCal.setOnClickListener(view1 -> {
            if (showNormalCalculator) {
                binding.scientificCalculatorId.setVisibility(View.VISIBLE);
                binding.normalCalculatorId.containerNormalCal.setVisibility(View.INVISIBLE);
                showNormalCalculator = false;
                binding.fabNormalToScientificCal.setImageResource(R.drawable.normal_cal_icon);
            } else {
                binding.scientificCalculatorId.setVisibility(View.INVISIBLE);
                binding.normalCalculatorId.containerNormalCal.setVisibility(View.VISIBLE);
                binding.fabNormalToScientificCal.setImageResource(R.drawable.scientific_cal_icon);
                showNormalCalculator = true;

            }
        });
        binding.normalCalculatorId.fabOne.setOnClickListener(this);
        binding.normalCalculatorId.fabTwo.setOnClickListener(this);
        binding.normalCalculatorId.fabThree.setOnClickListener(this);
        binding.normalCalculatorId.fabFour.setOnClickListener(this);
        binding.normalCalculatorId.fabFive.setOnClickListener(this);
        binding.normalCalculatorId.fabSix.setOnClickListener(this);
        binding.normalCalculatorId.fabSeven.setOnClickListener(this);
        binding.normalCalculatorId.fabEight.setOnClickListener(this);
        binding.normalCalculatorId.fabNine.setOnClickListener(this);
        binding.normalCalculatorId.fabAddition.setOnClickListener(this);
        binding.normalCalculatorId.fabSubtraction.setOnClickListener(this);
        binding.normalCalculatorId.fabDivision.setOnClickListener(this);
        binding.normalCalculatorId.fabMultiplication.setOnClickListener(this);
        binding.normalCalculatorId.fabDeleteLeft.setOnClickListener(this);
        binding.normalCalculatorId.fabAllClear.setOnClickListener(this);
        binding.normalCalculatorId.fabModulus.setOnClickListener(this);
        binding.normalCalculatorId.fabZero.setOnClickListener(this);
        binding.normalCalculatorId.fabDot.setOnClickListener(this);
        binding.normalCalculatorId.ivEqual.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.fabOne:
                userInputString += "1";
                binding.tvInputString.setText(userInputString);
                break;
            case R.id.fabTwo:
                userInputString += "2";
                binding.tvInputString.setText(userInputString);
                break;
            case R.id.fabThree:
                userInputString += "3";
                binding.tvInputString.setText(userInputString);
                break;
            case R.id.fabFour:
                userInputString += "4";
                binding.tvInputString.setText(userInputString);
                break;
            case R.id.fabFive:
                userInputString += "5";
                binding.tvInputString.setText(userInputString);
                break;
            case R.id.fabSix:
                userInputString += "6";
                binding.tvInputString.setText(userInputString);
                break;
            case R.id.fabSeven:
                userInputString += "7";
                binding.tvInputString.setText(userInputString);
                break;
            case R.id.fabEight:
                userInputString += "8";
                binding.tvInputString.setText(userInputString);
                break;
            case R.id.fabNine:
                userInputString += "9";
                binding.tvInputString.setText(userInputString);
                break;
            case R.id.fabZero:
                userInputString += "0";
                binding.tvInputString.setText(userInputString);
                break;
            case R.id.fabAddition:
                userInputString += "+";
                binding.tvInputString.setText(userInputString);
                break;
            case R.id.fabSubtraction:
                userInputString += "-";
                binding.tvInputString.setText(userInputString);
                break;
            case R.id.fabMultiplication:
                userInputString += "*";
                binding.tvInputString.setText(userInputString);
                break;
            case R.id.fabDivision:
                userInputString += "/";
                binding.tvInputString.setText(userInputString);
                break;
            case R.id.fabDeleteLeft:
                //int l = userInputString.length();
                if (userInputString.length() > 0) {
                    userInputString = userInputString.substring(0, userInputString.length() - 1);
                    binding.tvInputString.setText(userInputString);
                } else {
                    Log.e("TAG", "empty string");
                }
                break;

            default:
                Log.e("TAG", "Error");
                break;
        }


        String result = getResult(userInputString);
        if (!result.equals("Err")) {
            binding.tvOutput.setText(result);
        }
        if (view.getId() == R.id.ivEqual) {
            binding.tvInputString.setText("");
            userInputString = "";
            binding.tvOutput.setText(result);
        }
        if (view.getId() == R.id.fabAllClear) {
            binding.tvInputString.setText("");
            binding.tvOutput.setText("");
        }


    }

    String getResult(String userInputString) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, userInputString, "Javascript", 1, null).toString();
            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        } catch (Exception e) {
            return "Err";
        }
    }
}

/*TextView resultTv,solutionTv;



    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        solutionTv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            resultTv.setText(finalResult);
        }

    }

    String getResult(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }*/