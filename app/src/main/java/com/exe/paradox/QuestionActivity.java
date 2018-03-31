package com.exe.paradox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;
import android.widget.Toast;

import com.exe.paradox.api.model.Hints;
import com.exe.paradox.api.model.Profile;
import com.exe.paradox.api.response.AcknowedgementResponse;
import com.exe.paradox.api.response.HintResponse;
import com.exe.paradox.api.response.LevelResponse;
import com.exe.paradox.api.response.ReadOneResponse;
import com.exe.paradox.api.rest.ApiClient;
import com.exe.paradox.api.rest.ApiInterface;
import com.exe.paradox.util.Constants;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.ArrayList;

import am.appwise.components.ni.NoInternetDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionActivity extends AppCompatActivity {
    public int c = 0;
    public int hint_count = 1;
    public String hint1, hint2, hint3;
    TextView hint_number, hint_text, hint_next, hint_prev;
    NoInternetDialog noInternetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_question);
        setSupportActionBar(toolbar);
        noInternetDialog = new NoInternetDialog.Builder(this).build();
        hint_number = findViewById(R.id.h);
        hint_text = findViewById(R.id.h1);
        hint_next = findViewById(R.id.h2);
        hint_prev = findViewById(R.id.h3);
        final EasyFlipView flip = findViewById(R.id.flip);
        final TextView hintText = findViewById(R.id.t2);

        final ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        hintText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c == 0) {
                    AlphaAnimation animObj = new AlphaAnimation(0, 1);
                    animObj.setDuration(1000);
                    hintText.startAnimation(animObj);
                    hintText.setText("back to Question!!");
                    c = 1;
                } else {
                    AlphaAnimation animObj = new AlphaAnimation(0, 1);
                    animObj.setDuration(1000);
                    hintText.startAnimation(animObj);
                    hintText.setText("Go for Hints!!");
                    c = 0;
                }
                flip.flipTheView(true);
            }
        });

        /*
        *Fetching Picture from level
        * we need to store the googleID
        */
        Call<ReadOneResponse> responseCall = apiService.getProfile(Constants.GOOGLE_ID, Constants.FETCH_TYPE, Constants.FETCH_TOKEN);
        responseCall.enqueue(new Callback<ReadOneResponse>() {
            @Override
            public void onResponse(Call<ReadOneResponse> call, Response<ReadOneResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getProfileData().size() > 0) {
                        Profile profile = response.body().getProfileData().get(0);
                        Call<LevelResponse> levelResponseCall = apiService.getLevelResponse(Constants.FETCH_TOKEN, String.valueOf(Constants.FETCH_TYPE), profile.getLevel());
                        levelResponseCall.enqueue(new Callback<LevelResponse>() {
                            @Override
                            public void onResponse(Call<LevelResponse> call, Response<LevelResponse> response) {
                                // URL's HERE
                                // Toast.makeText(QuestionActivity.this, response.body().getLevelUrlList().get(0).getUrl(), Toast.LENGTH_SHORT).show();
                                // ^^^^^^^^^^
                            }

                            @Override
                            public void onFailure(Call<LevelResponse> call, Throwable t) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<ReadOneResponse> call, Throwable t) {
                Log.d(getClass().getSimpleName(), "FAIL");
            }
        });

        // Submitting an answer
        responseCall.clone().enqueue(new Callback<ReadOneResponse>() {
            @Override
            public void onResponse(Call<ReadOneResponse> call, Response<ReadOneResponse> response) {
                Profile profile = response.body().getProfileData().get(0);
                Call<AcknowedgementResponse> acknowedgementResponseCall = apiService.submitAnswer(Constants.FETCH_TOKEN, String.valueOf(Constants.FETCH_TYPE), profile.getLevel(), "fuck you", Constants.GOOGLE_ID);
                acknowedgementResponseCall.enqueue(new Callback<AcknowedgementResponse>() {
                    @Override
                    public void onResponse(Call<AcknowedgementResponse> call, Response<AcknowedgementResponse> response) {
                        if(response.body().getMessage().matches("true")){
                            //CORRECT
                        }
                        else {
                            // WRONG ANSWER
                        }
                    }

                    @Override
                    public void onFailure(Call<AcknowedgementResponse> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<ReadOneResponse> call, Throwable t) {

            }
        });

        //Fetching Hints
        Call<HintResponse> call = apiService.getHints(1, Constants.FETCH_TOKEN, Constants.FETCH_TYPE);
        Log.d(getClass().getSimpleName(), call.request().url().toString());
        call.enqueue(new Callback<HintResponse>() {
            @Override
            public void onResponse(Call<HintResponse> call, Response<HintResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("SUCCESS", "SUCCESS");
                    ArrayList<Hints> hints = response.body().getList();
                    Log.d("WORKING", hints.get(0).getHint1() + " " + hints.get(0).getHint2() + " " + hints.get(0).getHint3());
                    hint1 = hints.get(0).getHint1();
                    AlphaAnimation animObj = new AlphaAnimation(0, 1);
                    animObj.setDuration(1000);
                    hint_text.startAnimation(animObj);
                    hint_text.setText(hint1);
                    hint2 = hints.get(0).getHint2();
                    hint3 = hints.get(0).getHint3();
                }
            }

            @Override
            public void onFailure(Call<HintResponse> call, Throwable t) {
                Log.d(getClass().getSimpleName(), "ERROR");
            }
        });
        AlphaAnimation animObj = new AlphaAnimation(0, 1);
        animObj.setDuration(1000);
        hint_number.startAnimation(animObj);
        hint_number.setText("HINT NO " + Integer.toString(hint_count));


        hint_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (hint_count) {
                    case 1:
                        Toast.makeText(QuestionActivity.this, "One more hint left!!", Toast.LENGTH_SHORT).show();
                        hint_count = 2;
                        AlphaAnimation animObj = new AlphaAnimation(0, 1);
                        animObj.setDuration(1000);
                        hint_number.startAnimation(animObj);
                        hint_text.startAnimation(animObj);
                        hint_number.setText("HINT NO " + Integer.toString(hint_count));
                        hint_text.setText(hint2);
                        break;
                    case 2:
                        Toast.makeText(QuestionActivity.this, "No more hint left!!", Toast.LENGTH_SHORT).show();

                        hint_count = 3;
                        AlphaAnimation animObj1 = new AlphaAnimation(0, 1);
                        animObj1.setDuration(1000);
                        hint_number.startAnimation(animObj1);
                        hint_text.startAnimation(animObj1);
                        hint_number.setText("HINT NO " + Integer.toString(hint_count));
                        hint_text.setText(hint3);
                        break;
                    default:
                        Toast.makeText(QuestionActivity.this, "No more hint left!!", Toast.LENGTH_SHORT).show();

                        break;

                }
            }
        });
        hint_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (hint_count) {
                    case 3:     //Toast.makeText(QuestionActivity.this, "One more hint left!!", Toast.LENGTH_SHORT).show();
                        hint_count = 2;
                        AlphaAnimation animObj = new AlphaAnimation(0, 1);
                        animObj.setDuration(1000);
                        hint_number.startAnimation(animObj);
                        hint_text.startAnimation(animObj);
                        hint_number.setText("HINT NO " + Integer.toString(hint_count));
                        hint_text.setText(hint2);
                        break;
                    case 2:     //Toast.makeText(QuestionActivity.this, "No more hint left!!", Toast.LENGTH_SHORT).show();

                        hint_count = 1;
                        AlphaAnimation animObj1 = new AlphaAnimation(0, 1);
                        animObj1.setDuration(1000);
                        hint_number.startAnimation(animObj1);
                        hint_text.startAnimation(animObj1);
                        hint_number.setText("HINT NO " + Integer.toString(hint_count));
                        hint_text.setText(hint1);
                        break;
                    default:    //Toast.makeText(QuestionActivity.this, "No more hint left!!", Toast.LENGTH_SHORT).show();

                        break;

                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        noInternetDialog.onDestroy();
    }
}

