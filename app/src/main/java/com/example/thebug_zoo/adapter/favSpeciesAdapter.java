package com.example.thebug_zoo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thebug_zoo.R;
import com.example.thebug_zoo.database.DatabaseAcess;
import com.example.thebug_zoo.entity.Species;
import com.example.thebug_zoo.pages.Search;

import java.util.List;

public class favSpeciesAdapter extends RecyclerView.Adapter<SpeciesAdapter>{

    public Context context;
    public List<Species> species;
    public Search search;
    public TextView order;
    public DatabaseAcess database;

    public favSpeciesAdapter(Context context, List<Species> species){
        this.context = context;
        this.species = species;
    }

    @NonNull
    @Override
    public SpeciesAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.layout_list_search, viewGroup, false);

        return new SpeciesAdapter(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpeciesAdapter holder, int position) {
        database = new DatabaseAcess(context);
        species.clear();
        species = database.searchAll();

        holder.order.setText(species.get(position).ordem);

    }

    @Override
    public int getItemCount() {
        return species.size();
    }

}
