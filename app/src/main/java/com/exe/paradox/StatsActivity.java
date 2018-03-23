package com.exe.paradox;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import az.plainpie.PieView;
import az.plainpie.animation.PieAngleAnimation;

public class StatsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_stats);
        setSupportActionBar(toolbar);

        PieView pieView = (PieView) findViewById(R.id.pieView);
        pieView.setPercentageBackgroundColor(Color.GREEN);
        // assuming 12 levels
        pieView.setInnerText("Level 9");

        PieAngleAnimation animation = new PieAngleAnimation(pieView);
        animation.setDuration(2500);
        pieView.startAnimation(animation);

        pieView.setPieInnerPadding(50);

        pieView.setPercentageTextSize(35);
    }
}
