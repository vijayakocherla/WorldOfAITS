package com.example.worldofaits;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class FacultyForgotPassword extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText res, facid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_forgot_password);
        mAuth = FirebaseAuth.getInstance();
        res = findViewById(R.id.link);
        facid = findViewById(R.id.fid);

    }

    public void reset(View view) {
        mAuth.sendPasswordResetEmail(res.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(FacultyForgotPassword.this, "Please check your mail", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(FacultyForgotPassword.this, "User not found", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
