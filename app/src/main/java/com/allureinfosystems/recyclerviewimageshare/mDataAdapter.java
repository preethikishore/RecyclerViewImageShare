package com.allureinfosystems.recyclerviewimageshare;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class mDataAdapter extends RecyclerView.Adapter<mDataAdapter.MyViewHolder> {
    private ArrayList<HashMap<String, String>> dataSet;
    Context context;


    public mDataAdapter(Context context,ArrayList< HashMap<String, String>> dataSet) {
        this.dataSet = dataSet;
        this.context = context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_data, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final HashMap<String, String> Details = dataSet.get(position);
        holder.bName.setText(Details.get("Bname"));
        holder.bImage.setImageResource(Integer.parseInt((Details.get("Bimage"))));


        holder.visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String website = Details.get("Burl");
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(website));
                context.startActivity(browserIntent);

            }
        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // create file from drawable image
                Bitmap bm = BitmapFactory.decodeResource(context.getResources(), Integer.parseInt(Details.get("Bimage")));

                File filesDir = context.getApplicationContext().getFilesDir();
                File imageFile = new File(filesDir, "birds.png");

                OutputStream os;
                try {
                    os = new FileOutputStream(imageFile);
                    bm.compress(Bitmap.CompressFormat.PNG, 100, os); // 100% quality
                    os.flush();
                    os.close();
                } catch (Exception e) {
                    Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
                }


                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                Uri imageUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID, imageFile);
                intent.putExtra(Intent.EXTRA_STREAM, imageUri);
                intent.setType("image/*");
                context.startActivity(intent);
            }
        });

    }

    @Override

    public int getItemCount() {
        return (dataSet == null) ? 0 : dataSet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView bName;
        ImageView bImage;
        Button visit;
        Button share;

        MyViewHolder(@NonNull View itemView) {

            super(itemView);
            bName = (TextView) itemView.findViewById(R.id.bName);
            bImage = (ImageView) itemView.findViewById(R.id.bImage);
            visit = (Button) itemView.findViewById(R.id.website);
            share = (Button) itemView.findViewById(R.id.share);

        }
    }
}

