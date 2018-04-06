package com.exe.paradox.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.exe.paradox.R;
import com.exe.paradox.model.Member;
import com.exe.paradox.util.TouchImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class MemberDisplayActivity extends AppCompatActivity {

    private TextView name, desc;
    private ImageView backNav;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_display);
        TouchImageView imageView = findViewById(R.id.image);
        supportPostponeEnterTransition();

        Toolbar toolbar = findViewById(R.id.toolbar_member_display);
        setSupportActionBar(toolbar);

        setViews();
        Member member = getIntent().getParcelableExtra(Intent.EXTRA_TEXT);
        name.setText(member.getName());

        Picasso.get()
                .load(member.getDrawable())
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        supportStartPostponedEnterTransition();
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

        desc.setText(member.getAbout());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.BLACK);
        }
    }

    private void setViews() {
        name = findViewById(R.id.name);
        desc = findViewById(R.id.desc);
        backNav = findViewById(R.id.back_button);
        toolbar = findViewById(R.id.toolbar_member_display);
    }
}