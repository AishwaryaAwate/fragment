package com.example.fragmenttask.ui.android;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fragmenttask.R;
import com.squareup.picasso.Picasso;

public class AndroidDetails_Fragment extends Fragment {

    AndroidPojo androidPojo;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_android_details,container,false);

        ImageView imageViewDetails;
        TextView text_name, text_bio;

        imageViewDetails = root.findViewById(R.id.imageViewDetails);
        text_name= root.findViewById(R.id.text_name);
        text_bio= root.findViewById(R.id.text_bio);

        //receiving data from android fragment from adapter
        Bundle args = getArguments();
        if (args != null) {
            androidPojo= args.getParcelable("data");
        }

        //loading image through its url using picasso
        Picasso.with(getContext()).load(androidPojo.getImageurl()).into(imageViewDetails);
        //Glide.with(getContext()).load(androidPojo.getImageurl()).into(imageViewDetails);
        text_name.setText(androidPojo.getName());
        text_bio.setText(androidPojo.getBio());

        Log.d("test","Data : "+androidPojo);
        return root;
    }
}
