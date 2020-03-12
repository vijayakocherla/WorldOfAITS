package com.example.worldofaits;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {
EditText mail,key;
    private FirebaseAuth mAuth;

    TextView fac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        mail=findViewById(R.id.user_mail);
        fac=findViewById(R.id.faculty);
        key=findViewById(R.id.password);
        if(mAuth.getCurrentUser() != null){

            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
        fac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.faculty);
            }
        });
    }

    public void login(View view) {

        String email=mail.getText().toString();
        String password=key.getText().toString();
        if(TextUtils.isEmpty(email)){
            mail.setError("Email is Required.");
            return;
        }

        if(TextUtils.isEmpty(password)){
            key.setError("Password is Required.");
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));

                    } else {
                        // If sign in fails, display a message to the user.
                      //  Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(Login.this, "Authentication failed."+task.getException().getMessage(),
                                Toast.LENGTH_SHORT).show();

                    }

                    // ...
                }
            });



    }

    public void checkIn(View view) {
        startActivity(new Intent(this,MainActivity.class));

    }
    //open register class
    public void newRegister(View view) {

        startActivity(new Intent(this,Register.class));
    }
    //method for reset password
    public void forgot(View view) {
        if(TextUtils.isEmpty(mail.getText().toString())){
            mail.setError("Email is Required.");
            return;
        }
        mAuth.sendPasswordResetEmail(mail.getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Login.this, "Please check your mail", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Login.this, "User not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void faculty_forgot(View view) {
        startActivity(new Intent(this,FacultyForgotPassword.class));
    }

    public void loginFaculty(View view) {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
