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

public class Mech4 extends AppCompatActivity {
    FloatingActionButton fmm4;

    StorageReference sref;
    DatabaseReference dref;
    DataModelFile dmf;
    FirebaseAuth mAuth;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mech4);
        fmm4=findViewById(R.id.uploadmatm4);
        final RecyclerView rm4 =findViewById(R.id.mech4_recycler);
        rm4.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
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
                    fmm4.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Mech4.this, ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        fmm4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Mech4.this, MaterialFilesUpload.class);
                intent.putExtra("branch","mech4");
                startActivity(intent);

                // startActivity(new Intent(Cse1.this, CseFileUploads.class));

            }});
        dref.child("MECH").child("MECH4").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<DataModelFile> dataModelFiles=new ArrayList<>();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    dmf=  ds.getValue(DataModelFile.class);
                    dataModelFiles.add(dmf);

                }
                FileAdapter matm4 =new FileAdapter(getApplication(),dataModelFiles);
                rm4.setAdapter(matm4);

                // Toast.makeText(Hostel.this, ""+dataModelImgList.get(0).getUri(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
