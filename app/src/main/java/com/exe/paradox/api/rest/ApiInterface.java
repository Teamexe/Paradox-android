package com.exe.paradox.api.rest;

import com.exe.paradox.api.response.AcknowedgementResponse;
import com.exe.paradox.api.response.HintResponse;
import com.exe.paradox.api.response.LeaderboardResponse;
import com.exe.paradox.api.response.LevelResponse;
import com.exe.paradox.api.response.ReadOneResponse;
import com.exe.paradox.api.response.ReferralResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    //READ HINTS
    @POST("hints/read.php")
    @FormUrlEncoded
    Call<HintResponse> getHints(@Field("level") int level, @Field("live_token") String token, @Field("req_type") int type);

    //READ ONE
    @POST("profile/read_one.php")
    @FormUrlEncoded
    Call<ReadOneResponse> getProfile(@Field("google_id") String googleId, @Field("req_type") int type, @Field("live_token") String token);

    //SUBMIT AN ANSWER
    @POST("profile/ans_submit.php")
    @FormUrlEncoded
    Call<AcknowedgementResponse> submitAnswer(@Field("live_token") String liveToken, @Field("req_type") String reqType, @Field("level") String level, @Field("answer") String answer, @Field("google_id") String googleId);

    //READ LEADERBOARD
    @POST("profile/read.php")
    @FormUrlEncoded
    Call<LeaderboardResponse> getLeaderboard(@Field("live_token") String liveToken, @Field("req_type") int type);

    //RESPONSE FOR CREATING A NEW ACCOUNT
    @POST("users/create_new.php")
    @FormUrlEncoded
    Call<AcknowedgementResponse> createProfile(@Field("live_token") String liveToken, @Field("req_type") String req_type, @Field("google_id") String googleId, @Field("name") String name, @Field("email") String email, @Field("picture") String picture);

    //REFERRAL SUBMISSION
    @POST("referral/refer_by.php")
    @FormUrlEncoded
    Call<AcknowedgementResponse> submitReferral(@Field("live_token") String liveToken, @Field("req_type") String reqType, @Field("google_id") String googleId, @Field("ref_code") String refCode);

    //FETCHING LEVEL IMAGE
    @POST("questions/read_level.php")
    @FormUrlEncoded
    Call<LevelResponse> getLevelResponse(@Field("live_token") String liveToken, @Field("req_type") String reqType, @Field("level") String level);

    //REFERRAL VALIDATION
    @POST("referral/ref_success.php")
    @FormUrlEncoded
    Call<ReferralResponse>  getReferralData(@Field("live_token") String liveToken, @Field("req_type") String reqType, @Field("google_id") String googleId);
}
