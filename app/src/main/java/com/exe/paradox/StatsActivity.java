package com.exe.paradox;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.exe.paradox.api.model.Profile;
import com.exe.paradox.api.response.ReadOneResponse;
import com.exe.paradox.api.response.ReferralResponse;
import com.exe.paradox.api.rest.ApiClient;
import com.exe.paradox.api.rest.ApiInterface;
import com.exe.paradox.util.Constants;
import com.squareup.picasso.Picasso;

import am.appwise.components.ni.NoInternetDialog;
import az.plainpie.PieView;
import az.plainpie.animation.PieAngleAnimation;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatsActivity extends AppCompatActivity {

    TextView nameTv, emailTv, scoreTv, dateOfRegTv, levelTv, timeOfRegTv, refCodeTv, refGivenTv;
    PieView pieView;
    CircleImageView circleImageView;
    NoInternetDialog noInternetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Toolbar toolbar = findViewById(R.id.toolbar_stats);
        setSupportActionBar(toolbar);
        setViews();
        noInternetDialog = new NoInternetDialog.Builder(this).build();
        final GPlusFragment gPlusFragment = new GPlusFragment();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ReadOneResponse> responseCall = apiService.getProfile(gPlusFragment.getSignId(), Constants.FETCH_TYPE, Constants.FETCH_TOKEN);
        responseCall.enqueue(new Callback<ReadOneResponse>() {
            @Override
            public void onResponse(Call<ReadOneResponse> call, Response<ReadOneResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getProfileData().size() > 0) {
                        Profile profile = response.body().getProfileData().get(0);
                        doPieChart(profile);

                        Picasso.get().load(gPlusFragment.getImg()).placeholder(R.drawable.user_icon).into(circleImageView);

                        nameTv.setText(gPlusFragment.getDisplayName());
                        emailTv.setText(gPlusFragment.getEmail());

                        if(Integer.parseInt(profile.getScore())<0)
                            scoreTv.setText("0");
                        else
                            scoreTv.setText(profile.getScore());

                        dateOfRegTv.setText(profile.getRegTime().split(" ")[0]);

                        timeOfRegTv.setText(profile.getRegTime().split(" ")[1]);

                        levelTv.setText(profile.getLevel());

                        refCodeTv.setText(profile.getRefCode());
                    }
                }
            }

            @Override
            public void onFailure(Call<ReadOneResponse> call, Throwable t) {

            }
        });

        Call<ReferralResponse> referralResponseCall = apiService.getReferralData(Constants.FETCH_TOKEN, String.valueOf(Constants.FETCH_TYPE), gPlusFragment.getSignId());
        referralResponseCall.enqueue(new Callback<ReferralResponse>() {
            @Override
            public void onResponse(Call<ReferralResponse> call, Response<ReferralResponse> response) {
                refGivenTv.setText(response.body().getReferralList().get(0).getReferralSuccessful());
            }

            @Override
            public void onFailure(Call<ReferralResponse> call, Throwable t) {

            }
        });
    }

    private void setViews() {
        pieView = (PieView) findViewById(R.id.pieView);
        circleImageView = findViewById(R.id.profile_pic);
        nameTv = findViewById(R.id.name);
        emailTv = findViewById(R.id.email);
        scoreTv = findViewById(R.id.score);
        dateOfRegTv = findViewById(R.id.date_show);
        timeOfRegTv = findViewById(R.id.time);
        levelTv = findViewById(R.id.level);
        refCodeTv = findViewById(R.id.ref_code);
        refGivenTv = findViewById(R.id.ref_given);
    }

    private void doPieChart(Profile profile) {
        pieView.setPercentageBackgroundColor(Color.parseColor("#ff1744"));
        pieView.setInnerText("Level " + profile.getLevel());
        pieView.setPercentage((Float.parseFloat(profile.getLevel()) / 12) * 100);
        pieView.setPieAngle((pieView.getPercentage() / 100) * 360);
        pieView.setPieInnerPadding(50);
        pieView.setPercentageTextSize(35);

        PieAngleAnimation animation = new PieAngleAnimation(pieView);
        animation.setDuration(2500);
        pieView.startAnimation(animation);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        noInternetDialog.onDestroy();
    }
}
