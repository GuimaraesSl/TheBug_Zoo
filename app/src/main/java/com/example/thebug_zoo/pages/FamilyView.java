package com.example.thebug_zoo.pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.thebug_zoo.R;
import com.example.thebug_zoo.adapter.SpeciesOrdersAdapter;
import com.example.thebug_zoo.components.CommonItemSpaceDecoration;
import com.example.thebug_zoo.database.DatabaseAcess;
import com.example.thebug_zoo.entity.Species;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FamilyView extends AppCompatActivity {

    ImageView iconMeioUmido, taxidermizados, osteologia, back;
    public static int ID;
    DatabaseAcess database;
    List<Species> specie;
    ConstraintLayout layoutSearch;
    RecyclerView recyclerView;
    SpeciesOrdersAdapter adapter;
    List<String> orderAdded = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_view);
//        setTypeSearch();
//        setAdapter();
//        SearchView searchView = (SearchView) findViewById(R.id.searchView);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                adapter.getFilter().filter(newText);
//                return false;
//            }
//        });
    }

//    void setTypeSearch(){
//        if(ID==1) {
//            final DatabaseAcess databaseAcess = DatabaseAcess.getInstance(this, "table_meio_umido");
//            databaseAcess.open();
//            database = new DatabaseAcess(this, "table_meio_umido");
//            iconMeioUmido = (ImageView) findViewById(R.id.iconSearch);
//            iconMeioUmido.setImageResource(R.drawable.icon_meio_umido_search);
//        } else if (ID==2){
//            final DatabaseAcess databaseAcess = DatabaseAcess.getInstance(this, "table_taxidermizados");
//            databaseAcess.open();
//            database = new DatabaseAcess(this, "table_taxidermizados");
//            taxidermizados = (ImageView) findViewById(R.id.iconSearch);
//            taxidermizados.setImageResource(R.drawable.icon_taxidermizados_search);
//        } else if(ID==3){
//            final DatabaseAcess databaseAcess = DatabaseAcess.getInstance(this, "table_osteologia");
//            databaseAcess.open();
//            database = new DatabaseAcess(this, "table_osteologia");
//            osteologia = (ImageView) findViewById(R.id.iconSearch);
//            osteologia.setImageResource(R.drawable.icon_osteologia_search);
//        }
//
//        back = (ImageView) findViewById(R.id.imageSeta);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
//
//    void setAdapter() {
//        layoutSearch = (ConstraintLayout)findViewById(R.id.layoutOrder);
//        recyclerView = (RecyclerView) findViewById(R.id.recyrcleView);
//
//        specie = database.searchAll();
//        for (int i = 0; i < specie.size(); i++){
//            if (!orderAdded.contains(specie.get(i).ordem)){
//                orderAdded.add(specie.get(i).ordem);
//            }
//        };
//        Collections.sort(orderAdded);
////        adapter = new SpeciesOrdersAdapter(this, orderAdded, this);
////        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
////        recyclerView.addItemDecoration(new CommonItemSpaceDecoration(16));
////        recyclerView.setLayoutManager(layoutManager);
////        recyclerView.setItemAnimator(new DefaultItemAnimator());
////        recyclerView.setHasFixedSize(true);
////        recyclerView.setAdapter(adapter);
//    }
}