package com.example.calculatorlockdemo.ui.fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
    private String outputString = "";
    private Context context;
    android.content.Context context1;


    public CalculatorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCalculatorBinding.inflate(inflater, container, false);
        context1 = getContext();
        //Disable edittext default keyboard programmatically---------------
        binding.etUserInput.setShowSoftInputOnFocus(false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //Set onclick listener for view ----------------------------------
        setOnClickListener();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.fabCurrencyConverter:
                Navigation.findNavController(view).navigate(R.id.currencyConverterFragment);
                Toast.makeText(context1, "Currency Converter", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fabBMI:
                Navigation.findNavController(view).navigate(R.id.BMIFragment);
                Toast.makeText(context1, "BMI Calculator", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fabSwitchNormalToScientificCal:
                switchNormalToScientificCal();
                break;
            case R.id.fabZero:
            case R.id.fabZeroSC:
                createUserInputString("0");
                break;
            case R.id.fabOne:
            case R.id.fabOneSC:
                createUserInputString("1");
                break;
            case R.id.fabTwo:
            case R.id.fabTwoSC:
                createUserInputString("2");
                break;
            case R.id.fabThree:
            case R.id.fabThreeSC:
                createUserInputString("3");
                break;
            case R.id.fabFour:
            case R.id.fabFourSC:
                createUserInputString("4");
                break;
            case R.id.fabFive:
            case R.id.fabFiveSC:
                createUserInputString("5");
                break;
            case R.id.fabSix:
            case R.id.fabSixSC:
                createUserInputString("6");
                break;
            case R.id.fabSeven:
            case R.id.fabSevenSC:
                createUserInputString("7");
                break;
            case R.id.fabEight:
            case R.id.fabEightSC:
                createUserInputString("8");
                break;
            case R.id.fabNine:
            case R.id.fabNineSC:
                createUserInputString("9");
                break;
            case R.id.fabAddition:
            case R.id.fabAdditionSC:
                createUserInputString("+");
                break;
            case R.id.fabSubtraction:
            case R.id.fabSubtractionSC:
                createUserInputString("-");
                break;
            case R.id.fabMultiplication:
            case R.id.fabMultiplicationSC:
                createUserInputString("×");
                break;
            case R.id.fabDivision:
            case R.id.fabDivisionSC:
                createUserInputString("÷");
                break;
            case R.id.fabModulus:
            case R.id.fabModulusSC:
                createUserInputString("%");
                break;
            case R.id.fabDot:
            case R.id.fabDotSC:
                createUserInputString(".");
                break;
            case R.id.fabDeleteLeft:
            case R.id.fabDeleteLeftSC:
                createUserInputString("dl");
                break;
            case R.id.fabSin:
                createUserInputString("sin(");
                break;
            case R.id.fabCos:
                createUserInputString("cos(");
                break;
            case R.id.fabINV:
                //createUserInputString("sin(");
                //Toast.makeText(context1, "", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fabLog:
                createUserInputString("log");
                break;
            case R.id.fabTan:
                createUserInputString("tan");
                break;
            case R.id.fabCot:
                createUserInputString("cot");
                break;
            case R.id.fabRoot:
                createUserInputString("√");
                break;
            case R.id.fabFactorial:
                //createUserInputString("fact");
                break;
            case R.id.fabLN:
                createUserInputString("ln");
                break;
            case R.id.fabSquare:
                squareCalculate();
                break;
            case R.id.fabPISc:
                createUserInputString("3.14");
                break;
            case R.id.fabE:
                createUserInputString("e");
                break;
            case R.id.fabOpenParenthesis:
                createUserInputString("(");
                break;
            case R.id.fabCloseParenthesis:
                createUserInputString(")");
                break;
            case R.id.fabByX:
                //createUserInputString("");
                break;
            case R.id.ivEqual:
            case R.id.ivEqualSC:
                equalClicked();
                break;
            case R.id.fabAllClear:
            case R.id.fabAllClearSC:
                allClear();
                break;
            default:
                Log.e("TAG", "Error");
                break;
        }
    }

    private void squareCalculate() {
        if (userInputString.isEmpty()) {
            binding.etUserInput.setText("");
            return;
        }
        boolean lastCharSquareOrNot = checkLastCharSquareOrNot(userInputString.charAt(userInputString.length() - 1));
        if (lastCharSquareOrNot) {
            binding.etUserInput.setText(userInputString);
            return;
        }
        String temp = userInputString;
        userInputString = userInputString + "²";
        int userInput = Integer.parseInt(temp);
        int res = userInput * userInput;
        outputString = String.valueOf(res);
        binding.etUserInput.setText(userInputString);
        binding.tvOutput.setText(String.valueOf(outputString));
        userInputString=outputString;

    }

    private boolean checkLastCharSquareOrNot(char ch) {
        return ch == '²';
    }


    //Create user input string for calculation----------------------------
    private void createUserInputString(String userInput) {

        //Press delete left button then delete one by one from left to right til string length
        if (userInput.equals("dl") && userInputString.length() > 0) {
            userInputString = userInputString.substring(0, userInputString.length() - 1);
            binding.etUserInput.setText(userInputString);
        }

        if (isNumeric(userInput)) {
            userInputString = userInputString + userInput;
            binding.etUserInput.setText(userInputString);
        } else {

            if (userInput.equals(".") && userInputString.length() == 0) {
                userInputString = userInputString + "0.";
                binding.etUserInput.setText(userInputString);
            }
            //Check user input length 0 or not --------------------------------------------
            //if it's 0 then click operator but it won't work------------------------------
            if (userInputString.length() != 0) {
                int l = userInputString.length();
                //Check user input inputStringLastCharOperator or not-----------------------------------------
                boolean inputStringLastCharOperator = checkLastCharOperatorOrNot(userInputString.charAt(l - 1));
                boolean lastCharPointOrNot = checkPointOrNot(userInputString.charAt(l - 1));
                if (lastCharPointOrNot && isNumeric(userInput)) {
                    if (userInput.equals(".")) {
                        Log.e("TAG", "nothing to do");
                    } else {
                        userInputString = userInputString + userInput;
                        binding.etUserInput.setText(userInputString);
                    }
                } else if (userInput.equals("+") || userInput.equals("-") || userInput.equals("×") || userInput.equals("÷") || userInput.equals(".") || userInput.equals("%")) {
                    if (!inputStringLastCharOperator) {
                        userInputString = userInputString + userInput;
                        binding.etUserInput.setText(userInputString);
                    }
                }
            }
        }


        String finalString = userInputString.replaceAll("×", "*").replaceAll("÷", "/");
        outputString = getResult(finalString);
        if (!outputString.equals("Err")) {
            binding.tvOutput.setText(outputString);
        }
    }


    private boolean checkPointOrNot(char c) {
        return c == '.';
    }

    private boolean checkLastCharOperatorOrNot(char ch) {
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

    private void switchNormalToScientificCal() {
        if (showNormalCalculator) {
            binding.includeScientificCalculator.containerScientificCal.setVisibility(View.VISIBLE);
            binding.includeNormalCalculator.containerNormalCal.setVisibility(View.INVISIBLE);
            showNormalCalculator = false;
            binding.etUserInput.setText("");
            binding.tvOutput.setText("");
            outputString = "";
            binding.fabSwitchNormalToScientificCal.setImageResource(R.drawable.normal_cal_icon);
        } else {
            binding.includeScientificCalculator.containerScientificCal.setVisibility(View.INVISIBLE);
            binding.includeNormalCalculator.containerNormalCal.setVisibility(View.VISIBLE);
            binding.fabSwitchNormalToScientificCal.setImageResource(R.drawable.scientific_cal_icon);
            showNormalCalculator = true;
            binding.etUserInput.setText("");
            binding.tvOutput.setText("");
            outputString = "";

        }

    }

    private void allClear() {
        binding.etUserInput.setText("");
        binding.tvOutput.setText("");
        userInputString = "";
    }

    private void equalClicked() {
        if (userInputString.equals("1111")) {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.actionToHomeFragment);
        }
        if (!outputString.equals("Err")) {
            binding.etUserInput.setText(outputString);
            binding.tvOutput.setText("");
            userInputString = outputString;
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


    private void setOnClickListener() {
        //set text as image Bitmap-----------------------------------
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

        binding.fabSwitchNormalToScientificCal.setOnClickListener(this);
        binding.fabBMI.setOnClickListener(this);
        binding.fabCurrencyConverter.setOnClickListener(this);
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
        binding.includeScientificCalculator.ivEqualSC.setOnClickListener(this);
    }
}
