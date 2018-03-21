package com.exe.paradox;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);
/*
        TextView txt = findViewById(R.id.home_title);
        Typeface font = Typeface.createFromAsset(getAssets(), "psb.ttf");
        txt.setTypeface(font);*/
    }
}
