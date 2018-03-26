package com.exe.paradox.api.rest;

import com.exe.paradox.api.response.HintResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by shasha on 26/3/18.
 */

public interface ApiInterface {
    @GET("hints/read.php")
    Call<HintResponse> getHints(@Query("level") int level);
}
