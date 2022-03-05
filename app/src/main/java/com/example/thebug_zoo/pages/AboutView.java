package com.example.thebug_zoo.pages;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.example.thebug_zoo.MainActivity;
import com.example.thebug_zoo.R;
import com.example.thebug_zoo.adapter.DrawerAdapter;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;

public class AboutView extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        slidingRootNav = new SlidingRootNavBuilder(this)
                .withDragDistance(180)
                .withRootViewElevation(25)
                .withRootViewScale(0.75f)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.drawer_menu)
                .inject();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_CLOSE),
                createItemFor(POS_HOME),
                createItemFor(POS_ACERVO),
                createItemFor(POS_IMERSAO),
                createItemFor(POS_EDUCATION),
                createItemFor(POS_ABOUT).setChecked(true)
        ));

        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.drawer_list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        RelativeLayout privacy_policy_bt = findViewById(R.id.buttonPrivacy);
        privacy_policy_bt.setOnClickListener(v -> {
            Intent it = new Intent(this, PrivacyPolicy.class);
            startActivity(it);
        });
    }
}