package com.example.leo.bmi.DAO;

import android.content.Context;

import com.example.leo.bmi.SQLiteDatabaseHelper;

public class BmiDao extends SQLiteDatabaseHelper {

    private static BmiDao bmidao;
    public static final String TABLE_NAME = "bmi";
    public static final String CREATE_TABLE_SQL =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    "_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "height INTEGER, " +
                    "weight INTEGER)";

    public BmiDao(Context context) {
        super(context);
    }

}
