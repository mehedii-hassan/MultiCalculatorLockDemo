package com.example.calculatorlockdemo.network;

import com.example.calculatorlockdemo.entities.CurrencySymbolResponseModel;
import com.example.calculatorlockdemo.entities.CurrencyResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ExchangeRateServiceApi {
    @GET("latest")
    Call<CurrencyResponseModel> getData(@Query("apikey") String apikey);

    @GET("currency-symbols")
    Call<CurrencySymbolResponseModel> getCurrencySymbol(@Query("apikey") String apikey);


}
