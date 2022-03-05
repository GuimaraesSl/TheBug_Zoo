package com.example.thebug_zoo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thebug_zoo.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SpeciesOrdersAdapter extends RecyclerView.Adapter<SpeciesOrdersAdapter.SpeciesViewHolder> implements Filterable {

    final Context context;
    final List<String> ordersAdded;
    final List<String> ordersAddedAll;
    private final ClickListenerFeature listener;

    public SpeciesOrdersAdapter(Context context, List<String> ordersAdded, ClickListenerFeature listener){
        this.context = context;
        this.ordersAdded = ordersAdded;
        this.ordersAddedAll = new ArrayList<>(ordersAdded);
        this.listener = listener;
    }


    @NonNull
    @Override
    public SpeciesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_list, parent,false);
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
        return filter;
    }

    final Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<String> filteredList = new ArrayList<>();
            if(charSequence.toString().isEmpty()){
                filteredList.addAll(ordersAddedAll);
            } else {
                for (String orders: ordersAddedAll) {
                    if (orders.toLowerCase().contains(charSequence.toString().toLowerCase())){
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

    public class SpeciesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final TextView order;

        public SpeciesViewHolder(@NonNull View itemView) {
            super(itemView);
            order = itemView.findViewById(R.id.textList);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    public interface ClickListenerFeature{
        void onClick(View v, int position);
    }
}
