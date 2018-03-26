package com.exe.paradox;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.exe.paradox.adapter.LeaderboardAdapter;
import com.exe.paradox.api.model.Hints;
import com.exe.paradox.api.response.HintResponse;
import com.exe.paradox.api.rest.ApiClient;
import com.exe.paradox.api.rest.ApiInterface;

import java.io.IOException;
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
        NestedScrollView nestedScrollView = findViewById(R.id.nested_scroll_view);
        recyclerView.setFocusable(false);
        nestedScrollView.requestFocus();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new LeaderboardAdapter());
        recyclerView.setNestedScrollingEnabled(false);

        /*
        * NOTE:- @octacode
        * Debug code to test the APIs
        * */

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<HintResponse> call = apiService.getHints(1);
        Log.d(getClass().getSimpleName(), call.request().url().toString());

        call.enqueue(new Callback<HintResponse>() {
            @Override
            public void onResponse(Call<HintResponse> call, Response<HintResponse> response) {
                Log.d(getClass().getSimpleName(), "SUCCESS");
            }

            @Override
            public void onFailure(Call<HintResponse> call, Throwable t) {
                Log.d(getClass().getSimpleName(), "ERROR");
            }
        });
    }
}
