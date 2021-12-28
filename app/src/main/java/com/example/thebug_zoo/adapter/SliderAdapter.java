package com.example.thebug_zoo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.thebug_zoo.R;
import com.example.thebug_zoo.entity.Species;
import com.ortiz.touchview.TouchImageView;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.Holder> {

    final Context context;
    final String[] imageUrl;


    public SliderAdapter(Context context, String[] imageUrl) {
        this.context = context;
        this.imageUrl = imageUrl;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {
        if (!imageUrl[0].equals("")){
            Picasso.get()
                    .load(imageUrl[position])
                    .into(viewHolder.imageView);
        } else {
            viewHolder.imageView.setImageResource(R.mipmap.image_no_conection);
        }
    }

    @Override
    public int getCount() {
        return imageUrl.length == 1 ? 1:2;
    }

    public static class Holder extends SliderViewAdapter.ViewHolder {
        private final TouchImageView imageView;
        final View itemView;
        public Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_slide);
            this.itemView = itemView;
        }
    }
}
