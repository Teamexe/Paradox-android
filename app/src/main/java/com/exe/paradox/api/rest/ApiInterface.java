package com.exe.paradox.api.rest;

import com.exe.paradox.api.response.HintResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by shasha on 26/3/18.
 */

public interface ApiInterface {
    @POST("hints/read.php")
    @FormUrlEncoded
    Call<HintResponse> getHints(@Field("level") int level, @Field("live_token") String token, @Field("req_type") int type);
}
