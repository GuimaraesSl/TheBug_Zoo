package com.example.thebug_zoo.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thebug_zoo.R;
import com.example.thebug_zoo.components.SizeUtils;
import com.example.thebug_zoo.entity.Species;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SpeciesOrdersAdapter extends RecyclerView.Adapter<SpeciesOrdersAdapter.SpeciesViewHolder> implements Filterable {

    Context context;
    List<String> ordersAdded;
    List<String> ordersAddedAll;

    public SpeciesOrdersAdapter(Context context, List<String> ordersAdded){
        this.context = context;
        this.ordersAdded = ordersAdded;
        this.ordersAddedAll = new ArrayList<>(ordersAdded);
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

    @Override
    public Filter getFilter() {
        Log.d("ENTROU", "GET_FILTER");
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<String> filteredList = new ArrayList<>();
            Log.d("ENTROU", "FILTER");
            if(charSequence.toString().isEmpty()){
                Log.d("ENTROU", "EMPTY");
                Log.d("Ordens:", ordersAddedAll.toString());
                filteredList.addAll(ordersAddedAll);
            } else {
                Log.d("ENTROU", "NOT_EMPTY");
                for (String orders: ordersAddedAll) {
                    if (orders.toLowerCase().contains(charSequence.toString().toLowerCase())){
                        Log.d("Ordens:", orders);
                        filteredList.add(orders);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            ordersAdded.clear();
            ordersAdded.addAll((Collection<? extends String>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class SpeciesViewHolder extends RecyclerView.ViewHolder {

        public TextView order;

        public SpeciesViewHolder(@NonNull View itemView) {
            super(itemView);
            order = (TextView)itemView.findViewById(R.id.textTeste);
        }
    }

}
