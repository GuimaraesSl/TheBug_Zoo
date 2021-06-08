package com.example.thebug_zoo.pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.thebug_zoo.R;
import com.example.thebug_zoo.adapter.EducationAdapter;
import com.example.thebug_zoo.entity.EducationItem;

import java.util.ArrayList;

public class EducationView extends AppCompatActivity {

    public ImageView back;
    private ArrayList<EducationItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_view);

        back = (ImageView) findViewById(R.id.imageSeta);
        back.setOnClickListener(v -> finish());

        RecyclerView recyclerView = findViewById(R.id.educationRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new EducationAdapter(items, this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        items.add(new EducationItem(R.drawable.porifero, getString(R.string.poriferos_title), getString(R.string.poriferos_desc)));
        items.add(new EducationItem(R.drawable.celenterado, getString(R.string.celenterados_title), getString(R.string.celenterados_desc)));
        items.add(new EducationItem(R.drawable.platelmintos, getString(R.string.platelmintos_title), getString(R.string.platelmintos_desc)));
        items.add(new EducationItem(R.drawable.nematelmintos, getString(R.string.nematelmintos_title), getString(R.string.nematelmintos_desc)));
        items.add(new EducationItem(R.drawable.anelideos, getString(R.string.anelideos_title), getString(R.string.anelideos_desc)));
        items.add(new EducationItem(R.drawable.moluscos, getString(R.string.moluscos_title), getString(R.string.moluscos_desc)));
        items.add(new EducationItem(R.drawable.artropodes, getString(R.string.artropodes_title), getString(R.string.artropodes_desc)));
        items.add(new EducationItem(R.drawable.equinodermos, getString(R.string.equinodermos_title), getString(R.string.equinodermos_desc)));
        items.add(new EducationItem(R.drawable.cordados, getString(R.string.cordados_title), getString(R.string.cordados_desc)));
    }
}