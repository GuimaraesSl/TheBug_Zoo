package com.example.thebug_zoo.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thebug_zoo.R;
import com.example.thebug_zoo.database.DatabaseAcess;
import com.example.thebug_zoo.entity.Species;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

public class InfAdicionais extends AppCompatActivity {

    SliderLayout sliderLayout;
    ImageView imageSeta;
    Species specie;
    TextView txtOrdem, txtFamilia, txtNumId, txtNumArmario, txtOrdem3, textFamilia3, txtIdentificacao2, txtInformacoesAd2, txtFont2, txtColetor2, txtLocal2, txtData2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inf_adicionais);
        specie = getIntent().getParcelableExtra("selected_specie");
        //Fazendo referência e chamando as funções do SliderView
        sliderLayout = (SliderLayout) findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setScrollTimeInSec(2);

        setInformation();
        setSliderViwes();
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

    void setSliderViwes(){
        for(int i = 0; i<=2; i++){

            DefaultSliderView sliderView = new DefaultSliderView(this);
            switch (i){
                case 0:
                    byte[] imagem = new byte[]{};
                    try {
                        imagem = (OrderView.database.GetImageByID(String.valueOf(specie._id)));
                    } catch (Exception e){
                        DatabaseAcess database = new DatabaseAcess (this, specie.table);
                        imagem = (database.GetImageByID(String.valueOf(specie._id)));
                    }
                    sliderView.setImageByte(imagem);
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