package com.example.worldofaits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import de.hdodenhof.circleimageview.CircleImageView;

public class Hostel extends AppCompatActivity {
FloatingActionButton fh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel);
       uploadinHostel();
}
public void uploadinHostel(){
    fh=findViewById(R.id.uploadhere1);
    fh.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
             startActivity(new Intent(Hostel.this,UploadImage.class));
        }
    });
}

}