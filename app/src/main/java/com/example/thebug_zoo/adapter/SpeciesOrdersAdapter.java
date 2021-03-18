package com.example.thebug_zoo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thebug_zoo.R;
import com.example.thebug_zoo.entity.Species;

import java.util.List;

public class SpeciesOrdersAdapter extends RecyclerView.Adapter<SpeciesOrdersAdapter.SpeciesViewHolder> {

    Context context;
    List<Species> speciesList;

    public SpeciesOrdersAdapter(Context context, List<Species> speciesList){
        this.context = context;
        this.speciesList = speciesList;
    }

    @NonNull
    @Override
    public SpeciesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_list_orders, parent,false);
        return new SpeciesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpeciesViewHolder holder, int position) {
        Log.d("Index", String.valueOf(position));
        Log.d("SpecieOrderIndex", speciesList.get(position).ordem);
        holder.order.setText(speciesList.get(position).ordem);
    }

    @Override
    public int getItemCount() {
        return speciesList.size();
    }

    public class SpeciesViewHolder extends RecyclerView.ViewHolder {

        public TextView order;

        public SpeciesViewHolder(@NonNull View itemView) {
            super(itemView);
            order = (TextView)itemView.findViewById(R.id.textTeste);
        }
    }
}
