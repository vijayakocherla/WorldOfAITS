package com.example.worldofaits;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
TextView name,cid,password,mail;
RadioGroup prghos,prgcrt;
RadioButton phos,pcrt;
DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name=findViewById(R.id.profile_name);
        cid=findViewById(R.id.profile_collegeID);
        password=findViewById(R.id.profile_password);
        mail=findViewById(R.id.profile_recovery);
        prghos=findViewById(R.id.profile_rg_hostler);
        prgcrt=findViewById(R.id.profile_rg_crt);
        int selectedIdh=prghos.getCheckedRadioButtonId();
        int selectedIdc=prgcrt.getCheckedRadioButtonId();
        phos=findViewById(R.id.profile_shostler);
        pcrt=findViewById(selectedIdc);
        phos.setChecked(true);
        phos.setClickable(false);
      //  myRef=FirebaseDatabase.getInstance().getReference();
//        myRef.child("AITS").child("Total").orderByChild("M1fU0FYhOxuqrBsdyzD").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot ds:dataSnapshot.getChildren()){
//                if(ds.child("fullName").getValue().toString().equals("vijaya Lakshmi")){
//                    String name1=ds.child("fullName").getValue().toString();
//                    name.setText(name1);
//
//                }}
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

    }

    public void editUser(View view) {


    }

    public void Done(View view) {

    }
}
