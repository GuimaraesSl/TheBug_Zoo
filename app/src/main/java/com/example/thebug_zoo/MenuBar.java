package com.example.thebug_zoo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

public class MenuBar extends AppCompatActivity {

    ImageButton meio_umido, taxidermizados, osteologia;
    Animation frombottom, frombottom2, frombottom3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_bar);

        meio_umido = (ImageButton) findViewById(R.id.button_meio_umido);
        taxidermizados = (ImageButton) findViewById(R.id.button_taxidermizados);
        osteologia = (ImageButton) findViewById(R.id.button_osteologia);

        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        frombottom2 = AnimationUtils.loadAnimation(this, R.anim.frombottom2);
        frombottom3 = AnimationUtils.loadAnimation(this, R.anim.frombottom3);

        meio_umido.setAnimation(frombottom);
        taxidermizados.setAnimation(frombottom2);
        osteologia.setAnimation(frombottom3);

    }
}