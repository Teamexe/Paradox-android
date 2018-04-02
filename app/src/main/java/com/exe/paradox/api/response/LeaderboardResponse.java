package com.exe.paradox.api.response;

import com.exe.paradox.api.model.Leaderboard;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LeaderboardResponse {
    @SerializedName("records")
    List<Leaderboard> leaderboardList;

    public List<Leaderboard> getLeaderboardList() {
        return leaderboardList;
    }
}
