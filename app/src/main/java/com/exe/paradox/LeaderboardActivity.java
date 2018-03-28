package com.exe.paradox;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.exe.paradox.adapter.LeaderboardAdapter;
import com.exe.paradox.api.model.CreateNew;
import com.exe.paradox.api.model.Hints;
import com.exe.paradox.api.model.Rank;
import com.exe.paradox.api.model.Referral;
import com.exe.paradox.api.model.User;
import com.exe.paradox.api.response.HintResponse;
import com.exe.paradox.api.response.LeaderboardResponse;
import com.exe.paradox.api.response.ReadOneResponse;
import com.exe.paradox.api.rest.ApiClient;
import com.exe.paradox.api.rest.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_leaderboard);
        setSupportActionBar(toolbar);
        RecyclerView recyclerView = findViewById(R.id.recv_leaderboard);

        recyclerView.setFocusable(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new LeaderboardAdapter());
        recyclerView.setNestedScrollingEnabled(false);
        /*
        * NOTE:- @octacode
        * Debug code to test the APIs
        * */


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        // FETCHING LEADERBOARD
        Call<LeaderboardResponse> leaderboardResponseCall = apiService.getLeaderboard();
        leaderboardResponseCall.enqueue(new Callback<LeaderboardResponse>() {
            @Override
            public void onResponse(Call<LeaderboardResponse> call, Response<LeaderboardResponse> response) {
                if (response.isSuccessful()) {
                    ArrayList<Rank> leaderboards = response.body().getList();
                    Log.d("SUCCESS", leaderboards.get(0).getName()+" "+leaderboards.size());
                }
            }

            @Override
            public void onFailure(Call<LeaderboardResponse> call, Throwable t) {

            }
        });

        //READ ONE
        Call<ReadOneResponse> readOneResponseCall = apiService.getUser("1524");
        readOneResponseCall.enqueue(new Callback<ReadOneResponse>() {
            @Override
            public void onResponse(Call<ReadOneResponse> call, Response<ReadOneResponse> response) {
                if (response.isSuccessful()) {
                    User user = response.body().getList().get(0);
                    Log.d("READ_ONE", user.getPicture());
                }
            }

            @Override
            public void onFailure(Call<ReadOneResponse> call, Throwable t) {

            }
        });

        // REFERRAL
        Call<Referral> referralCall = apiService.getResponse("","");
        referralCall.enqueue(new Callback<Referral>() {
            @Override
            public void onResponse(Call<Referral> call, Response<Referral> response) {
                if(response.isSuccessful()){
                    Log.d("REFERRAL", response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<Referral> call, Throwable t) {

            }
        });

        // CREATE NEW ACCOUNT
        Call<CreateNew> createNewCall = apiService.getResponse("123","name","email","link","pic");
        createNewCall.enqueue(new Callback<CreateNew>() {
            @Override
            public void onResponse(Call<CreateNew> call, Response<CreateNew> response) {
                if (response.isSuccessful()) {
                    Log.d("CREATE NEW", response.body().getMessage());
                    // if new tuple has been inserted then proceed further else don't
                }
            }

            @Override
            public void onFailure(Call<CreateNew> call, Throwable t) {

            }
        });
    }
}
