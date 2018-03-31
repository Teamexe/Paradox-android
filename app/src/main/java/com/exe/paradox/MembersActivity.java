package com.exe.paradox;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import com.example.GridViewScrollable;
import com.exe.paradox.adapter.ProjectAdapter;


public class MembersActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);
        RecyclerView mentor = findViewById(R.id.recv_mentor);
        RecyclerView finalYear = findViewById(R.id.recv_final);
        RecyclerView thirdYear = findViewById(R.id.recv_third);
        RecyclerView secondYear = findViewById(R.id.recv_second);
        RecyclerView firstYear = findViewById(R.id.recv_first);

        setAdapter(mentor);
        setAdapter(finalYear);
        setAdapter(thirdYear);
        setAdapter(secondYear);
        setAdapter(firstYear);
    }

    private void setAdapter(RecyclerView recv) {
        ProjectAdapter projectAdapter = new ProjectAdapter(this);
        recv.setLayoutManager(new GridLayoutManager(MembersActivity.this, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recv.setAdapter(projectAdapter);
    }
}
