package com.exe.paradox.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Window;
import android.view.WindowManager;

import com.exe.paradox.R;
import com.exe.paradox.util.CubeOutDepthTransformation;
import com.exe.paradox.util.Preferences;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class IntroductionActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        addSlide(AppIntroFragment.newInstance("Team .EXE", "Team .EXE is the technical team of Computer Science & Engineering Department for technical fest NIMBUS at NIT Hamirpur.", R.drawable.hu1, getResources().getColor(R.color.slide_1)));
        addSlide(AppIntroFragment.newInstance("Paradox", "It is the annual event by Team .EXE and this time it is bigger than before. Welcome to Paradox 11.0!", R.drawable.hu2, getResources().getColor(R.color.slide_2)));
        addSlide(AppIntroFragment.newInstance("Questions", "A picture = 1000 words\n1000 words = 1000 riddles\nYou just have to solve one.", R.drawable.ques, getResources().getColor(R.color.slide_3)));
        addSlide(AppIntroFragment.newInstance("Rules", "rule1\nrule2\nrule3\nrule4", R.drawable.rules, getResources().getColor(R.color.slide_4)));
        addSlide(AppIntroFragment.newInstance("Prizes", "Cash prizes for winners and improvement of problem solving and critical thinking skills for all.", R.drawable.cash, getResources().getColor(R.color.slide_1)));
        addSlide(AppIntroFragment.newInstance("Leaderboard", "The board shows all players with their rankings and score.\nTry harder to get up the board!!", R.drawable.leaderboard, getResources().getColor(R.color.slide_2)));
        addSlide(AppIntroFragment.newInstance("Statistics", "Realtime statistics are shown of current performance and other details.", R.drawable.stats, getResources().getColor(R.color.slide_3)));
        addSlide(AppIntroFragment.newInstance("Referrals", "Maximum referrals allowed are 5.\nIf person A uses code of person B then he gets +1 and A gets +2.", R.drawable.referrals, getResources().getColor(R.color.slide_4)));
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
