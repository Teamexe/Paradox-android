package com.exe.paradox.api.rest;

import com.exe.paradox.api.model.CreateNew;
import com.exe.paradox.api.model.Referral;
import com.exe.paradox.api.response.HintResponse;
import com.exe.paradox.api.response.LeaderboardResponse;
import com.exe.paradox.api.response.ReadOneResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @POST("users/create_new.php")
    Call<CreateNew> getResponse(@Query("google_id") String googleId, @Query("name") String name, @Query("email") String email, @Query("gplus_link") String gPlusLink, @Query("picture") String pic);

    @POST("referral/refer_by.php")
    Call<Referral> getResponse(@Query("google_id") String googleId, @Query("ref_code") String refCode);
}
