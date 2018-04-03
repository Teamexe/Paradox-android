package com.exe.paradox.api.response;

import com.exe.paradox.api.model.Referral;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReferralResponse {
    @SerializedName("records")
    List<Referral> referralList;

    public List<Referral> getReferralList() {
        return referralList;
    }
}
