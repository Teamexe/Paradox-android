package com.exe.paradox.api.response;

import com.exe.paradox.api.model.LevelUrl;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by shasha on 30/3/18.
 */

public class LevelResponse {
    @SerializedName("records")
    List<LevelUrl> levelUrlList;

    public List<LevelUrl> getLevelUrlList() {
        return levelUrlList;
    }
}
