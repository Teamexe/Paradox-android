package com.exe.paradox.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shasha on 29/3/18.
 */

public class Profile {
    @SerializedName("picture")
    String pictureUrl;

    @SerializedName("score")
    String score;

    @SerializedName("level")
    String level;

    public String getPictureUrl() {
        return pictureUrl;
    }

    public String getScore() {
        return score;
    }

    public String getLevel() {
        return level;
    }
}
