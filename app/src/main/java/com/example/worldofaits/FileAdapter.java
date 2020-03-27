package com.example.worldofaits;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewInfof>{
    Context ctx;
    List<DataModelFile> idata;

    public FileAdapter(Context ctx, List<DataModelFile> idata) {
        this.ctx = ctx;
        this.idata = idata;
    }

    @NonNull
    @Override
    public FileAdapter.ViewInfof onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(ctx).inflate(R.layout.file_design,parent,false);
        return new ViewInfof(v);

    }

    @Override
    public void onBindViewHolder(@NonNull FileAdapter.ViewInfof holder, int position) {
        final String url=idata.get(position).getUrl().trim();
        final String fn =idata.get(position).getFile_name();
        holder.time.setText(idata.get(position).getTime());
        holder.filename.setText(idata.get(position).getFile_name());
        holder.filename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ctx, "download file", Toast.LENGTH_SHORT).show();
               DownloadManager.Request request=new DownloadManager.Request(Uri.parse(url));
               request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI|DownloadManager.Request.NETWORK_MOBILE);
               request.setTitle("Download");
               request.setDescription("Downloading file ...");
               request.allowScanningByMediaScanner();
               request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
               request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,""+fn);
               DownloadManager manager=(DownloadManager) ctx.getSystemService(Context.DOWNLOAD_SERVICE);
                manager.enqueue(request);
            }
        });

    }

    @Override
    public int getItemCount() {
        return idata.size();
    }

    public class ViewInfof extends RecyclerView.ViewHolder {
        TextView filename, time;

        public ViewInfof(@NonNull View itemView) {
            super(itemView);
            filename = itemView.findViewById(R.id.filenames);
            time = itemView.findViewById(R.id.filetime);
        }
    }
}
