package com.example.fragmenttask.ui.android;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmenttask.MainActivity;
import com.example.fragmenttask.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class AndroidAdapter extends RecyclerView.Adapter<AndroidAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<AndroidPojo> arrayList;


    public AndroidAdapter(Context mContext, ArrayList<AndroidPojo> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.adapter_android,null);



        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final AndroidPojo androidPojo = arrayList.get(position);

        Log.d("test","data :" +androidPojo.getImageurl());
        Picasso.with(mContext).load(androidPojo.getImageurl()).into(holder.imageView);
        //Glide.with(mContext).load(androidPojo.getImageurl()).into(holder.imageView);


        holder.txtName.setText(androidPojo.getName());


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // passing data from one fragment to android details fragment on click of item from recylcer view
                Fragment fragment = new AndroidDetails_Fragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("data",androidPojo);
                fragment.setArguments(bundle);
                ((MainActivity)mContext).replaceFragment(fragment,"Details ");

            }
        });

    }


    @Override
    public int getItemCount() {

        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayout;
        ImageView imageView;
        TextView txtName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.main_container);

            imageView =itemView.findViewById(R.id.imageViewURL);
            txtName =itemView.findViewById(R.id.txtName);

        }
    }


}
