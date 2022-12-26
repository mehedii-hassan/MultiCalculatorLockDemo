package com.example.calculatorlockdemo.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    public static ExchangeRateServiceApi getService() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.currencyfreaks.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ExchangeRateServiceApi.class);
    }
}
