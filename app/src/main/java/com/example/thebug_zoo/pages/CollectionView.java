package com.example.thebug_zoo.pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.thebug_zoo.R;
import com.example.thebug_zoo.adapter.CollectionAdapter;
import com.example.thebug_zoo.database.DatabaseAcess;
import com.example.thebug_zoo.entity.Species;

import java.util.ArrayList;
import java.util.List;

import ru.embersoft.expandabletextview.ExpandableTextView;

public class CollectionView extends AppCompatActivity {

    ExpandableTextView expandableTextView;
    DatabaseAcess databaseAcess;
    List<String> orders = new ArrayList<>();
    List<Integer> numbers = new ArrayList<>();
    List<String> allOrders = new ArrayList<>();
    DatabaseAcess TABLE_MEIO_UMIDO, TABLE_TAXIDERMIZADOS, TABLE_OSTEOLOGIA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_view);
        ArrayList<List<Species>> result = new ArrayList<>();
        TABLE_MEIO_UMIDO = new DatabaseAcess(getApplicationContext(), "table_meio_umido");
        result.add(TABLE_MEIO_UMIDO.searchAll());
        TABLE_TAXIDERMIZADOS = new DatabaseAcess(getApplicationContext(), "table_taxidermizados");
        result.add(TABLE_TAXIDERMIZADOS.searchAll());
        TABLE_OSTEOLOGIA = new DatabaseAcess(getApplicationContext(), "table_osteologia");
        result.add(TABLE_OSTEOLOGIA.searchAll());
        orders = TABLE_TAXIDERMIZADOS.getAllOrders(TABLE_MEIO_UMIDO.searchAll());
        numbers = TABLE_MEIO_UMIDO.getAllNumbersSpeciesOfOrders(orders);

        RecyclerView recyclerView = findViewById(R.id.recyrcleTeste);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new CollectionAdapter(numbers, orders, this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        expandableTextView = (ExpandableTextView) findViewById(R.id.descTextViewCollection);
        expandableTextView.setOnStateChangeListener(new ExpandableTextView.OnStateChangeListener() {
            @Override
            public void onStateChange(boolean isShrink) {

            }
        });
    }

}