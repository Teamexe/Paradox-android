package com.exe.paradox.api.model;

import com.google.gson.annotations.SerializedName;

public class Profile {
    @SerializedName("picture")
    String pictureUrl;

    @SerializedName("score")
    String score;

    @SerializedName("level")
    String level;

    @SerializedName("ref_code")
    String refCode;

    @SerializedName("name")
    String name;

    @SerializedName("reg_time")
    String regTime;

    public String getRefCode() {
        return refCode;
    }

    public String getName() {
        return name;
    }

    public String getRegTime() {
        return regTime;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public String getScore() {
        return score;
    }

    public String getLevel() {
        return level;
    }

    public String getString() {
        return name+" "+refCode+" "+regTime+" "+pictureUrl+" "+score+" "+level;
    }
}
