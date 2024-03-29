package com.example.thebug_zoo.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.ConnectivityManager;

import com.example.thebug_zoo.APIConsume.Methods;
import com.example.thebug_zoo.APIConsume.Model;
import com.example.thebug_zoo.APIConsume.RetrofitClient;
import com.example.thebug_zoo.entity.Species;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class DatabaseAcess {

    public static String[] imageUrl;
    //================CONEXÃ0=====================
    private final SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAcess instance;
    public final String TABLE;
    public String type;
    public final List<String> tables = new ArrayList<>();

    public DatabaseAcess(Context context, String TABLE){
        this.openHelper = new BancoController(context);
        this.TABLE = TABLE;
        tables.add("table_meio_umido");
        tables.add("table_taxidermizados");
        tables.add("table_osteologia");

        if(TABLE.equals(tables.get(0))){
            type = "umido";
        } else if(TABLE.equals(tables.get(1))){
            type = "taxidermizados";
        } else if(TABLE.equals(tables.get(2))){
            type = "osteologia";
        }
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
    public static final String COLUMN_FILO = "filo";
    final String[] sqlSelect = {COLUMN__ID, COLUMN_ID, COLUMN_WARDROBE, COLUMN_BOOKCASE, COLUMN_ORDER, COLUMN_FAMILY, COLUMN_IDENTIFICATION, COLUMN_INF, COLUMN_SOURCE, COLUMN_COLLECTOR, COLUMN_PLACE, COLUMN_DATE, COLUMN_FILO};

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
                species._data = cursor.getString((cursor.getColumnIndex(COLUMN_DATE)));
                species.filo = cursor.getString((cursor.getColumnIndex(COLUMN_FILO)));
                species.table = TABLE;
                species.type = type;

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

    public List<String> searchByPhylum(String filo){
        open();
        List<String> result = new ArrayList<>();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(TABLE);
        Cursor cursor = database.query(TABLE, sqlSelect, COLUMN_FILO + " = ?", new String[]{filo}, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                if(!result.contains(cursor.getString(4))) {
                    result.add(cursor.getString(4));
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

    public List<Species> searchByFamily(String family, String order){
        open();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(TABLE);
        Cursor cursor = database.query(TABLE, sqlSelect, COLUMN_FAMILY + " = ? and " + COLUMN_ORDER + " = ?", new String[]{family, order}, null, null, null, null);

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
                species.table = TABLE;
                species.type=type;

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

        for(int i = 0; i < list.size(); i++){
            aux = list.get(i);
            ArrayList<Integer> ids = new ArrayList<>();
            for(int l = 0; l < aux.size(); l++){
                if(aux.get(l).filo.equals("") || aux.get(l).filo == null){
                    aux.get(l).filo = "";
                }
                if(aux.get(l).ordem.intern().toLowerCase().contains(keyword.intern().toLowerCase())){
                    if(!ids.contains(aux.get(l)._id)){
                        ids.add(aux.get(l)._id);
                    }
                }
                if(aux.get(l).familia.intern().toLowerCase().contains(keyword.intern().toLowerCase())){
                    if(!ids.contains(aux.get(l)._id)){
                        ids.add(aux.get(l)._id);
                    }
                }
                if(aux.get(l).identificacao.intern().toLowerCase().contains(keyword.intern().toLowerCase())){
                    if(!ids.contains(aux.get(l)._id)){
                        ids.add(aux.get(l)._id);
                    }
                }
                if(aux.get(l).inf_adicionais.intern().toLowerCase().contains(keyword.intern().toLowerCase())){
                    if(!ids.contains(aux.get(l)._id)){
                        ids.add(aux.get(l)._id);
                    }
                }
                if(aux.get(l).filo.intern().toLowerCase().contains(keyword.intern().toLowerCase())){
                    if(!ids.contains(aux.get(l)._id)){
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
                    species.table = "table_meio_umido";
                    species.type = "umido";

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
                    species.table = "table_taxidermizados";
                    species.type = "taxidermizados";

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
                    species.table = "table_osteologia";
                    species.type = "osteologia";

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

    public String[] GetImageByID (String _id, String type) {

        imageUrl = null;
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.getImage(type, _id);

        Runnable objRunnable = () -> {
            try {
                Response<Model> response = call.execute();
                if (response.body() == null) throw new Exception();
                imageUrl = response.body().getExemplary().getImages();
            } catch (Exception e) {
                imageUrl = new String[]{""};
            }
        };
        Thread objThread = new Thread(objRunnable);
        objThread.start();

        while (imageUrl == null){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return imageUrl;
    }


    public List<String> getAllOrders(ArrayList<List<Species>> list){

        List<String> allOrders = new ArrayList<>();

        for(int i = 0; i < list.size(); i++){
            for(int j = 0; j < list.get(i).size(); j++){
                if(!allOrders.contains(list.get(i).get(j).ordem)){
                    allOrders.add(list.get(i).get(j).ordem);
                }
            }
        }

        return allOrders;
    }

    public List<Integer> getAllNumbersSpeciesOfOrders(List<String> list){

        List<Integer> numbersSpecies = new ArrayList<>();

        for(int i = 0; i < tables.size(); i++){
            for(int j = 0; j < list.size(); j++){
                open();
                SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
                queryBuilder.setTables(tables.get(i));
                Cursor cursor = queryBuilder.query(database, sqlSelect, COLUMN_ORDER + " = ?", new String[]{String.valueOf(list.get(j))}, null, null, null);
                int cont = 0;
                if(cursor.moveToFirst()){
                    do{
                        cont++;
                    }while (cursor.moveToNext());
                }
                cursor.close();
                close();
                if(cont != 0){
                    numbersSpecies.add(cont);
                }
            }
        }

        return numbersSpecies;
    }

    public List<String> getAllFamilys(ArrayList<List<Species>> list){

        List<String> allFamilys = new ArrayList<>();

        for(int i = 0; i < list.size(); i++){
            for(int j = 0; j < list.get(i).size(); j++){
                if(!allFamilys.contains(list.get(i).get(j).familia)){
                    allFamilys.add(list.get(i).get(j).familia);
                }
            }
        }

        return allFamilys;
    }

    public List<Integer> getAllNumbersSpeciesOfFamilys(List<String> list){

        List<Integer> numbersSpecies = new ArrayList<>();

        for(int i = 0; i < tables.size(); i++){
            for(int j = 0; j < list.size(); j++){
                open();
                SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
                queryBuilder.setTables(tables.get(i));
                Cursor cursor = queryBuilder.query(database, sqlSelect, COLUMN_FAMILY + " = ?", new String[]{String.valueOf(list.get(j))}, null, null, null);
                int cont = 0;
                if(cursor.moveToFirst()){
                    do{
                        cont++;
                    }while (cursor.moveToNext());
                }
                cursor.close();
                close();
                if(cont != 0){
                    numbersSpecies.add(cont);
                }
            }
        }

        return numbersSpecies;
    }

    public boolean isConnected(Context context){
        ConnectivityManager connectivityManager  = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo()!=null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

}
