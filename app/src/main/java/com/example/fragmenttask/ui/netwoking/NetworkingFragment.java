package com.example.fragmenttask.ui.netwoking;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.fragmenttask.CircleImageView;
import com.example.fragmenttask.MainActivity;
import com.example.fragmenttask.R;
import com.example.fragmenttask.ui.android.AndroidDetails_Fragment;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Matcher;

public class NetworkingFragment extends Fragment {

    TextInputEditText edt_username, edt_email, edt_password, edt_confirm_password, edt_mobileNo;
    RadioButton rBtn_female, rBtn_Male;
    Button btnSubmit;
    CircleImageView imgProfilePic;

    DatabaseHelper databaseHelper;

    String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_networking, container, false);

        databaseHelper = new DatabaseHelper(getContext());


        edt_username = root.findViewById(R.id.edt_username);
        edt_email = root.findViewById(R.id.edt_email);
        edt_password = root.findViewById(R.id.edt_passward);
        edt_confirm_password = root.findViewById(R.id.edt_confirm_passward);
        edt_mobileNo = root.findViewById(R.id.edt_mobileNo);

        rBtn_female = root.findViewById(R.id.radio_female);
        rBtn_Male = root.findViewById(R.id.radio_male);

        imgProfilePic = root.findViewById(R.id.profile_pic);
       /* imgProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageLoader = (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT)
                        ? new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE)
                        : new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(imageLoader, 1);
            }
        });*/

        btnSubmit = root.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (validateInputData()) {

                    Fragment fragment = new LoginFragment();
                    ((MainActivity) getContext()).replaceFragment(fragment, "Welcome page ");

                    insertIntoDB();

                }


            }
        });


        return root;
    }

    private boolean validateInputData() {

        if (edt_username.getText().toString().length()==0) {
            edt_username.requestFocus();
            edt_username.setError("Enter valid Username");
            return false;
        }
         if (edt_email.getText().toString().matches(EMAIL_PATTERN)) {
             edt_email.requestFocus();
             edt_email.setError("Enter valid Email");
             return false;
         }
          if (edt_password.getText().toString().matches(PASSWORD_PATTERN)) {
              edt_password.requestFocus();
              edt_password.setError("Enter valid Password");
              return false;
          }

         if (edt_password.getText().toString().equals(edt_confirm_password.getText().toString())) {
             edt_confirm_password.requestFocus();
             edt_confirm_password.setError("Password do not match");
             return false;
         }
             if (edt_mobileNo.getText().toString().length() <= 10) {
                 edt_mobileNo.requestFocus();
                 edt_mobileNo.setError("Enter valid mobile number");
                 return false;
             }

         return true;
    }


    private void insertIntoDB() {

        NetworkPOJO networkPOJO = new NetworkPOJO(null, edt_username.getText().toString(),
                edt_email.getText().toString(),
                edt_password.getText().toString(),
                edt_confirm_password.getText().toString(),
                edt_mobileNo.getText().toString());


        if (networkPOJO != null) {

            boolean isInserted = databaseHelper.insertData(networkPOJO);
            if (isInserted) {
                Toast.makeText(getContext(), "User Added successfully", Toast.LENGTH_LONG).show();
                Log.d("test", " username :" + networkPOJO.getUsername() + " Email :" + networkPOJO.getEmail() + " password :" + networkPOJO.getPassword() + " confirm password :" + networkPOJO.getPassword() + " mobile :" + networkPOJO.getMobileNo());
            } else {

                Toast.makeText(getContext(), "User not Added ", Toast.LENGTH_LONG).show();
            }
        }
    }


}