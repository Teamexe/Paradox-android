package com.exe.paradox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.exe.paradox.api.model.Profile;
import com.exe.paradox.api.response.AcknowedgementResponse;
import com.exe.paradox.api.response.LevelResponse;
import com.exe.paradox.api.response.ReadOneResponse;
import com.exe.paradox.api.rest.ApiClient;
import com.exe.paradox.api.rest.ApiInterface;
import com.exe.paradox.util.Constants;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayoutFrontCardFlip extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_front);

        /*
        *Fetching Picture from level
        *
        */
        final GPlusFragment gPlusFragment = new GPlusFragment();
        final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

    }
}
