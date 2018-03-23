package com.exe.paradox;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import android.widget.Toast;
import com.wajahatkarim3.easyflipview.EasyFlipView;

public class QuestionActivity extends AppCompatActivity {
public class question extends AppCompatActivity {
    public int c=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_question);
        setSupportActionBar(toolbar);
        final EasyFlipView flip = findViewById(R.id.flip);

        final TextView hintText = findViewById(R.id.t2);
        hintText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c==0) {
                    AlphaAnimation animObj = new AlphaAnimation(0,1);
                    animObj.setDuration(1000);
                    hintText.startAnimation(animObj);
                    hintText.setText("back to Question!!");
                    c=1;
                }
                else{
                    AlphaAnimation animObj = new AlphaAnimation(0,1);
                    animObj.setDuration(1000);
                    hintText.startAnimation(animObj);
                    hintText.setText("Go for Hints!!");
                    c=0;
                }
                flip.flipTheView(true);
            }
        });
    }
}

