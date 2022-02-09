package com.example.urdu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

//    ImageView img;
//    TextView t;

    Button animal, fruit, furniture, item;



    private FirebaseAuth mAuth;

    public void Sound(String url){
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
            mediaPlayer.prepare();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.example_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.SignOut:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),login.class));
                finish();
                break;
            default:

                return super.onOptionsItemSelected(item);

        }
        return super.onOptionsItemSelected(item);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animal = findViewById(R.id.buttonAnimals);
        fruit = findViewById(R.id.buttonFruits);
        furniture = findViewById(R.id.buttonFurniture);
        item = findViewById(R.id.buttonItems);


        animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), exercise.class);
                i.putExtra("ExerciseName", "Animals");
                startActivity(i);
                Sound("https://firebasestorage.googleapis.com/v0/b/urdulearningapp-cc61c.appspot.com/o/buttonclick.wav?alt=media&token=27edbb69-e6b0-480d-bdbc-1ce14f88b160");
            }
        });


        fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), exercise.class);
                i.putExtra("ExerciseName", "Fruits");
                startActivity(i);
                Sound("https://firebasestorage.googleapis.com/v0/b/urdulearningapp-cc61c.appspot.com/o/buttonclick.wav?alt=media&token=27edbb69-e6b0-480d-bdbc-1ce14f88b160");

            }
        });


        furniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), exercise.class);
                i.putExtra("ExerciseName", "Furniture");
                startActivity(i);
                Sound("https://firebasestorage.googleapis.com/v0/b/urdulearningapp-cc61c.appspot.com/o/buttonclick.wav?alt=media&token=27edbb69-e6b0-480d-bdbc-1ce14f88b160");

            }
        });


        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), exercise.class);
                i.putExtra("ExerciseName", "Items");
                startActivity(i);
                Sound("https://firebasestorage.googleapis.com/v0/b/urdulearningapp-cc61c.appspot.com/o/buttonclick.wav?alt=media&token=27edbb69-e6b0-480d-bdbc-1ce14f88b160");
            }
        });

    }

//        MediaPlayer mediaPlayer = new MediaPlayer();
//        try {
//            mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/projectmobdev-53231.appspot.com/o/buttonclick.wav?alt=media&token=cbaea4e0-733e-4e91-8ea7-8b4c2201472e");
//            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                    mp.start();
//                }
//            });
//            mediaPlayer.prepare();
//        }catch (IOException e)
//        {
//            e.printStackTrace();
//        }


////        Intent i = new Intent(getApplicationContext(),exerciselist.class);
////        startActivity(i);
//
//        img = findViewById(R.id.ImgMCQ1);
//
//        Intent i = new Intent(getApplicationContext(),exercise.class);
//        i.putExtra("ExerciseName", "Furniture");
//        startActivity(i);
//
//    }
//    public void logout(View view)
//    {
//        FirebaseAuth.getInstance().signOut();
//        startActivity(new Intent(getApplicationContext(),login.class));
//        finish();
//    }

}