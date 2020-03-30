package com.example.worldofaits;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout=findViewById(R.id.tab);
        viewPager=findViewById(R.id.viewpage);
        mAuth=FirebaseAuth.getInstance();
        MyAdapter myAdapter=new MyAdapter(getSupportFragmentManager());
        myAdapter.addFragment(new NewsFeed(),"News Feed");
        myAdapter.addFragment(new Materials(),"Materials");
        myAdapter.addFragment(new Circulars(),"Circulars");
        viewPager.setAdapter(myAdapter);
        tabLayout.setupWithViewPager(viewPager);
     //   Toast.makeText(this, ""+mAuth.getUid(), Toast.LENGTH_SHORT).show();
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==
                    PackageManager.PERMISSION_DENIED){
                String[] permissions={Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions,1000);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        if (getIntent().getStringExtra("name").equals("otheruser")) {
            MenuItem itemid = menu.findItem(R.id.profile);
            MenuItem itemid1 = menu.findItem(R.id.logout);
            itemid.setVisible(false);
            itemid1.setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){


            case R.id.hostel:
                startActivity(new Intent(this,Hostel.class));
                break;
            case R.id.campus:
                startActivity(new Intent(this,Campus.class));
                break;
            case R.id.profile:
                startActivity(new Intent(this, Profile.class));
                break;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                 Intent intent =new Intent(this,Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//makesure user cant go back
                startActivity(intent);

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length>0&& grantResults[0]==
                PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Permissions accepted!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Please allow storage permissions ...", Toast.LENGTH_SHORT).show();
        }
    }
}
