package com.example.worldofaits;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class Ece4 extends AppCompatActivity {
        FloatingActionButton fme4;
        FirebaseAuth mAuth;
        String userid;
        StorageReference sref;
        DatabaseReference dref;
        DataModelFile dmf;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ece4);
        fme4=findViewById(R.id.uploadmate4);
        final RecyclerView re4 =findViewById(R.id.ece4_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        re4.setLayoutManager(linearLayoutManager);
       // re4.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        dref= FirebaseDatabase.getInstance().getReference();
        sref= FirebaseStorage.getInstance().getReference();
        dmf=new DataModelFile();
        mAuth= FirebaseAuth.getInstance();
        userid=mAuth.getUid();
        dref.child("AITS").child("Faculty").child(userid).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                                // fmc2.setVisibility(View.GONE);
                        }
                        else {
                                fme4.setVisibility(View.GONE);
                        }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(Ece4.this, ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
        });
        fme4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent intent=new Intent(Ece4.this, MaterialFilesUpload.class);
                        intent.putExtra("branch","ece4");
                        startActivity(intent);
                      //  startActivity(new Intent(Ece4.this, Uploadfile.class));

                }});
        dref.child("ECE").child("ECE4").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        List<DataModelFile> dataModelFiles=new ArrayList<>();
                        for(DataSnapshot ds:dataSnapshot.getChildren()){
                                dmf=  ds.getValue(DataModelFile.class);
                                dataModelFiles.add(dmf);

                        }
                        FileAdapter mate4=new FileAdapter(getApplication(),dataModelFiles);
                        re4.setAdapter(mate4);

                        // Toast.makeText(Hostel.this, ""+dataModelImgList.get(0).getUri(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
        });







        }
        }