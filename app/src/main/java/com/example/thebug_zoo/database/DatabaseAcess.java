package com.example.thebug_zoo.database;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.example.thebug_zoo.entity.Species;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAcess {

    //================CONEXÃ0=====================
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAcess instance;
    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    public DatabaseAcess(Context context){
        this.openHelper = new BancoController(context);
    }
    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAcess getInstance(Context context){
        if (instance == null){
            instance = new DatabaseAcess(context);
        }
        return instance;
    }
    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }
    /**
     * Close the database connection.
     */
    public void close(){
        if(database != null){
            this.database.close();
        }
    }
    //=======================================================================================

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
    int contador = 0;
    String[] sqlSelect = {COLUMN__ID, COLUMN_ID, COLUMN_WARDROBE, COLUMN_BOOKCASE, COLUMN_ORDER, COLUMN_FAMILY, COLUMN_IDENTIFICATION, COLUMN_INF, COLUMN_SOURCE, COLUMN_COLLECTOR, COLUMN_PLACE, COLUMN_DATE};

    public List<Species> searchAll(){
        open();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(TABLE);
        Cursor cursor = queryBuilder.query(database, sqlSelect, null, null, null, null, null);
        List<Species> result = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                Species species = new Species();
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
                Log.d("SpeciesOrder", species.ordem);
                result.add(species);
                Log.d("Result", result.get(contador).ordem);
                contador = contador + 1;
            }while (cursor.moveToNext());
                Log.d("Result=0", result.get(0).ordem);
                Log.d("Result=1", result.get(1).ordem);
                Log.d("Result=2", result.get(2).ordem);

                cursor.close();
                close();
                return result;
        } else {
            cursor.close();
            close();
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

    public List<String> searchByOrder(String order){
        List<String> result = new ArrayList<>();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(TABLE);
        Cursor cursor = database.query(TABLE, sqlSelect, COLUMN_ORDER + " = ?", new String[]{order}, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                result.add(cursor.getString(0));
            }while (cursor.moveToNext());
                return result;
        } else {
            return null;
        }
    }

    public List<String> serarchByFamily(String family){
        List<String> result = new ArrayList<>();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(TABLE);
        Cursor cursor = database.query(TABLE, sqlSelect, COLUMN_FAMILY + " = ?", new String[]{family}, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                result.add(cursor.getString(0));
            }while (cursor.moveToNext());
                return result;
        } else {
            return null;
        }
    }
}
