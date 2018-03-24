package com.exe.paradox;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ramotion.foldingcell.FoldingCell;


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
