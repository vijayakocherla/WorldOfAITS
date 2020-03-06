package com.example.worldofaits;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Materials extends Fragment implements View.OnClickListener {
 ImageView icse,iece,ieee,imech,icivil,ihns;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View obj= inflater.inflate(R.layout.fragment_materials, container, false);
        icse=obj.findViewById(R.id.cse);
        icse.setOnClickListener(this);
        iece=obj.findViewById(R.id.ece);
        iece.setOnClickListener(this);
        ieee=obj.findViewById(R.id.eee);
        ieee.setOnClickListener(this);
        imech=obj.findViewById(R.id.mech);
        imech.setOnClickListener(this);
        icivil=obj.findViewById(R.id.civil);
        icivil.setOnClickListener(this);

    return obj;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cse:
                startActivity(new Intent(getContext(),MaterialView.class));
                break;
            case R.id.ece:
                startActivity(new Intent(getContext(),MaterialView.class));
                break;
            case R.id.eee:
                startActivity(new Intent(getContext(),MaterialView.class));
                break;
            case R.id.mech:
                startActivity(new Intent(getContext(),MaterialView.class));
                break;
            case R.id.civil:
                startActivity(new Intent(getContext(),MaterialView.class));
                break;

        }

    }
}
