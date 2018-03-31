package com.exe.paradox;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.exe.paradox.adapter.FeaturedAdapter;
import com.exe.paradox.adapter.HomeNavItemsAdapter;
import com.exe.paradox.adapter.ProjectAdapter;
import com.pixelcan.inkpageindicator.InkPageIndicator;
import com.squareup.picasso.Picasso;


import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {

    CircleImageView img;
    TextView name;
    LinearLayout rankings, paradox, stats, referral, members;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        GPlusFragment beta = new GPlusFragment();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        ViewPager pager = findViewById(R.id.pager);
        PagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.setClipToPadding(false);
        pager.setPadding(60, 0, 60, 0);
        pager.setPageMargin(20);
        InkPageIndicator inkPageIndicator = findViewById(R.id.indicator);
        inkPageIndicator.setViewPager(pager);

        name = findViewById(R.id.title_name);
        img = findViewById(R.id.acc_img);

        if(beta.getImg()!="null")
        Picasso.get().load(beta.getImg()).placeholder(R.drawable.user_icon).into(img);
        name.setText(beta.getDisplayName());

        RecyclerView recv = findViewById(R.id.recv_orange);
        ProjectAdapter projectAdapter = new ProjectAdapter(this);
        recv.setLayoutManager(new GridLayoutManager(HomeActivity.this, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recv.setAdapter(projectAdapter);

        setAllListeners();

        RecyclerView featuredProjects = findViewById(R.id.featured_projects);
        FeaturedAdapter featuredAdapter = new FeaturedAdapter(this);
        featuredProjects.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        featuredProjects.setAdapter(featuredAdapter);
    }

    private void setAllListeners () {
    //rankings, paradox, stats, referral, members
        setButtons();
        rankings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, LeaderboardActivity.class));
            }
        });

        paradox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, QuestionActivity.class));
            }
        });

        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, StatsActivity.class));
            }
        });

        referral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ReferActivity.class));
            }
        });

        members.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, MembersActivity.class));
            }
        });
    }

    private void setButtons () {
        rankings = findViewById(R.id.rankings_ll);
        paradox = findViewById(R.id.paradox_ll);
        stats = findViewById(R.id.stats_ll);
        referral = findViewById(R.id.referral_ll);
        members = findViewById(R.id.members_ll);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        int[] drawables = {R.drawable.u1, R.drawable.u2, R.drawable.u3, R.drawable.u4, R.drawable.u5, R.drawable.u6, R.drawable.u7, R.drawable.u8};

        ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new MyFragment(drawables[position], position);
        }

        @Override
        public int getCount() {
            return drawables.length;
        }
    }
}