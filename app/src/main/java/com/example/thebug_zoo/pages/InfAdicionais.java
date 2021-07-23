package com.example.thebug_zoo.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thebug_zoo.R;
import com.example.thebug_zoo.adapter.SliderAdapter;
import com.example.thebug_zoo.database.DatabaseAcess;
import com.example.thebug_zoo.entity.Species;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class InfAdicionais extends AppCompatActivity {

    SliderView sliderView;
    final ArrayList<byte[]> images = new ArrayList<>();
    SliderAdapter sliderAdapter;
    ImageView imageSeta;
    Species specie;
    FloatingActionButton shareButton;
    TextView txtOrdem, txtFamilia, txtNumId, txtNumArmario, txtOrdem3, textFamilia3, txtIdentificacao2, txtInformacoesAd2, txtFont2, txtColetor2, txtLocal2, txtData2, txtArmario, txtPrat, txtNumPrat;

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
        shareButton = findViewById(R.id.share);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareFunc(v);
            }
        });
    }

    public void shareFunc(View v){
        Log.d("ENTROU", "COMPARTILHANDO");
        byte[] image;
        try {
            image = (OrderView.database.GetImageByID(String.valueOf(specie._id),"first"));
        } catch (Exception e) {
            DatabaseAcess database = new DatabaseAcess(this, specie.table);
            image = (database.GetImageByID(String.valueOf(specie._id), "first"));
        }
        Log.d("IMAGE", "Pegou");
        Bitmap bt = BitmapFactory.decodeByteArray(image, 0, image.length);
        Log.d("Bitmap", "Pegou");
        File file = new File(getExternalCacheDir()+"/"+getResources().getString(R.string.app_name)+".png");
        Log.d("FILE", "Pegou");
        Intent intent;
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            bt.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            Log.d("Compress", "Pegou");

            outputStream.flush();
            outputStream.close();

            intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.putExtra(Intent.EXTRA_TEXT,"ORDEM: " + specie.ordem + "\n" + "FAMÍLIA: " + specie.familia + "\n" + "ID: " + specie.id);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        startActivity(Intent.createChooser(intent, "Share Specie"));
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
        txtNumPrat = findViewById(R.id.txtNumPrat);
        txtNumPrat.setText(String.valueOf(specie.estante));
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

        if(specie.table.equals("table_taxidermizados")) {
            txtArmario = findViewById(R.id.txtArmario);
            txtArmario.setText(R.string.bancada);

            txtPrat = findViewById(R.id.txtPrat);
            txtPrat.setText("");

            txtNumPrat = findViewById(R.id.txtNumPrat);
            txtNumPrat.setText("");
        }

        if(specie.table.equals("table_osteologia")) {
            txtArmario = findViewById(R.id.txtArmario);
            txtArmario.setText(R.string.localizacao);

            txtPrat = findViewById(R.id.txtPrat);
            txtPrat.setText("");

            txtNumPrat = findViewById(R.id.txtNumPrat);
            txtNumPrat.setText("");
        }

    }

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
        imageSeta.setOnClickListener(v -> finish());

    }
}