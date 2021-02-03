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

    ImageView icon_meio_umido, taxidermizados, osteologia, image_seta;
    TextView text_meio_umido, text_taxidermizados, text_osteologia;
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
            icon_meio_umido = (ImageView) findViewById(R.id.icon_search);
            text_meio_umido = (TextView) findViewById(R.id.text_search);
            icon_meio_umido.setImageResource(R.drawable.icon_meio_umido_search);
            text_meio_umido.setText("Meio úmido");
        } else if (ID==2){
            taxidermizados = (ImageView) findViewById(R.id.icon_search);
            text_taxidermizados = (TextView) findViewById(R.id.text_search);
            taxidermizados.setImageResource(R.drawable.icon_taxidermizados_search);
            text_taxidermizados.setText("Taxidermizados");
        } else if(ID==3){
            osteologia = (ImageView) findViewById(R.id.icon_search);
            text_osteologia = (TextView) findViewById(R.id.text_search);
            osteologia.setImageResource(R.drawable.icon_osteologia_search);
            text_osteologia.setText("Osteologia");
        }

        image_seta = (ImageView) findViewById(R.id.image_seta);
        image_seta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        teste = (ImageButton) findViewById(R.id.button_teste);
        teste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Search.this, InfAdicionais.class);
                startActivity(it);
            }
        });
    }
}