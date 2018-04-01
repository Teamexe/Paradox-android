package com.exe.paradox;

import android.animation.Animator;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.exe.paradox.adapter.FeaturedAdapter;
import com.exe.paradox.adapter.ProjectAdapter;
import com.exe.paradox.api.response.ReferralResponse;
import com.exe.paradox.api.rest.ApiClient;
import com.exe.paradox.api.rest.ApiInterface;
import com.exe.paradox.util.Constants;
import com.exe.paradox.util.RecyclerItemClickListener;
import com.github.florent37.parallax.ScrollView;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.pixelcan.inkpageindicator.InkPageIndicator;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import am.appwise.components.ni.NoInternetDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    NoInternetDialog noInternetDialog;
    CircleImageView img;
    TextView name, sign_out;
    LinearLayout rankings, paradox, stats, referral, members;
    GPlusFragment beta;
    List<Integer> drawables;
    List<Project> projectsFeatured, projectsExe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        noInternetDialog = new NoInternetDialog.Builder(this).build();
        ScrollView rootLayout = (ScrollView) findViewById(R.id.holder);
        projectsFeatured = new ArrayList<>();
        projectsExe = new ArrayList<>();
        drawables = new ArrayList<>();
        populateDrawables();
        if (savedInstanceState == null) {
            rootLayout.setVisibility(View.INVISIBLE);

            ViewTreeObserver viewTreeObserver = rootLayout.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        ScrollView rootLayout = (ScrollView) findViewById(R.id.holder);
                        enterReveal();

                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            rootLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        } else {
                            rootLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }
                });
            }
        }

        beta = new GPlusFragment();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        ViewPager pager = findViewById(R.id.pager);
        PagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.setClipToPadding(false);
        pager.setPadding(60, 0, 60, 0);
        pager.setPageMargin(20);
        InkPageIndicator inkPageIndicator = findViewById(R.id.indicator);
        inkPageIndicator.setViewPager(pager);

        name = findViewById(R.id.title_name);
        img = findViewById(R.id.acc_img);
        sign_out = findViewById(R.id.signout);
        //HIDDEN SIGNOUT
        sign_out.setVisibility(View.GONE);
        final GPlusFragment gPlusFragment = new GPlusFragment();
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeActivity.this);
                alertDialog.setTitle("Log out?");
                alertDialog.setMessage("Do you really want to log out?");
                alertDialog.setPositiveButton("Yes, get me out!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //BECAUSE SIGN_IN TRIGGERS AT ONSTART() OF GPlusFragment so re-login will happen again and again
                        gPlusFragment.signOut();
                        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                        finish();
                    }
                });
                alertDialog.setNegativeButton("I clicked it by mistake, Take me back :)", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });

        if (beta.getImg() != "null")
            Picasso.get().load(beta.getImg()).placeholder(R.drawable.user_icon).into(img);
        name.setText(beta.getDisplayName());
        setAllListeners();

        RecyclerView recv = findViewById(R.id.recv_orange);
        recv.setLayoutManager(new GridLayoutManager(HomeActivity.this, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        projectsExe = getProjectsExe(projectsExe);
        final ProjectAdapter projectAdapter = new ProjectAdapter(this, projectsExe);
        recv.setAdapter(projectAdapter);
        recv.addOnItemTouchListener(new RecyclerItemClickListener(HomeActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                final MaterialStyledDialog.Builder dialog = new MaterialStyledDialog.Builder(HomeActivity.this)
                        .setHeaderDrawable(R.drawable.header)
                        .setIcon(new IconicsDrawable(HomeActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                        .withDialogAnimation(true)
                        .setTitle(projectsExe.get(position).getTitle())
                        .setDescription(projectsExe.get(position).getDesc())
                        .setPositiveText("GitHub")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(projectsExe.get(position).getLink())));
                            }
                        })
                        .setNegativeText("Back");
                dialog.build().show();
            }
        }));

        RecyclerView featuredProjects = findViewById(R.id.featured_projects);
        projectsFeatured = populateFeatured(projectsFeatured);
        FeaturedAdapter featuredAdapter = new FeaturedAdapter(this, projectsFeatured);
        featuredProjects.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        featuredProjects.setAdapter(featuredAdapter);

        featuredProjects.addOnItemTouchListener(new RecyclerItemClickListener(HomeActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                final MaterialStyledDialog.Builder dialog = new MaterialStyledDialog.Builder(HomeActivity.this)
                        .setHeaderDrawable(R.drawable.header)
                        .setIcon(new IconicsDrawable(HomeActivity.this).icon(MaterialDesignIconic.Icon.gmi_github).color(Color.WHITE))
                        .withDialogAnimation(true)
                        .setTitle(projectsFeatured.get(position).getTitle())
                        .setDescription(projectsFeatured.get(position).getDesc())
                        .setPositiveText("GitHub")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(projectsFeatured.get(position).getLink())));
                            }
                        })
                        .setNegativeText("Not now");
                dialog.build().show();
            }
        }));
    }

    private void populateDrawables() {
        drawables.add(R.drawable.hu1);
        drawables.add(R.drawable.hu2);
        drawables.add(R.drawable.hu3);
        drawables.add(R.drawable.hu4);
        drawables.add(R.drawable.hu5);
        drawables.add(R.drawable.hu6);
        drawables.add(R.drawable.hu7);
        Collections.shuffle(drawables);
    }

    private void setAllListeners() {
        //rankings, paradox, stats, referral, members
        setButtons();
        rankings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, LeaderboardActivity.class));
            }
        });

        paradox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, QuestionActivity.class));
            }
        });

        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, StatsActivity.class));
            }
        });

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ReferralResponse> referralResponseCall = apiService.getReferralData(Constants.FETCH_TOKEN, String.valueOf(Constants.FETCH_TYPE), beta.getSignId());
        referralResponseCall.enqueue(new Callback<ReferralResponse>() {
            @Override
            public void onResponse(Call<ReferralResponse> call, Response<ReferralResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getReferralList().get(0).getReferredBy() == null) {
                        referral.setVisibility(View.VISIBLE);
                        referral.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(HomeActivity.this, ReferActivity.class));
                                finish();
                            }
                        });
                    } else {
                        referral.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ReferralResponse> call, Throwable t) {
                referral.setVisibility(View.GONE);
            }
        });


        members.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, MembersActivity.class));
            }
        });
    }

    private void setButtons() {
        rankings = findViewById(R.id.rankings_ll);
        paradox = findViewById(R.id.paradox_ll);
        stats = findViewById(R.id.stats_ll);
        referral = findViewById(R.id.referral_ll);
        members = findViewById(R.id.members_ll);
    }

    private void enterReveal() {
        ScrollView rootLayout = (ScrollView) findViewById(R.id.holder);
        int cx = rootLayout.getWidth() / 2;
        int cy = rootLayout.getHeight() / 2;

        float finalRadius = Math.max(rootLayout.getWidth(), rootLayout.getHeight());

        // create the animator for this view (the start radius is zero)
        Animator circularReveal = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, cx, 0, 0, finalRadius);
        }
        circularReveal.setDuration(1600);

        // make the view visible and start the animation
        rootLayout.setVisibility(View.VISIBLE);
        circularReveal.start();
    }

    private List<Project> populateFeatured(List<Project> projects) {
        final String[] titlesFeatured = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight"};
        final String[] descFeatured = {"werqwer", "sdewafdva", "ewafsfgagEFDDAF", "SFASRF3EFASFDFAAFS", "qdqdsadqwdadad", "sadfasdfwaefcQCD", "FADSFFCDCasdvafdsaf", "ufdhsadflgsajf"};
        final String[] linkFeatured = {"https://github.com/octacode", "https://github.com/octacode", "https://github.com/octacode", "https://github.com/octacode", "https://github.com/octacode", "https://github.com/octacode", "https://github.com/octacode", "https://github.com/octacode"};
        final int[] drawables = {R.drawable.u1, R.drawable.u2, R.drawable.u3, R.drawable.u4, R.drawable.u5, R.drawable.u6, R.drawable.u7, R.drawable.u1};

        for (int i = 0; i < titlesFeatured.length; i++) {
            Project project = new Project(titlesFeatured[i], descFeatured[i], linkFeatured[i], drawables[i]);
            projects.add(project);
        }
        Collections.shuffle(projects);
        return projects;
    }

    private List<Project> getProjectsExe(List<Project> projects) {
        final String[] titlesExe = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight"};
        final String[] descExe = {"werqwer", "sdewafdva", "ewafsfgagEFDDAF", "SFASRF3EFASFDFAAFS", "fsdfwfsfafwef", "qdqdsadqwdadad", "sadfasdfwaefcQCD", "FADSFFCDCasdvafdsaf"};
        final String[] linkExe = {"https://github.com/octacode", "https://github.com/octacode", "https://github.com/octacode", "https://github.com/octacode", "https://github.com/octacode", "https://github.com/octacode", "https://github.com/octacode", "https://github.com/octacode"};
        final int[] drawables = {R.drawable.u1, R.drawable.u2, R.drawable.u3, R.drawable.u4, R.drawable.u5, R.drawable.u6, R.drawable.u7, R.drawable.u1};
        for (int i = 0; i < titlesExe.length; i++) {
            Project project = new Project(titlesExe[i], descExe[i], linkExe[i], drawables[i]);
            projects.add(project);
        }
        Collections.shuffle(projects);
        return projects;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        noInternetDialog.onDestroy();
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new MyFragment(drawables.get(position), position);
        }

        @Override
        public int getCount() {
            return drawables.size();
        }
    }
}



