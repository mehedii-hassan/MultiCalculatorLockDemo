package com.example.calculatorlockdemo.ui.fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
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
import com.example.calculatorlockdemo.utils.Constants;
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
        setOnClickListener(binding, this);
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
        binding.includeNormalCalculator.ivEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!result.equals("Err")) {
                    binding.tvInputString.setText(result);
                    binding.tvOutput.setText("");
                }
            }
        });
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

    private void setOnClickListener(FragmentCalculatorBinding binding, View.OnClickListener onClickListener) {
        binding.includeNormalCalculator.fabAllClear.setImageBitmap(Constants.textAsBitmap("AC", 40, Color.parseColor("#605C5C")));
        binding.includeScientificCalculator.fabAllClearSC.setImageBitmap(Constants.textAsBitmap("AC", 40, Color.parseColor("#605C5C")));
        binding.includeScientificCalculator.fabLog.setImageBitmap(Constants.textAsBitmap("log", 40, Color.parseColor("#605C5C")));
        binding.includeScientificCalculator.fabINV.setImageBitmap(Constants.textAsBitmap("INV", 40, Color.parseColor("#605C5C")));
        binding.includeScientificCalculator.fabCot.setImageBitmap(Constants.textAsBitmap("cot", 40, Color.parseColor("#605C5C")));
        binding.includeScientificCalculator.fabSin.setImageBitmap(Constants.textAsBitmap("sin", 40, Color.parseColor("#605C5C")));
        binding.includeScientificCalculator.fabCos.setImageBitmap(Constants.textAsBitmap("cos", 40, Color.parseColor("#605C5C")));
        binding.includeScientificCalculator.fabTan.setImageBitmap(Constants.textAsBitmap("tan", 40, Color.parseColor("#605C5C")));
        binding.includeScientificCalculator.fabFactorial.setImageBitmap(Constants.textAsBitmap("X!", 40, Color.parseColor("#605C5C")));
        binding.includeScientificCalculator.fabByX.setImageBitmap(Constants.textAsBitmap("1/x", 40, Color.parseColor("#605C5C")));
        binding.includeScientificCalculator.fabLN.setImageBitmap(Constants.textAsBitmap("ln", 40, Color.parseColor("#605C5C")));
        binding.includeScientificCalculator.fabSquare.setImageBitmap(Constants.textAsBitmap("X²", 40, Color.parseColor("#605C5C")));
        binding.includeScientificCalculator.fabOpenParenthesis.setImageBitmap(Constants.textAsBitmap("(", 40, Color.parseColor("#605C5C")));
        binding.includeScientificCalculator.fabCloseParenthesis.setImageBitmap(Constants.textAsBitmap(")", 40, Color.parseColor("#605C5C")));
        binding.includeScientificCalculator.fabE.setImageBitmap(Constants.textAsBitmap("e", 40, Color.parseColor("#605C5C")));
        //Change calculator mode from normal to scientific or scientific to normal--------------------------
        binding.fabNormalToScientificCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showNormalCalculator) {
                    binding.includeScientificCalculator.containerScientificCal.setVisibility(View.VISIBLE);
                    binding.includeNormalCalculator.containerNormalCal.setVisibility(View.INVISIBLE);
                    showNormalCalculator = false;
                    binding.tvInputString.setText("");
                    binding.tvOutput.setText("");
                    binding.fabNormalToScientificCal.setImageResource(R.drawable.normal_cal_icon);
                } else {
                    binding.includeScientificCalculator.containerScientificCal.setVisibility(View.INVISIBLE);
                    binding.includeNormalCalculator.containerNormalCal.setVisibility(View.VISIBLE);
                    binding.fabNormalToScientificCal.setImageResource(R.drawable.scientific_cal_icon);
                    showNormalCalculator = true;
                    binding.tvInputString.setText("");
                    binding.tvOutput.setText("");

                }
            }
        });
        //set on click listener in normal calculator----------------------------------
        binding.includeNormalCalculator.fabOne.setOnClickListener(this);
        binding.includeNormalCalculator.fabTwo.setOnClickListener(this);
        binding.includeNormalCalculator.fabThree.setOnClickListener(this);
        binding.includeNormalCalculator.fabFour.setOnClickListener(this);
        binding.includeNormalCalculator.fabFive.setOnClickListener(this);
        binding.includeNormalCalculator.fabSix.setOnClickListener(this);
        binding.includeNormalCalculator.fabSeven.setOnClickListener(this);
        binding.includeNormalCalculator.fabEight.setOnClickListener(this);
        binding.includeNormalCalculator.fabNine.setOnClickListener(this);
        binding.includeNormalCalculator.fabAddition.setOnClickListener(this);
        binding.includeNormalCalculator.fabSubtraction.setOnClickListener(this);
        binding.includeNormalCalculator.fabDivision.setOnClickListener(this);
        binding.includeNormalCalculator.fabMultiplication.setOnClickListener(this);
        binding.includeNormalCalculator.fabDeleteLeft.setOnClickListener(this);
        binding.includeNormalCalculator.fabAllClear.setOnClickListener(this);
        binding.includeNormalCalculator.fabModulus.setOnClickListener(this);
        binding.includeNormalCalculator.fabZero.setOnClickListener(this);
        binding.includeNormalCalculator.fabDot.setOnClickListener(this);
        binding.includeNormalCalculator.ivEqual.setOnClickListener(this);

        //set on click listener in scientific calculator--------------------------------------
        binding.includeScientificCalculator.fabZeroSC.setOnClickListener(this);
        binding.includeScientificCalculator.fabOneSC.setOnClickListener(this);
        binding.includeScientificCalculator.fabTwoSC.setOnClickListener(this);
        binding.includeScientificCalculator.fabThreeSC.setOnClickListener(this);
        binding.includeScientificCalculator.fabFourSC.setOnClickListener(this);
        binding.includeScientificCalculator.fabFiveSC.setOnClickListener(this);
        binding.includeScientificCalculator.fabSixSC.setOnClickListener(this);
        binding.includeScientificCalculator.fabSevenSC.setOnClickListener(this);
        binding.includeScientificCalculator.fabEightSC.setOnClickListener(this);
        binding.includeScientificCalculator.fabNineSC.setOnClickListener(this);
        binding.includeScientificCalculator.fabDotSC.setOnClickListener(this);
        binding.includeScientificCalculator.fabModulusSC.setOnClickListener(this);
        binding.includeScientificCalculator.fabPISc.setOnClickListener(this);
        binding.includeScientificCalculator.fabSquare.setOnClickListener(this);
        binding.includeScientificCalculator.fabLN.setOnClickListener(this);
        binding.includeScientificCalculator.fabFactorial.setOnClickListener(this);
        binding.includeScientificCalculator.fabLog.setOnClickListener(this);
        binding.includeScientificCalculator.fabTan.setOnClickListener(this);
        binding.includeScientificCalculator.fabCot.setOnClickListener(this);
        binding.includeScientificCalculator.fabRoot.setOnClickListener(this);
        binding.includeScientificCalculator.fabOpenParenthesis.setOnClickListener(this);
        binding.includeScientificCalculator.fabCloseParenthesis.setOnClickListener(this);
        binding.includeScientificCalculator.fabByX.setOnClickListener(this);
        binding.includeScientificCalculator.fabSin.setOnClickListener(this);
        binding.includeScientificCalculator.fabCos.setOnClickListener(this);
        binding.includeScientificCalculator.fabINV.setOnClickListener(this);
        binding.includeScientificCalculator.fabAllClearSC.setOnClickListener(this);
        binding.includeScientificCalculator.fabINV.setOnClickListener(this);
        binding.includeScientificCalculator.fabDeleteLeftSC.setOnClickListener(this);
        binding.includeScientificCalculator.fabDivisionSC.setOnClickListener(this);
        binding.includeScientificCalculator.fabMultiplicationSC.setOnClickListener(this);
        binding.includeScientificCalculator.fabSubtractionSC.setOnClickListener(this);
        binding.includeScientificCalculator.fabAdditionSC.setOnClickListener(this);
        binding.includeScientificCalculator.fabEqualSC.setOnClickListener(this);
    }
}
