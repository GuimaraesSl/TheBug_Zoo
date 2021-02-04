package com.example.thebug_zoo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Search extends AppCompatActivity {

    ImageView iconMeioUmido, taxidermizados, osteologia, back;
    TextView textMeioUmido, textTaxidermizados, textOsteologia;
    SearchView searchView;
    ImageButton teste;
    public static int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        icon();
        Toast.makeText(getApplicationContext(), ""+ID, Toast.LENGTH_LONG).show();

    }

    void icon(){
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

        teste = (ImageButton) findViewById(R.id.buttonTeste);
        teste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Search.this, InfAdicionais.class);
                startActivity(it);
            }
        });
    }
}