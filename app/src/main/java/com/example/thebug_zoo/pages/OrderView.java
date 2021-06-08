package com.example.thebug_zoo.pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.thebug_zoo.R;
import com.example.thebug_zoo.adapter.SpeciesOrdersAdapter;
import com.example.thebug_zoo.database.DatabaseAcess;
import com.example.thebug_zoo.entity.Species;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderView extends AppCompatActivity{

    public static ImageView defaultIcon;
    public ImageView back;
    public static int ID;
    public static DatabaseAcess database;
    List<Species> specie;
    ConstraintLayout layoutSearch;
    RecyclerView recyclerView;
    SpeciesOrdersAdapter adapter;
    List<String> orderAdded = new ArrayList<>();
    private SpeciesOrdersAdapter.ClickListenerFeature listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setTypeSearch();
        setAdapter();
        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    void setTypeSearch(){
        if(ID==1) {
            final DatabaseAcess databaseAcess = DatabaseAcess.getInstance(this, "table_meio_umido");
            databaseAcess.open();
            database = new DatabaseAcess(this, "table_meio_umido");
            defaultIcon = (ImageView) findViewById(R.id.iconSearch);
            defaultIcon.setImageResource(R.drawable.icon_meio_umido);
        } else if (ID==2){
            final DatabaseAcess databaseAcess = DatabaseAcess.getInstance(this, "table_taxidermizados");
            databaseAcess.open();
            database = new DatabaseAcess(this, "table_taxidermizados");
            defaultIcon = (ImageView) findViewById(R.id.iconSearch);
            defaultIcon.setImageResource(R.drawable.icon_taxidermizados);
        } else if(ID==3){
            final DatabaseAcess databaseAcess = DatabaseAcess.getInstance(this, "table_osteologia");
            databaseAcess.open();
            database = new DatabaseAcess(this, "table_osteologia");
            defaultIcon = (ImageView) findViewById(R.id.iconSearch);
            defaultIcon.setImageResource(R.drawable.icon_osteologia);
        }

        back = (ImageView) findViewById(R.id.imageSeta);
        back.setOnClickListener(v -> finish());
    }

    void setAdapter() {
        setOnClickListener();
        layoutSearch = (ConstraintLayout)findViewById(R.id.layoutOrder);
        recyclerView = (RecyclerView) findViewById(R.id.recycleOrderView);

        specie = database.searchAll();
        for (int i = 0; i < specie.size(); i++){
            if (!orderAdded.contains(specie.get(i).ordem)){
                orderAdded.add(specie.get(i).ordem);
            }
        }
        Collections.sort(orderAdded);
        adapter = new SpeciesOrdersAdapter(this, orderAdded, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void setOnClickListener() {
        listener = (v, position) -> {
            Intent intent = new Intent(getApplicationContext(), FamilyView.class);
            intent.putExtra("selected_order", orderAdded.get(position));
            startActivity(intent);
        };
    }

}