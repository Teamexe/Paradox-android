package com.exe.paradox.activity;

import android.animation.Animator;
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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
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
import com.exe.paradox.R;
import com.exe.paradox.adapter.FeaturedAdapter;
import com.exe.paradox.adapter.ProjectAdapter;
import com.exe.paradox.api.response.ReferralResponse;
import com.exe.paradox.api.rest.ApiClient;
import com.exe.paradox.api.rest.ApiInterface;
import com.exe.paradox.fragment.GPlusFragment;
import com.exe.paradox.fragment.MyFragment;
import com.exe.paradox.model.Event;
import com.exe.paradox.model.FeaturedProject;
import com.exe.paradox.model.Project;
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
    List<Event> events;
    CardView paradoxSite, exeSite;
    List<FeaturedProject> projectsFeatured;
    List<Project> projectsExe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        noInternetDialog = new NoInternetDialog.Builder(this).build();
        ScrollView rootLayout = (ScrollView) findViewById(R.id.holder);
        projectsFeatured = new ArrayList<>();
        projectsExe = new ArrayList<>();
        events = new ArrayList<>();
        paradoxSite = findViewById(R.id.paradox_site);
        exeSite = findViewById(R.id.exe_site);
        populateDrawables();

        paradoxSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://teamexe.in/paradox")));
            }
        });

        exeSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://teamexe.in")));
            }
        });

        if (savedInstanceState == null) {
            rootLayout.setVisibility(View.INVISIBLE);

            ViewTreeObserver viewTreeObserver = rootLayout.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        ScrollView rootLayout = (ScrollView) findViewById(R.id.holder);
                        enterReveal();
                        rootLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
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
        pager.setPadding(0, 0, 0, 0);
        pager.setPageMargin(20);
        InkPageIndicator inkPageIndicator = findViewById(R.id.indicator);
        inkPageIndicator.setViewPager(pager);

        name = findViewById(R.id.title_name);
        img = findViewById(R.id.acc_img);
        sign_out = findViewById(R.id.signout);
        //HIDDEN SIGNOUT
        sign_out.setVisibility(View.GONE);

        if (beta.getImg() != "null") {
            Picasso.get().load(beta.getImg()).placeholder(R.drawable.user_icon).into(img);
        } else {
            img.setImageDrawable(getResources().getDrawable(R.drawable.default_image));
        }

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ContactActivity.class));
            }
        });

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
                        .setPositiveText("Back")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                            }
                        });
                dialog.build().show();
            }
        }));

        RecyclerView featuredProjects = findViewById(R.id.featured_projects);
        projectsFeatured = populateFeatured(projectsFeatured);
        FeaturedAdapter featuredAdapter = new FeaturedAdapter(HomeActivity.this, projectsFeatured);
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
        int drawables[] = {R.drawable.au3, R.drawable.au1, R.drawable.au7, R.drawable.au4, R.drawable.au6, R.drawable.au2, R.drawable.au5};
        String titles[] = {
                "Game of codes",
                "Web smash",
                "Paradox",
                "Code golf",
                "Decodance",
                "Hunt the code",
                "Big Oh"
        };
        String desc[] = {
                "It is an online coding event that is organized under the umbrella of a coding platform, before NIMBUS. This is a large scale “short” challenge that is open to any participation(global included), giving the participants from the institute a much-needed peek into the online competitive coding community.",
                "It is an event aimed at recognizing the web-developer community at NITH.Each participant is given a theme/template as an impetus to their project, and they must build upon the provided knowledge to create a webpage/website for the same.",
                "It is a globally acclaimed event that is organized before as well as during NIMBUS.It is an online level-based game where the participant looks for a “word” that is signified by an image/set of images. Hints are provided periodically to help with the process of thinking in the right direction.It is also open for participation globally.",
                "It is a coding-based exercise that is organized on an online platform.The participant is given a simple logical problem for which he/she must write an “short” piece of code, decided on the basis of length, time of execution etc. The shortest code that perfectly solves the problem, wins.",
                "It is a cryptography based event that uses the prevalent encryption methods to weave a path for the user to find a hidden message, which wins them the competition.The participant is given a file which they have to examine thoroughly and find a clue to the next step in the puzzle, and they should also know when to stop too. The one reaching furthest in the chain wins the competition.",
                "This event is organized via an online platform, chiefly requiring a file-sharing medium.The participant is provided with an executable file and examples for the program that it runs. The participant then must infer the working of the logic and write code to emulate the program.",
                "It is an online 3-day long coding event that is organized under the umbrella of a coding platform, pre NIMBUS. This is a “long” challenge open to all students of the college,with a set of challenging coding problems."
        };
        for (int i = 0; i < titles.length; i++) {
            events.add(new Event(titles[i], desc[i], drawables[i]));
        }
        Collections.shuffle(events);
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
        ScrollView rootLayout = findViewById(R.id.holder);
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

    private List<FeaturedProject> populateFeatured(List<FeaturedProject> projects) {
        final String[] titlesFeatured = {"Amahi", "VLC", "Wine", "Kodi", "Mozilla", "Libre", "WordPress", "Gnome"};

        final String[] descFeatured = {
                "Amahi is software that runs on a dedicated PC as a central computer for your home. It handles your entertainment, storage, and computing needs. You can store, organize and deliver your recorded TV shows, videos and music to media devices in your network.",
                "VLC media player (commonly known as VLC) is a free and open-source, portable and cross-platform media player and streaming media server developed by the VideoLAN project. VLC is available for desktop operating systems and mobile platforms, such as Android, iOS, Tizen, Windows 10 Mobile and Windows Phone.",
                "Wine (recursive backronym for Wine Is Not an Emulator) is a free and open-source compatibility layer that aims to allow computer programs (application software and computer games) developed for Microsoft Windows to run on Unix-like operating systems. Wine also provides a software library, known as Winelib, against which developers can compile Windows applications to help port them to Unix-like systems.",
                "Kodi (formerly XBMC) is a free and open-source media player software application developed by the XBMC Foundation, a non-profit technology consortium.[6] Kodi is available for multiple operating systems and hardware platforms, with a software 10-foot user interface for use with televisions and remote controls. It allows users to play and view most streaming media, such as videos, music, podcasts, and videos from the Internet, as well as all common digital media files from local and network storage media.",
                "Mozilla (stylized as moz://a) is a free software community founded in 1998 by members of Netscape. The Mozilla community uses, develops, spreads and supports Mozilla products, thereby promoting exclusively free software and open standards, with only minor exceptions.[1] The community is supported institutionally by the not-for-profit Mozilla Foundation and its tax-paying subsidiary, the Mozilla Corporation.",
                "LibreOffice is a free and open source office suite, a project of The Document Foundation. It was forked from OpenOffice.org in 2010, which was an open-sourced version of the earlier StarOffice. The LibreOffice suite comprises programs for word processing, the creation and editing of spreadsheets, slideshows, diagrams and drawings, working with databases, and composing mathematical formulae.",
                "WordPress is a free and open-source content management system (CMS) based on PHP and MySQL.[4] To function, WordPress has to be installed on a web server, which would either be part of an Internet hosting service or a network host in its own right. An example of the first scenario may be a service like WordPress.com, and the second case could be a computer running the software package WordPress.org.",
                "GNOME was originally an acronym for GNU Network Object Model Environment but the acronym was dropped because it no longer reflected the vision of the GNOME project."};

        final String[] linkFeatured = {
                "https://github.com/amahi",
                "https://github.com/videolan",
                "https://github.com/wine-mirror/wine",
                "https://github.com/xbmc",
                "https://github.com/mozilla",
                "https://github.com/LibreOffice",
                "https://github.com/wordpress",
                "https://github.com/GNOME"
        };

        final String[] drawables = {
                "https://therevise.com/wp-content/uploads/2017/11/amahi-dice-77a6485901684f2cdd23567f79257cecf3fa4399e5c4aaa16a3efa4685489416.png",
                "https://store-images.microsoft.com/image/apps.46941.9007199267003443.3d957046-8905-49de-bf30-878f1e27537c.2f6f059b-de17-469c-8fc6-8a6d879ff523?w=180&h=180&q=60",
                "https://upload.wikimedia.org/wikipedia/commons/e/ed/WINE-logo.png",
                "https://store-images.s-microsoft.com/image/apps.8345.13510798887593391.9280eebd-4ccf-40e5-93dc-49e5bce69c2b.35851374-4403-43e1-b268-f52f1f1a80a3?w=180&h=180&q=60",
                "https://fthmb.tqn.com/YPbxL6pQesGz4wCeWgShkG7Fn_I=/768x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/Firefox-logo-56a0c3773df78cafdaa4cdef.jpg",
                "https://image.freepik.com/free-icon/libreoffice-logo_318-40194.jpg",
                "https://appp-wpengine.netdna-ssl.com/wp-content/uploads/2014/01/wordpress-logo-300.jpg",
                "https://www.culturacuantica.com.ar/wp-content/uploads/gnome-logo.jpg"};

        for (int i = 0; i < titlesFeatured.length; i++) {
            FeaturedProject project = new FeaturedProject(titlesFeatured[i], descFeatured[i], linkFeatured[i], drawables[i]);
            projects.add(project);
        }

        Collections.shuffle(projects);
        return projects;
    }

    private List<Project> getProjectsExe(List<Project> projects) {
        final String[] titlesExe = {
                "NITH Chatbot",
                "Online Outpass System",
                "Arduino Based Fighting Robot",
                "Self-Playing Mario Bot",
                "Sophisticated Polling Application",
                "VR-Based Multiplayer Application",
                "Automated Invigilator Duty Scheduler"
        };

        final String[] descExe = {
                "Chat bot is meant to be used by the student and faculty for the\n" +
                        "campus needs. The first time user says a Hi, the bot will reply back by asking\n" +
                        "the user, his/her name and entry and when user wants to know details about\n" +
                        "any student the bot can reply back by telling name, entry no. and email id\n" +
                        "where the user can contact the person. The user can ask FAQs related to any\n" +
                        "institute policy and even ask for telephone numbers. The bot can be further\n" +
                        "trained for providing the information about events going in the campus. The\n" +
                        "bot will perform intelligent chat with the user and can get information\n" +
                        "regarding any topic of the campus.",
                "We are making an online outpass system. In this we will generate\n" +
                        "an online outpass form and there will be two records one for student and one\n" +
                        "for the MMCA. All the requests within a specified time limit will be sent to\n" +
                        "the warden for approval where she can approve them with a click of a button.\n" +
                        "It will use: Php, Html, Css, Mysql, Javascript, python, flask",
                "In this project, we will build a simple robot using Arduino that could move\n" +
                        "towards the fire and pump out water around it to put down the fire. The main\n" +
                        "brain of this project is the Arduino, but in-order to sense fire we use the Fire\n" +
                        "sensor module. These sensors have an IR Receiver (Photodiode) which is\n" +
                        "used to detect the fire. When fire burns it emits a small amount of Infra-red\n" +
                        "light, this light will be received by the IR receiver on the sensor module.\n" +
                        "Then we use an Op-Amp to check for change in voltage across the IR\n" +
                        "Receiver, so that if a fire is detected the output pin (DO) will give\n" +
                        "0V(LOW) and if the is no fire the output pin will be 5V(HIGH). We detect\n" +
                        "the direction of the fire and then we can use the motors to move near the fire\n" +
                        "by driving our motors through the L293D module. When near a fire we have\n" +
                        "to put it out using water.",
                "This project will utilize machine learning to train a program to\n" +
                        "learn and master playing the Mario game. It will utilize aspects of AI and\n" +
                        "Neural Networks to achieve a set target in the given level.",
                "We will show different fields of polling and in the respective fields\n" +
                        "we will add a log in for candidate which will be opened for a short period of\n" +
                        "time and candidates will register their candidature. Then on the day of polling\n" +
                        "a link of respective voting will be opened from which one can directly vote.",
                "This will be a new addition to the roster of technologies used by\n" +
                        "Team.EXE. Using cutting edge algorithms and high performance renderers\n" +
                        "we will bring the world of virtual reality right at the footsteps of the students\n" +
                        "of NITH.",
                "It is a software for automatic scheduling of invigilation duties during examination in NITH."
        };

        final String[] linkDrawable = {
                "http://exe.nith.ac.in/images/rishabh.png",
                "http://exe.nith.ac.in/images/rishabh.png",
                "http://exe.nith.ac.in/images/rishabh.png",
                "http://exe.nith.ac.in/images/rishabh.png",
                "http://exe.nith.ac.in/images/rishabh.png",
                "http://exe.nith.ac.in/images/rishabh.png",
                "http://exe.nith.ac.in/images/rishabh.png",
                "http://exe.nith.ac.in/images/rishabh.png"
        };

        for (int i = 0; i < titlesExe.length; i++) {
            Project project = new Project(titlesExe[i], descExe[i], linkDrawable[i]);
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
            return new MyFragment(events.get(position));
        }

        @Override
        public int getCount() {
            return events.size();
        }
    }
}



