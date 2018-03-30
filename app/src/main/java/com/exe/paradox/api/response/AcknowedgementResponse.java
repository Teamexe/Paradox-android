package com.exe.paradox.api.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shasha on 30/3/18.
 */

public class AcknowedgementResponse {
    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }
}
