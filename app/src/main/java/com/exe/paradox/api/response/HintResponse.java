package com.exe.paradox.api.response;

import com.exe.paradox.api.model.Hints;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HintResponse {
    @SerializedName("records")
    ArrayList<Hints> list;

    public ArrayList<Hints> getList() {
        return list;
    }

    public void setList(ArrayList<Hints> list) {
        this.list = list;
    }
}
