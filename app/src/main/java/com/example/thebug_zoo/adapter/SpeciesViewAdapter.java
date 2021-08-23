package com.example.thebug_zoo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.example.thebug_zoo.pages.FiloView;
import com.example.thebug_zoo.pages.OrderView;

import java.util.List;

public class SpeciesViewAdapter extends RecyclerView.Adapter<SpeciesViewAdapter.SpeciesViewHolder> {

    private final Context context;
    private final List<Species> speciesResult;
    private final ClickListenerFeature listener;
    private final String typeAcess;


    public SpeciesViewAdapter(Context context, List<Species> speciesResult, ClickListenerFeature listener, String typeAcess){
        this.context = context;
        this.speciesResult = speciesResult;
        this.listener = listener;
        this.typeAcess = typeAcess;
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
        byte[] image = new byte[]{};
        if (typeAcess.equals("home")) {
            DatabaseAcess database = new DatabaseAcess(context, speciesResult.get(position).table);
            image = (database.GetImageByID(String.valueOf(speciesResult.get(position)._id), "first"));
        } else if (typeAcess.equals("normal")){
            image = (FiloView.database.GetImageByID(String.valueOf(speciesResult.get(position)._id), "first"));
        }
        Bitmap bt = BitmapFactory.decodeByteArray(image, 0, image.length);
        holder.speciesImage.setImageBitmap(bt);
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
