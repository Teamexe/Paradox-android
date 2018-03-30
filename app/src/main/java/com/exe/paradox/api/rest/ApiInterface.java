package com.exe.paradox.api.rest;

import com.exe.paradox.api.response.HintResponse;
import com.exe.paradox.api.response.LeaderboardResponse;
import com.exe.paradox.api.response.ReadOneResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("hints/read.php")
    @FormUrlEncoded
    Call<HintResponse> getHints(@Field("level") int level, @Field("live_token") String token, @Field("req_type") int type);

    @POST("profile/read_one.php")
    @FormUrlEncoded
    Call<ReadOneResponse> getProfile(@Field("google_id") String googleId, @Field("req_type") int type, @Field("live_token") String token);

    //RESPONSE FOR SUBMIT AN ANSWER IS MISSING

    @POST("profile/read.php")
    @FormUrlEncoded
    Call<LeaderboardResponse> getLeaderboard(@Field("live_token") String loveToken, @Field("req_type") int type);

    //RESPONSE FOR CREATING A NEW ACCOUNT

    //RESPONSE FOR REFERRAL IS MISSING

    //RESPONSE FOR FETCHING LEVEL IMAGE
}
