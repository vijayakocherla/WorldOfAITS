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
import android.text.format.DateFormat;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UploadImage extends AppCompatActivity {
ImageView img;
EditText sub,mes;
Uri imageUri;
ProgressDialog pd;
FirebaseAuth mAuth;
StorageReference mStorageRef;
    DatabaseReference dref,uRef;
DataModelImg dataModelImg;
String name="";
    String cid="",userid;
    String email="",propic,time="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);
        sub=findViewById(R.id.subject);
        mes=findViewById(R.id.msg);
        img=findViewById(R.id.image_upload);
        mAuth=FirebaseAuth.getInstance();
         dref= FirebaseDatabase.getInstance().getReference();
         pd=new ProgressDialog(UploadImage.this);
        dataModelImg=new DataModelImg();
        mStorageRef= FirebaseStorage.getInstance().getReference();
         userid=mAuth.getUid();
//        Calendar cal=Calendar.getInstance(Locale.ENGLISH);
//        cal.setTimeInMillis(Long.parseLong(timestamp));
//         time = DateFormat.format("dd-MM-yyyy hh:mm:ss", cal).toString();
         uRef=dref.child("AITS").child("Total").child(userid);
         ValueEventListener eventListener=new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 for (DataSnapshot ds1:dataSnapshot.getChildren()){
                      name=dataSnapshot.child("fullName").getValue().toString();
                      cid=dataSnapshot.child("collegeID").getValue().toString();
                      email=dataSnapshot.child("email").getValue().toString();
                      if(dataSnapshot.child("profilepic").exists()){
                          propic=dataSnapshot.child("profilepic").getValue().toString();
                      }
                      else{
                         // String url=
                      }
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         };
         uRef.addListenerForSingleValueEvent(eventListener);
    }

    public void uploadimage(final View view) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"+"\n"+"HH:mm");
        time= dateFormat.format(new Date());
        if(TextUtils.isEmpty(sub.getText().toString())){
            sub.setError("Subject is mandatory.");
            return;
        }
        if(TextUtils.isEmpty(mes.getText().toString())){
            mes.setError("Text message is mandatory.");
            return;
        }
        if(imageUri !=null) {
            pd.setTitle("Image is Uploading...");
            pd.show();
            // Find todays date

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
                        pd.dismiss();
                        Toast.makeText(UploadImage.this, "Image uploaded", Toast.LENGTH_SHORT).show();
                        dataModelImg = new DataModelImg(uri.toString(), mes.getText().toString(), sub.getText().toString(), name, cid, email,time,propic);
                        dref.child("urls").child(SystemClock.elapsedRealtime() + "").setValue(dataModelImg);
                        //startActivity(new Intent(UploadImage.this,Hostel.class));
                    finish();
                    }
                });}
         else {
           // Toast.makeText(UploadImage.this, ""+imageUri.toString(), Toast.LENGTH_SHORT).show();
            dataModelImg = new DataModelImg(null, mes.getText().toString(), sub.getText().toString(), name, cid, email,time,propic);
            dref.child("urls").child(SystemClock.elapsedRealtime() + "").setValue(dataModelImg);
            Toast.makeText(UploadImage.this, "text uploaded", Toast.LENGTH_SHORT).show();
           // startActivity(new Intent(UploadImage.this,Hostel.class));
            finish();
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

