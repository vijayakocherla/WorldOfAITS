package com.example.worldofaits;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
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

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class Uploadfile extends AppCompatActivity {
TextView filename;
Uri file;
String path;
DatabaseReference dRef,uRef;
FirebaseAuth mAuth;
DataModelFile df;
StorageReference sRef;
ProgressDialog pd;
String userid,name,time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadfile);
        filename=findViewById(R.id.selectedfile);
        sRef= FirebaseStorage.getInstance().getReference();
        dRef= FirebaseDatabase.getInstance().getReference();
        mAuth=FirebaseAuth.getInstance();
        df=new DataModelFile();
        pd=new ProgressDialog(Uploadfile.this);
        userid=mAuth.getUid();
        uRef=dRef.child("AITS").child("Total").child(userid);
        ValueEventListener eventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds1:dataSnapshot.getChildren()){
                    name=dataSnapshot.child("fullName").getValue().toString();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        uRef.addListenerForSingleValueEvent(eventListener);
    }

    public void uploadfile(View view) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"+"\n"+"HH:mm");
        time= dateFormat.format(new Date());
        if(file!=null){
            pd.setTitle("File is Uploading...");
            pd.show();
        final StorageReference storageReference=sRef.child("files").child(SystemClock.elapsedRealtime()+".pdf");
        storageReference.putFile(file)
                .continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                       if(task.isSuccessful()){
                           return storageReference.getDownloadUrl();

                       }
                        return null;
                    }
                })
            .addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    Uri uri=task.getResult();
                    pd.dismiss();
                    Toast.makeText(Uploadfile.this, "File uploaded", Toast.LENGTH_SHORT).show();

                    df=new DataModelFile(name,uri.toString(),path,time);
                    dRef.child("circularurls").child(SystemClock.elapsedRealtime()+"").setValue(df);
                   // startActivity(new Intent(Uploadfile.this,Circulars.class));
                    finish();
                }
            });

        }

    }

    public void selectfile(View view) {
        Intent intent= new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,42);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            Toast.makeText(this, ""+data, Toast.LENGTH_SHORT).show();
            file=data.getData();
           path=getRealPathFromURI(this,file);
            filename.setText(path);
        }
    }
    private String getRealPathFromURI(Context cntx,Uri contentURI) {
        Cursor cursor = null;
        try {
            cursor = cntx.getContentResolver().query(contentURI,  null, null, null, null);
            int nameoffile = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            cursor.moveToFirst();
            return cursor.getString(nameoffile);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
