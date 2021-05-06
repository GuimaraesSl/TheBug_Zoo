package com.example.thebug_zoo.pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.thebug_zoo.R;
import com.example.thebug_zoo.adapter.SpeciesFamilyAdapter;

import java.util.Collections;
import java.util.List;

public class FamilyView extends AppCompatActivity {

    ImageView back, icon;
    public static int ID;
    String order;
    ConstraintLayout layoutSearch;
    RecyclerView recyclerView;
    SpeciesFamilyAdapter adapter;
    List<String> familyAdded;
    private SpeciesFamilyAdapter.ClickListenerFeature listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_view);
        setSearch();
        setAdapter();
        SearchView searchView = (SearchView) findViewById(R.id.searchFamilyView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    void setSearch(){
        layoutSearch = (ConstraintLayout)findViewById(R.id.layoutFamily);
        icon = (ImageView) findViewById(R.id.iconFamilySearch);
        icon.setImageDrawable(OrderView.defaultIcon.getDrawable());
        back = (ImageView) findViewById(R.id.imageSeta);
        back.setOnClickListener(v -> finish());
    }

    void setAdapter() {
        recyclerView = (RecyclerView) findViewById(R.id.recycleViewFamily);
        order = getIntent().getStringExtra("selected_order");
        familyAdded = OrderView.database.searchByOrder(order);
        Collections.sort(familyAdded);
        adapter = new SpeciesFamilyAdapter(this, familyAdded, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
}