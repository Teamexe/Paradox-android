package com.exe.paradox.api.response;

import com.exe.paradox.api.model.User;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by shasha on 26/3/18.
 */

public class ReadOneResponse {
    @SerializedName("records")
    ArrayList<User> list;

    public ArrayList<User> getList() {
        return list;
    }

    public void setList(ArrayList<User> list) {
        this.list = list;
    }
}
