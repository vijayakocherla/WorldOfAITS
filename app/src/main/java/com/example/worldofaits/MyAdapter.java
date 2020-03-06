package com.example.worldofaits;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends FragmentStatePagerAdapter {
    List<Fragment> fragmentList= new ArrayList<>();
    List<String> titles=new ArrayList<>();
    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
    public CharSequence getPageTitle(int position){
        return titles.get(position);
    }

    public void addFragment(Fragment f, String s) {
    fragmentList.add(f);
    titles.add(s);
    }
}
