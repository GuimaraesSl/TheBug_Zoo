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

public class SpeciesFamilyAdapter extends RecyclerView.Adapter<SpeciesFamilyAdapter.SpeciesViewHolder> implements Filterable {

    final Context context;
    final List<String> familyAdded;
    final List<String> familyAddedAll;
    private final ClickListenerFeature listener;

    public SpeciesFamilyAdapter(Context context, List<String> familyAdded, ClickListenerFeature listener){
        this.context = context;
        this.familyAdded = familyAdded;
        this.familyAddedAll = new ArrayList<>(familyAdded);
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
        holder.family.setText(familyAdded.get(position));
    }

    @Override
    public int getItemCount() {
        return familyAdded.size();
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
                filteredList.addAll(familyAddedAll);
            } else {
                for (String orders: familyAddedAll) {
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
            familyAdded.clear();
            familyAdded.addAll((Collection<? extends String>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class SpeciesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final TextView family;

        public SpeciesViewHolder(@NonNull View itemView) {
            super(itemView);
            family = itemView.findViewById(R.id.textList);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {listener.onClick(view, getAdapterPosition());
        }
    }

    public interface ClickListenerFeature{
        void onClick(View v, int position);
    }
}

