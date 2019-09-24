package com.example.leo.bmi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.leo.bmi.DAO.BmiDao;

public class SQLiteDatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQLiteDatabaseHelper";
    private static final String mDatabaseName = "bmi_DB.db";
    private static final int mVersion = 1;

    public SQLiteDatabaseHelper(@Nullable Context context) {
        super(context, mDatabaseName, null, mVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BmiDao.CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
