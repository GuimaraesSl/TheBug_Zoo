package com.example.thebug_zoo.pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thebug_zoo.MainActivity;
import com.example.thebug_zoo.R;
import com.example.thebug_zoo.adapter.DrawerAdapter;
import com.example.thebug_zoo.database.DatabaseAcess;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;

public class CollectionView extends MainActivity {

    DatabaseAcess databaseAcess;
    TextView textCollection;
    ImageButton orders, familys;
    int cont = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        slidingRootNav = new SlidingRootNavBuilder(this)
                .withDragDistance(180)
                .withRootViewElevation(25)
                .withRootViewScale(0.75f)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.drawer_menu)
                .inject();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_CLOSE),
                createItemFor(POS_HOME),
                createItemFor(POS_ACERVO).setChecked(true),
                createItemFor(POS_IMERSAO),
                createItemFor(POS_EDUCATION)
        ));

        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.drawer_list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

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

}