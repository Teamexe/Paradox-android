package com.exe.paradox.api.response;

import com.exe.paradox.api.model.General;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by shasha on 2/4/18.
 */

public class GeneralResponse {
    @SerializedName("records")
    List<General> list;

    public List<General> getList() {
        return list;
    }
}
