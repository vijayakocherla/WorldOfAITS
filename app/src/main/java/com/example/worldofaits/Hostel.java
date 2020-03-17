package com.example.worldofaits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import de.hdodenhof.circleimageview.CircleImageView;

public class Hostel extends AppCompatActivity {
FloatingActionButton fh;
TextView namehos,msghos,subhos;
ImageView imghos;
    DataModelImg dmi=new DataModelImg();

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel);
        fh=findViewById(R.id.uploadhere1);
        fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Hostel.this,UploadImage.class));

            }
        });
       namehos=findViewById(R.id.hos_name);
       msghos=findViewById(R.id.msg_hos);
       subhos=findViewById(R.id.sub_hos);
       imghos=findViewById(R.id.img_display);
      // imghos.setImageURI(dmi.getUri());
        namehos.setText(dmi.getFullName());
        subhos.setText(dmi.getSubject());
        msghos.setText(dmi.getMessage());

}


}