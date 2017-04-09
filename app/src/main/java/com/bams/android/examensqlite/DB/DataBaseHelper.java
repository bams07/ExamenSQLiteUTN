package com.bams.android.examensqlite.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bams on 3/16/17.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Restaurante.db";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DataBaseContract.SQL_CREATE_INSUMO);
        sqLiteDatabase.execSQL(DataBaseContract.SQL_CREATE_PLATO);
        sqLiteDatabase.execSQL(DataBaseContract.SQL_CREATE_INSUMO_PLATO);
        sqLiteDatabase.execSQL(DataBaseContract.SQL_CREATE_ORDEN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DataBaseContract.SQL_DELETE_ORDEN);
        sqLiteDatabase.execSQL(DataBaseContract.SQL_DELETE_INSUMO_PLATO);
        sqLiteDatabase.execSQL(DataBaseContract.SQL_DELETE_PLATO);
        sqLiteDatabase.execSQL(DataBaseContract.SQL_DELETE_INSUMO);
        onCreate(sqLiteDatabase);
    }


}
