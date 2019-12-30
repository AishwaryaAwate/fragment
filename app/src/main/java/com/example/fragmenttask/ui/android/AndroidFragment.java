package com.example.fragmenttask.ui.android;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.fragmenttask.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class AndroidFragment extends Fragment {



    RecyclerView recyclerView;
    ArrayList arrayList;
    AndroidAdapter androidAdapter;
    static  final  String URL = "https://simplifiedcoding.net/demos/marvel/";

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_android, container, false);


        recyclerView = root.findViewById(R.id.recyclerView);

        arrayList = new ArrayList<AndroidPojo>();

        setAdapter();


/*

// adding data from code just to check how its looking in recycler view

        AndroidPojo androidPojo1=new AndroidPojo();
        androidPojo1.setCreatedby("ABCD");
        androidPojo1.setImageurl("");
        androidPojo1.setName("TOY STORY");
        androidPojo1.setPublisher("CCCC");

        AndroidPojo androidPojo2=new AndroidPojo();
        androidPojo2.setCreatedby("ZZZZ");
        androidPojo2.setImageurl("");
        androidPojo2.setName("TOY STORY 2");
        androidPojo1.setPublisher("NNNN");

        arrayList.add(androidPojo1);
        arrayList.add(androidPojo2);

*/

        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setTitle(getString(R.string.loading));
        dialog.setMessage(getString(R.string.please_wait));
        dialog.setIndeterminate(true);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();


        //fetching data from url using volley
        RequestQueue queue = Volley.newRequestQueue(getContext());

        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {

                        // displaying response
                        Log.d("Response", response.toString());

                        try{

                            for(int i=0;i<response.length();i++){

                                String name=response.getJSONObject(i).getString("name");
                                String team=response.getJSONObject(i).getString("team");
                                String createdBy=response.getJSONObject(i).getString("createdby");
                                String publisher=response.getJSONObject(i).getString("publisher");
                                String imageurl=response.getJSONObject(i).getString("imageurl");
                                String bio=response.getJSONObject(i).getString("bio");

                                AndroidPojo androidPojo = new AndroidPojo();
                                androidPojo.setName(name);
                                androidPojo.setTeam(team);
                                androidPojo.setCreatedby(createdBy);
                                androidPojo.setPublisher(publisher);
                                androidPojo.setImageurl(imageurl);
                                androidPojo.setBio(bio);

                                 arrayList.add(androidPojo);

                                if (arrayList != null && arrayList.size() > 0) {

                                    setAdapter();

                                }

                                Log.d("test"," name : "+name+ "Team :"+team+"created by :"+createdBy+" publisher : "+publisher);

                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                        //dismissing progress dialog after loading the data
                        dialog.dismiss();

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),"Somethings goes wrong!! Check Internet Connection.",Toast.LENGTH_LONG).show();
                        Log.d("Error.Response", error.toString());
                    }
                }
        );



        // add it to the RequestQueue
        queue.add(getRequest);

        return root;
    }

    public void setAdapter(){

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        androidAdapter = new AndroidAdapter(getContext(),arrayList);
        recyclerView.setAdapter(androidAdapter);
    }

}