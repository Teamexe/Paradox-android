package com.exe.paradox;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.exe.paradox.adapter.MembersAdapter;
import com.exe.paradox.adapter.ProjectAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MembersActivity extends AppCompatActivity {
    //GOING TO BE ONLY ONE MENTOR
    private String[] mentorName = {"Rishabh Choudhary"};
    private String[] mentorAbout = {"Blah blah blah"};
    private int[] mentorDrawable = {R.drawable.u8};

    //FINAL YEAR
    private String[] finalName = {"Rishabh Choudhary", "Rishabh Choudhary", "Rishabh Choudhary", "Rishabh Choudhary", "Rishabh Choudhary", "Rishabh Choudhary", "Rishabh Choudhary", "Rishabh Choudhary"};
    private String[] finalAbout = {"Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah"};
    private int[] finalDrawable = {R.drawable.u1, R.drawable.u2, R.drawable.u3, R.drawable.u4, R.drawable.u5, R.drawable.u6, R.drawable.u7, R.drawable.u8};

    //THIRD YEAR
    private String[] thirdName = {"Rishabh Choudhary", "Rishabh Choudhary", "Rishabh Choudhary", "Rishabh Choudhary", "Rishabh Choudhary", "Rishabh Choudhary", "Rishabh Choudhary", "Rishabh Choudhary"};
    private String[] thirdAbout = {"Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah"};
    private int[] thirdDrawable = {R.drawable.u1, R.drawable.u2, R.drawable.u3, R.drawable.u4, R.drawable.u5, R.drawable.u6, R.drawable.u7, R.drawable.u8};

    //SECOND YEAR
    private String[] secondName = {"Rishabh Choudhary", "Rishabh Choudhary", "Rishabh Choudhary", "Rishabh Choudhary", "Rishabh Choudhary", "Rishabh Choudhary", "Rishabh Choudhary", "Rishabh Choudhary"};
    private String[] secondAbout = {"Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah"};
    private int[] secondDrawable = {R.drawable.u1, R.drawable.u2, R.drawable.u3, R.drawable.u4, R.drawable.u5, R.drawable.u6, R.drawable.u7, R.drawable.u8};

    //FIRST YEAR
    private String[] firstName = {"Rishabh Choudhary", "Rishabh Choudhary", "Rishabh Choudhary", "Rishabh Choudhary", "Rishabh Choudhary", "Rishabh Choudhary", "Rishabh Choudhary", "Rishabh Choudhary"};
    private String[] firstAbout = {"Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah", "Blah blah blah"};
    private int[] firstDrawable = {R.drawable.u1, R.drawable.u2, R.drawable.u3, R.drawable.u4, R.drawable.u5, R.drawable.u6, R.drawable.u7, R.drawable.u8};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);
        RecyclerView mentor = findViewById(R.id.recv_mentor);
        RecyclerView finalYear = findViewById(R.id.recv_final);
        RecyclerView thirdYear = findViewById(R.id.recv_third);
        RecyclerView secondYear = findViewById(R.id.recv_second);
        RecyclerView firstYear = findViewById(R.id.recv_first);

        setAdapter(mentor, getShuffledList(mentorName, mentorAbout, mentorDrawable));
        setAdapter(finalYear, getShuffledList(finalName, finalAbout, finalDrawable));
        setAdapter(thirdYear, getShuffledList(thirdName, thirdAbout, thirdDrawable));
        setAdapter(secondYear, getShuffledList(secondName, secondAbout, secondDrawable));
        setAdapter(firstYear, getShuffledList(firstName, firstAbout, firstDrawable));
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

    private List<Member> getShuffledList(String[] name, String[] desc, int pics[]) {
        List<Member> list = new ArrayList<>();
        for(int i=0; i<name.length; i++) {
            Member member = new Member(name[i], desc[i], pics[i]);
            list.add(member);
        }
        Collections.shuffle(list);
        return list;
    }
}
