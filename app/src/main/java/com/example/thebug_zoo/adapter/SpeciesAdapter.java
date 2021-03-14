package com.example.thebug_zoo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thebug_zoo.R;
import com.example.thebug_zoo.database.DatabaseAcess;
import com.example.thebug_zoo.entity.Species;
import com.example.thebug_zoo.pages.Search;

import java.util.ArrayList;
import java.util.List;

public class SpeciesAdapter extends RecyclerView.ViewHolder {

    public TextView order;

    public SpeciesAdapter(@NonNull View itemView) {
        super(itemView);
        order = (TextView)itemView.findViewById(R.id.textTeste);
    }
}

