package com.exe.paradox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.exe.paradox.api.model.Profile;
import com.exe.paradox.api.response.AcknowedgementResponse;
import com.exe.paradox.api.response.ReadOneResponse;
import com.exe.paradox.api.rest.ApiClient;
import com.exe.paradox.api.rest.ApiInterface;
import com.exe.paradox.util.Constants;
import com.exe.paradox.util.Preferences;

import am.appwise.components.ni.NoInternetDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReferActivity extends AppCompatActivity {

    TextView refTv;
    NoInternetDialog noInternetDialog;
    EditText refCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer);
        refTv = findViewById(R.id.ref_tv);
        refCode = findViewById(R.id.ref_code);
        noInternetDialog = new NoInternetDialog.Builder(this).build();
        Button refCall = findViewById(R.id.submit);
        final GPlusFragment gPlusFragment = new GPlusFragment();
        final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        refCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callReferral(gPlusFragment.getSignId(), apiService);
            }
        });
        Call<ReadOneResponse> readOneResponseCall = apiService.getProfile(gPlusFragment.getSignId(), Constants.FETCH_TYPE, Constants.FETCH_TOKEN);
        readOneResponseCall.enqueue(new Callback<ReadOneResponse>() {
            @Override
            public void onResponse(Call<ReadOneResponse> call, Response<ReadOneResponse> response) {
                Profile profile = response.body().getProfileData().get(0);
                String message = "Your referral code is: " + profile.getRefCode();
                refTv.setText(message);
            }

            @Override
            public void onFailure(Call<ReadOneResponse> call, Throwable t) {

            }
        });
    }

    private void callReferral(final String googleID, final ApiInterface apiService) {
        if (validate()) {
            Call<ReadOneResponse> responseCall = apiService.getProfile(googleID, Constants.FETCH_TYPE, Constants.FETCH_TOKEN);
            responseCall.enqueue(new Callback<ReadOneResponse>() {
                @Override
                public void onResponse(Call<ReadOneResponse> call, Response<ReadOneResponse> response) {
                    Profile profile = response.body().getProfileData().get(0);
                    Call<AcknowedgementResponse> acknowedgementResponseCall = apiService.submitReferral(Constants.FETCH_TOKEN, String.valueOf(Constants.FETCH_TYPE), googleID, refCode.getText().toString());
                    acknowedgementResponseCall.enqueue(new Callback<AcknowedgementResponse>() {
                        @Override
                        public void onResponse(Call<AcknowedgementResponse> call, Response<AcknowedgementResponse> response) {
                            //Referral is a success
                            if (response.body().getMessage() == null) {
                                Toast.makeText(ReferActivity.this, "The maximum referrals allowed for the referral code has been reached or the referral code is incorrect", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(ReferActivity.this, "Referral successfully added", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(ReferActivity.this, HomeActivity.class));
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<AcknowedgementResponse> call, Throwable t) {
                            //Referral failed
                            Toast.makeText(ReferActivity.this, "The maximum referrals allowed limit has been reached or the referral code is incorrect", Toast.LENGTH_LONG).show();
                            onBackPressed();
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
        if (refCode.getText() == null) {
            Toast.makeText(ReferActivity.this, "Referral code not entered", Toast.LENGTH_LONG).show();
            return false;
        }
        else if (refTv.getText().toString().split(": ")[1].toLowerCase().matches(refCode.getText().toString().trim().toLowerCase())) {
            Toast.makeText(ReferActivity.this, "You've to enter your FRIEND'S referral code not YOUR'S, Smartass!", Toast.LENGTH_LONG).show();
            return false;
        } else if (refCode.getText().toString().trim().length() == 0) {
            Toast.makeText(ReferActivity.this, "Referral code not entered", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        noInternetDialog.onDestroy();
    }
}
