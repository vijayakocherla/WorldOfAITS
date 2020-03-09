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
RadioButton pshos,pscrt,pnohos,pnocrt;
DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name=findViewById(R.id.profile_name);
        cid=findViewById(R.id.profile_collegeID);
        password=findViewById(R.id.profile_password);
        mail=findViewById(R.id.profile_recovery);
        pshos=findViewById(R.id.profile_shostler);
        pscrt=findViewById(R.id.profile_sCRT);
        pnohos=findViewById(R.id.profile_nohostler);
        pnocrt=findViewById(R.id.profile_noCRT);

        myRef=FirebaseDatabase.getInstance().getReference();
        myRef.child("AITS").child("Total").orderByChild("-M1uAVaJ_kApzkyVi8Of").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                if(ds.child("email").getValue().toString().equals("vijayakocherla@gmail.com")){
                    String name1=ds.child("fullName").getValue().toString();
                    name.setText(name1);
                    String cid1=ds.child("collegeID").getValue().toString();
                    cid.setText(cid1);
                    String mail1=ds.child("email").getValue().toString();
                    mail.setText(mail1);
                    String crt =ds.child("crtCheck").getValue().toString();
                     if(crt.equals("yes")){
                         pscrt.setChecked(true);
                         pscrt.setClickable(false);
                     }
                     else {
                         pnocrt.setChecked(true);
                         pnocrt.setClickable(false);
                     }
                    String hos =ds.child("hosCheck").getValue().toString();
                    if(hos.equals("yes")){
                        pshos.setChecked(true);
                        pshos.setClickable(false);
                    }
                    else {
                        pnohos.setChecked(true);
                        pnohos.setClickable(false);
                    }


                }}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void editUser(View view) {


    }

    public void Done(View view) {

    }
}
