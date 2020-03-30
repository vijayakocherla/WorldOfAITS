package com.example.worldofaits;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFeed extends Fragment {
private FloatingActionButton nf;
DatabaseReference dref;
DataModelImg dmi;
FirebaseAuth mAuth;


    public NewsFeed() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view= inflater.inflate(R.layout.fragment_news_feed, container, false);
        nf = view.findViewById(R.id.uploadhere);
        final RecyclerView rvn=view.findViewById(R.id.news_recycler);
        mAuth=FirebaseAuth.getInstance();
        dref= FirebaseDatabase.getInstance().getReference();
        rvn.setLayoutManager(new LinearLayoutManager(getContext()));
        dmi=new DataModelImg();
        if(mAuth.getUid()==null){
            nf.setVisibility(View.GONE);
        }
        nf.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        startActivity(new Intent(getActivity(),ImageNews.class));
       // DataModelImg dmi=new DataModelImg();
        }
         });

        dref.child("urlsnews").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<DataModelImg> dataModelImgList = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    dmi = ds.getValue(DataModelImg.class);
                    dataModelImgList.add(dmi);

                }
                HosAdapter hosfeed = new HosAdapter(getActivity(), dataModelImgList);
                rvn.setAdapter(hosfeed);
            }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
                });
    return view;
    }}





