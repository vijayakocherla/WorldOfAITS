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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Circulars extends Fragment {

    private FloatingActionButton fbc;
View view;
DatabaseReference databaseReference;
DataModelFile dfc;

    public Circulars() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_circulars, container, false);
        fbc=view.findViewById(R.id.uploaddoc);
        final RecyclerView rvc =view.findViewById(R.id.circulars_recycler);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        rvc.setLayoutManager(new LinearLayoutManager(getContext()));
        dfc=new DataModelFile();
        fbc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Uploadfile.class));
            }
        });
        databaseReference.child("circularurls").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<DataModelFile> dataModeFileList = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    dfc = ds.getValue(DataModelFile.class);
                    dataModeFileList.add(dfc);

                }
                FileAdapter fileAdapter= new FileAdapter(getActivity(),dataModeFileList);
                rvc.setAdapter(fileAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return  view;
    }

}
