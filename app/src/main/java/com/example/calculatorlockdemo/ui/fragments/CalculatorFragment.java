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

import com.example.calculatorlockdemo.R;
import com.example.calculatorlockdemo.databinding.FragmentCalculatorBinding;
import com.faendir.rhino_android.RhinoAndroidHelper;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class CalculatorFragment extends Fragment implements View.OnClickListener {

    private FragmentCalculatorBinding binding;
    private boolean showNormalCalculator = true;
    private String userInputString = "";
    private Context context;
    android.content.Context context1;


    public CalculatorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCalculatorBinding.inflate(inflater, container, false);
        context1 = getContext();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //super.onViewCreated(view, savedInstanceState);

        //Set onclick listener for view ----------------------------------
        setOnClickListener(binding);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.fabZero:
                createUserInputString("0");
                break;
            case R.id.fabOne:
                createUserInputString("1");
                break;
            case R.id.fabTwo:
                createUserInputString("2");
                break;
            case R.id.fabThree:
                createUserInputString("3");
                break;
            case R.id.fabFour:
                createUserInputString("4");
                break;
            case R.id.fabFive:
                createUserInputString("5");
                break;
            case R.id.fabSix:
                createUserInputString("6");
                break;
            case R.id.fabSeven:
                createUserInputString("7");
                break;
            case R.id.fabEight:
                createUserInputString("8");
                break;
            case R.id.fabNine:
                createUserInputString("9");
                break;
            case R.id.fabAddition:
                createUserInputString("+");
                break;
            case R.id.fabSubtraction:
                createUserInputString("-");
                break;
            case R.id.fabMultiplication:
                createUserInputString("×");
                break;
            case R.id.fabDivision:
                createUserInputString("÷");
                break;
            case R.id.fabModulus:
                createUserInputString("%");
                break;
            case R.id.fabDot:
                createUserInputString(".");
                break;
            case R.id.fabDeleteLeft:
                createUserInputString("dl");
                break;

            default:
                Log.e("TAG", "Error");
                break;
        }


        if (view.getId() == R.id.fabAllClear) {
            binding.tvInputString.setText("");
            binding.tvOutput.setText("");
            userInputString = "";
        }


    }

    //Create user input string for calculation----------------------------
    private void createUserInputString(String userInput) {

        //Press delete left button then delete one by one from left to right til string length
        if (userInput.equals("dl") && userInputString.length() > 0) {
            userInputString = userInputString.substring(0, userInputString.length() - 1);
            binding.tvInputString.setText(userInputString);
        }

        if (isNumeric(userInput)) {
            userInputString = userInputString + userInput;
            binding.tvInputString.setText(userInputString);
        } else {

            if (userInput.equals(".") && userInputString.length() == 0) {
                userInputString = userInputString + "0.";
                binding.tvInputString.setText(userInputString);
            }
            //Check user input length 0 or not --------------------------------------------
            //if it's 0 then click operator but it won't work------------------------------
            if (userInputString.length() != 0) {
                int l = userInputString.length();
                //Check user input inputStringLastCharOperator or not-----------------------------------------
                boolean inputStringLastCharOperator = checkOperatorOrNot(userInputString.charAt(l - 1));
                boolean lastCharPointOrNot = checkPointOrNot(userInputString.charAt(l - 1));
                if (lastCharPointOrNot && isNumeric(userInput)) {
                    if (userInput.equals(".")) {
                        Log.e("TAG", "nothing to do");
                    } else {
                        userInputString = userInputString + userInput;
                        binding.tvInputString.setText(userInputString);
                    }
                } else if (userInput.equals("+") || userInput.equals("-") || userInput.equals("×") || userInput.equals("÷") || userInput.equals(".")) {
                    if (!inputStringLastCharOperator) {
                        userInputString = userInputString + userInput;
                        binding.tvInputString.setText(userInputString);
                    }
                }
            }
        }

        String finalString = userInputString.replaceAll("×", "*").replaceAll("÷", "/");
        String result = getResult(finalString);
        if (!result.equals("Err")) {
            binding.tvOutput.setText(result);
        }
    }

    private boolean checkPointOrNot(char c) {
        return c == '.';
    }

    private boolean checkOperatorOrNot(char ch) {
        return ch == '+' || ch == '-' || ch == '×' || ch == '÷' || ch == '.';
    }


    String getResult(String userInputString) {
        try {

            RhinoAndroidHelper rhinoAndroidHelper = new RhinoAndroidHelper(context1);
            context = rhinoAndroidHelper.enterContext();
            context.setOptimizationLevel(1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, userInputString, "Javascript", 1, null).toString();
            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        } catch (Exception e) {
            Log.e("TAG4", e.getLocalizedMessage());
            return "Err";
        }
    }


    //Check given input number or not----------------------------------------
    //if it's a number then return true otherwise false----------------------
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void setOnClickListener(FragmentCalculatorBinding binding) {
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
}
