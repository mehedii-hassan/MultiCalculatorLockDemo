package com.example.calculatorlockdemo.ui.dialogfragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.calculatorlockdemo.R;
import com.example.calculatorlockdemo.databinding.FragmentAboutBMIDialogBinding;


public class AboutBMIDialogFragment extends DialogFragment {


    private FragmentAboutBMIDialogBinding binding;

    public AboutBMIDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAboutBMIDialogBinding.inflate(inflater, container, false);
        binding.btnCloseAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return binding.getRoot();
    }

    //custom dialogFragment width set maximize----------------------
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = 650;
            // int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }
}