package com.exe.paradox;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.exe.paradox.api.model.Hints;
import com.exe.paradox.api.model.Profile;
import com.exe.paradox.api.response.AcknowedgementResponse;
import com.exe.paradox.api.response.HintResponse;
import com.exe.paradox.api.response.LevelResponse;
import com.exe.paradox.api.response.ReadOneResponse;
import com.exe.paradox.api.rest.ApiClient;
import com.exe.paradox.api.rest.ApiInterface;
import com.exe.paradox.util.Constants;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.ArrayList;
import java.util.Random;

import am.appwise.components.ni.NoInternetDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionActivity extends AppCompatActivity {
    public int c = 0;
    public int hint_count = 1;
    public String hint1, hint2, hint3;
    public ImageView imageView;
    Button submitButton;
    EditText answer;
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
        answer = findViewById(R.id.e4);
        hint_text = findViewById(R.id.h1);
        hint_next = findViewById(R.id.h2);
        hint_prev = findViewById(R.id.h3);
        imageView = findViewById(R.id.i1);
        submitButton = findViewById(R.id.submit);
        final EasyFlipView flip = findViewById(R.id.flip);
        final TextView hintText = findViewById(R.id.t2);

        final ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        final GPlusFragment gPlusFragment = new GPlusFragment();

        hintText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c == 0) {
                    AlphaAnimation animObj = new AlphaAnimation(0, 1);
                    animObj.setDuration(1000);
                    hintText.startAnimation(animObj);
                    hintText.setText("Back to Question!!");
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

        final Call<ReadOneResponse> responseCall = apiService.getProfile(gPlusFragment.getSignId(), Constants.FETCH_TYPE, Constants.FETCH_TOKEN);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAnswer(responseCall, apiService, gPlusFragment);
            }
        });

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
                                if (response.body().getLevelUrlList().size() > 0)
                                    Glide.with(QuestionActivity.this).load(response.body().getLevelUrlList().get(0).getUrl()).into(imageView);
                                else {
                                    Toast.makeText(QuestionActivity.this, "No more questions available for now", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(QuestionActivity.this, HomeActivity.class));
                                    finish();
                                }
                            }

                            @Override
                            public void onFailure(Call<LevelResponse> call, Throwable t) {
                                Toast.makeText(QuestionActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
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

        responseCall.clone().enqueue(new Callback<ReadOneResponse>() {
            @Override
            public void onResponse(Call<ReadOneResponse> call, Response<ReadOneResponse> response) {
                //Fetching Hints
                Call<HintResponse> callHint = apiService.getHints(Integer.parseInt(response.body().getProfileData().get(0).getLevel()), Constants.FETCH_TOKEN, Constants.FETCH_TYPE);
                callHint.enqueue(new Callback<HintResponse>() {
                    @Override
                    public void onResponse(Call<HintResponse> call, Response<HintResponse> response) {
                        if (response.isSuccessful()) {
                            ArrayList<Hints> hints = response.body().getList();
                            if (hints.size() > 0) {
                                if (hints.get(0).getHint1() != null)
                                    hint1 = hints.get(0).getHint1();
                                AlphaAnimation animObj = new AlphaAnimation(0, 1);
                                animObj.setDuration(1000);
                                hint_text.startAnimation(animObj);
                                hint_text.setText(hint1);
                                if (hints.get(0).getHint2() != null)
                                    hint2 = hints.get(0).getHint2();
                                if (hints.get(0).getHint3() != null)
                                    hint3 = hints.get(0).getHint3();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<HintResponse> call, Throwable t) {
                        Log.d(getClass().getSimpleName(), "ERROR");
                    }
                });
            }

            @Override
            public void onFailure(Call<ReadOneResponse> call, Throwable t) {

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
                        hint_count = 2;
                        AlphaAnimation animObj = new AlphaAnimation(0, 1);
                        animObj.setDuration(1000);
                        hint_number.startAnimation(animObj);
                        hint_text.startAnimation(animObj);
                        hint_number.setText("HINT NO " + Integer.toString(hint_count));
                        hint_text.setText(hint2);
                        break;
                    case 2:
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

    private void submitAnswer(Call<ReadOneResponse> responseCall, final ApiInterface apiService, final GPlusFragment gPlusFragment) {
        if (validate()) {
            responseCall.clone().enqueue(new Callback<ReadOneResponse>() {
                @Override
                public void onResponse(Call<ReadOneResponse> call, Response<ReadOneResponse> response) {
                    Profile profile = response.body().getProfileData().get(0);
                    Call<AcknowedgementResponse> acknowledgementResponseCall = apiService.submitAnswer(Constants.FETCH_TOKEN, String.valueOf(Constants.FETCH_TYPE), profile.getLevel(), answer.getText().toString().toLowerCase(), gPlusFragment.getSignId());
                    acknowledgementResponseCall.enqueue(new Callback<AcknowedgementResponse>() {
                        @Override
                        public void onResponse(Call<AcknowedgementResponse> call, Response<AcknowedgementResponse> response) {
                            if (response.body().getMessage().matches("true")) {
                                showCorrect();
                            } else {
                                showFalse();
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
        }
    }

    private boolean validate() {
        if (answer.getText() == null) {
            Toast.makeText(QuestionActivity.this, "Answer not entered", Toast.LENGTH_LONG).show();
            return false;
        } else if (answer.getText().toString().trim().length() == 0) {
            Toast.makeText(QuestionActivity.this, "Answer not entered", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void showCorrect() {
        new FancyAlertDialog.Builder(this)
                .setTitle("Correct Answer")
                .setBackgroundColor(Color.parseColor("#32CD32"))
                .setMessage(getMessage())
                .setPositiveBtnBackground(Color.parseColor("#32CD32"))
                .setPositiveBtnText("Rate")
                .setNegativeBtnText("Go back")
                .setAnimation(Animation.SIDE)
                .isCancellable(false)
                .setIcon(R.drawable.ic_alert_tick, Icon.Visible)
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        startActivity(new Intent(QuestionActivity.this, HomeActivity.class));
                        finish();
                    }
                })
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        //Link to the Play store account.
                    }
                })
                .build();
    }

    private void showFalse() {
        new FancyAlertDialog.Builder(this)
                .setTitle("Wrong Answer")
                .setBackgroundColor(Color.parseColor("#B22222"))
                .setMessage(getMessage())
                .setPositiveBtnBackground(Color.parseColor("#B22222"))
                .setPositiveBtnText("Rate")
                .setNegativeBtnText("Go back")
                .setAnimation(Animation.SIDE)
                .isCancellable(true)
                .setIcon(R.drawable.wrong, Icon.Visible)
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        onBackPressed();
                    }
                })
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        //Link to the Play store account.
                    }
                })
                .build();
    }

    private String getMessage() {
        String array[] = getResources().getStringArray(R.array.message_array);
        return array[new Random().nextInt(array.length)];
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        noInternetDialog.onDestroy();
    }
}

