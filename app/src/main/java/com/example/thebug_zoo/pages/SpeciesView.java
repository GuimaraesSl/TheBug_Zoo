package com.example.thebug_zoo.pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.ImageView;

import com.example.thebug_zoo.R;
import com.example.thebug_zoo.adapter.SpeciesViewAdapter;
import com.example.thebug_zoo.entity.Species;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpeciesView extends AppCompatActivity {

    public static ImageView defaultIcon, back;
    private RecyclerView SpeciesRecycler;
    private List<Species> speciesAdded;
    private SpeciesViewAdapter adapter;
    private String family;
    private SpeciesViewAdapter.ClickListenerFeature listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_species_view);
        setAdapter();
        setBackButton();
    }


    void setAdapter() {
        setOnClickListener();
        family = getIntent().getStringExtra("selected_family");
        speciesAdded = OrderView.database.searchByFamily(family);
        SpeciesRecycler = findViewById(R.id.speciesRecycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        SpeciesRecycler.setLayoutManager(gridLayoutManager);
        SpeciesRecycler.setHasFixedSize(true);
        adapter = new SpeciesViewAdapter(this, speciesAdded, listener);
        SpeciesRecycler.setAdapter(adapter);
    }

    private void setOnClickListener() {
        listener = (v, position) -> {
            Intent intent = new Intent(getApplicationContext(), InfAdicionais.class);
            intent.putExtra("selected_specie", speciesAdded.get(position));
            Log.d("Name", speciesAdded.get(position).identificacao);
            startActivity(intent);
        };
    }

    void setBackButton(){
        back = (ImageView) findViewById(R.id.imageSeta);
        back.setOnClickListener(v -> finish());
    }
}