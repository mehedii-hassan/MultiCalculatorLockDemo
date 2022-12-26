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
import com.example.calculatorlockdemo.callbacks.CurrencyCallback;
import com.example.calculatorlockdemo.databinding.FragmentCurrencyConverterBinding;
import com.example.calculatorlockdemo.db.DB;
import com.example.calculatorlockdemo.entities.CurrencySymbolResponseModel;
import com.example.calculatorlockdemo.entities.CurrencyResponseModel;
import com.example.calculatorlockdemo.entities.Rates;
import com.example.calculatorlockdemo.network.RetrofitService;
import com.example.calculatorlockdemo.ui.dialogfragments.ChangeCurrencyDialogFragment;
import com.example.calculatorlockdemo.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyConverterFragment extends Fragment implements View.OnClickListener, CurrencyCallback {


    private FragmentCurrencyConverterBinding binding;
    private String userInputString = "";
    private String outputStringOne = "";
    private String outputStringTwo = "";
    private int position;
    private int id;
    int etAmountId;
    private List<Integer> countryFlags;
    private String[] currencyCodes;
    private String[] currencyNames;
    final private String api_key = "424e97dc913148ae9175b4fec3760462";


    public CurrencyConverterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCurrencyConverterBinding.inflate(inflater, container, false);
        //disable default keyboard when clicked edittext -----------------------
        binding.etUserCurrencyAmount.setShowSoftInputOnFocus(false);
        binding.etUserCurrencyAmountOne.setShowSoftInputOnFocus(false);
        binding.etUserCurrencyAmountTwo.setShowSoftInputOnFocus(false);
        binding.fabAllClearCC.setImageBitmap(Constants.textAsBitmap("AC", 40, Color.parseColor("#ffffff")));


        countryFlags = Constants.countryFlagList();
        currencyNames = getResources().getStringArray(R.array.CurrencyNames);
        currencyCodes = getResources().getStringArray(R.array.CurrencyCodes);


        //default country flag setting---------
        binding.ivFlag.setImageResource(countryFlags.get(4));
        binding.ivFlagOne.setImageResource(countryFlags.get(3));
        binding.ivFlagTwo.setImageResource(countryFlags.get(2));
        //data receive from  ChangeCurrencyFragment
        if (getArguments() != null) {
            position = getArguments().getInt("position");
            id = getArguments().getInt("id");
            bind(id, position);
        }

        return binding.getRoot();


    }

    private void bind(int id, int position) {
        if (id == R.id.tvCurrencyCode) {
            binding.ivFlag.setImageResource(countryFlags.get(position));
            binding.tvCurrencyCode.setText(currencyCodes[position]);
            binding.tvCurrencyName.setText(currencyNames[position]);

        } else if (id == R.id.tvCurrencyCodeOne) {
            binding.ivFlagOne.setImageResource(countryFlags.get(position));
            binding.tvCurrencyCodeOne.setText(currencyCodes[position]);
            binding.tvCurrencyNameOne.setText(currencyNames[position]);
        } else if (id == R.id.tvCurrencyCodeTwo) {
            binding.ivFlagTwo.setImageResource(countryFlags.get(position));
            binding.tvCurrencyCodeTwo.setText(currencyCodes[position]);
            binding.tvCurrencyNameTwo.setText(currencyNames[position]);
        } else {
            Log.e("TAG", "id " + id + "pos " + position);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //Set onClick listener with view ------------------------
        setOnClickListener();
        binding.fabArrowBackCC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.actionCCToCalculatorFragment);
                Toast.makeText(v.getContext(), "Normal Calculator", Toast.LENGTH_SHORT).show();
            }
        });

        Log.e("TAG", "response start");
        Call<CurrencySymbolResponseModel> call1 = RetrofitService.getService().getCurrencySymbol(api_key);
        call1.enqueue(new Callback<CurrencySymbolResponseModel>() {
            @Override
            public void onResponse(Call<CurrencySymbolResponseModel> call, Response<CurrencySymbolResponseModel> response) {
                if (response.isSuccessful()) {
                    Log.e("TAG", "" + response.body());
                }
            }

            @Override
            public void onFailure(Call<CurrencySymbolResponseModel> call, Throwable t) {

            }
        });

        Call<CurrencyResponseModel> call = RetrofitService.getService().getData(api_key);
        call.enqueue(new Callback<CurrencyResponseModel>() {
            @Override
            public void onResponse(Call<CurrencyResponseModel> call, Response<CurrencyResponseModel> response) {

                if (response.isSuccessful()) {
                    Log.e("TAG", "" + response.body().getRates().getBDT());
                    Rates model = response.body().getRates();
                    DB.getDb(getContext()).getDao().insert(new CurrencyResponseModel(model));
                }
            }

            @Override
            public void onFailure(Call<CurrencyResponseModel> call, Throwable t) {
                Log.e("TAG", "fail " + t.getLocalizedMessage());
            }
        });

        Log.e("TAG", "response end");

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvCurrencyCode:
            case R.id.tvCurrencyCodeOne:
            case R.id.tvCurrencyCodeTwo:
                id = v.getId();
                ChangeCurrencyDialogFragment dialogFragment = new ChangeCurrencyDialogFragment(this);
                dialogFragment.show(getParentFragmentManager(), "currency names");
                break;
            case R.id.fabZeroCC:
                createUserInputString("0");
                break;
            case R.id.fabDoubleZeroCC:
                createUserInputString("00");
                break;
            case R.id.fabOneCC:
                createUserInputString("1");
                break;
            case R.id.fabTwoCC:
                createUserInputString("2");
                break;
            case R.id.fabThreeCC:
                createUserInputString("3");
                break;
            case R.id.fabFourCC:
                createUserInputString("4");
                break;
            case R.id.fabFiveCC:
                createUserInputString("5");
                break;
            case R.id.fabSixCC:
                createUserInputString("6");
                break;
            case R.id.fabSevenCC:
                createUserInputString("7");
                break;
            case R.id.fabEightCC:
                createUserInputString("8");
                break;
            case R.id.fabNineCC:
                createUserInputString("9");
                break;
            case R.id.etUserCurrencyAmount:
            case R.id.etUserCurrencyAmountOne:
            case R.id.etUserCurrencyAmountTwo:
                etAmountId = v.getId();
                createUserInputString("1");
                break;
            case R.id.fabDeleteLeftCC:
                deleteLeft("dl");
                break;
            case R.id.fabAllClearCC:
                allClear("ac");
                break;
        }
    }

    private void deleteLeft(String dl) {
    }

    private void allClear(String ac) {
        binding.etUserCurrencyAmount.setText("");
        binding.etUserCurrencyAmountOne.setText("");
        binding.etUserCurrencyAmountTwo.setText("");
        userInputString = "";
    }

    private void createUserInputString(String userInput) {
        userInputString = userInputString + userInput;
        binding.etUserCurrencyAmount.setText(userInputString);
        double temp = Double.parseDouble(userInputString);
        double result = temp * 90;
        double resultOne = temp * 20;
        binding.etUserCurrencyAmountOne.setText(String.valueOf(result));
        binding.etUserCurrencyAmountTwo.setText(String.valueOf(resultOne));
    }

    private void setOnClickListener() {
        binding.tvCurrencyCode.setOnClickListener(this);
        binding.tvCurrencyCodeOne.setOnClickListener(this);
        binding.tvCurrencyCodeTwo.setOnClickListener(this);
        binding.tvCurrencyName.setOnClickListener(this);
        binding.tvCurrencyNameOne.setOnClickListener(this);
        binding.tvCurrencyNameTwo.setOnClickListener(this);
        binding.etUserCurrencyAmount.setOnClickListener(this);
        binding.etUserCurrencyAmountOne.setOnClickListener(this);
        binding.etUserCurrencyAmountTwo.setOnClickListener(this);
        binding.fabZeroCC.setOnClickListener(this);
        binding.fabDoubleZeroCC.setOnClickListener(this);
        binding.fabOneCC.setOnClickListener(this);
        binding.fabTwoCC.setOnClickListener(this);
        binding.fabThreeCC.setOnClickListener(this);
        binding.fabFourCC.setOnClickListener(this);
        binding.fabFiveCC.setOnClickListener(this);
        binding.fabSixCC.setOnClickListener(this);
        binding.fabSevenCC.setOnClickListener(this);
        binding.fabEightCC.setOnClickListener(this);
        binding.fabNineCC.setOnClickListener(this);
        binding.fabDeleteLeftCC.setOnClickListener(this);
        binding.fabAllClearCC.setOnClickListener(this);
    }

    @Override
    public void onCurrencyItemClickListener(int position) {
        if (id == R.id.tvCurrencyCode) {
            binding.ivFlag.setImageResource(countryFlags.get(position));
            binding.tvCurrencyCode.setText(currencyCodes[position]);
            binding.tvCurrencyName.setText(currencyNames[position]);
        } else if (id == R.id.tvCurrencyCodeOne) {
            binding.ivFlagOne.setImageResource(countryFlags.get(position));
            binding.tvCurrencyCodeOne.setText(currencyCodes[position]);
            binding.tvCurrencyNameOne.setText(currencyNames[position]);

        } else if (id == R.id.tvCurrencyCodeTwo) {
            binding.ivFlagTwo.setImageResource(countryFlags.get(position));
            binding.tvCurrencyCodeTwo.setText(currencyCodes[position]);
            binding.tvCurrencyNameTwo.setText(currencyNames[position]);

        }
    }
}