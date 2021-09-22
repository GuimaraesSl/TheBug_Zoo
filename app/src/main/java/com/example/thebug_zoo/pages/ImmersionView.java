package com.example.thebug_zoo.pages;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.thebug_zoo.R;

public class ImmersionView extends AppCompatActivity {
    ImageView imageSeta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immersion_view);

        imageSeta = findViewById(R.id.imageSeta2);
        imageSeta.setOnClickListener(v -> finish());

        VideoView videoView = findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://"+getPackageName()+"/"+ R.raw.imersao_zoo);

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.start();
    }
}