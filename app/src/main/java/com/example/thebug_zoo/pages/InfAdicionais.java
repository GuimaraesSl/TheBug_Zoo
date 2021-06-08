package com.example.thebug_zoo.pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.viewpager.widget.ViewPager;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowInsetsAnimation;
import android.view.animation.Animation;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thebug_zoo.R;
import com.example.thebug_zoo.adapter.SliderAdapter;
import com.example.thebug_zoo.database.DatabaseAcess;
import com.example.thebug_zoo.entity.Species;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class InfAdicionais extends AppCompatActivity {

    SliderView sliderView;
    ArrayList<byte[]> images = new ArrayList<>();
    SliderAdapter sliderAdapter;
    ImageView imageSeta;
    Species specie;
    TextView txtOrdem, txtFamilia, txtNumId, txtNumArmario, txtOrdem3, textFamilia3, txtIdentificacao2, txtInformacoesAd2, txtFont2, txtColetor2, txtLocal2, txtData2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inf_adicionais);
        specie = getIntent().getParcelableExtra("selected_specie");
        //Fazendo referência e chamando as funções do SliderView
        sliderView = (SliderView) findViewById(R.id.imageSlider);



        setInformation();
        setSliderViews();
        icons();
    }

    void setInformation(){
      txtOrdem = findViewById(R.id.txtOrdem);
      txtOrdem.setText(specie.ordem);
      txtFamilia = findViewById(R.id.txtFamilia);
      txtFamilia.setText(specie.familia);
      txtNumId = findViewById(R.id.txtNumId);
      txtNumId.setText(String.valueOf(specie.id));
      txtNumArmario = findViewById(R.id.txtNumArmario);
      txtNumArmario.setText(String.valueOf(specie.armario));
      txtOrdem3 = findViewById(R.id.txtOrdem3);
      txtOrdem3.setText(specie.ordem);
      textFamilia3 = findViewById(R.id.textFamilia3);
      textFamilia3.setText(specie.familia);
      txtIdentificacao2 = findViewById(R.id.txtIdentificacao2);
      txtIdentificacao2.setText(specie.identificacao);
      txtInformacoesAd2 = findViewById(R.id.txtInformacoesAd2);
      txtInformacoesAd2.setText(specie.inf_adicionais);
      txtFont2 = findViewById(R.id.txtFont2);
      txtFont2.setText(specie.fonte);
      txtColetor2 = findViewById(R.id.txtColetor2);
      txtColetor2.setText(specie.coletor);
      txtLocal2 = findViewById(R.id.txtLocal2);
      txtLocal2.setText(specie._local);
      txtData2 = findViewById(R.id.txtData2);
      txtData2.setText(specie._data);
    };

    void setSliderViews(){
        for (int i = 0; i<=1; i++){
            byte[] image;
            try {
                image = (OrderView.database.GetImageByID(String.valueOf(specie._id), i==0?"first":"second"));
            } catch (Exception e) {
                DatabaseAcess database = new DatabaseAcess(this, specie.table);
                image = (database.GetImageByID(String.valueOf(specie._id), i==0?"first":"second"));
            }
            images.add(image);
        }

        if (images.get(1)==null){
            sliderAdapter = new SliderAdapter(specie, this, "single", images);
        } else {
            sliderAdapter = new SliderAdapter(specie, this, "double", images);
        }

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setIndicatorAnimationDuration(800);
        sliderView.setSliderAnimationDuration(800);
        sliderView.setScrollTimeInSec(4);
        sliderView.startAutoCycle();
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