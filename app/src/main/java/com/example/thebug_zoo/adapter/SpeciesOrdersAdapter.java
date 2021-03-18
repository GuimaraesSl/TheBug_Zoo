package com.example.thebug_zoo.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thebug_zoo.R;
import com.example.thebug_zoo.components.SizeUtils;
import com.example.thebug_zoo.entity.Species;

import java.util.ArrayList;
import java.util.List;

public class SpeciesOrdersAdapter extends RecyclerView.Adapter<SpeciesOrdersAdapter.SpeciesViewHolder> {

    Context context;
    List<String> ordersAdded;

    public SpeciesOrdersAdapter(Context context, List<String> ordersAdded){
        this.context = context;
        this.ordersAdded = ordersAdded;
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
            holder.order.setText(ordersAdded.get(position));
    }

    @Override
    public int getItemCount() {
        return ordersAdded.size();
    }

    public class SpeciesViewHolder extends RecyclerView.ViewHolder {

        public TextView order;

        public SpeciesViewHolder(@NonNull View itemView) {
            super(itemView);
            order = (TextView)itemView.findViewById(R.id.textTeste);
        }
    }

}
