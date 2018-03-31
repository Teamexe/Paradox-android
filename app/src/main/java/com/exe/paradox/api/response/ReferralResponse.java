package com.exe.paradox.api.response;

import com.exe.paradox.api.model.Referral;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by shasha on 31/3/18.
 */

public class ReferralResponse {
    @SerializedName("records")
    List<Referral> referralList;

    public List<Referral> getReferralList() {
        return referralList;
    }
}
