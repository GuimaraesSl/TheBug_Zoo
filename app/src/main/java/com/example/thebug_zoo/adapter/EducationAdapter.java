package com.example.thebug_zoo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thebug_zoo.R;
import com.example.thebug_zoo.entity.EducationItem;

import java.util.ArrayList;

import ru.embersoft.expandabletextview.ExpandableTextView;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.ViewHolder> {

    private ArrayList<EducationItem> items;
    private Context context;

    public EducationAdapter(ArrayList<EducationItem> items, Context context) {
        this.items = items;
        this.context = context;
    }
    @NonNull
    @Override
    public EducationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.education_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EducationAdapter.ViewHolder holder, int position) {
        final EducationItem item = items.get(position);
        holder.imageEducation.setImageResource(item.getImageResourse());
        holder.titleEducation.setText(item.getTitle());
        holder.descTextView.setText(item.getDesc());
        holder.descTextView.setOnStateChangeListener(new ExpandableTextView.OnStateChangeListener() {
            @Override
            public void onStateChange(boolean isShrink) {
                EducationItem contentItem = items.get(position);
                contentItem.setShrink(isShrink);
                items.set(position, contentItem);
            }
        });
        holder.descTextView.setText(item.getDesc());
        holder.descTextView.resetState(item.isShrink());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageEducation;
        ExpandableTextView descTextView;
        TextView titleEducation;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageEducation = itemView.findViewById(R.id.imageEducation);
            descTextView = itemView.findViewById(R.id.descTextView);
            titleEducation = itemView.findViewById(R.id.titleEducation);
        }
    }
}
