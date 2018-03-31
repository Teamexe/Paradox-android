package com.exe.paradox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.exe.paradox.adapter.LeaderboardAdapter;
import com.exe.paradox.api.model.Leaderboard;
import com.exe.paradox.api.response.HintResponse;
import com.exe.paradox.api.response.LeaderboardResponse;
import com.exe.paradox.api.rest.ApiClient;
import com.exe.paradox.api.rest.ApiInterface;
import com.exe.paradox.util.Constants;

import java.util.ArrayList;
import java.util.List;

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


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<LeaderboardResponse> call = apiService.getLeaderboard(Constants.FETCH_TOKEN, Constants.FETCH_TYPE);
        call.enqueue(new Callback<LeaderboardResponse>() {
            @Override
            public void onResponse(Call<LeaderboardResponse> call, Response<LeaderboardResponse> response) {
                if(response.isSuccessful()) {
                    List<Leaderboard> ranks = response.body().getLeaderboardList();
                    LeaderboardAdapter leaderboardAdapter = new LeaderboardAdapter(ranks);
                    RecyclerView recyclerView = findViewById(R.id.recv_leaderboard);

                    recyclerView.setFocusable(false);
                    recyclerView.setLayoutManager(new LinearLayoutManager(LeaderboardActivity.this));
                    recyclerView.setAdapter(leaderboardAdapter);
                    recyclerView.setNestedScrollingEnabled(false);
                }
            }

            @Override
            public void onFailure(Call<LeaderboardResponse> call, Throwable t) {

            }
        });
    }
}
