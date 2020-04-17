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

public class Mech1 extends AppCompatActivity {
    FloatingActionButton fmm1;

    StorageReference sref;
    DatabaseReference dref;
    DataModelFile dmf;
    FirebaseAuth mAuth;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mech1);
        fmm1=findViewById(R.id.uploadmatm1);
        final RecyclerView rm1 =findViewById(R.id.mech1_recycler);
       // rm1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        rm1.setLayoutManager(linearLayoutManager);
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
                    fmm1.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Mech1.this, ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        fmm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Mech1.this, MaterialFilesUpload.class);
                intent.putExtra("branch","mech1");
                startActivity(intent);

                // startActivity(new Intent(Cse1.this, CseFileUploads.class));

            }});
        dref.child("MECH").child("MECH1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<DataModelFile> dataModelFiles=new ArrayList<>();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    dmf=  ds.getValue(DataModelFile.class);
                    dataModelFiles.add(dmf);

                }
                FileAdapter matm1=new FileAdapter(getApplication(),dataModelFiles);
                rm1.setAdapter(matm1);

                // Toast.makeText(Hostel.this, ""+dataModelImgList.get(0).getUri(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
