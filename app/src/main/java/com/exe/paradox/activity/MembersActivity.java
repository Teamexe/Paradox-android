package com.exe.paradox.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.exe.paradox.R;
import com.exe.paradox.adapter.MembersAdapter;
import com.exe.paradox.model.Member;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MembersActivity extends AppCompatActivity {
    final String[] linkMentorDrawable = {"https://teamexe.in/images/rishabh.png"};
    final String[] linkFinalDrawable = {"https://teamexe.in/images/pooja.jpg", "https://teamexe.in/images/rishabh.png", "https://teamexe.in/images/gurpreet.jpg", "https://teamexe.in/images/sheweta.jpg", "https://teamexe.in/images/mahima.png", "https://teamexe.in/images/mayank.png", "https://teamexe.in/images/Jalaz.jpg", "https://teamexe.in/images/milli.jpg"};
    final String[] linkThirdDrawable = {"https://teamexe.in/images/ankit.jpg", "https://teamexe.in/images/vai.jpeg", "https://avatars2.githubusercontent.com/u/20819968?s=460&v=4", "https://teamexe.in/images/Vishal.png", "https://teamexe.in/images/sarabjit.jpg", "https://teamexe.in/images/title.png", "https://teamexe.in/images/title.png", "https://teamexe.in/images/amit.jpg", "https://teamexe.in/images/anant.jpg", "https://teamexe.in/images/divyansh.jpg", "https://teamexe.in/images/apoorva.jpg", "https://teamexe.in/images/sourav.jpg"};
    final String[] linkSecondDrawable = {"https://teamexe.in/images/akanksha.jpg", "https://teamexe.in/images/title.png", "https://teamexe.in/images/gopesh.jpg", "https://teamexe.in/images/gunjan.jpg", "https://teamexe.in/images/nidhi.jpg", "https://teamexe.in/images/title.png", "https://teamexe.in/images/title.png", "https://teamexe.in/images/simpy.jpg", "https://teamexe.in/images/udit.jpg"};
    final String[] linkFirstDrawable = {"https://teamexe.in/images/title.png", "https://teamexe.in/images/title.png", "https://teamexe.in/images/jain.jpg", "https://teamexe.in/images/title.png", "https://teamexe.in/images/title.png", "https://teamexe.in/images/lakshay.jpg", "https://teamexe.in/images/muskan.jpg", "https://teamexe.in/images/title.png", "https://teamexe.in/images/title.png", "https://teamexe.in/images/title.png", "https://teamexe.in/images/rohit.jpg", "https://teamexe.in/images/title.png", "https://teamexe.in/images/title.png", "https://teamexe.in/images/vasu.JPG", "https://teamexe.in/images/title.png", "https://teamexe.in/images/title.png"};
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

    private void startThread(final RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Glide.with(MembersActivity.this).resumeRequests();
                }
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    Glide.with(MembersActivity.this).pauseRequests();
                }
            }
        });
    }

    private void setAdapter(RecyclerView recv, final List<Member> list) {
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
        for (int i = 0; i < 3; i++) {
            Member member = new Member(name[i], desc[i], pics[i]);
            list.add(member);
        }
        List<Member> memberList = new ArrayList<>();
        for (int i = 3; i < name.length; i++) {
            memberList.add(new Member(name[i], desc[i], pics[i]));
        }
        Collections.shuffle(memberList);

        list.addAll(memberList);

        return list;
    }
}