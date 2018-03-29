package com.exe.paradox.api.response;

import com.exe.paradox.api.model.Leaderboard;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by shasha on 29/3/18.
 */

public class LeaderboardResponse {
    @SerializedName("records")
    List<Leaderboard> leaderboardList;

    public List<Leaderboard> getLeaderboardList() {
        return leaderboardList;
    }
}
