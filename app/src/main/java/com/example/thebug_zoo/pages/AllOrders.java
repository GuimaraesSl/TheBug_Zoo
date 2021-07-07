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

public class AllOrders extends AppCompatActivity {

    DatabaseAcess database;
    List<Integer> numbers;
    List<String> orders;
    ImageView back;
    final ArrayList<List<Species>> result = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_orders);

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
        orders = database.getAllOrders(result);
        numbers = database.getAllNumbersSpeciesOfOrders(orders);

        RecyclerView recyclerView = findViewById(R.id.recycleOrders);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(new CollectionAdapter(numbers, orders, this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    void setBackButton(){
        back = (ImageView) findViewById(R.id.imageSeta);
        back.setOnClickListener(v -> finish());
    }
}