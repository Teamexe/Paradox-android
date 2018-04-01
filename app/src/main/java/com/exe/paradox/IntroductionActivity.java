package com.exe.paradox;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;

import com.exe.paradox.util.CubeOutDepthTransformation;
import com.exe.paradox.util.Preferences;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.IndicatorController;

public class IntroductionActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(AppIntroFragment.newInstance("One", "One's desc", R.drawable.hu1, getResources().getColor(R.color.slide_1)));
        addSlide(AppIntroFragment.newInstance("Two", "Two's desc", R.drawable.hu2, getResources().getColor(R.color.slide_2)));
        addSlide(AppIntroFragment.newInstance("Three", "Three's desc", R.drawable.hu3, getResources().getColor(R.color.slide_3)));
        addSlide(AppIntroFragment.newInstance("Four", "Four's desc", R.drawable.hu4, getResources().getColor(R.color.slide_4)));
        addSlide(AppIntroFragment.newInstance("Five", "Five's desc", R.drawable.hu5, getResources().getColor(R.color.slide_1)));
        addSlide(AppIntroFragment.newInstance("Six", "Six's desc", R.drawable.hu6, getResources().getColor(R.color.slide_2)));
        addSlide(AppIntroFragment.newInstance("Seven", "Seven's desc", R.drawable.hu7, getResources().getColor(R.color.slide_3)));
        addSlide(AppIntroFragment.newInstance("Eight", "Eight's desc", R.drawable.hu1, getResources().getColor(R.color.slide_4)));

        setCustomTransformer(new CubeOutDepthTransformation());
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Preferences.setFirstRun(this);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Preferences.setFirstRun(this);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
