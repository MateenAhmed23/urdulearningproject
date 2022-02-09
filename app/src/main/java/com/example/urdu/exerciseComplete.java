package com.example.urdu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class exerciseComplete extends AppCompatActivity {

    TextView main, score;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_complete);

        main = findViewById(R.id.main);
        score = findViewById(R.id.score);


        Bundle bundle = getIntent().getExtras();

        String correct = bundle.getString("correct");
        String total = bundle.getString("total");
        String name = bundle.getString("name");

        try {
            main.setText("You have completed " + name + " Exercise");

            score.setText(correct + "/" + total);

        } catch (Exception e) {
            e.printStackTrace();
        }


//        Storing User Information

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
//        mAuth.getCurrentUser();

        if (currentUser != null) {
            String uid = currentUser.getUid();


            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("users").child(uid).child("exercises").child(name);
            myRef.setValue(""+correct+"/"+total);



            // User is signed in
        } else {
            Intent i = new Intent(getApplicationContext(), login.class );
            startActivity(i);
            // No user is signed in
        }


        Button retry = findViewById(R.id.retry);

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), exercise.class );
                i.putExtra("ExerciseName", name);
                startActivity(i);
            }
        });


        Button main = findViewById(R.id.mainbtn);

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class );
                startActivity(i);
            }
        });
    }
}