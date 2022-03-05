package com.example.thebug_zoo.pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.thebug_zoo.R;
import com.example.thebug_zoo.adapter.SpeciesFilosAdapter;
import com.example.thebug_zoo.adapter.SpeciesOrdersAdapter;
import com.example.thebug_zoo.database.DatabaseAcess;
import com.example.thebug_zoo.entity.Species;

import java.util.ArrayList;
import java.util.List;

public class FiloView extends AppCompatActivity {

    public static ImageView defaultIcon;
    public ImageView back;
    public static int ID;
    public static DatabaseAcess database;
    List<Species> specie;
    RecyclerView recyclerView;
    SpeciesFilosAdapter adapter;
    final List<String> filoAdded = new ArrayList<>();
    private SpeciesFilosAdapter.ClickListenerFeature listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filo_view);
        setTypeSearch();
        setAdapter();
        SearchView searchView = findViewById(R.id.searchView);
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
            defaultIcon = findViewById(R.id.iconSearch);
            defaultIcon.setImageResource(R.mipmap.icon_meio_umido);
        } else if (ID==2){
            final DatabaseAcess databaseAcess = DatabaseAcess.getInstance(this, "table_taxidermizados");
            databaseAcess.open();
            database = new DatabaseAcess(this, "table_taxidermizados");
            defaultIcon = findViewById(R.id.iconSearch);
            defaultIcon.setImageResource(R.mipmap.icon_taxidermizados);
        } else if(ID==3){
            final DatabaseAcess databaseAcess = DatabaseAcess.getInstance(this, "table_osteologia");
            databaseAcess.open();
            database = new DatabaseAcess(this, "table_osteologia");
            defaultIcon = findViewById(R.id.iconSearch);
            defaultIcon.setImageResource(R.mipmap.icon_osteologia);
        }

        back = findViewById(R.id.imageSeta);
        back.setOnClickListener(v -> finish());
    }

    void setAdapter() {
        setOnClickListener();
        recyclerView = findViewById(R.id.recycleFiloView);

        specie = database.searchAll();

        for (int i = 0; i < specie.size(); i++){
            if (!filoAdded.contains(specie.get(i).filo) && specie.get(i).filo != null){
                filoAdded.add(specie.get(i).filo);
            }
        }
//        Collections.sort(filoAdded);
        adapter = new SpeciesFilosAdapter(this, filoAdded, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void setOnClickListener() {
        listener = (v, position) -> {
            Intent intent = new Intent(getApplicationContext(), OrderView.class);
            intent.putExtra("selected_filo", filoAdded.get(position));
            startActivity(intent);
        };
    }
}