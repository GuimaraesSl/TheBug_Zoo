package com.example.thebug_zoo.pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.thebug_zoo.R;
import com.example.thebug_zoo.adapter.SpeciesViewAdapter;
import com.example.thebug_zoo.entity.Species;

import java.util.List;

public class SpeciesView extends AppCompatActivity {

    public ImageView back;
    private List<Species> speciesAdded;
    private SpeciesViewAdapter.ClickListenerFeature listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_species_view);
        setAdapter();
        setBackButton();
        if (!FiloView.database.isConnected(this)){
            Toast.makeText(this, "Sem Conexão com a Internet", Toast.LENGTH_LONG).show();
        }
    }

    void setAdapter() {
        setOnClickListener();
        SpeciesViewAdapter adapter;
        RecyclerView speciesRecycler;

        try {
            String family = getIntent().getStringExtra("selected_family");
            speciesAdded = FiloView.database.searchByFamily(family, FamilyView.order);
        } catch (Exception e){
            speciesAdded = getIntent().getParcelableArrayListExtra("species_home");
        }

        speciesRecycler = findViewById(R.id.speciesRecycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        speciesRecycler.setHasFixedSize(true);
        speciesRecycler.setItemViewCacheSize(20);
        speciesRecycler.setLayoutManager(gridLayoutManager);
        adapter = new SpeciesViewAdapter(this, speciesAdded, listener);
        speciesRecycler.setAdapter(adapter);
    }

    private void setOnClickListener() {
        listener = (v, position) -> {
            Intent intent = new Intent(getApplicationContext(), InfAdicionais.class);
            intent.putExtra("selected_specie", speciesAdded.get(position));
            startActivity(intent);
        };
    }

    void setBackButton(){
        back = findViewById(R.id.imageSeta);
        back.setOnClickListener(v -> finish());
    }
}