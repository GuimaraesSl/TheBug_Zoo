package com.example.thebug_zoo;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.thebug_zoo.adapter.DrawerAdapter;
import com.example.thebug_zoo.adapter.DrawerItem;
import com.example.thebug_zoo.adapter.SimpleIten;
import com.example.thebug_zoo.database.BancoController;
import com.example.thebug_zoo.pages.CollectionView;
import com.example.thebug_zoo.pages.EducationView;
import com.example.thebug_zoo.pages.HomeView;
import com.example.thebug_zoo.pages.ImmersionView;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener{

    BancoController bancoController;
    SQLiteDatabase conection;

    protected static final int POS_CLOSE = 0;
    protected static final int POS_HOME = 1;
    protected static final int POS_ACERVO = 2;
    protected static final int POS_IMERSAO = 3;
    protected static final int POS_EDUCATION = 4;

    protected String[] screenTitles;
    protected Drawable[] screenIcons;

    protected SlidingRootNav slidingRootNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createConection();

        setTitle(null);
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

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_CLOSE),
                createItemFor(POS_HOME).setChecked(true),
                createItemFor(POS_ACERVO),
                createItemFor(POS_IMERSAO),
                createItemFor(POS_EDUCATION)
        ));

        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.drawer_list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

    }

    void createConection(){
        try {
            bancoController = new BancoController(this);
            conection = bancoController.getWritableDatabase();

        } catch (SQLException exception){

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Erro");
            alertDialog.setMessage(exception.getMessage());
            alertDialog.setNeutralButton("OK", null);
            alertDialog.show();

        }
    }

    protected DrawerItem createItemFor(int position){
        return new SimpleIten(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.menu_item))
                .withTextTint(color(R.color.menu_item))
                .withSelectedIconTint(color(R.color.purple_text))
                .withSelectedTextTint(color(R.color.purple_text));
    }

    @ColorInt
    private int color(@ColorRes int res){
        return ContextCompat.getColor(this, res);
    }

    public String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.id_activityScreenTitles);
    }

    public Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.id_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++){
            int id = ta.getResourceId(i, 0);
            if(id!=0){
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onItemSelected(int position) {
        if (position == POS_HOME) {
            Intent it = new Intent(this, HomeView.class);
            startActivity(it);
        } else if (position == POS_ACERVO) {
            Intent it = new Intent(this, CollectionView.class);
            startActivity(it);
        } else if (position == POS_EDUCATION) {
            Intent it = new Intent(this, EducationView.class);
            startActivity(it);
        } else if (position == POS_IMERSAO) {
            Intent it = new Intent(this, ImmersionView.class);
            startActivity(it);
        }
        slidingRootNav.closeMenu();
    }
}