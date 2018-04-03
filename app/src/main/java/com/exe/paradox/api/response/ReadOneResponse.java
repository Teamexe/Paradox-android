package com.exe.paradox.api.response;

import com.exe.paradox.api.model.Profile;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReadOneResponse {
    @SerializedName("records")
    List<Profile> profileData;

    public List<Profile> getProfileData() {
        return profileData;
    }
}
