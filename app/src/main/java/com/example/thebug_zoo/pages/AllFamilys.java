package com.example.thebug_zoo.pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.thebug_zoo.R;
import com.example.thebug_zoo.adapter.CollectionAdapter;
import com.example.thebug_zoo.database.DatabaseAcess;
import com.example.thebug_zoo.entity.Species;

import java.util.ArrayList;
import java.util.List;

public class AllFamilys extends AppCompatActivity {

    DatabaseAcess database;
    List<Integer> numbers = new ArrayList<>();
    List<String> familys;
    ImageView back;
    ArrayList<List<Species>> result = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_familys);

        setBackButton();
        database = new DatabaseAcess(this, "table_meio_umido");
        result.add(database.searchAll());
        database.close();
        database = new DatabaseAcess(this, "table_taxidermizados");
        result.add(database.searchAll());
        database.close();
        database = new DatabaseAcess(this, "table_osteologia");
        result.add(database.searchAll());
        database.close();
        familys = database.getAllFamilys(result);
        numbers = database.getAllNumbersSpeciesOfFamilys(familys);

        RecyclerView recyclerView = findViewById(R.id.recycleFamily);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(new CollectionAdapter(numbers, familys, this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    void setBackButton(){
        back = (ImageView) findViewById(R.id.imageSeta);
        back.setOnClickListener(v -> finish());
    }
}