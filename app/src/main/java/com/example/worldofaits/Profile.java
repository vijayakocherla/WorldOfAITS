package com.example.worldofaits;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.text.PrecomputedText;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {
EditText name,cid,password,mail;
RadioButton pshos,pscrt,pnohos,pnocrt;
DatabaseReference myRef,uRef;
FirebaseAuth mAuth;
String scrtCheck="",shosCheck="";
String userid;
Uri imageUri;
StorageReference mStorageRef;
CircleImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.profile_name);
        cid = findViewById(R.id.profile_collegeID);
        password = findViewById(R.id.profile_password);
        mail = findViewById(R.id.profile_recovery);
        pshos = findViewById(R.id.profile_shostler);
        pscrt = findViewById(R.id.profile_sCRT);
        pnohos = findViewById(R.id.profile_nohostler);
        pnocrt = findViewById(R.id.profile_noCRT);
        img = findViewById(R.id.circularimg);
        mAuth = FirebaseAuth.getInstance();
        userid = mAuth.getUid();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        myRef = FirebaseDatabase.getInstance().getReference();
        //Toast.makeText(this, ""+mAuth.getCurrentUser().toString(), Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "user id :" + userid, Toast.LENGTH_SHORT).show();

        uRef=myRef.child("AITS").child("Total").child(userid);
       ValueEventListener eventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.child("profilepic").exists()) {
                        String url = ds.child("profilepic").getValue().toString();
                        Picasso.get().load(url).into(img);
                    } else {
                        Picasso.get().load(R.drawable.aits_logo).into(img);
                    }
                    String name1 = dataSnapshot.child("fullName").getValue().toString();
                    name.setText(name1);
                    String psd1 = dataSnapshot.child("password").getValue().toString();
                    password.setText(psd1);
                    String cid1 = dataSnapshot.child("collegeID").getValue().toString();
                    cid.setText(cid1);
                    String mail1 = dataSnapshot.child("email").getValue().toString();
                    mail.setText(mail1);
                    String crt = dataSnapshot.child("crtCheck").getValue().toString();
                    if (crt.equals("yes")) {
                        pscrt.setChecked(true);
                        //  pscrt.setClickable(false);
                    } else {
                        pnocrt.setChecked(true);
                        //  pnocrt.setClickable(false);
                    }
                    String hos = dataSnapshot.child("hosCheck").getValue().toString();
                    if (hos.equals("yes")) {
                        pshos.setChecked(true);
                        // pshos.setClickable(false);
                    } else {
                        pnohos.setChecked(true);
                        // pnohos.setClickable(false);
                    }


                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Profile.this, ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
            uRef.addListenerForSingleValueEvent(eventListener);
}


    public void save(View view) {
        DataModel dm = new DataModel();
        DataModel dmhc=new DataModel();

        final String sname=name.getText().toString();
        final String scid=cid.getText().toString();
        final String spassword=password.getText().toString();
        final String smail=mail.getText().toString();
        dm =new DataModel(sname,scid,smail,spassword,scrtCheck,shosCheck);
        dmhc=new DataModel(sname,scid,smail,spassword);
       if(pnocrt.isChecked()){
           myRef.child("AITS").child("CRT").orderByChild(userid)
                   .addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           if(dataSnapshot.exists()){
                            dataSnapshot.getRef().removeValue();
                               }
                           scrtCheck="no";
                           myRef.child("AITS").child("Total").child(userid).child("crtCheck").setValue(scrtCheck);
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                   });
       }
       if(pscrt.isChecked()){
           final DataModel dm2 =new DataModel(sname,scid,smail,spassword,scrtCheck,shosCheck);
           final DataModel finalDmhc = dmhc;
           myRef.child("AITS").child("CRT").orderByChild(userid)
                   .addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           if(dataSnapshot.exists()){
                               Toast.makeText(Profile.this, "you are a member of CRT", Toast.LENGTH_SHORT).show();
                           }
                           else {
                               scrtCheck="yes";
//                               dm[0] =new DataModel(sname,scid,smail,spassword,scrtCheck,shosCheck);

                               myRef.child("AITS").child("CRT").child(userid).setValue(finalDmhc);
                               myRef.child("AITS").child("Total").child(userid).child("crtCheck").setValue(scrtCheck);

                           }
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                   });
       }
       if(pnohos.isChecked()){
           myRef.child("AITS").child("Hostler").orderByChild(userid)
                   .addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           if(dataSnapshot.exists()){
                               dataSnapshot.getRef().removeValue();

                           }
                           shosCheck="no";
                           myRef.child("AITS").child("Total").child(userid).child("hosCheck").setValue(shosCheck);

                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                   });
       }
       if(pshos.isChecked()){

          final DataModel dm1 =new DataModel(sname,scid,smail,spassword,scrtCheck,shosCheck);

           final DataModel finalDmhc1 = dmhc;
           myRef.child("AITS").child("Hostler").orderByChild(userid)
                   .addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           if(dataSnapshot.exists()){
                               Toast.makeText(Profile.this, "you are a member of hostel", Toast.LENGTH_SHORT).show();
                           }
                           else {

                               shosCheck="yes";
                               myRef.child("AITS").child("Hostler").child(userid).setValue(finalDmhc1);
                               myRef.child("AITS").child("Total").child(userid).child("hosCheck").setValue(shosCheck);


                           }
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                   });
       }

        myRef.child("AITS").child("Total").child(userid).setValue(dm);

    }


    public void selectprfimg(View view) {
        Intent intent= new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,42);

    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            Toast.makeText(this, ""+data, Toast.LENGTH_SHORT).show();
            imageUri=data.getData();
            uploadprfimg();
            try {
                Bitmap bit= MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                img.setImageBitmap(bit);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void uploadprfimg() {
        final StorageReference storage=mStorageRef.child("images").child(SystemClock.elapsedRealtime()+".jpeg");
        storage.putFile(imageUri)
                .continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if(task.isSuccessful()){
                            return storage.getDownloadUrl();

                        }
                        return null;
                    }
                })
                .addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        Uri uri=task.getResult();
                      // img.setImageURI(uri);
                       // Picasso.get().load(uri).into(img);
                        Toast.makeText(Profile.this, ""+uri, Toast.LENGTH_SHORT).show();
                        myRef.child("AITS").child("Total").child(userid).child("profilepic").setValue(uri.toString());
                        Toast.makeText(Profile.this, "Image uploaded", Toast.LENGTH_SHORT).show();


                    }
                });
    }
}

