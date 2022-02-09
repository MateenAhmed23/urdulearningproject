package com.example.urdu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {

    EditText mfullname,memail,mpassword,mphone_no;
    Button mreg_btn;
    TextView mloginbtn;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    ProgressBar mprogressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mfullname = findViewById(R.id.fullname);
        memail = findViewById(R.id.email);
        mpassword = findViewById(R.id.password);
        mphone_no = findViewById(R.id.phone_no);
        mreg_btn = findViewById(R.id.reg_btn);
        mloginbtn = findViewById(R.id.createbtn);
        /*mprogressBar = findViewById(R.id.progressBar);*/

        fauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        if(fauth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        mreg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = memail.getText().toString().trim();
                String password = mpassword.getText().toString().trim();
                if(TextUtils.isEmpty(email))
                {
                    memail.setError("Email is required field");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    mpassword.setError("Password is required field");
                    return;
                }
                if(password.length()<6)
                {
                    mpassword.setError("Password must be greater than 6 characters");
                    return;
                }
                /*mprogressBar.setVisibility(View.VISIBLE);*/

                fauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {

                            MediaPlayer mediaPlayer = new MediaPlayer();
                            try {
                                mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/projectmobdev-53231.appspot.com/o/buttonclick.wav?alt=media&token=cbaea4e0-733e-4e91-8ea7-8b4c2201472e");
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


                            FirebaseUser user= fauth.getCurrentUser();
                            Toast.makeText(register.this, "User Created", Toast.LENGTH_SHORT).show();
                            DocumentReference df = fstore.collection("Users").document(user.getUid());
                            Map<String,Object> userinfo = new HashMap<>();
                            userinfo.put("mfullname",mfullname.getText().toString());
                            userinfo.put("memail",memail.getText().toString());
                            userinfo.put("mphone_no",mphone_no.getText().toString());

                            userinfo.put("isUser","1");
                            df.set(userinfo);

                            String uid = user.getUid();

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("users").child(uid);

                            DatabaseReference name = myRef.child("name");
                            name.setValue(mfullname.getText().toString());

                            DatabaseReference email = myRef.child("email");
                            email.setValue(memail.getText().toString());
//

                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();
                        }
                        else
                        {
                            Toast.makeText(register.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),login.class));
            }
        });

    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//    }
}