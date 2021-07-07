package com.example.thebug_zoo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thebug_zoo.R;

import java.util.List;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {

    private final List<String> orders;
    final List<Integer> numbersOfSpecies;
    final Context context;

    public CollectionAdapter(List<Integer> numbersOfSpecies, List<String> orders, Context context) {
        this.orders = orders;
        this.numbersOfSpecies = numbersOfSpecies;
        this.context = context;
    }
    @NonNull
    @Override
    public CollectionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionAdapter.ViewHolder holder, int position) {
        final String item = orders.get(position);
        final Integer number = numbersOfSpecies.get(position);
        holder.orders.setText(context.getString(R.string.collection_case, item, number));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView orders;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orders = itemView.findViewById(R.id.textOrders);
        }
    }
}
