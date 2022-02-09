package com.example.urdu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class adminview extends AppCompatActivity {

    LinearLayout li;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminview);

        li = findViewById(R.id.linear);

//        Fetching from database


        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference("users");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for (DataSnapshot user: dataSnapshot.getChildren())
                {
//                    Loop of users

                    for(DataSnapshot exercise: user.getChildren())
                    {
                        TextView tv = new TextView(getApplicationContext());
                        tv.setText(exercise.getValue().toString());
                        li.addView(tv);
                    }

                }

//                skip.setText(Questions.get(0).getUrdu());




//                String value = dataSnapshot.getValue(String.class);
//                Log.d("****Read", "Value is: " + value);
//                Toast.makeText(getApplicationContext(), value, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("****Error", "Failed to read value.", error.toException());
            }
        });

    }
}