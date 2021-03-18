package com.example.thebug_zoo.pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thebug_zoo.R;
import com.example.thebug_zoo.adapter.SpeciesOrdersAdapter;
import com.example.thebug_zoo.components.CommonItemSpaceDecoration;
import com.example.thebug_zoo.database.DatabaseAcess;
import com.example.thebug_zoo.entity.Species;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderView extends AppCompatActivity {

    ImageView iconMeioUmido, taxidermizados, osteologia, back;
    TextView textMeioUmido, textTaxidermizados, textOsteologia;
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
        setContentView(R.layout.activity_order);
        icon();
        setAdapter();
        Toast.makeText(getApplicationContext(), ""+ID, Toast.LENGTH_LONG).show();

    }

    void icon(){
        final DatabaseAcess databaseAcess = DatabaseAcess.getInstance(this);
        databaseAcess.open();

        if(ID==1) {
            iconMeioUmido = (ImageView) findViewById(R.id.iconSearch);
            textMeioUmido = (TextView) findViewById(R.id.textSearch);
            iconMeioUmido.setImageResource(R.drawable.icon_meio_umido_search);
            textMeioUmido.setText("Meio úmido");
        } else if (ID==2){
            taxidermizados = (ImageView) findViewById(R.id.iconSearch);
            textTaxidermizados = (TextView) findViewById(R.id.textSearch);
            taxidermizados.setImageResource(R.drawable.icon_taxidermizados_search);
            textTaxidermizados.setText("Taxidermizados");
        } else if(ID==3){
            osteologia = (ImageView) findViewById(R.id.iconSearch);
            textOsteologia = (TextView) findViewById(R.id.textSearch);
            osteologia.setImageResource(R.drawable.icon_osteologia_search);
            textOsteologia.setText("Osteologia");
        }

        back = (ImageView) findViewById(R.id.imageSeta);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void setAdapter() {
        layoutSearch = (ConstraintLayout)findViewById(R.id.layoutOrder);
        recyclerView = (RecyclerView) findViewById(R.id.recyrcleView);
        database = new DatabaseAcess(this);
        specie = database.searchAll();
        for (int i = 0; i < specie.size(); i++){
            if (!orderAdded.contains(specie.get(i).ordem)){
                orderAdded.add(specie.get(i).ordem);
            }
        };
        Collections.sort(orderAdded);
        adapter = new SpeciesOrdersAdapter(this, orderAdded);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.addItemDecoration(new CommonItemSpaceDecoration(16));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
}