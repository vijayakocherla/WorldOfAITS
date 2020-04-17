package com.example.worldofaits;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class Eee1 extends AppCompatActivity {
    FloatingActionButton fmee1;

    StorageReference sref;
    DatabaseReference dref;
    DataModelFile dmf;
    FirebaseAuth mAuth;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eee1);
        fmee1=findViewById(R.id.uploadmatee1);
        final RecyclerView ree1 =findViewById(R.id.eee1_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        ree1.setLayoutManager(linearLayoutManager);
       // ree1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
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
                    fmee1.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Eee1.this, ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        fmee1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Eee1.this, MaterialFilesUpload.class);
                intent.putExtra("branch","eee1");
                startActivity(intent);

                // startActivity(new Intent(Cse1.this, CseFileUploads.class));

            }});
        dref.child("EEE").child("EEE1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<DataModelFile> dataModelFiles=new ArrayList<>();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    dmf=  ds.getValue(DataModelFile.class);
                    dataModelFiles.add(dmf);

                }
                FileAdapter matee1=new FileAdapter(getApplication(),dataModelFiles);
                ree1.setAdapter(matee1);

                // Toast.makeText(Hostel.this, ""+dataModelImgList.get(0).getUri(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
