package com.example.thebug_zoo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.thebug_zoo.R;
import com.example.thebug_zoo.database.DatabaseAcess;
import com.example.thebug_zoo.entity.Species;
import com.example.thebug_zoo.pages.OrderView;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.Holder> {

    Species specie;
    Context context;
    String type;
    ArrayList<byte[]> images;


    public SliderAdapter(Species specie, Context context, String type, ArrayList<byte[]> images) {
        this.specie = specie;
        this.context = context;
        this.type = type;
        this.images = images;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {
        Bitmap bt = BitmapFactory.decodeByteArray(images.get(position), 0, images.get(position).length);
        viewHolder.imageView.setImageBitmap(bt);
    }

    @Override
    public int getCount() {
        return type=="single"?1:2;
    }

    public class Holder extends SliderViewAdapter.ViewHolder {
        private ImageView imageView;
        View itemView;
        public Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_slide);
            this.itemView = itemView;
        }
    }
}
