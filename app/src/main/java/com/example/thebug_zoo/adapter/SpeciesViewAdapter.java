package com.example.thebug_zoo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thebug_zoo.R;
import com.example.thebug_zoo.database.DatabaseAcess;
import com.example.thebug_zoo.entity.Species;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SpeciesViewAdapter extends RecyclerView.Adapter<SpeciesViewAdapter.SpeciesViewHolder> {

    private final Context context;
    private final List<Species> speciesResult;
    private final ClickListenerFeature listener;
    public String[] imageUrl;
    public final Picasso mPicasso = Picasso.get();

    public SpeciesViewAdapter(Context context, List<Species> speciesResult, ClickListenerFeature listener){
        this.context = context;
        this.speciesResult = speciesResult;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SpeciesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
        return new SpeciesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpeciesViewHolder holder, int position) {
        holder.speciesTitle.setText(speciesResult.get(position).identificacao);
        DatabaseAcess database = new DatabaseAcess(context, speciesResult.get(position).table);
        imageUrl = database.GetImageByID(String.valueOf(speciesResult.get(position)._id), speciesResult.get(position).type);
        if (!imageUrl[0].equals("")){
            mPicasso
                    .load(imageUrl[0])
                    .fit()
                    .centerCrop()
                    .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                    .into(holder.speciesImage);
        } else {
            holder.speciesImage.setImageResource(R.mipmap.image_no_conection);
        }
    }

    @Override
    public int getItemCount() {
        if(speciesResult == null){
            return 0;
        } else {
            return speciesResult.size();
        }
    }

    public class SpeciesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        final ImageView speciesImage;
        final TextView speciesTitle;

        public SpeciesViewHolder(@NonNull View itemView) {
            super(itemView);

            speciesImage = itemView.findViewById(R.id.speciesImage);
            speciesTitle = itemView.findViewById((R.id.speciesTitle));
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }

    public interface ClickListenerFeature{
        void onClick(View v, int position);
    }

}
