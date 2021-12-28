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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thebug_zoo.R;
import com.example.thebug_zoo.database.DatabaseAcess;
import com.example.thebug_zoo.entity.Species;
import com.example.thebug_zoo.pages.FiloView;
import com.example.thebug_zoo.pages.OrderView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SpeciesViewAdapter extends RecyclerView.Adapter<SpeciesViewAdapter.SpeciesViewHolder> {

    private final Context context;
    private final List<Species> speciesResult;
    private final ClickListenerFeature listener;
    public String[] imageUrl;
    int cont = 0;


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
        Log.d("POSITION", String.valueOf(position));
        holder.speciesTitle.setText(speciesResult.get(position).identificacao);
        DatabaseAcess database = new DatabaseAcess(context, "table_arthro");
        imageUrl = database.GetImageByID(context, String.valueOf(speciesResult.get(position)._id));
        if (!imageUrl[0].equals("")){
            Picasso.get()
                    .load(imageUrl[0])
                    .into(holder.speciesImage);
        } else {
            if (cont == 0) {
                //NA HOME OU AQUI?
                if (!database.isConnected(context)){
                    Toast.makeText(context, "Sem Conexão com a Internet", Toast.LENGTH_LONG).show();
                    cont = 1;
                }
            }
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
