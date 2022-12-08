package com.example.calculatorlockdemo.ui.dialogfragments;

import android.os.Bundle;

import androidx.annotation.DrawableRes;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
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

        return binding.getRoot();

    }

    private void calculateBMI() {

        int ft = Integer.parseInt(feet);
        int inch = Integer.parseInt(inches);
        int w = Integer.parseInt(weight);
        int a = Integer.parseInt(age);
        double height = ((ft * 12) + inch) * 0.0254;//convert inches to meter

        double bmi = w / (height * height);

        DecimalFormat df = new DecimalFormat("0.00");
        String fs = df.format(bmi);
        binding.tvBMIScore.setText(fs);

        if (bmi < 18.5) {
            binding.tvBmiCondition.setText("Underweight");
            binding.tvGender.setText(gender);
            binding.tvDesc.setText(getResources().getString(R.string.desc_underweight));
        } else if (bmi >= 18.5 && bmi < 25) {
            binding.tvBmiCondition.setText("Healthy weight");
            binding.tvDesc.setText(getResources().getString(R.string.desc_healthy_weight));
        } else if (bmi >= 30) {
            binding.tvBmiCondition.setText("Overweight");
            binding.tvDesc.setText(getResources().getString(R.string.desc_overweight));
        }


        /* if(intbmi<16)
        {

        else if(intbmi<16.9 && intbmi>16)
        {
            mbmicategory.setText("Moderate Thinness");
            mbackground.setBackgroundColor(R.color.halfwarn);
            mimageview.setImageResource(R.drawable.warning);
         //   mimageview.setBackground(colorDrawable2);

        }
        else if(intbmi<18.4 && intbmi>17)
        {
            mbmicategory.setText("Mild Thinness");
            mbackground.setBackgroundColor(R.color.halfwarn);
            mimageview.setImageResource(R.drawable.warning);
       //   mimageview.setBackground(colorDrawable2);
        }
        else if(intbmi<24.9 && intbmi>18.5 )
        {
            mbmicategory.setText("Normal");
            mimageview.setImageResource(R.drawable.ok);
           // mbackground.setBackgroundColor(Color.YELLOW);
          //  mimageview.setBackground(colorDrawable2);
        }
        else if(intbmi <29.9 && intbmi>25)
        {
            mbmicategory.setText("Overweight");
            mbackground.setBackgroundColor(R.color.halfwarn);
            mimageview.setImageResource(R.drawable.warning);
            //mimageview.setBackground(colorDrawable2);
        }
        else if(intbmi<34.9 && intbmi>30)
        {
            mbmicategory.setText("Obese Class I");
            mbackground.setBackgroundColor(R.color.halfwarn);
            mimageview.setImageResource(R.drawable.warning);
          //  mimageview.setBackground(colorDrawable2);
        }
        else
        {
            mbmicategory.setText("Obese Class II");
            mbackground.setBackgroundColor(R.color.warn);
            mimageview.setImageResource(R.drawable.crosss);
          //  mimageview.setBackground(colorDrawable2);
        }*/

    }
}