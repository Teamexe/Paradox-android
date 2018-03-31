package com.exe.paradox;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import com.example.GridViewScrollable;



public class MembersActivity extends AppCompatActivity {
    String[] web = {
            "1", "2", "3", "4", "5", "6", "7", "8", "9"

    };
    int[] imageId = {
            R.drawable.u1,
            R.drawable.u2,
            R.drawable.u3,
            R.drawable.u4,
            R.drawable.u5,
            R.drawable.u6,
            R.drawable.u7,
            R.drawable.u8,
            R.drawable.bg_pager_indicator,

    };
    String[] web2 = {
            "1", "2", "3", "4", "5", "6", "7", "8", "9"

    };
    int[] imageId2 = {
            R.drawable.u1,
            R.drawable.u2,
            R.drawable.u3,
            R.drawable.u4,
            R.drawable.u5,
            R.drawable.u6,
            R.drawable.u7,
            R.drawable.u8,
            R.drawable.bg_pager_indicator,

    };
    String[] web3 = {
            "1", "2", "3", "4", "5", "6", "7", "8", "9"

    };
    int[] imageId3 = {
            R.drawable.u1,
            R.drawable.u2,
            R.drawable.u3,
            R.drawable.u4,
            R.drawable.u5,
            R.drawable.u6,
            R.drawable.u7,
            R.drawable.u8,
            R.drawable.bg_pager_indicator,

    };
    String[] web4 = {
            "1", "2", "3", "4", "5", "6", "7", "8", "9"

    };
    int[] imageId4 = {
            R.drawable.u1,
            R.drawable.u2,
            R.drawable.u3,
            R.drawable.u4,
            R.drawable.u5,
            R.drawable.u6,
            R.drawable.u7,
            R.drawable.u8,
            R.drawable.bg_pager_indicator,

    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        GridViewScrollable grid;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);

        MembersGridFill adapter = new MembersGridFill(MembersActivity.this, web, imageId);

        grid = (GridViewScrollable) findViewById(R.id.grid1);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(MembersActivity.this, "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();

            }
        });
        MembersGridFill adapter2 = new MembersGridFill(MembersActivity.this, web, imageId2);
        grid = (GridViewScrollable) findViewById(R.id.grid2);
        grid.setAdapter(adapter2);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(MembersActivity.this, "You Clicked at " + web2[+position], Toast.LENGTH_SHORT).show();

            }
        });
        MembersGridFill adapter3 = new MembersGridFill(MembersActivity.this, web3, imageId3);
        grid = (GridViewScrollable) findViewById(R.id.grid3);
        grid.setAdapter(adapter2);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(MembersActivity.this, "You Clicked at " + web3[+position], Toast.LENGTH_SHORT).show();

            }
        });
        MembersGridFill adapter4 = new MembersGridFill(MembersActivity.this, web4, imageId4);
        grid = (GridViewScrollable) findViewById(R.id.grid4);
        grid.setAdapter(adapter4);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(MembersActivity.this, "You Clicked at " + web4[+position], Toast.LENGTH_SHORT).show();

            }
        });
    }

}
