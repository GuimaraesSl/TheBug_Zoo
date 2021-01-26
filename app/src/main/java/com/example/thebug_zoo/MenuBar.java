package com.example.thebug_zoo;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class MenuBar extends AppCompatActivity {

    ImageButton meio_umido, taxidermizados, osteologia;
    Animation frombottom, frombottom2, frombottom3;
    ImageView icon_meio_umido, icon_seta, icon_seta2, icon_seta3, icon_taxidermizados, icon_osteologia;
    TextView text_meio_umido, text_taxidermizados, text_osteologia;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_bar);

        icons();
        drawerMenu();

    }

    void icons(){
        //Importando os ImageButton da activity home
        meio_umido = (ImageButton) findViewById(R.id.button_meio_umido);
        taxidermizados = (ImageButton) findViewById(R.id.button_taxidermizados);
        osteologia = (ImageButton) findViewById(R.id.button_osteologia);

        //Importando os icons da activity home
        icon_meio_umido = (ImageView) findViewById(R.id.icon_meio_umido);
        icon_seta = (ImageView) findViewById(R.id.icon_seta);
        icon_seta2 = (ImageView) findViewById(R.id.icon_seta2);
        icon_seta3 = (ImageView) findViewById(R.id.icon_seta3);
        icon_taxidermizados = (ImageView) findViewById(R.id.icon_taxidermizados);
        icon_osteologia = (ImageView) findViewById(R.id.icon_osteologia);

        //Importando os TextView da activity home
        text_meio_umido = (TextView) findViewById(R.id.text_meio_umido);
        text_taxidermizados = (TextView) findViewById(R.id.text_taxidermizados);
        text_osteologia = (TextView) findViewById(R.id.text_osteologia);

        //Impotando as anmações da activity home
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        frombottom2 = AnimationUtils.loadAnimation(this, R.anim.frombottom2);
        frombottom3 = AnimationUtils.loadAnimation(this, R.anim.frombottom3);

        //Animações do button meio_umido
        meio_umido.setAnimation(frombottom);
        icon_meio_umido.setAnimation(frombottom);
        text_meio_umido.setAnimation(frombottom);
        icon_seta.setAnimation(frombottom);

        //Animações do button taxidermizados
        taxidermizados.setAnimation(frombottom2);
        icon_taxidermizados.setAnimation(frombottom2);
        text_taxidermizados.setAnimation(frombottom2);
        icon_seta2.setAnimation(frombottom2);

        //Animações do button osteologia
        osteologia.setAnimation(frombottom3);
        icon_osteologia.setAnimation(frombottom3);
        text_osteologia.setAnimation(frombottom3);
        icon_seta3.setAnimation(frombottom3);
    }

    void drawerMenu(){
        //Importando itens para utilização do DrawerMenu
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Importando layout do DrawerMenu
        drawerLayout = findViewById(R.id.drawer_menu);
        navigationView = findViewById(R.id.navigation_view);

        //Setando uso do DrawerMenu
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}