package com.example.worldofaits;

import android.app.AlertDialog;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HosAdapter extends RecyclerView.Adapter<HosAdapter.ViewInfo>{
    Context ctx;
    List<DataModelImg> idata;

    public HosAdapter(Context ctx, List<DataModelImg> idata) {
        this.ctx = ctx;
        this.idata = idata;
    }

    @NonNull
    @Override
    public HosAdapter.ViewInfo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View v= LayoutInflater.from(ctx).inflate(R.layout.hostel_design,parent,false);

        return new ViewInfo(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewInfo holder, int position) {
        holder.subject.setText(idata.get(position).getSubject());
        holder.message.setText(idata.get(position).getMessage());
        holder.message.setMovementMethod(new ScrollingMovementMethod());
        holder.id.setText(idata.get(position).getCollegeID());
        holder.time.setText(idata.get(position).getTime());
        holder.name.setText(idata.get(position).getFullName());

        final String url=idata.get(position).getUri();
        if(url!=null) {
            Picasso.get().load(url).into(holder.img);
                   }
        else {
            holder.cd.setVisibility(View.GONE);
        }
        String pro_url=idata.get(position).getProImg();
        if(pro_url!=null) {
            Picasso.get().load(pro_url).into(holder.pic);
        }
        else {
            Picasso.get().load(R.drawable.aits_logo).into(holder.pic);
        }

        // holder.pic.setText(idata.get(position).getSubject());

    }

    @Override
    public int getItemCount() {
        return idata.size();
    }

    public class ViewInfo extends RecyclerView.ViewHolder {
        TextView subject,message,id,time,name;
        ImageView img;
        CardView cd;
        CircleImageView pic;
        public ViewInfo(@NonNull View itemView) {
            super(itemView);
            subject=itemView.findViewById(R.id.sub_hos);
            name=itemView.findViewById(R.id.hos_name);
            message=itemView.findViewById(R.id.msg_hos);
            id=itemView.findViewById(R.id.hos_id);
            time=itemView.findViewById(R.id.hos_time);
            img=itemView.findViewById(R.id.img_display);
            cd=itemView.findViewById(R.id.cd_hos);
            pic=itemView.findViewById(R.id.pro_img);

        }
    }
}