package com.example.fragmenttask.ui.java;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmenttask.MainActivity;
import com.example.fragmenttask.R;
import com.example.fragmenttask.ui.android.AndroidDetails_Fragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class JavaAdapter extends RecyclerView.Adapter<JavaAdapter.JavaViewHolder> {

    Context mContext;
    List<JavaPOJO> list;

    public JavaAdapter(Context mContext, List<JavaPOJO> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public JavaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.adapter_java, null);

        return new JavaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JavaViewHolder holder, int position) {

        final JavaPOJO javaPOJO = list.get(position);

        Log.d("test", "data :" + javaPOJO.getPicture());

        if (javaPOJO.getPicture() != null && !javaPOJO.getPicture().isEmpty()) {
            Picasso.with(mContext).load(javaPOJO.getPicture()).into(holder.imageViewJava);
            //holder.text_picture.setVisibility(View.GONE);
        } else {

            Picasso.with(mContext).load(R.drawable.avatar).into(holder.imageViewJava);


            /*holder.imageViewJava.setVisibility(View.GONE);
            holder.text_picture.setVisibility(View.VISIBLE);
            holder.text_picture.setText(" " +javaPOJO.getFrom().charAt(0));*/
        }

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = new JavaDetails_Fragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("data",javaPOJO);
                fragment.setArguments(bundle);
                ((MainActivity)mContext).replaceFragment(fragment,"Java Details ");

            }
        });
        holder.text_from.setText(javaPOJO.getFrom());
        holder.text_subject.setText(javaPOJO.getSubject());
        holder.text_message.setText(javaPOJO.getMessage());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class JavaViewHolder extends RecyclerView.ViewHolder {

        TextView text_from, text_subject, text_message, text_picture;
        RelativeLayout relativeLayout;
        ImageView imageViewJava;

        public JavaViewHolder(@NonNull View itemView) {
            super(itemView);

            relativeLayout = itemView.findViewById(R.id.relative_layout);

            imageViewJava = itemView.findViewById(R.id.imageViewJava);

            text_from = itemView.findViewById(R.id.text_from);
            //text_picture = itemView.findViewById(R.id.text_picture);
            text_subject = itemView.findViewById(R.id.text_subject);
            text_message = itemView.findViewById(R.id.text_messege);
        }
    }
}
