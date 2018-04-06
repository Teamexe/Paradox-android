package com.exe.paradox.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.exe.paradox.model.Member;
import com.exe.paradox.R;
import com.exe.paradox.adapter.MembersAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MembersActivity extends AppCompatActivity {
    final String[] linkMentorDrawable = {"http://exe.nith.ac.in/images/rishabh.png"};
    final String[] linkFinalDrawable = {"http://exe.nith.ac.in/images/pooja.jpg", "http://exe.nith.ac.in/images/rishabh.png", "http://exe.nith.ac.in/images/gurpreet.jpg", "http://exe.nith.ac.in/images/sheweta.jpg", "http://exe.nith.ac.in/images/mahima.png", "http://exe.nith.ac.in/images/mayank.png", "http://exe.nith.ac.in/images/Jalaz.jpg", "http://exe.nith.ac.in/images/milli.jpg"};
    final String[] linkThirdDrawable = {"http://exe.nith.ac.in/images/ankit.jpg", "http://exe.nith.ac.in/images/vai.jpeg", "https://avatars2.githubusercontent.com/u/20819968?s=460&v=4", "http://exe.nith.ac.in/images/Vishal.png", "http://exe.nith.ac.in/images/sarabjit.jpg", "http://exe.nith.ac.in/images/title.png", "http://exe.nith.ac.in/images/title.png", "http://exe.nith.ac.in/images/amit.jpg", "http://exe.nith.ac.in/images/anant.jpg", "http://exe.nith.ac.in/images/divyansh.jpg", "http://exe.nith.ac.in/images/apoorva.jpg", "http://exe.nith.ac.in/images/sourav.jpg"};
    final String[] linkSecondDrawable = {"http://exe.nith.ac.in/images/akanksha.jpg", "http://exe.nith.ac.in/images/title.png", "http://exe.nith.ac.in/images/gopesh.jpg", "http://exe.nith.ac.in/images/gunjan.jpg", "http://exe.nith.ac.in/images/nidhi.jpg", "http://exe.nith.ac.in/images/title.png", "http://exe.nith.ac.in/images/title.png", "http://exe.nith.ac.in/images/simpy.jpg", "http://exe.nith.ac.in/images/udit.jpg"};
    final String[] linkFirstDrawable = {"http://exe.nith.ac.in/images/title.png", "http://exe.nith.ac.in/images/title.png", "http://exe.nith.ac.in/images/jain.jpg", "http://exe.nith.ac.in/images/title.png", "http://exe.nith.ac.in/images/title.png", "http://exe.nith.ac.in/images/lakshay.jpg", "http://exe.nith.ac.in/images/muskan.jpg", "http://exe.nith.ac.in/images/title.png", "http://exe.nith.ac.in/images/title.png", "http://exe.nith.ac.in/images/title.png", "http://exe.nith.ac.in/images/rohit.jpg", "http://exe.nith.ac.in/images/title.png", "http://exe.nith.ac.in/images/title.png", "http://exe.nith.ac.in/images/vasu.JPG", "http://exe.nith.ac.in/images/title.png", "http://exe.nith.ac.in/images/title.png"};
    //GOING TO BE ONLY ONE MENTOR
    private String[] mentorName = {"Rishabh Choudhary"};
    private String[] mentorAbout = {"The only mentor, campus mein chalti hai!"};

    //FINAL YEAR
    private String[] finalName = {"Pooja Saini", "Rishabh Choudhary", "Gurpreet Singh", "Sheweta Khatri", "Mahima Mahanat", "Mayank Kashyap", "Jalaz Kumar", "Milindra Pratap"};
    private String[] finalAbout = {"Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah"};

    //THIRD YEAR
    private String[] thirdName = {"Ankit Guleria", "Vaibhav Sharma", "Kumar Shashwat", "Vishal Choudhary", "Sarbjit Cheema", "Nimit Bhardwaj", "Rashi Shah", "Amit Chambial", "Anant Kabra", "Divyansh Sharma", "Apoorva Sharma", "Sourav Sahoo"};
    private String[] thirdAbout = {"Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah"};

    //SECOND YEAR
    private String[] secondName = {"Akanksha Gahalot", "Ekta Tyagi", "Gopesh Singhal", "Gunjan Agrawal", "Nidhi Singh", "Pranjal Thakur", "Ranjeet Walia", "Simpy Kumari", "Udit Gulati"};
    private String[] secondAbout = {"Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah"};

    //FIRST YEAR
    private String[] firstName = {"Aman Gupta", "Anany Sharma", "Ayush Jain", "Jatin Nagpal", "Kashish Kalsi", "Lakshay Gupta", "Muskan Mangal", "Nishant Hada", "Nitika", "Priyank Kumar", "Rohit Hill", "Shivam Sood", "Shruti Sharma", "Vasu Jain", "Vedaant Rajoo", "Vishal Parmar"};
    private String[] firstAbout = {"Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);
        RecyclerView mentor = findViewById(R.id.recv_mentor);
        RecyclerView finalYear = findViewById(R.id.recv_final);
        RecyclerView thirdYear = findViewById(R.id.recv_third);
        RecyclerView secondYear = findViewById(R.id.recv_second);
        RecyclerView firstYear = findViewById(R.id.recv_first);

        startThread(mentor);
        startThread(finalYear);
        startThread(thirdYear);
        startThread(secondYear);
        startThread(firstYear);

        setAdapter(mentor, getShuffledList(mentorName, mentorAbout, linkMentorDrawable));
        setAdapter(finalYear, getShuffledList(finalName, finalAbout, linkFinalDrawable));
        setAdapter(thirdYear, getList(thirdName, thirdAbout, linkThirdDrawable));
        setAdapter(secondYear, getShuffledList(secondName, secondAbout, linkSecondDrawable));
        setAdapter(firstYear, getShuffledList(firstName, firstAbout, linkFirstDrawable));
    }

    private void startThread(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Glide.with(MembersActivity.this).resumeRequests();
                }
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    Glide.with(MembersActivity.this).pauseRequests();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
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

    private List<Member> getShuffledList(String[] name, String[] desc, String pics[]) {
        List<Member> list = new ArrayList<>();
        for (int i = 0; i < name.length; i++) {
            Member member = new Member(name[i], desc[i], pics[i]);
            list.add(member);
        }
        Collections.shuffle(list);
        return list;
    }

    private List<Member> getList(String name[], String desc[], String pics[]) {
        List<Member> list = new ArrayList<>();
        for (int i = 0; i < name.length; i++) {
            Member member = new Member(name[i], desc[i], pics[i]);
            list.add(member);
        }
        return list;
    }
}