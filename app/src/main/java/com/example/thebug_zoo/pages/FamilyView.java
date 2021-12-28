package com.example.thebug_zoo.pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.thebug_zoo.R;
import com.example.thebug_zoo.adapter.SpeciesFamilyAdapter;

import java.util.Collections;
import java.util.List;

public class FamilyView extends AppCompatActivity {

    ImageView back, icon;
    static String order;
    ConstraintLayout layoutFamily;
    RecyclerView recyclerView;
    SpeciesFamilyAdapter adapter;
    List<String> familyAdded;
    private SpeciesFamilyAdapter.ClickListenerFeature listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_view);
        setSearch();
        setAdapter();
        SearchView searchView = findViewById(R.id.searchFamilyView);
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
        layoutFamily = findViewById(R.id.layoutFamily);
        icon = findViewById(R.id.iconFamilySearch);
        icon.setImageDrawable(FiloView.defaultIcon.getDrawable());
        back = findViewById(R.id.imageSeta);
        back.setOnClickListener(v -> finish());
    }

    void setAdapter() {
        setOnClickListener();
        recyclerView = findViewById(R.id.recycleViewFamily);
        order = getIntent().getStringExtra("selected_order");
        familyAdded = FiloView.database.searchByOrder(order);
        Collections.sort(familyAdded);
        adapter = new SpeciesFamilyAdapter(this, familyAdded, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void setOnClickListener() {
        listener = (v, position) -> {
            Toast.makeText(this, "Carregando...", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), SpeciesView.class);
            intent.putExtra("selected_family", familyAdded.get(position));
            startActivity(intent);
        };
    }
}