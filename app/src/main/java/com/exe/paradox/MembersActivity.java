package com.exe.paradox;


import android.animation.Animator;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.exe.paradox.adapter.MembersAdapter;
import com.exe.paradox.adapter.ProjectAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MembersActivity extends AppCompatActivity {
    //GOING TO BE ONLY ONE MENTOR
    private String[] mentorName = {"Rishabh Choudhary"};
    private String[] mentorAbout = {"Blah blah blah"};
    final String[] linkMentorDrawable = {"http://exe.nith.ac.in/images/rishabh.png"};


    private int[] mentorDrawable = {R.drawable.u8};

    //FINAL YEAR
    private String[] finalName = {"Pooja Saini", "Rishabh Choudhary", "Gurpreet Singh", "Sheweta Khatri", "Mahima Mahanat", "Mayank Kashyap", "Jalaz Kumar", "Milindra Pratap"};
    private String[] finalAbout = {"Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah"};
    final String[] linkFinalDrawable = {"http://exe.nith.ac.in/images/pooja.jpg", "http://exe.nith.ac.in/images/rishabh.png", "http://exe.nith.ac.in/images/gurpreet.jpg", "http://exe.nith.ac.in/images/sheweta.jpg", "http://exe.nith.ac.in/images/mahima.png", "http://exe.nith.ac.in/images/mayank.png", "http://exe.nith.ac.in/images/Jalaz.jpg", "http://exe.nith.ac.in/images/milli.jpg"};

    //THIRD YEAR
    private String[] thirdName = {"Ankit Guleria", "Vaibhav Sharma", "Vishal Choudhary", "Kumar Shashwat", "Sarbjit Cheema", "Nimit Bhardwaj", "Rashi Shah", "Amit Chambial", "Anant Kabra", "Divyansh Sharma", "Apoorva Sharma", "Sourav Sahoo"};
    private String[] thirdAbout = {"Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah"};
    final String[] linkThirdDrawable = {"http://exe.nith.ac.in/images/ankit.jpg", "http://exe.nith.ac.in/images/vai.jpeg", "http://exe.nith.ac.in/images/Vishal.png", "http://exe.nith.ac.in/images/shashwat.jpg", "http://exe.nith.ac.in/images/sarabjit.jpg", "http://exe.nith.ac.in/images/title.png", "http://exe.nith.ac.in/images/title.png", "http://exe.nith.ac.in/images/amit.jpg", "http://exe.nith.ac.in/images/anant.jpg", "http://exe.nith.ac.in/images/divyansh.jpg", "http://exe.nith.ac.in/images/apoorva.jpg", "http://exe.nith.ac.in/images/sourav.jpg"};

    //SECOND YEAR
    private String[] secondName = {"Akanksha Gahalot", "Ekta Tyagi", "Gopesh Singhal", "Gunjan Agrawal", "Nidhi Singh", "Pranjal Thakur", "Ranjeet Walia", "Simpy Kumari", "Udit Gulati"};
    private String[] secondAbout = {"Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah"};
    final String[] linkSecondDrawable = {"http://exe.nith.ac.in/images/akanksha.jpg", "http://exe.nith.ac.in/images/title.png", "http://exe.nith.ac.in/images/gopesh.jpg", "http://exe.nith.ac.in/images/gunjan.jpg", "http://exe.nith.ac.in/images/nidhi.jpg", "http://exe.nith.ac.in/images/title.png", "http://exe.nith.ac.in/images/title.png", "http://exe.nith.ac.in/images/simpy.jpg", "http://exe.nith.ac.in/images/udit.jpg"};

    //FIRST YEAR
    private String[] firstName = {"Aman Gupta", "Anany Sharma", "Ayush Jain", "Jatin Nagpal", "Kashish Kalsi", "Lakshay Gupta", "Muskan Mangal", "Nishant Hada", "Nitika", "Priyank Kumar", "Rohit Hill", "Shivam Sood", "Shruti Sharma", "Vasu Jain", "Vedaant Rajoo", "Vishal Parmar"};
    private String[] firstAbout = {"Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah"};
    final String[] linkFirstDrawable = {"http://exe.nith.ac.in/images/title.png", "http://exe.nith.ac.in/images/title.png", "http://exe.nith.ac.in/images/jain.jpg", "http://exe.nith.ac.in/images/title.png", "http://exe.nith.ac.in/images/title.png", "http://exe.nith.ac.in/images/lakshay.jpg", "http://exe.nith.ac.in/images/muskan.jpg", "http://exe.nith.ac.in/images/title.png", "http://exe.nith.ac.in/images/title.png", "http://exe.nith.ac.in/images/title.png", "http://exe.nith.ac.in/images/rohit.jpg", "http://exe.nith.ac.in/images/title.png", "http://exe.nith.ac.in/images/title.png", "http://exe.nith.ac.in/images/vasu.JPG", "http://exe.nith.ac.in/images/title.png", "http://exe.nith.ac.in/images/title.png"};

    RelativeLayout rootLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.do_not_move, R.anim.do_not_move);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        setContentView(R.layout.activity_members);
        rootLayout = findViewById(R.id.mroot);

        if (savedInstanceState == null) {
            rootLayout.setVisibility(View.INVISIBLE);

            ViewTreeObserver viewTreeObserver = rootLayout.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        circularRevealActivity();
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            rootLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        } else {
                            rootLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                        }
                    }
                });
            }
        }


        RecyclerView mentor = findViewById(R.id.recv_mentor);
        RecyclerView finalYear = findViewById(R.id.recv_final);
        RecyclerView thirdYear = findViewById(R.id.recv_third);
        RecyclerView secondYear = findViewById(R.id.recv_second);
        RecyclerView firstYear = findViewById(R.id.recv_first);

        setAdapter(mentor, getShuffledList(mentorName, mentorAbout, linkMentorDrawable));
        setAdapter(finalYear, getShuffledList(finalName, finalAbout, linkFinalDrawable));
        setAdapter(thirdYear, getShuffledList(thirdName, thirdAbout, linkThirdDrawable));
        setAdapter(secondYear, getShuffledList(secondName, secondAbout, linkSecondDrawable));
        setAdapter(firstYear, getShuffledList(firstName, firstAbout, linkFirstDrawable));


    }

    private void setAdapter(RecyclerView recv, List<Member> list) {
        MembersAdapter membersAdapter = new MembersAdapter(this, list);
        recv.setLayoutManager(new GridLayoutManager(MembersActivity.this, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recv.setAdapter(membersAdapter);
    }

    private List<Member> getShuffledList(String[] name, String[] desc, String[] pics) {
        List<Member> list = new ArrayList<>();
        for (int i = 0; i < name.length; i++) {
            Member member = new Member(name[i], desc[i], pics[i]);
            list.add(member);
        }
        Collections.shuffle(list);
        return list;
    }

    private void circularRevealActivity() {

        int cx = rootLayout.getWidth() / 2;
        int cy = rootLayout.getHeight() / 2;

        float finalRadius = Math.max(rootLayout.getWidth(), rootLayout.getHeight());

        // create the animator for this view (the start radius is zero)
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, cx, 0, 0, finalRadius * 2);
        circularReveal.setDuration(1000);

        // make the view visible and start the animation
        rootLayout.setVisibility(View.VISIBLE);
        circularReveal.start();
    }

    private void backCircular() {

        int cx = rootLayout.getWidth() / 2;
        int cy = rootLayout.getHeight() / 2;

        float finalRadius = Math.max(rootLayout.getWidth(), rootLayout.getHeight());

        // create the animator for this view (the start radius is zero)
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, cx, 0, finalRadius * 2, 0);
        circularReveal.setDuration(1000);

        // make the view visible and start the animation
        circularReveal.start();
        circularReveal.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                rootLayout.setVisibility(View.INVISIBLE);

                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        backCircular();
    }
}
