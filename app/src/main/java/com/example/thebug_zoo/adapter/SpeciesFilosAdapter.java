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

public class SpeciesFilosAdapter extends RecyclerView.Adapter<SpeciesFilosAdapter.SpeciesViewHolder> implements Filterable {

    final Context context;
    final List<String> filosAdded;
    final List<String> filosAddedAll;
    private final ClickListenerFeature listener;

    public SpeciesFilosAdapter(Context context, List<String> filosAdded, ClickListenerFeature listener){
        this.context = context;
        this.filosAdded = filosAdded;
        this.filosAddedAll = new ArrayList<>(filosAdded);
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
        holder.filo.setText(filosAdded.get(position));
    }

    @Override
    public int getItemCount() {
        return filosAddedAll.size();
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
                filteredList.addAll(filosAddedAll);
            } else {
                for (String filos: filosAddedAll) {
                    if (filos.toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filteredList.add(filos);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            filosAdded.clear();
            filosAdded.addAll((Collection<? extends String>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class SpeciesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final TextView filo;

        public SpeciesViewHolder(@NonNull View itemView) {
            super(itemView);
            filo = itemView.findViewById(R.id.textList);
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