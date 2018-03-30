package com.exe.paradox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.exe.paradox.api.model.Profile;
import com.exe.paradox.api.response.AcknowedgementResponse;
import com.exe.paradox.api.response.ReadOneResponse;
import com.exe.paradox.api.rest.ApiClient;
import com.exe.paradox.api.rest.ApiInterface;
import com.exe.paradox.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReferActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer);

        final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ReadOneResponse> responseCall = apiService.getProfile(Constants.GOOGLE_ID, Constants.FETCH_TYPE, Constants.FETCH_TOKEN);
        responseCall.enqueue(new Callback<ReadOneResponse>() {
            @Override
            public void onResponse(Call<ReadOneResponse> call, Response<ReadOneResponse> response) {
                Profile profile = response.body().getProfileData().get(0);
                Call<AcknowedgementResponse> acknowedgementResponseCall = apiService.submitReferral(Constants.FETCH_TOKEN, String.valueOf(Constants.FETCH_TYPE), Constants.GOOGLE_ID, profile.getRefCode());
                acknowedgementResponseCall.enqueue(new Callback<AcknowedgementResponse>() {
                    @Override
                    public void onResponse(Call<AcknowedgementResponse> call, Response<AcknowedgementResponse> response) {
                        //Referral is a success
                        Toast.makeText(ReferActivity.this, "Referral successfully added", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<AcknowedgementResponse> call, Throwable t) {
                        //Referral failed
                        Toast.makeText(ReferActivity.this, "The maximum referrals allowed limit has been reached", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<ReadOneResponse> call, Throwable t) {

            }
        });
    }
}
