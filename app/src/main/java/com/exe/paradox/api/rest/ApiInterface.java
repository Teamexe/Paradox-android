package com.exe.paradox.api.rest;

import com.exe.paradox.api.model.CreateNew;
import com.exe.paradox.api.response.HintResponse;
import com.exe.paradox.api.response.LeaderboardResponse;
import com.exe.paradox.api.response.ReadOneResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by shasha on 26/3/18.
 */

public interface ApiInterface {
    @GET("hints/read.php")
    Call<HintResponse> getHints(@Query("level") int level);

    @GET("profile/read.php")
    Call<LeaderboardResponse> getLeaderboard();

    @GET("profile/read_one.php")
    Call<ReadOneResponse> getUser(@Query("google_id") String googleId);

    @GET("users/create_new.php")
    Call<CreateNew> getResponse();
}
