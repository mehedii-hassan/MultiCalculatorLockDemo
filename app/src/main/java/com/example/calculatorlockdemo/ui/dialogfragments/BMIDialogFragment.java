package com.example.calculatorlockdemo.ui.dialogfragments;

import android.os.Bundle;

import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.calculatorlockdemo.R;
import com.example.calculatorlockdemo.databinding.FragmentBMIDialogBinding;

import java.text.DecimalFormat;


public class BMIDialogFragment extends DialogFragment {


    private FragmentBMIDialogBinding binding;
    private String gender;
    private String feet;
    private String inches;
    private String weight;
    private String age;
    private double yourHealthyWeighRangeStart;
    private double yourHealthyWeighRangeEnd;
    private double needWeightforHealthyWeight;
    private double reduceWeightForHealthyWeight;
    private final DecimalFormat df = new DecimalFormat("0.00");
    ;
    String fsYourHealthyWeightRange;
    String fsNeedWeightforHealthyWeightValue;
    String fsReduceWeightForHealthyWeightValue;


    public BMIDialogFragment(String gender, String feet, String inches, String weight, String age) {
        this.gender = gender;
        this.feet = feet;
        this.inches = inches;
        this.weight = weight;
        this.age = age;
    }

    public BMIDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentBMIDialogBinding.inflate(inflater, container, false);
        calculateBMI();


        binding.btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissNow();
            }
        });

        binding.fabThreeDotMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                popupMenu.inflate(R.menu.bmi_item_menu);
                popupMenu.setForceShowIcon(true);
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.item_about_bmi:
                                AboutBMIDialogFragment dialogFragment = new AboutBMIDialogFragment();
                                dialogFragment.show(getParentFragmentManager(), "about dialog");
                                return true;
                            case R.id.item_bmiCategory:
                                BMICategoryDialogFragment dialogFragment1 = new BMICategoryDialogFragment();
                                dialogFragment1.show(getParentFragmentManager(), "Classification");
                                return true;
                            default:
                                return false;
                        }
                    }
                });

            }
        });

        return binding.getRoot();

    }

    private void calculateBMI() {

        double ft = Double.parseDouble(feet);
        double inch = Double.parseDouble(inches);
        double yourWeight = Double.parseDouble(weight);
        double yourAge = Double.parseDouble(age);
        //convert inch to meter---------------
        double height = ((ft * 12) + inch) * 0.0254;
        double bmi = yourWeight / (height * height);
        binding.tvBMIScore.setText(df.format(bmi));


        if (bmi < 18.5) {
            yourHealthyWeighRangeStart = ((18.5 - bmi) + bmi) * (height * height);
            yourHealthyWeighRangeEnd = ((24.9 - bmi) + bmi) * (height * height);
            needWeightforHealthyWeight = yourHealthyWeighRangeStart - yourWeight;
        } else if (bmi < 25) {
            yourHealthyWeighRangeStart = 18.5 * (height * height);
            yourHealthyWeighRangeEnd = 24.9 * (height * height);
        } else {
            yourHealthyWeighRangeStart = (bmi - (bmi - 18.5)) * (height * height);
            yourHealthyWeighRangeEnd = (bmi - (bmi - 24.9)) * (height * height);
            reduceWeightForHealthyWeight = yourWeight - yourHealthyWeighRangeEnd;
        }
        //create Format string for setText
        fsYourHealthyWeightRange = df.format(yourHealthyWeighRangeStart) + " – " + df.format(yourHealthyWeighRangeEnd) + " kg";
        fsNeedWeightforHealthyWeightValue = df.format(needWeightforHealthyWeight) + " kg";
        fsReduceWeightForHealthyWeightValue = df.format(reduceWeightForHealthyWeight) + " kg";

        if (bmi < 18.5) {
            Log.e("TAG", "underweight bmi score " + bmi);
            bind(R.string.underWeight, R.string.desc_underweight, R.string.needWeightTxt, fsNeedWeightforHealthyWeightValue);
        } else if (bmi < 25) {
            Log.e("TAG", "healthy bmi score " + bmi);
            bind(R.string.healthyWeight, R.string.desc_healthy_weight, R.string.alreadyHealthyWeightTxt, "Alright");
        } else if (bmi < 30) {
            Log.e("TAG", "overweight bmi score " + bmi);
            bind(R.string.overWeight, R.string.desc_overweight, R.string.reduceWeightTxt, fsReduceWeightForHealthyWeightValue);
        } else if (bmi < 35) {
            Log.e("TAG", "obese one bmi score " + bmi);

            bind(R.string.obeseClassOne, R.string.desc_obesity_class_I, R.string.reduceWeightTxt, fsReduceWeightForHealthyWeightValue);
        } else if (bmi < 40) {
            Log.e("TAG", "obese two bmi score " + bmi);
            bind(R.string.obeseClassTwo, R.string.desc_obesity_class_I, R.string.reduceWeightTxt, fsReduceWeightForHealthyWeightValue);
        } else if (bmi >= 40) {
            Log.e("TAG", "obese three bmi score " + bmi);
            bind(R.string.obeseClassThree, R.string.desc_obesity_class_I, R.string.reduceWeightTxt, fsReduceWeightForHealthyWeightValue);
        } else {
            Log.e("TAG", "BMI = " + bmi);
        }
    }

    private void bind(int bmiConditionId, int bmiConditionDescId, int needOrReduceWeightTxt, String achieveOrReduceWeightTXT) {
        binding.tvBmiCondition.setText(bmiConditionId);
        binding.tvGender.setText(gender);
        binding.tvDesc.setText(bmiConditionDescId);
        binding.tvHWForYourHeightValue.setText(fsYourHealthyWeightRange);
        binding.tvAchieveOrReduceWeightTxt.setText(needOrReduceWeightTxt);
        binding.tvAchieveOrReduceValue.setText(achieveOrReduceWeightTXT);
    }
}