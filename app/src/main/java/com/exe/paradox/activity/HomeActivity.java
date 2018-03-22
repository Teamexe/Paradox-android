package com.exe.paradox.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.exe.paradox.R;

public class HomeActivity extends AppCompatActivity {

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
