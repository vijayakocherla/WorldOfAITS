package com.example.worldofaits;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class CivilMaterialView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_view);
    }


    public void first(View view) {
        startActivity(new Intent(this,Cse1.class));
    }
    public void second(View view) {
        startActivity(new Intent(this,Cse2.class));

    }

    public void third(View view) {
        startActivity(new Intent(this,Cse3.class));

    }
    public void fourth(View view) {
        startActivity(new Intent(this,Cse4.class));

    }


}