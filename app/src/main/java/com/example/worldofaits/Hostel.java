package com.example.worldofaits;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Hostel extends AppCompatActivity {
FloatingActionButton fh;
FirebaseAuth mAuth;
String userid;
StorageReference sref;
DatabaseReference dref;
    DataModelImg dmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel);
        fh=findViewById(R.id.uploadhere1);
        final RecyclerView rv =findViewById(R.id.hos_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        rv.setLayoutManager(linearLayoutManager);
       // rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,true));
        dref= FirebaseDatabase.getInstance().getReference();
        sref= FirebaseStorage.getInstance().getReference();
        mAuth= FirebaseAuth.getInstance();
        userid=mAuth.getUid();
        if(userid==null){
            fh.setVisibility(View.GONE);
        }
        else{
        dref.child("AITS").child("Hostler").child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    // fmc2.setVisibility(View.GONE);
                }
                else {
                    fh.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //  Toast.makeText(Circulars.this, ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });}
         dmi=new DataModelImg();
        fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Hostel.this, UploadImage.class));

            }});
        dref.child("urls").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<DataModelImg> dataModelImgList=new ArrayList<>();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                 dmi=  ds.getValue(DataModelImg.class);
                 dataModelImgList.add(dmi);

                }
                HosAdapter hosfeed=new HosAdapter(getApplication(),dataModelImgList);
                rv.setAdapter(hosfeed);

               // Toast.makeText(Hostel.this, ""+dataModelImgList.get(0).getUri(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



}


}