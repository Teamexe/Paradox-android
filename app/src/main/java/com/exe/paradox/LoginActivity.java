package com.exe.paradox;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.sdsmdg.harjot.rotatingtext.RotatingTextWrapper;
import com.sdsmdg.harjot.rotatingtext.models.Rotatable;

import am.appwise.components.ni.NoInternetDialog;

public class LoginActivity extends AppCompatActivity {

    NoInternetDialog noInternetDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        noInternetDialog = new NoInternetDialog.Builder(this).build();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        Typeface font = Typeface.createFromAsset(this.getAssets(), "ps.ttf");
        Typeface font1 = Typeface.createFromAsset(this.getAssets(), "psb.ttf");


        RotatingTextWrapper rotatingTextWrapper = (RotatingTextWrapper) findViewById(R.id.custom_switcher);
        rotatingTextWrapper.setSize(35);
        rotatingTextWrapper.setTypeface(font);

        Rotatable rotatable = new Rotatable(Color.parseColor("#FFA036"), 1000, " ","Fun","Tough", "Interesting", "All New", "Paradox 11.0");
        rotatable.setSize(35);
        rotatable.setAnimationDuration(500);
        rotatable.setTypeface(font1);
        rotatingTextWrapper.setContent("This is ?", rotatable);

        if (fragment == null) {
            fragment = new GPlusFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        noInternetDialog.onDestroy();
    }
}