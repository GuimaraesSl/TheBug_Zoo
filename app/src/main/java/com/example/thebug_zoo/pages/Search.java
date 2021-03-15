package com.example.thebug_zoo.pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thebug_zoo.R;
import com.example.thebug_zoo.adapter.ClassSpeciesAdapter;
import com.example.thebug_zoo.database.DatabaseAcess;
import com.example.thebug_zoo.entity.Species;

import java.util.List;

public class Search extends AppCompatActivity {

    ImageView iconMeioUmido, taxidermizados, osteologia, back;
    TextView textMeioUmido, textTaxidermizados, textOsteologia, textTeste;
    SearchView searchView;
    ImageButton teste;
    public static int ID;
    DatabaseAcess database;
    List<Species> specie;
    ListView listView;
    ConstraintLayout layoutSearch;
    RecyclerView recyclerView;
    ClassSpeciesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        icon();
        setAdapter();
        Toast.makeText(getApplicationContext(), ""+ID, Toast.LENGTH_LONG).show();

    }

    void icon(){
        final DatabaseAcess databaseAcess = DatabaseAcess.getInstance(this);
        databaseAcess.open();
//        database = new DatabaseAcess(this);
//        specie = new Species();
//        specie = database.selectSpecie(1);
//        recyclerView = (RecyclerView)findViewById(R.id.recyrcleView);

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

//        textTeste = (TextView) findViewById(R.id.textTeste);
//        textTeste.setText(specie.ordem);

//        teste = (ImageButton) findViewById(R.id.testeButton);
//        teste.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent it = new Intent(Search.this, InfAdicionais.class);
//                startActivity(it);
//            }
//        });
    }

    void setAdapter() {
        layoutSearch = (ConstraintLayout)findViewById(R.id.layoutSearch);
        recyclerView = (RecyclerView) findViewById(R.id.recyrcleView);
        database = new DatabaseAcess(this);
        specie = database.searchAll();
        adapter = new ClassSpeciesAdapter(this, specie);



        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
}