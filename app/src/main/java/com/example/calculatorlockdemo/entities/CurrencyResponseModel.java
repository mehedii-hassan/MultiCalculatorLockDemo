package com.example.calculatorlockdemo.entities;

import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "tbl_currency_response")
public class CurrencyResponseModel {

	public CurrencyResponseModel(Rates rates) {
		this.rates = rates;
	}

	@SerializedName("date")
	private String date;

	@SerializedName("rates")
	private Rates rates;

	@SerializedName("base")
	private String base;

	public String getDate(){
		return date;
	}

	public Rates getRates(){
		return rates;
	}

	public String getBase(){
		return base;
	}
}