package com.example.worldofaits;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout=findViewById(R.id.tab);
        viewPager=findViewById(R.id.viewpage);
        MyAdapter myAdapter=new MyAdapter(getSupportFragmentManager());
        myAdapter.addFragment(new NewsFeed(),"News Feed");
        myAdapter.addFragment(new Materials(),"Materials");
        myAdapter.addFragment(new Circulars(),"Circulars");
        viewPager.setAdapter(myAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
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
                startActivity(new Intent(this,Login.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
