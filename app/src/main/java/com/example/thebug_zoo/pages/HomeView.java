package com.example.thebug_zoo.pages;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thebug_zoo.R;
import com.example.thebug_zoo.database.BancoController;
import com.example.thebug_zoo.database.DatabaseAcess;
import com.example.thebug_zoo.entity.Species;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class HomeView extends AppCompatActivity {

    ImageButton meioUmido, taxidermizados, osteologia;
    Animation frombottom, frombottom2, frombottom3;
    ImageView iconMeioUmido, iconSeta, iconSeta2, iconSeta3, iconTaxidermizados, iconOsteologia;
    TextView textMeioUmido, textTaxidermizados, textOsteologia;
    OrderView id;
    SQLiteDatabase conection;
    BancoController bancoController;
    ConstraintLayout layoutMenuBar;
    DatabaseAcess databaseAcess;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        icons();
        drawerMenu();
        createConection();
    }

    void icons(){
        //Inicializando o objeto da classe Search
        id = new OrderView();

        //Importando os ImageButton da activity home
        meioUmido = (ImageButton) findViewById(R.id.buttonMeioUmido);
        taxidermizados = (ImageButton) findViewById(R.id.buttonTaxidermizados);
        osteologia = (ImageButton) findViewById(R.id.buttonOsteologia);

        //Importando os icons da activity home
        iconMeioUmido = (ImageView) findViewById(R.id.iconMeioUmido);
        iconSeta = (ImageView) findViewById(R.id.iconSeta);
        iconSeta2 = (ImageView) findViewById(R.id.iconSeta2);
        iconSeta3 = (ImageView) findViewById(R.id.iconSeta3);
        iconTaxidermizados = (ImageView) findViewById(R.id.iconTaxidermizados);
        iconOsteologia = (ImageView) findViewById(R.id.iconOsteologia);

        //Importando os TextView da activity home
        textMeioUmido = (TextView) findViewById(R.id.text_meio_umido);
        textTaxidermizados = (TextView) findViewById(R.id.textTaxidermizados);
        textOsteologia = (TextView) findViewById(R.id.textOsteologia);

        //Impotando as animações da activity home
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        frombottom2 = AnimationUtils.loadAnimation(this, R.anim.frombottom2);
        frombottom3 = AnimationUtils.loadAnimation(this, R.anim.frombottom3);

        //Animações do button meio_umido
        meioUmido.setAnimation(frombottom);
        iconMeioUmido.setAnimation(frombottom);
        textMeioUmido.setAnimation(frombottom);
        iconSeta.setAnimation(frombottom);

        //Animações do button taxidermizados
        taxidermizados.setAnimation(frombottom2);
        iconTaxidermizados.setAnimation(frombottom2);
        textTaxidermizados.setAnimation(frombottom2);
        iconSeta2.setAnimation(frombottom2);

        //Animações do button osteologia
        osteologia.setAnimation(frombottom3);
        iconOsteologia.setAnimation(frombottom3);
        textOsteologia.setAnimation(frombottom3);
        iconSeta3.setAnimation(frombottom3);

        //Setando Clicks
        meioUmido.setOnClickListener(view -> {
            OrderView.ID = 1;
            Intent it = new Intent(HomeView.this, OrderView.class);
            startActivity(it);
        });

        taxidermizados.setOnClickListener(view -> {
            OrderView.ID = 2;
            Intent it = new Intent(HomeView.this, OrderView.class);
            startActivity(it);
        });

        osteologia.setOnClickListener(view -> {
            OrderView.ID = 3;
            Intent it = new Intent(HomeView.this, OrderView.class);
            startActivity(it);
        });
    }

    void drawerMenu(){
        //Importando itens para utilização do DrawerMenu
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Importando layout do DrawerMenu
        drawerLayout = findViewById(R.id.drawer_menu);
        navigationView = findViewById(R.id.navigationView);

        //Setando uso do DrawerMenu
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.getDrawerArrowDrawable().setColor(ContextCompat.getColor(this, R.color.white));
        drawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_educacao){
                    Intent it = new Intent(HomeView.this, EducationView.class);
                    startActivity(it);
                    return true;
                } else if (item.getItemId() == R.id.menu_acervo) {
                    Intent it2 = new Intent(HomeView.this, CollectionView.class);
                    startActivity(it2);
                    return true;
                }
                return true;
            }
        });
    }

    void createConection(){
        try {

            layoutMenuBar = (ConstraintLayout) findViewById(R.id.layoutHome);
            bancoController = new BancoController(this);
            conection = bancoController.getWritableDatabase();
            Snackbar.make(layoutMenuBar, "Conection", Snackbar.LENGTH_LONG)
                    .setAction("OK", null).show();

        } catch (SQLException exception){

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Erro");
            alertDialog.setMessage(exception.getMessage());
            alertDialog.setNeutralButton("OK", null);
            alertDialog.show();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ArrayList<List<Species>> result = new ArrayList<>();
                List<Species> finalResult = new ArrayList<>();
                databaseAcess = new DatabaseAcess(getApplicationContext(), "table_meio_umido");
                result.add(databaseAcess.searchAll());
                databaseAcess.close();
                databaseAcess = new DatabaseAcess(getApplicationContext(), "table_taxidermizados");
                result.add(databaseAcess.searchAll());
                databaseAcess.close();
                databaseAcess = new DatabaseAcess(getApplicationContext(), "table_osteologia");
                result.add(databaseAcess.searchAll());
                databaseAcess.close();
                finalResult = databaseAcess.searchByIdList(databaseAcess.searchByKeyword(result, query));
                databaseAcess.close();
                Intent intent = new Intent(getApplicationContext(), SpeciesView.class);
                intent.putParcelableArrayListExtra("species_home", (ArrayList<? extends Parcelable>) finalResult);
                startActivity(intent);
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