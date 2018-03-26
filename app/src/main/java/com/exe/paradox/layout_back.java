package com.exe.paradox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class layout_back extends AppCompatActivity {

    public int hint_count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_back);
        TextView hint_number = findViewById(R.id.h);
        TextView hint_text = findViewById(R.id.h1);
        TextView hint_next = findViewById(R.id.h2);


    }
}
