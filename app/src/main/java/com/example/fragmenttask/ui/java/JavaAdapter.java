package com.example.fragmenttask.ui.java;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmenttask.R;
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

        JavaPOJO javaPOJO = list.get(position);

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
        ImageView imageViewJava;

        public JavaViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewJava = itemView.findViewById(R.id.imageViewJava);

            text_from = itemView.findViewById(R.id.text_from);
            //text_picture = itemView.findViewById(R.id.text_picture);
            text_subject = itemView.findViewById(R.id.text_subject);
            text_message = itemView.findViewById(R.id.text_messege);
        }
    }
}
