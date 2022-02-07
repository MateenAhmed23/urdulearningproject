package com.example.urdu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class exerciseComplete extends AppCompatActivity {

    TextView main, score;

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


        Button retry = findViewById(R.id.retry);

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class );
                startActivity(i);
            }
        });
    }
}