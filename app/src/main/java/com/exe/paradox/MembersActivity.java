package com.exe.paradox;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;




public class MembersActivity extends AppCompatActivity {
    String[] web = {
            "1","2","3","4","5","6","7","8","9"

    } ;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        GridView grid;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);

        MembersGridFill adapter = new MembersGridFill(MembersActivity.this, web, imageId);

        grid =(GridView)findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(MembersActivity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

            }
        });
}

}
