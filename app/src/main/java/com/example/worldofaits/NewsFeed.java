package com.example.worldofaits;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFeed extends Fragment {
private FloatingActionButton nf;
View view;
TextView namedisplay,msgdisplay,subdisplay,timedisplay;


    public NewsFeed() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view= inflater.inflate(R.layout.fragment_news_feed, container, false);
        namedisplay=view.findViewById(R.id.news_name);

        nf = view.findViewById(R.id.uploadhere);
  // ci.setClickable(true);
        nf.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        startActivity(new Intent(getActivity(),UploadImage.class));
       // DataModelImg dmi=new DataModelImg();
            List<DataModelImg> dataModelImgList=new ArrayList<>();


        }
         });

    return view;
    }

}
