package com.example.worldofaits;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Faculty extends AppCompatActivity {
    EditText facid,facpsd;
    DatabaseReference myRef;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty);
        facid=findViewById(R.id.faculty_id);
        myRef= FirebaseDatabase.getInstance().getReference();
        facpsd=findViewById(R.id.faculty_password);
    }
    public void loginFaculty(View view) {

       String fac=facid.getText().toString();
       final String facp=facpsd.getText().toString();

        myRef.child("AITS").child("Faculty").child(fac)

                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.exists()){
                            for(DataSnapshot ds:dataSnapshot.getChildren()){
                                String pswd=ds.child("password").getValue().toString();
                                if(pswd.equals(facp)){

                                    Intent in=new Intent(Faculty.this,MainActivity.class);
                                    in.putExtra("name","faculty");
                                    startActivity(in);

                                }
                                else {
                                    Toast.makeText(Faculty.this, "Invalid password", Toast.LENGTH_SHORT).show();
                                }

                            }

                            Toast.makeText(Faculty.this, "Hello", Toast.LENGTH_SHORT).show();
                        }

                       else {

                            Toast.makeText(Faculty.this, "bye", Toast.LENGTH_SHORT).show();
                        }     //startActivity(new Intent(getApplication(),MainActivity.class));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }

}
