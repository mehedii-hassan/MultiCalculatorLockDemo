package com.example.calculatorlockdemo.daos;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.calculatorlockdemo.entities.CurrencyResponseModel;

@Dao
public interface CurrencyResponseDao {
    @Insert
    void insert(CurrencyResponseModel model);
}
