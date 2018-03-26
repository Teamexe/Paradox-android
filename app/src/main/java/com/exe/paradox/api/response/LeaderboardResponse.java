package com.exe.paradox.api.response;

import com.exe.paradox.api.model.Rank;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by shasha on 26/3/18.
 */

public class LeaderboardResponse {
    @SerializedName("records")
    ArrayList<Rank> list;

    public ArrayList<Rank> getList() {
        return list;
    }

    public void setList(ArrayList<Rank> list) {
        this.list = list;
    }
}
