package com.example.urdu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class admin extends AppCompatActivity {


    EditText mpassword,memail;
    Button madminlogin_btn;
    TextView mcreatebtn;
    TextView madminbtn;
    boolean valid = true;

    FirebaseAuth fauth;
    FirebaseFirestore fstore;

    private void checkuseraccesslevel(String uid)
    {
        DocumentReference df = fstore.collection("Users").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG","onSucess:"+documentSnapshot.getData());

                if(documentSnapshot.getString("isAdmin")!=null)
                {
                    Toast.makeText(admin.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),adminactivity.class));
                    finish();
                }
                else
                {
                    Toast.makeText(admin.this, "Error Not Admin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        memail = findViewById(R.id.email);
        mpassword = findViewById(R.id.password);
        madminlogin_btn = findViewById(R.id.adminlogin_btn);
        mcreatebtn = findViewById(R.id.createbtn);
        madminbtn = findViewById(R.id.adminbtn);


        fauth = FirebaseAuth.getInstance();
        fstore= FirebaseFirestore.getInstance();

        madminlogin_btn.setOnClickListener(new View.OnClickListener() {
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

                if(valid)
                {
                    fauth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            /*Toast.makeText(admin.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();*/
                            checkuseraccesslevel(authResult.getUser().getUid());

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(admin.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });

        madminbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),login.class));
            }
        });
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_admin);
//    }
}