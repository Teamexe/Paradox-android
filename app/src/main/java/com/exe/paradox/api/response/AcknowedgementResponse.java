package com.exe.paradox.api.response;

import com.google.gson.annotations.SerializedName;

public class AcknowedgementResponse {
    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }
}
