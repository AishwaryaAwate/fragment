package com.example.fragmenttask.ui.java;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fragmenttask.CircleImageView;
import com.example.fragmenttask.R;
import com.squareup.picasso.Picasso;


public class JavaDetails_Fragment extends Fragment {

    TextView text_subject_details, text_from_details, text_message_details;
    CircleImageView imageview_in_details;
    Button btn_replay, btn_replay_all, btn_forward;

    JavaPOJO javaPOJO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (inflater.inflate(R.layout.fragment_java_details, container, false));

        text_subject_details = view.findViewById(R.id.text_subject_details);
        text_from_details = view.findViewById(R.id.text_from_details);
        text_message_details = view.findViewById(R.id.text_message_details);

        imageview_in_details = view.findViewById(R.id.imageview_in_details);

        btn_replay = view.findViewById(R.id.btn_replay);
        btn_replay_all = view.findViewById(R.id.btn_replay_all);
        btn_forward = view.findViewById(R.id.btn_forward);

        Bundle args = getArguments();
        if (args != null) {
            javaPOJO = args.getParcelable("data");
        }

        //loading image through its url using picasso
        if (javaPOJO.getPicture() != null && !javaPOJO.getPicture().isEmpty()) {

            Picasso.with(getContext()).load(javaPOJO.getPicture()).into(imageview_in_details);
        } else {

            Picasso.with(getContext()).load(R.drawable.avatar).into(imageview_in_details);
        }


        text_subject_details.setText(javaPOJO.getSubject());
        text_from_details.setText(javaPOJO.getFrom());
        text_message_details.setText(javaPOJO.getMessage());

        btn_replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Replay...", Toast.LENGTH_LONG).show();
            }
        });

        btn_replay_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Replay all....", Toast.LENGTH_LONG).show();
            }
        });

        btn_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Forward to......", Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }
}
