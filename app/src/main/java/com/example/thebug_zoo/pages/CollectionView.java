package com.example.thebug_zoo.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thebug_zoo.R;
import com.example.thebug_zoo.database.DatabaseAcess;

public class CollectionView extends AppCompatActivity {

    DatabaseAcess databaseAcess;
    TextView textCollection;
    ImageButton orders, familys;
    ImageView back;
    int cont = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_view);

        setBackButton();
        databaseAcess = new DatabaseAcess(this, "table_meio_umido");
        cont = databaseAcess.searchAll().size();
        databaseAcess.close();
        databaseAcess = new DatabaseAcess(this, "table_taxidermizados");
        cont += databaseAcess.searchAll().size();
        databaseAcess.close();
        databaseAcess = new DatabaseAcess(this, "table_osteologia");
        cont += databaseAcess.searchAll().size();
        databaseAcess.close();
        textCollection = (TextView) findViewById(R.id.textCollection);
        orders = (ImageButton) findViewById(R.id.buttonAllOrders);
        familys = (ImageButton) findViewById(R.id.buttonAllFamilys);

        textCollection.setText(getString(R.string.collection_case2, cont));

        orders.setOnClickListener(v -> {
            Intent it = new Intent(CollectionView.this, AllOrders.class);
            startActivity(it);
        });

        familys.setOnClickListener(v -> {
            Intent it = new Intent(CollectionView.this, AllFamilys.class);
            startActivity(it);
        });

    }

    void setBackButton(){
        back = (ImageView) findViewById(R.id.imageSeta);
        back.setOnClickListener(v -> finish());
    }

}