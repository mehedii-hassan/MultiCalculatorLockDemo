package com.example.calculatorlockdemo.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.calculatorlockdemo.R;
import com.example.calculatorlockdemo.adapters.FileNameAdapter;
import com.example.calculatorlockdemo.databinding.FragmentHomeBinding;
import com.example.calculatorlockdemo.utils.Constants;

import java.util.List;


public class HomeFragment extends Fragment {


    private FragmentHomeBinding binding;
    private String[] fileNames;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = com.example.calculatorlockdemo.databinding.FragmentHomeBinding
                .inflate(inflater, container, false);

        fileNames = getResources().getStringArray(R.array.FileNames);

        FileNameAdapter adapter = new FileNameAdapter(Constants.fileIconList(), fileNames);
        binding.rvHomeFragment.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        binding.rvHomeFragment.setAdapter(adapter);
        Log.e("TAG", "fileName " + fileNames.length + "icon le" + Constants.fileIconList().size());


        return binding.getRoot();
    }


}