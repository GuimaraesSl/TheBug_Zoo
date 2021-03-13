package com.example.thebug_zoo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class BancoController extends SQLiteAssetHelper {

    public static final int BANK_VERSION = 1;
    public static final String BANK_NAME = "bd_zoo.sqlite";

    public BancoController(Context context) {
        super(context, BANK_NAME, null, BANK_VERSION);
    }
}
