package com.example.thebug_zoo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thebug_zoo.R;
import com.ortiz.touchview.TouchImageView;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;


public class SliderAdapter extends SliderViewAdapter<SliderAdapter.Holder> {

    final String[] imageUrl;


    public SliderAdapter(Context context, String[] imageUrl) {
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
        public Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_slide);
        }
    }
}
