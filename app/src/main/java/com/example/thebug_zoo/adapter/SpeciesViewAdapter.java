package com.example.thebug_zoo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thebug_zoo.R;
import com.example.thebug_zoo.entity.Species;
import com.example.thebug_zoo.pages.OrderView;

import java.util.List;

public class SpeciesViewAdapter extends RecyclerView.Adapter<SpeciesViewAdapter.SpeciesViewHolder> {

    private Context context;
    private List<Species> speciesResult;
    private ClickListenerFeature listener;

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
        byte[] imagem = (OrderView.database.GetImageByID(String.valueOf(speciesResult.get(position)._id)));
        Bitmap bt = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
        holder.speciesImage.setImageBitmap(bt);
    }

    @Override
    public int getItemCount() {
        return speciesResult.size();
    }

    public class SpeciesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView speciesImage;
        TextView speciesTitle;

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
