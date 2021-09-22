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
import android.widget.ImageView;

import com.example.thebug_zoo.R;
import com.example.thebug_zoo.adapter.SpeciesOrdersAdapter;
import com.example.thebug_zoo.database.DatabaseAcess;
import com.example.thebug_zoo.entity.Species;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderView extends AppCompatActivity{
    public ImageView back, icon;
    public String filo;
    public static DatabaseAcess database;
    List<String> orders;
    ConstraintLayout layoutSearch;
    RecyclerView recyclerView;
    SpeciesOrdersAdapter adapter;
    private SpeciesOrdersAdapter.ClickListenerFeature listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setAdapter();
        setSearch();
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

    void setSearch(){
        layoutSearch = (ConstraintLayout)findViewById(R.id.layoutOrder);
        icon = (ImageView) findViewById(R.id.iconSearchOrder);
        icon.setImageDrawable(FiloView.defaultIcon.getDrawable());
        back = (ImageView) findViewById(R.id.imageSeta);
        back.setOnClickListener(v -> finish());
    }

    void setAdapter() {
        setOnClickListener();
        recyclerView = (RecyclerView) findViewById(R.id.recycleOrderView);

        filo = getIntent().getStringExtra("selected_filo");
        orders = FiloView.database.searchByPhylum(filo);

        Collections.sort(orders);
        adapter = new SpeciesOrdersAdapter(this, orders, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void setOnClickListener() {
        listener = (v, position) -> {
            Intent intent = new Intent(getApplicationContext(), FamilyView.class);
            intent.putExtra("selected_order", orders.get(position));
            startActivity(intent);
        };
    }

}