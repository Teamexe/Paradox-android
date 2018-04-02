package com.exe.paradox.api.model;

import com.google.gson.annotations.SerializedName;

public class Hints {
    @SerializedName("hint1")
    String hint1;
    @SerializedName("hint2")
    String hint2;
    @SerializedName("hint3")
    String hint3;

    public String getHint1() {
        return hint1;
    }

    public void setHint1(String hint1) {
        this.hint1 = hint1;
    }

    public String getHint2() {
        return hint2;
    }

    public void setHint2(String hint2) {
        this.hint2 = hint2;
    }

    public String getHint3() {
        return hint3;
    }

    public void setHint3(String hint3) {
        this.hint3 = hint3;
    }
}
