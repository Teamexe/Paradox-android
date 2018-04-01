package com.exe.paradox.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shasha on 2/4/18.
 */

public class General {
    @SerializedName("num_user")
    String numUser;

    @SerializedName("num_ques")
    String numQues;

    public String getNumUser() {
        return numUser;
    }

    public String getNumQues() {
        return numQues;
    }
}
