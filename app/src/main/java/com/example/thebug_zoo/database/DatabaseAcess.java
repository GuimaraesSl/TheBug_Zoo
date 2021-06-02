package com.example.thebug_zoo.database;

import android.content.Context;
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
    public final String TABLE;

    public DatabaseAcess(Context context, String TABLE){
        this.openHelper = new BancoController(context);
        this.TABLE = TABLE;
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAcess getInstance(Context context, String TABLE){

        if (instance == null){
            instance = new DatabaseAcess(context, TABLE);
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
    public static final String COLUMN_FOTO1 = "foto1";
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

                result.add(species);
            }while (cursor.moveToNext());
            cursor.close();
            close();
            return result;
        } else {
            cursor.close();
            close();
            return null;
        }
    }

    public List<String> searchByOrder(String order){
        open();
        List<String> result = new ArrayList<>();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(TABLE);
        Cursor cursor = database.query(TABLE, sqlSelect, COLUMN_ORDER + " = ?", new String[]{order}, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                if(!result.contains(cursor.getString(5))) {
                    result.add(cursor.getString(5));
                }
            }while (cursor.moveToNext());
                cursor.close();
                close();
                return result;
        } else {
            cursor.close();
            close();
            return null;
        }
    }

    public List<Species> searchByFamily(String family){
        open();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(TABLE);
        Cursor cursor = database.query(TABLE, sqlSelect, COLUMN_FAMILY + " = ?", new String[]{family}, null, null, null, null);

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

                if (!result.contains(species)) {
                    result.add(species);
                }

            }while (cursor.moveToNext());
            cursor.close();
            close();
            return result;
        } else {
            cursor.close();
            close();
            return null;
        }
    }

    public ArrayList<ArrayList<Integer>> searchByKeyword(ArrayList<List<Species>> list, String keyword){
        List<Species> aux;
        ArrayList<ArrayList<Integer>> idsOfSpecies = new ArrayList<>();

        Log.d("KEYWORD", keyword);
        for(int i = 0; i < list.size(); i++){
            aux = list.get(i);
            ArrayList<Integer> ids = new ArrayList<>();
            for(int l = 0; l < aux.size(); l++){
                Log.d("ORDENS ", String.valueOf(aux.get(l).ordem));
                if(aux.get(l).ordem.intern().toLowerCase().indexOf(keyword.intern().toLowerCase()) != -1){
                    if(!ids.contains(aux.get(l)._id)){
                        Log.d("IDS", String.valueOf(aux.get(l)._id));
                        ids.add(aux.get(l)._id);
                    }
                }
                Log.d("FAMILIAS", String.valueOf(aux.get(l).familia));
                if(aux.get(l).familia.intern().toLowerCase().indexOf(keyword.intern().toLowerCase()) != -1){
                    if(!ids.contains(aux.get(l)._id)){
                        Log.d("IDS", String.valueOf(aux.get(l)._id));
                        ids.add(aux.get(l)._id);
                    }
                }
                Log.d("IDENTIFICAÇÃO", String.valueOf(aux.get(l).identificacao));
                if(aux.get(l).identificacao.intern().toLowerCase().indexOf(keyword.intern().toLowerCase()) != -1){
                    if(!ids.contains(aux.get(l)._id)){
                        Log.d("IDS", String.valueOf(aux.get(l)._id));
                        ids.add(aux.get(l)._id);
                    }
                }
                Log.d("INFORMAÇÔES", String.valueOf(aux.get(l).inf_adicionais));
                if(aux.get(l).inf_adicionais.intern().toLowerCase().indexOf(keyword.intern().toLowerCase()) != -1){
                    if(!ids.contains(aux.get(l)._id)){
                        Log.d("IDS", String.valueOf(aux.get(l)._id));
                        ids.add(aux.get(l)._id);
                    }
                }
            }
            idsOfSpecies.add(ids);
        }

        return idsOfSpecies;
    }

    public List<Species> searchByIdList(ArrayList<ArrayList<Integer>> ids){
        open();
        List<Species> result = new ArrayList<>();
        int cont;
        ArrayList<Integer> aux;
        aux = ids.get(0);
        for(int i = 0; i < aux.size(); i++){
            cont = aux.get(i);
            Log.d("AQUIIIII", String.valueOf(cont));
            open();
            SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
            queryBuilder.setTables("table_meio_umido");
            Cursor cursor = queryBuilder.query(database, sqlSelect, COLUMN__ID + " = ?", new String[]{String.valueOf(cont)}, null, null, null);

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

                    result.add(species);
                }while (cursor.moveToNext());
                cursor.close();
                close();
            } else {
                cursor.close();
                close();
                return null;
            }
        }
        aux = ids.get(1);
        for(int i = 0; i < aux.size(); i++){
            cont = ids.get(1).get(i);
            open();
            SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
            queryBuilder.setTables("table_taxidermizados");
            Cursor cursor = queryBuilder.query(database, sqlSelect, COLUMN__ID + " = ?", new String[]{String.valueOf(cont)}, null, null, null);

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

                    result.add(species);
                }while (cursor.moveToNext());
                cursor.close();
                close();
            } else {
                cursor.close();
                close();
                return null;
            }
        }
        aux = ids.get(2);
        for(int i = 0; i < aux.size(); i++){
            cont = ids.get(2).get(i);
            open();
            SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
            queryBuilder.setTables("table_osteologia");
            Cursor cursor = queryBuilder.query(database, sqlSelect, COLUMN__ID + " = ?", new String[]{String.valueOf(cont)}, null, null, null);

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

                    result.add(species);
                }while (cursor.moveToNext());
                cursor.close();
                close();
            } else {
                cursor.close();
                close();
                return null;
            }
        }

        return result;
    }

    public void teste(List<Species> list){
        for(int i = 0; i < list.size(); i++){
            Log.d("RESULTAAADO FINAL", list.get(i).ordem);
        }
    }


    public byte[] GetImageByID(String ID) {
        open();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(TABLE);

        String[] sqlSelect1 = {COLUMN__ID, COLUMN_ID, COLUMN_WARDROBE, COLUMN_BOOKCASE, COLUMN_ORDER, COLUMN_FAMILY, COLUMN_IDENTIFICATION, COLUMN_INF, COLUMN_SOURCE, COLUMN_COLLECTOR, COLUMN_PLACE, COLUMN_DATE, COLUMN_FOTO1};
        Cursor cursor = queryBuilder.query(database, sqlSelect1, COLUMN__ID + "= ?", new String[]{String.valueOf(ID)}, null, null, null);

        byte[] result;
        if (cursor.moveToFirst()) {
            do {
                result = cursor.getBlob(12);
            } while (cursor.moveToNext());
            cursor.close();
            close();
        } else {
            cursor.close();
            close();
            return null;
        }
        return result;
    }

    public List<String> getAllOrders(List<Species> list){

//        List<String> aux = new ArrayList<>();
//        List<String> allOrders = new ArrayList<>();
//
//        for(int i = 0; i < list.size(); i++){
//            for(int j = 0; j < list.get(i).size(); j++){
//                if(!allOrders.contains(list.get(i).get(j).ordem)){
//                    allOrders.add(list.get(i).get(j).ordem);
//                    Log.d("ordens", list.get(i).get(j).ordem);
//                }
//            }
//        }

        List<String> allOrders = new ArrayList<>();

        for(int i = 0; i < list.size(); i++){
            if(!allOrders.contains(list.get(i).ordem)){
                allOrders.add(list.get(i).ordem);
            }
        }

        return allOrders;
    }

    public List<Integer> getAllNumbersSpeciesOfOrders(List<String> list){

//        List<Integer> numbersSpecies = new ArrayList<>();
//        List<String> aux = new ArrayList<>();
//        for(int i = 0; i < list.size(); i++){
//            open();
//            List<Species> result = new ArrayList<>();
//            String order = list.get(i);
//            Cursor cursor = database.query("table_meio_umido", sqlSelect, COLUMN_ORDER + " = ?", new String[]{order}, null, null, null, null);
//            if(cursor.moveToFirst()){
//                do{
//                    Species species = new Species();
//                    species._id = cursor.getInt((cursor.getColumnIndex(COLUMN__ID)));
//                    species.id = cursor.getInt((cursor.getColumnIndex(COLUMN_ID)));
//                    species.armario = cursor.getInt((cursor.getColumnIndex(COLUMN_WARDROBE)));
//                    species.estante = cursor.getInt((cursor.getColumnIndex(COLUMN_BOOKCASE)));
//                    species.ordem = cursor.getString((cursor.getColumnIndex(COLUMN_ORDER)));
//                    species.familia = cursor.getString((cursor.getColumnIndex(COLUMN_FAMILY)));
//                    species.identificacao = cursor.getString((cursor.getColumnIndex(COLUMN_IDENTIFICATION)));
//                    species.inf_adicionais = cursor.getString((cursor.getColumnIndex(COLUMN_INF)));
//                    species.fonte = cursor.getString((cursor.getColumnIndex(COLUMN_SOURCE)));
//                    species.coletor = cursor.getString((cursor.getColumnIndex(COLUMN_COLLECTOR)));
//                    species._local = cursor.getString((cursor.getColumnIndex(COLUMN_PLACE)));
//                    species._data = cursor.getString((cursor.getColumnIndex(COLUMN_DATE)));
//
//                    if (!result.contains(species)) {
//                        result.add(species);
//                    }
//
//                }while (cursor.moveToNext());
//                cursor.close();
//                close();
//            } else {
//                cursor.close();
//                close();
//                return null;
//            }
//            Log.d("AQUI", aux.get(i));
//            numbersSpecies.add(result.size());
//        }
//
//        for(int i = 0; i < list.size(); i++){
//            open();
//            String order = aux.get(i);
//            List<Species> result = new ArrayList<>();
//            Cursor cursor = database.query("table_taxidermizados", sqlSelect, COLUMN_ORDER + " = ?", new String[]{order}, null, null, null, null);
//            if(cursor.moveToFirst()){
//                do{
//                    Species species = new Species();
//                    species._id = cursor.getInt((cursor.getColumnIndex(COLUMN__ID)));
//                    species.id = cursor.getInt((cursor.getColumnIndex(COLUMN_ID)));
//                    species.armario = cursor.getInt((cursor.getColumnIndex(COLUMN_WARDROBE)));
//                    species.estante = cursor.getInt((cursor.getColumnIndex(COLUMN_BOOKCASE)));
//                    species.ordem = cursor.getString((cursor.getColumnIndex(COLUMN_ORDER)));
//                    species.familia = cursor.getString((cursor.getColumnIndex(COLUMN_FAMILY)));
//                    species.identificacao = cursor.getString((cursor.getColumnIndex(COLUMN_IDENTIFICATION)));
//                    species.inf_adicionais = cursor.getString((cursor.getColumnIndex(COLUMN_INF)));
//                    species.fonte = cursor.getString((cursor.getColumnIndex(COLUMN_SOURCE)));
//                    species.coletor = cursor.getString((cursor.getColumnIndex(COLUMN_COLLECTOR)));
//                    species._local = cursor.getString((cursor.getColumnIndex(COLUMN_PLACE)));
//                    species._data = cursor.getString((cursor.getColumnIndex(COLUMN_DATE)));
//
//                    if (!result.contains(species)) {
//                        result.add(species);
//                    }
//
//                }while (cursor.moveToNext());
//                cursor.close();
//                close();
//            } else {
//                cursor.close();
//                close();
//                return null;
//            }
//            Log.d("AQUI", aux.get(i));
//            numbersSpecies.add(result.size());
//        }
//
//        for(int i = 0; i < list.size(); i++){
//            open();
//            List<Species> result = new ArrayList<>();
//            String order = aux.get(i);
//            Cursor cursor = database.query("table_osteologia", sqlSelect, COLUMN_ORDER + " = ?", new String[]{order}, null, null, null, null);
//            if(cursor.moveToFirst()){
//                do{
//                    Species species = new Species();
//                    species._id = cursor.getInt((cursor.getColumnIndex(COLUMN__ID)));
//                    species.id = cursor.getInt((cursor.getColumnIndex(COLUMN_ID)));
//                    species.armario = cursor.getInt((cursor.getColumnIndex(COLUMN_WARDROBE)));
//                    species.estante = cursor.getInt((cursor.getColumnIndex(COLUMN_BOOKCASE)));
//                    species.ordem = cursor.getString((cursor.getColumnIndex(COLUMN_ORDER)));
//                    species.familia = cursor.getString((cursor.getColumnIndex(COLUMN_FAMILY)));
//                    species.identificacao = cursor.getString((cursor.getColumnIndex(COLUMN_IDENTIFICATION)));
//                    species.inf_adicionais = cursor.getString((cursor.getColumnIndex(COLUMN_INF)));
//                    species.fonte = cursor.getString((cursor.getColumnIndex(COLUMN_SOURCE)));
//                    species.coletor = cursor.getString((cursor.getColumnIndex(COLUMN_COLLECTOR)));
//                    species._local = cursor.getString((cursor.getColumnIndex(COLUMN_PLACE)));
//                    species._data = cursor.getString((cursor.getColumnIndex(COLUMN_DATE)));
//
//                    if (!result.contains(species)) {
//                        result.add(species);
//                    }
//
//                }while (cursor.moveToNext());
//                cursor.close();
//                close();
//            } else {
//                cursor.close();
//                close();
//                return null;
//            }
//            Log.d("AQUI", aux.get(i));
//            numbersSpecies.add(result.size());
//        }



        open();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(TABLE);
        List<Integer> numbersSpecies = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            open();
            List<Species> result = new ArrayList<>();
            String order = list.get(i);
            Cursor cursor = database.query(TABLE, sqlSelect, COLUMN_ORDER + " = ?", new String[]{order}, null, null, null, null);
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

                    if (!result.contains(species)) {
                        result.add(species);
                    }

                }while (cursor.moveToNext());
                cursor.close();
                close();
            } else {
                cursor.close();
                close();
                return null;
            }
            numbersSpecies.add(result.size());
        }
        return numbersSpecies;
    }

}
