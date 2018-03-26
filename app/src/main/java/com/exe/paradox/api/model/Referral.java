package com.exe.paradox.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shasha on 26/3/18.
 */

public class Referral {
    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }
}
