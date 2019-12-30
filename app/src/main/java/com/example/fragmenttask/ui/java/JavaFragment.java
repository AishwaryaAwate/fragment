package com.example.fragmenttask.ui.java;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmenttask.R;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JavaFragment extends Fragment {

    RecyclerView recyclerViewJava;
    //     ArrayList<JavaPOJO> arrayListJava;
    JavaAdapter javaAdapter;
    ProgressDialog dialog;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_java, container, false);

        recyclerViewJava = root.findViewById(R.id.recyclerViewJava);


        dialog = new ProgressDialog(getActivity());
        dialog.setTitle(getString(R.string.loading));
        dialog.setMessage(getString(R.string.please_wait));
        dialog.setIndeterminate(true);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();

        getMails();

        return root;
    }


    public void getMails() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<List<JavaPOJO>> call = api.getJavaPojo();


        call.enqueue(new Callback<List<JavaPOJO>>() {
            @Override
            public void onResponse(Call<List<JavaPOJO>> call, Response<List<JavaPOJO>> response) {

                if (response.code() == 200) {

                    dialog.dismiss();

                    List<JavaPOJO> pojoList = response.body();

                    Log.d("test", " SUCCESS : " + pojoList);

                    recyclerViewJava.setLayoutManager(new LinearLayoutManager(getContext()));
                    javaAdapter = new JavaAdapter(getContext(), pojoList);
                    recyclerViewJava.setAdapter(javaAdapter);


                }


            }

            @Override
            public void onFailure(Call<List<JavaPOJO>> call, Throwable t) {
                dialog.dismiss();
            }
        });


    }

    public class Java {

        @SerializedName("from, subject,picture")

        private String from;
        private  String subject;
        private String picture;

        public Java() {
        }
    }

}