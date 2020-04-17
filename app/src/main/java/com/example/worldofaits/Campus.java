package com.example.worldofaits;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

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

public class Campus extends AppCompatActivity {
FloatingActionButton fc;
    DatabaseReference dref;
    DataModelImg dmi;
    FirebaseAuth mAuth;
StorageReference sref;
String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus);

        fc=findViewById(R.id.uploadhere2);
        final RecyclerView rv =findViewById(R.id.campus_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        rv.setLayoutManager(linearLayoutManager);
       //  rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        dref= FirebaseDatabase.getInstance().getReference();
        sref= FirebaseStorage.getInstance().getReference();
        dmi=new DataModelImg();
        mAuth= FirebaseAuth.getInstance();
        userid=mAuth.getUid();
        if(userid==null){
            fc.setVisibility(View.GONE);
        }
        else{
        dref.child("AITS").child("CRT").child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    // fmc2.setVisibility(View.GONE);
                }
                else {
                    fc.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //  Toast.makeText(Circulars.this, ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });}
        fc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Campus.this, ImageCampus.class));

            }});
        dref.child("urlscampus").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<DataModelImg> dataModelImgList=new ArrayList<>();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    dmi=  ds.getValue(DataModelImg.class);
                    dataModelImgList.add(dmi);

                }
                HosAdapter camfeed=new HosAdapter(getApplication(),dataModelImgList);
                rv.setAdapter(camfeed);

                // Toast.makeText(Hostel.this, ""+dataModelImgList.get(0).getUri(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

}