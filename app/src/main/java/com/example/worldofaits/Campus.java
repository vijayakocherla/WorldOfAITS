package com.example.worldofaits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Campus extends AppCompatActivity {
FloatingActionButton fc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus);
uploadinCampus();

}
public void uploadinCampus(){
    fc=findViewById(R.id.uploadhere2);
    fc.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(Campus.this,UploadImage.class));
        }
    });

}
}