package com.example.calculatorlockdemo.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.calculatorlockdemo.daos.CurrencyResponseDao;
import com.example.calculatorlockdemo.entities.CurrencyResponseModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {CurrencyResponseModel.class}, version = 1)
public abstract class DB extends RoomDatabase {
    private static DB db;
    public static ExecutorService backgroundService = Executors.newFixedThreadPool(4);

    public abstract CurrencyResponseDao getDao();

    public static DB getDb(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context.getApplicationContext(), DB.class, "DataBase")
                    .allowMainThreadQueries().build();
        }
        return db;
    }
}

/*
@db(entities = {UserModel.class,TourEventModel.class, TourExpenseModel.class, TourMoreBudgetModel.class, TourImageModel.class}, version = 1)
public abstract class TourEventsDatabase extends RoomDatabase {

    private static TourEventsDatabase db;
    public static ExecutorService backgroundService= Executors.newFixedThreadPool(5);
    public abstract TourEventDao getEventDao();
    public abstract TourExpenseDao getExpenseDao();
    public abstract TourMoreBudgetDao getMoreBudgetDao();
    public abstract TourImageDao getTourImageDao();
    public abstract UserDao getSignUpDao();

    public static TourEventsDatabase getDb(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context.getApplicationContext(), TourEventsDatabase.class, "TourEventsDb")
                    .allowMainThreadQueries().build();
        }
        return db;
    }

}
*/