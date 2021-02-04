package com.example.thebug_zoo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

public class InfAdicionais extends AppCompatActivity {

    SliderLayout sliderLayout;
    ImageView imageSeta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inf_adicionais);

        //Fazendo referência e chamando as funções do SliderView
        sliderLayout = (SliderLayout) findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setScrollTimeInSec(2);
        setSliderViwes();

        icons();

    }

    void setSliderViwes(){
        for(int i = 0; i<=2; i++){

            DefaultSliderView sliderView = new DefaultSliderView(this);
            switch (i){
                case 0:
                    sliderView.setImageDrawable(R.drawable.image);
                    break;
                case 1:
                    sliderView.setImageDrawable(R.drawable.image2);
                    break;
                case 2:
                    sliderView.setImageDrawable(R.drawable.image3);
            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderView.setDescription("setDescription"+(i+1));
            final int finalI = i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    Toast.makeText(InfAdicionais.this, "This is slider"+(finalI+1), Toast.LENGTH_LONG).show();
                }
            });

            sliderLayout.addSliderView(sliderView);
        }
    }

    void icons(){

        imageSeta = (ImageView) findViewById(R.id.imageSeta2);
        imageSeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}