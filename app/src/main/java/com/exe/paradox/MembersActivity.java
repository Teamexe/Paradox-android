package com.exe.paradox;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.widget.ImageView;

import com.ramotion.foldingcell.FoldingCell;
import com.squareup.picasso.Picasso;


public class MembersActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);
        final FoldingCell fc = findViewById(R.id.folding_cell);
        fc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fc.toggle(false);
            }
        });
        final FoldingCell fc1 = findViewById(R.id.folding_cell1);
        fc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fc1.toggle(false);
            }
        });
        final FoldingCell fc2 = findViewById(R.id.folding_cell2);
        fc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fc2.toggle(false);
            }
        });
        final FoldingCell fc3 = findViewById(R.id.folding_cell3);
        fc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fc3.toggle(false);
            }
        });
    }

}
