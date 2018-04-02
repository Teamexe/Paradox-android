package com.exe.paradox.api.model;

import com.google.gson.annotations.SerializedName;

public class Referral {
    @SerializedName("referral_success")
    String referralSuccessful;

    @SerializedName("refer_by")
    String referredBy;

    public String getReferralSuccessful() {
        return referralSuccessful;
    }

    public String getReferredBy() {
        return referredBy;
    }
}
