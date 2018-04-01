package com.exe.paradox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.exe.paradox.adapter.LeaderboardAdapter;
import com.exe.paradox.api.model.Leaderboard;
import com.exe.paradox.api.response.LeaderboardResponse;
import com.exe.paradox.api.rest.ApiClient;
import com.exe.paradox.api.rest.ApiInterface;
import com.exe.paradox.util.Constants;
import com.exe.paradox.util.RecyclerItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import am.appwise.components.ni.NoInternetDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderboardActivity extends AppCompatActivity {

    NoInternetDialog noInternetDialog;
    TextView name1, level1, score1, name2, level2, score2, name3, level3, score3;
    ImageView img1, img2, img3;
    public static String url1, url2, url3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_leaderboard);
        setSupportActionBar(toolbar);
        noInternetDialog = new NoInternetDialog.Builder(this).build();

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<LeaderboardResponse> call = apiService.getLeaderboard(Constants.FETCH_TOKEN, Constants.FETCH_TYPE);
        call.enqueue(new Callback<LeaderboardResponse>() {
            @Override
            public void onResponse(Call<LeaderboardResponse> call, Response<LeaderboardResponse> response) {
                if (response.isSuccessful() && response.body().getLeaderboardList().size() > 0) {
                    List<Leaderboard> ranks = response.body().getLeaderboardList();
                    Leaderboard leaderboardOne = ranks.get(0);
                    url1 = leaderboardOne.getPicture();
                    Leaderboard leaderboardTwo = ranks.get(1);
                    url2 = leaderboardTwo.getPicture();
                    Leaderboard leaderboardThree = ranks.get(2);
                    url3 = leaderboardThree.getPicture();
                    setTopRanks(leaderboardOne, leaderboardTwo, leaderboardThree);
                    LeaderboardAdapter leaderboardAdapter = new LeaderboardAdapter(ranks.subList(3, ranks.size()));
                    RecyclerView recyclerView = findViewById(R.id.recv_leaderboard);
                    recyclerView.setFocusable(false);
                    recyclerView.setLayoutManager(new LinearLayoutManager(LeaderboardActivity.this));
                    recyclerView.setAdapter(leaderboardAdapter);
                    recyclerView.setNestedScrollingEnabled(false);
                }
            }

            @Override
            public void onFailure(Call<LeaderboardResponse> call, Throwable t) {
            }
        });
    }

    private void setTopRanks(Leaderboard one, Leaderboard two, Leaderboard three) {
        //name1, level1, score1, name2, level2, score2, name3, level3, score3;
        name1 = findViewById(R.id.name1);
        level1 = findViewById(R.id.level1);
        score1 = findViewById(R.id.score1);
        img1 = findViewById(R.id.image1);
        setValues(name1, level1, score1, img1, one);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeaderboardActivity.this, DpActivity.class).putExtra(Intent.EXTRA_TEXT, url1);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(LeaderboardActivity.this,
                                img1,
                                getString(R.string.transition_string));
                startActivity(intent, options.toBundle());
            }
        });

        name2 = findViewById(R.id.name2);
        level2 = findViewById(R.id.level2);
        score2 = findViewById(R.id.score2);
        img2 = findViewById(R.id.image2);
        setValues(name2, level2, score2, img2, two);

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeaderboardActivity.this, DpActivity.class).putExtra(Intent.EXTRA_TEXT, url2);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(LeaderboardActivity.this,
                                img2,
                                getString(R.string.transition_string));
                startActivity(intent, options.toBundle());
            }
        });

        name3 = findViewById(R.id.name3);
        level3 = findViewById(R.id.level3);
        score3 = findViewById(R.id.score3);
        img3 = findViewById(R.id.image3);
        setValues(name3, level3, score3, img3, three);

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeaderboardActivity.this, DpActivity.class).putExtra(Intent.EXTRA_TEXT, url3);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(LeaderboardActivity.this,
                                img3,
                                getString(R.string.transition_string));
                startActivity(intent, options.toBundle());
            }
        });
    }

    private void setValues(TextView name, TextView level, TextView score, ImageView image, Leaderboard leaderboard) {
        name.setText(leaderboard.getName().split(" ")[0]);
        level.setText(String.valueOf(leaderboard.getLevel()));
        if (leaderboard.getScore() >= 0)
            score.setText(String.valueOf(leaderboard.getScore()));
        else
            score.setText(String.valueOf("0"));
        Picasso.get().load(leaderboard.getPicture()).placeholder(R.drawable.user_icon).into(image);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        noInternetDialog.onDestroy();
    }
}
