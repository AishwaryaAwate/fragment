package com.example.fragmenttask.ui.php;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.fragmenttask.R;

public class PHPFragment extends Fragment {

    private PHPViewModel phpViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        phpViewModel =
                ViewModelProviders.of(this).get(PHPViewModel.class);
        View root = inflater.inflate(R.layout.fragment_php, container, false);
        final TextView textView = root.findViewById(R.id.text_php);
        phpViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}