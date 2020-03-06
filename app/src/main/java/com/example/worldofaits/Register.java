package com.example.worldofaits;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    DatabaseReference myRef;
    EditText name,psd,em,id;
    RadioButton hos,crt;
    RadioGroup rghostler,rgcrt;
    String hosCheck="",crtCheck="";
    String s="yes";
   // String n="no";
    DataModel dmodel=new DataModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        myRef= FirebaseDatabase.getInstance().getReference();
        name=findViewById(R.id.register_name);
        psd=findViewById(R.id.reg_password);
        em=findViewById(R.id.register_recovery);
        id=findViewById(R.id.register_collegeID);
        rghostler=findViewById(R.id.reg_rg_hostler);
        rgcrt=findViewById(R.id.reg_rg_crt);
       // hos=findViewById(R.id.shostler);
       // crt=findViewById(R.id.sCRT);


        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

    }

    public void hostler(View view) {

    }

    public void registerUser(View view) {

        String email=em.getText().toString();
        String password=psd.getText().toString();
        String cid=id.getText().toString();
        int selectedIdh=rghostler.getCheckedRadioButtonId();
        int selectedIdc=rgcrt.getCheckedRadioButtonId();
        hos=findViewById(selectedIdh);
        crt=findViewById(selectedIdc);
        if(selectedIdh==-1){
            Toast.makeText(this, "Nothing selected in hostler", Toast.LENGTH_SHORT).show();
        }
        else{
            hosCheck= (String) hos.getText();
        }
        // To store Data into datamodel objects
        if(selectedIdc==-1){
            Toast.makeText(this, "Nothing selected in hostler", Toast.LENGTH_SHORT).show();
        }
        else{
            crtCheck= (String) crt.getText();
        }
        final String dname=name.getText().toString();
        final String did=id.getText().toString();
        final String dpsd=psd.getText().toString();
        final String dmail=em.getText().toString();

        if(TextUtils.isEmpty(email)){
            em.setError("Email is Required.");
            return;
        }

        if(TextUtils.isEmpty(password)){
            psd.setError("Password is Required.");
            return;
        }
        if(TextUtils.isEmpty(cid)){
            id.setError("College ID is Required.");
            return;
        }
        //String roll=new String(ak1a0);
        boolean notcid= cid.contains("ak1a0")||cid.contains("ak5a0");
        if(!notcid){
            id.setError("it is not aits ID");
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            dmodel=new DataModel(dname,did,dmail,dpsd,crtCheck,hosCheck);
           myRef.child("AITS").child("Total").child(myRef.push().getKey()).setValue(dmodel)
                   .addOnCompleteListener( new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           if (task.isSuccessful()){
                               Toast.makeText(Register.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                           }
                       }
                   })
           .addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
                   Toast.makeText(Register.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    });
                            if(hosCheck.equals(s)){
                                myRef.child("AITS").child("Hostler").child(myRef.push().getKey()).setValue(dmodel);
                            }
                            if(crtCheck.equals(s)){
                                myRef.child("AITS").child("CRT").child(myRef.push().getKey()).setValue(dmodel);
                            }

                            // Sign in success, update UI with the signed-in user's information
                            startActivity(new Intent(getApplicationContext(),Login.class));
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Register.this, ""+task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

//            dmodel=new DataModel(dname,did,dmail,dpsd,crtCheck,hosCheck);
//            myRef.child("AITS").child("Total").child(myRef.push().getKey()).setValue(dmodel)
//                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if(task.isSuccessful()){
//                                Toast.makeText(Register.this, "Data is stored", Toast.LENGTH_SHORT).show();
//
//                            }
//                        }
//                    })
//                    .addOnFailureListener(this, new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(Register.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    });

    }


}
