package com.exe.paradox;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.squareup.picasso.Picasso;

public class members extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);


        ImageView image1 = (ImageView) findViewById(R.id.image1);
        Picasso.with(this)
                .load("http://exe.nith.ac.in/images/pooja.jpg")
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image1);

        ImageView image2 = (ImageView) findViewById(R.id.image2);
        Picasso.with(this)
                .load("http://exe.nith.ac.in/images/rishabh.png")
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image2);

        ImageView image3 = (ImageView) findViewById(R.id.image3);
        Picasso.with(this)
                .load("http://exe.nith.ac.in/images/gurpreet.jpg")
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image3);


        ImageView image4 = (ImageView) findViewById(R.id.image4);
        Picasso.with(this)
                .load("http://exe.nith.ac.in/images/sheweta.jpg")
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image4);
        ImageView image5 = (ImageView) findViewById(R.id.image5);
        Picasso.with(this)
                .load("http://exe.nith.ac.in/images/mahima.png")
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image5);
        ImageView image6 = (ImageView) findViewById(R.id.image6);
        Picasso.with(this)
                .load("http://exe.nith.ac.in/images/mayank.png")
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image6);
        ImageView image7 = (ImageView) findViewById(R.id.image7);
        Picasso.with(this)
                .load("http://exe.nith.ac.in/images/Jalaz.jpg")
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image7);
        ImageView image8 = (ImageView) findViewById(R.id.image8);
        Picasso.with(this)
                .load("http://exe.nith.ac.in/images/milli.jpg")
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image8);



        ImageView image9 = (ImageView) findViewById(R.id.image9);
        Picasso.with(this)
                .load("http://exe.nith.ac.in/images/ankit.jpg")
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image9);


        ImageView image10 = (ImageView) findViewById(R.id.image10);
        Picasso.with(this)
                .load("http://exe.nith.ac.in/images/vai.jpeg")
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image10);
        ImageView image11 = (ImageView) findViewById(R.id.image11);
        Picasso.with(this)
                .load("http://exe.nith.ac.in/images/Vishal.png")
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image11);
        ImageView image12 = (ImageView) findViewById(R.id.image12);
        Picasso.with(this)
                .load("http://exe.nith.ac.in/images/shashwat.jpg")
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image12);
        ImageView image13 = (ImageView) findViewById(R.id.image13);
        Picasso.with(this)
                .load("http://exe.nith.ac.in/images/sarabjit.jpg")
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image13);
        ImageView image14 = (ImageView) findViewById(R.id.image14);
        Picasso.with(this)
                .load(R.drawable.placeholder)
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image14);

        ImageView image15 = (ImageView) findViewById(R.id.image15);
        Picasso.with(this)
                .load(R.drawable.placeholder)
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image15);

        ImageView image16 = (ImageView) findViewById(R.id.image16);
        Picasso.with(this)
                .load("http://exe.nith.ac.in/images/amit.jpg")
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image16);

        ImageView image17 = (ImageView) findViewById(R.id.image17);
        Picasso.with(this)
                .load("http://exe.nith.ac.in/images/anant.jpg")
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image17);
        ImageView image18 = (ImageView) findViewById(R.id.image18);
        Picasso.with(this)
                .load("http://exe.nith.ac.in/images/divyansh.jpg")
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image18);

        ImageView image19 = (ImageView) findViewById(R.id.image19);
        Picasso.with(this)
                .load("http://exe.nith.ac.in/images/apoorva.jpg")
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image19);


        ImageView image20 = (ImageView) findViewById(R.id.image20);
        Picasso.with(this)
                .load("http://exe.nith.ac.in/images/sourav.jpg")
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image20);






        ImageView image21 = (ImageView) findViewById(R.id.image21);
        Picasso.with(this)
                .load("http://exe.nith.ac.in/images/akanksha.jpg")
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image21);
        ImageView image22 = (ImageView) findViewById(R.id.image22);
        Picasso.with(this)
                .load(R.drawable.placeholder)
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image22);
        ImageView image23 = (ImageView) findViewById(R.id.image23);
        Picasso.with(this)
                .load("http://exe.nith.ac.in/images/gopesh.jpg")
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image23);
        ImageView image24 = (ImageView) findViewById(R.id.image24);
        Picasso.with(this)
                .load("http://exe.nith.ac.in/images/gunjan.jpg")
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image24);
        ImageView image25 = (ImageView) findViewById(R.id.image25);
        Picasso.with(this)
                .load("http://exe.nith.ac.in/images/nidhi.jpg")
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image25);
        ImageView image26 = (ImageView) findViewById(R.id.image26);
        Picasso.with(this)
                .load(R.drawable.placeholder)
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image26);
        ImageView image27 = (ImageView) findViewById(R.id.image27);
        Picasso.with(this)
                .load(R.drawable.placeholder)
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image27);
        ImageView image28 = (ImageView) findViewById(R.id.image28);
        Picasso.with(this)
                .load("http://exe.nith.ac.in/images/simpy.jpg")
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image28);
        ImageView image29 = (ImageView) findViewById(R.id.image29);
        Picasso.with(this)
                .load("http://exe.nith.ac.in/images/udit.jpg")
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image29);




        ImageView image30 = (ImageView) findViewById(R.id.image30);
        Picasso.with(this)
                .load(R.drawable.placeholder)
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image30);
        ImageView image31 = (ImageView) findViewById(R.id.image31);
        Picasso.with(this)
                .load(R.drawable.placeholder)
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image31);
        ImageView image32 = (ImageView) findViewById(R.id.image32);
        Picasso.with(this)
                .load("http://exe.nith.ac.in/images/jain.jpg")
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image32);
        ImageView image33 = (ImageView) findViewById(R.id.image33);
        Picasso.with(this)
                .load(R.drawable.placeholder)
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image33);
        ImageView image34 = (ImageView) findViewById(R.id.image34);
        Picasso.with(this)
                .load(R.drawable.placeholder)
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image34);
        ImageView image35 = (ImageView) findViewById(R.id.image35);
        Picasso.with(this)
                .load("http://exe.nith.ac.in/images/lakshay.jpg")
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image35);
        ImageView image36 = (ImageView) findViewById(R.id.image36);
        Picasso.with(this)
                .load("http://exe.nith.ac.in/images/muskan.jpg")
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image36);
        ImageView image37 = (ImageView) findViewById(R.id.image37);
        Picasso.with(this)
                .load(R.drawable.placeholder)
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image37);
        ImageView image38 = (ImageView) findViewById(R.id.image38);
        Picasso.with(this)
                .load(R.drawable.placeholder)
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image38);

        ImageView image39 = (ImageView) findViewById(R.id.image39);
        Picasso.with(this)
                .load(R.drawable.placeholder)
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image39);

        ImageView image40 = (ImageView) findViewById(R.id.image40);
        Picasso.with(this)
                .load("http://exe.nith.ac.in/images/rohit.jpg")
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image40);
        ImageView image41 = (ImageView) findViewById(R.id.image41);
        Picasso.with(this)
                .load(R.drawable.placeholder)
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image41);
        ImageView image42 = (ImageView) findViewById(R.id.image42);
        Picasso.with(this)
                .load(R.drawable.placeholder)
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image42);

        ImageView image43 = (ImageView) findViewById(R.id.image43);
        Picasso.with(this)
                .load("http://exe.nith.ac.in/images/vasu.JPG")
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image43);
        ImageView image44 = (ImageView) findViewById(R.id.image44);
        Picasso.with(this)
                .load(R.drawable.placeholder)
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image44);
        ImageView image45 = (ImageView) findViewById(R.id.image45);
        Picasso.with(this)
                .load(R.drawable.placeholder)
                .resize(150,151)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image45);
    }

}
