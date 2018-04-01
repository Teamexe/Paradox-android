package com.exe.paradox;

import android.app.ProgressDialog;
import android.content.AsyncQueryHandler;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.exe.paradox.api.response.AcknowedgementResponse;
import com.exe.paradox.api.rest.ApiClient;
import com.exe.paradox.api.rest.ApiInterface;
import com.exe.paradox.util.Constants;
import com.exe.paradox.util.Typewriter;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GPlusFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "GPlusFragent";
    public static String mStatusTextView, mEmail, mGoogleId;
    public static String imgProfilePic = "null";
    GoogleSignInAccount acct;
    private int RC_SIGN_IN = 0;
    private GoogleApiClient mGoogleApiClient;
    private SignInButton signInButton;
    private ProgressDialog mProgressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity(), this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gplus, parent, false);
        signInButton = (SignInButton) v.findViewById(R.id.sign_in_button);
        imgProfilePic = "null";
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }

        });
        Typewriter typewriter = v.findViewById(R.id.paradox_tag);
        Typewriter by = v.findViewById(R.id.by_tag);
        by.setText("");
        by.setCharacterDelay(400);
        typewriter.setText("");
        typewriter.setCharacterDelay(150);
        typewriter.animateText("Paradox");
        by.animateText("by");
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            acct = result.getSignInAccount();
            mEmail = acct.getEmail();
            mGoogleId = acct.getId();
            mStatusTextView = acct.getDisplayName();
            if (acct.getPhotoUrl() != null)
                imgProfilePic = acct.getPhotoUrl().toString();
            updateUI(true);
        } else {
            updateUI(false);
        }
    }

    private void appLoginIn() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<AcknowedgementResponse> acknowedgementResponseCall = apiService.createProfile(Constants.FETCH_TOKEN, String.valueOf(Constants.FETCH_TYPE), getSignId(), getDisplayName(), getEmail(), getImg());
        acknowedgementResponseCall.enqueue(new Callback<AcknowedgementResponse>() {
            @Override
            public void onResponse(Call<AcknowedgementResponse> call, Response<AcknowedgementResponse> response) {
                if(response.isSuccessful()) {
                    if (response.body().getMessage().matches("true") || response.body().getMessage().matches("Account already exists.")) {
                        startActivity(new Intent(getContext(), HomeActivity.class));
                        getActivity().finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<AcknowedgementResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Login failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI(boolean signedIn) {
        if (signedIn) {
            final Handler handler = new Handler();
            final Runnable r = new Runnable() {
                public void run() {
                    appLoginIn();
                }
            };
            handler.postDelayed(r, 1000);
            signInButton.setVisibility(View.GONE);
        } else {
            imgProfilePic = "null";
            signInButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    public String getImg() {
        return imgProfilePic;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getDisplayName() {
        return mStatusTextView;
    }

    public String getSignId() {
        return mGoogleId;
    }

    public void signOut() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.clearDefaultAccountAndReconnect().setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(Status status) {
                    mGoogleApiClient.disconnect();
                    updateUI(false);
                }
            });

        }
    }
}