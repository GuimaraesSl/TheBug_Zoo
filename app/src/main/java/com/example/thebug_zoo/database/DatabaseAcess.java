package com.example.thebug_zoo.database;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.thebug_zoo.entity.Species;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAcess {

    public static final String TABLE = "table_meio_umido";
    public static final String COLUMN__ID = "_id";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_WARDROBE = "armario";
    public static final String COLUMN_BOOKCASE = "estante";
    public static final String COLUMN_ORDER = "ordem";
    public static final String COLUMN_FAMILY = "familia";
    public static final String COLUMN_IDENTIFICATION = "identificacao";
    public static final String COLUMN_INF = "inf_adicionais";
    public static final String COLUMN_SOURCE = "fonte";
    public static final String COLUMN_COLLECTOR = "coletor";
    public static final String COLUMN_PLACE = "_local";
    public static final String COLUMN_DATE = "_data";


    private SQLiteDatabase database;
    private SQLiteOpenHelper openHelper;
    String[] sqlSelect = {COLUMN__ID, COLUMN_ID, COLUMN_WARDROBE, COLUMN_BOOKCASE, COLUMN_ORDER, COLUMN_FAMILY, COLUMN_IDENTIFICATION, COLUMN_INF, COLUMN_SOURCE, COLUMN_COLLECTOR, COLUMN_PLACE, COLUMN_DATE};

    public DatabaseAcess(Context context){
        openHelper = new BancoController(context);
    }

    public void open(){
        database = openHelper.getReadableDatabase();
    }

    public List<Species> searchAll(){

        database = openHelper.getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(TABLE);
        List<Species> result = new ArrayList<>();
        Cursor cursor = queryBuilder.query(database, sqlSelect, null, null, null, null, null);
        Species species = new Species();

        if(cursor.moveToFirst()){
            do{
                species._id = cursor.getInt((cursor.getColumnIndex(COLUMN__ID)));
                species.id = cursor.getInt((cursor.getColumnIndex(COLUMN_ID)));
                species.armario = cursor.getInt((cursor.getColumnIndex(COLUMN_WARDROBE)));
                species.estante = cursor.getInt((cursor.getColumnIndex(COLUMN_BOOKCASE)));
                species.ordem = cursor.getString((cursor.getColumnIndex(COLUMN_ORDER)));
                species.familia = cursor.getString((cursor.getColumnIndex(COLUMN_FAMILY)));
                species.identificacao = cursor.getString((cursor.getColumnIndex(COLUMN_IDENTIFICATION)));
                species.inf_adicionais = cursor.getString((cursor.getColumnIndex(COLUMN_INF)));
                species.fonte = cursor.getString((cursor.getColumnIndex(COLUMN_SOURCE)));
                species.coletor = cursor.getString((cursor.getColumnIndex(COLUMN_COLLECTOR)));
                species._local = cursor.getString((cursor.getColumnIndex(COLUMN_PLACE)));
                species._data = cursor.getString((cursor.getColumnIndex(COLUMN_DATE)));
            }while (cursor.moveToNext());

            return result;
        } else {
            return null;
        }
    }

    public Species selectSpecie(int code){

        database = openHelper.getReadableDatabase();
        Cursor cursor = database.query(TABLE, sqlSelect, COLUMN__ID + "= ?", new String[]{String.valueOf(code)}, null, null, null, null);

        if( cursor!= null){
            cursor.moveToFirst();
        }

        Species species = new Species(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)),
                Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)), cursor.getString(4),
                cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8),
                cursor.getString(9), cursor.getString(10), cursor.getString(11));

        return species;
    }
}