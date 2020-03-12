package com.example.worldofaits;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.io.FileNotFoundException;
import java.io.IOException;

public class UploadImage extends AppCompatActivity {
ImageView img;
EditText sub,mes;
Uri imageUri;
FirebaseAuth mAuth;
StorageReference mStorageRef;
    DatabaseReference dref;
DataModelImg dataModelImg;
String name="";
    String cid="";
    String email="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);
        sub=findViewById(R.id.subject);
        mes=findViewById(R.id.msg);
        img=findViewById(R.id.image_upload);
        mAuth=FirebaseAuth.getInstance();
         dref= FirebaseDatabase.getInstance().getReference();
        dataModelImg=new DataModelImg();
        mStorageRef= FirebaseStorage.getInstance().getReference();
        String userid=mAuth.getUid();
         dref.child("AITS").child("Total").orderByChild(userid).addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 for (DataSnapshot ds1:dataSnapshot.getChildren()){
                      name=ds1.child("fullName").getValue().toString();
                      cid=ds1.child("collegeID").getValue().toString();
                      email=ds1.child("email").getValue().toString();

                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });
    }

    public void uploadimage(final View view) {
        if(TextUtils.isEmpty(sub.getText().toString())){
            sub.setError("Subject is mandatory.");
            return;
        }
        if(TextUtils.isEmpty(mes.getText().toString())){
            mes.setError("Text message is mandatory.");
            return;
        }
        if(imageUri !=null) {
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


                           dataModelImg = new DataModelImg(uri.toString(), mes.getText().toString(), sub.getText().toString(), name, cid, email);
                        dref.child("urls").child(SystemClock.elapsedRealtime() + "").setValue(dataModelImg);
                           Toast.makeText(UploadImage.this, "Image uploaded", Toast.LENGTH_SHORT).show();


                    }
                });}
         else {
            dataModelImg=new DataModelImg(mes.getText().toString(),sub.getText().toString(),name,cid,email);
            dref.child("urls").child(SystemClock.elapsedRealtime()+"").setValue(dataModelImg);
            Toast.makeText(UploadImage.this, "text uploaded", Toast.LENGTH_SHORT).show();

        }

    }

    public void selectimage(View view) {
        Intent intent= new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,42);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            Toast.makeText(this, ""+data, Toast.LENGTH_SHORT).show();
            imageUri=data.getData();
            try {
                Bitmap bit= MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                img.setImageBitmap(bit);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

