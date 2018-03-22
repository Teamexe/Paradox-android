package com.exe.paradox;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.VideoView;

import com.exe.paradox.adapter.FeaturedAdapter;
import com.exe.paradox.adapter.HomeNavItemsAdapter;
import com.exe.paradox.adapter.ProjectAdapter;
import com.pixelcan.inkpageindicator.InkPageIndicator;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);

        ViewPager pager = findViewById(R.id.pager);
        PagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        InkPageIndicator inkPageIndicator = findViewById(R.id.indicator);
        inkPageIndicator.setViewPager(pager);

        //NAV ITEMS
        RecyclerView homeNavRecv = findViewById(R.id.nav_recv);
        homeNavRecv.setLayoutManager(new GridLayoutManager(HomeActivity.this, 5){
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        HomeNavItemsAdapter homeNavItemsAdapter = new HomeNavItemsAdapter();
        homeNavRecv.setAdapter(homeNavItemsAdapter);

        //ORANGE
        RecyclerView recv = findViewById(R.id.recv_orange);
        ProjectAdapter projectAdapter = new ProjectAdapter(this);
        recv.setLayoutManager(new GridLayoutManager(HomeActivity.this, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recv.setAdapter(projectAdapter);

        RecyclerView featuredProjects = findViewById(R.id.featured_projects);
        FeaturedAdapter featuredAdapter = new FeaturedAdapter(this);
        featuredProjects.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        featuredProjects.setAdapter(featuredAdapter);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }
        int[] drawables = {R.drawable.u1,R.drawable.u2,R.drawable.u3,R.drawable.u4,R.drawable.u5,R.drawable.u6,R.drawable.u7,R.drawable.u8};
        @Override
        public Fragment getItem(int position) {
            return new MyFragment(drawables[position]);
        }

        @Override
        public int getCount() {
            return drawables.length;
        }
    }
}
