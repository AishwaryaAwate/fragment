package com.example.fragmenttask.ui.netwoking;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.fragmenttask.CircleImageView;
import com.example.fragmenttask.MainActivity;
import com.example.fragmenttask.R;
import com.example.fragmenttask.ui.android.AndroidDetails_Fragment;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URI;
import java.util.regex.Matcher;

public class NetworkingFragment extends Fragment {

    TextInputEditText edt_username, edt_email, edt_password, edt_confirm_password, edt_mobileNo;
    RadioGroup radioGroup;
    Button btnSubmit;
    CircleImageView imgProfilePic;
    Uri imageUri;
    String gender = "";


    DatabaseHelper databaseHelper;

    String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_networking, container, false);

        databaseHelper = new DatabaseHelper(getContext());

        edt_username = root.findViewById(R.id.edt_username);
        edt_email = root.findViewById(R.id.edt_email);
        edt_password = root.findViewById(R.id.edt_passward);
        edt_confirm_password = root.findViewById(R.id.edt_confirm_passward);
        edt_mobileNo = root.findViewById(R.id.edt_mobileNo);

        radioGroup = root.findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

                if (checkedId == R.id.radio_female) {
                    gender = "Female";
                    Toast.makeText(getContext(), "Female", Toast.LENGTH_SHORT).show();

                } else if (checkedId == R.id.radio_male) {
                    gender = "Male";
                    Toast.makeText(getContext(), "Male", Toast.LENGTH_SHORT).show();

                }

            }
        });


        imgProfilePic = root.findViewById(R.id.profile_pic);

        imgProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                openCameraIntent();

/*              // This login will work for old version but not work on latest version more than android 7+
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                File photo = new File(Environment.getExternalStorageDirectory() ,"Pic.jpg");
                */
/*              intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
                imageUri = Uri.fromFile(photo);*//*


                imageUri = FileProvider.getUriForFile(getContext(),getContext().getPackageName() + ".fileprovider", photo);
                intent.setDataAndType(imageUri,null);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivityForResult(intent,100);
*/

            }
        });

        btnSubmit = root.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validateInputData()) {

                    RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                    Toast.makeText(getContext(), radioButton.getText(), Toast.LENGTH_SHORT).show();

                    Fragment fragment = new LoginFragment();
                    ((MainActivity) getContext()).replaceFragment(fragment, "Welcome page ");

                    insertIntoDB();

                }
            }
        });

        return root;
    }

    private void openCameraIntent() {


        File photo = new File(Environment.getExternalStorageDirectory(), "temp.png");//"temp.png");
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            Uri photoURI = FileProvider.getUriForFile(getActivity(), getActivity().getApplicationContext().getPackageName(), photo);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        } else {
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
        }
        startActivityForResult(cameraIntent, 100);

    }

    private void insertIntoDB() {

        NetworkPOJO networkPOJO = new NetworkPOJO(imageUri.toString(), edt_username.getText().toString(),
                edt_email.getText().toString(),
                edt_password.getText().toString(),
                edt_confirm_password.getText().toString(),
                edt_mobileNo.getText().toString(),
                gender
        );

        if (networkPOJO != null) {

            boolean isInserted = databaseHelper.insertData(networkPOJO);
            if (isInserted) {

                Toast.makeText(getContext(), "User Added successfully", Toast.LENGTH_LONG).show();
                Log.d("test", "Image Url :" + networkPOJO.getProfilePic_URL() +
                        " Username :" + networkPOJO.getUsername() +
                        " Email :" + networkPOJO.getEmail() +
                        " Password :" + networkPOJO.getPassword() +
                        " Confirm password :" + networkPOJO.getPassword() +
                        " Mobile :" + networkPOJO.getMobileNo() +
                        " Gender :" + networkPOJO.getGender());
            } else {

                Toast.makeText(getContext(), "User not Added ", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 100) {

            Bitmap bitmap = null;

            File selected = new File(Environment.getExternalStorageDirectory(), "temp.png");
//            Log.e("requestCode", "" + CODE);
            bitmap = BitmapFactory.decodeFile(selected.getAbsolutePath());
//            Log.e("requestCode", "" + CODE);
//                bitmap = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth(),0.4), (int) (bitmap.getHeight()  0.4), true);
//            Log.e("requestCode", "" + CODE);
            String rpath = getRealPathFromURI(getActivity(), getImageUri(getActivity(), bitmap), requestCode + "");


            if (rpath != null) {

                imgProfilePic.setImageBitmap(bitmap);
            }


        }

/*

        switch (requestCode){

            case 100:
                if (resultCode == Activity.RESULT_OK){

                    Uri selectedImage = imageUri;
                    getActivity().getContentResolver().notifyChange(selectedImage,null);
                    ContentResolver cr = getActivity().getContentResolver();

                    Bitmap bitmap=null;

                    File selected = new File(Environment.getExternalStorageDirectory(), "temp.png");
//            Log.e("requestCode", "" + CODE);
                    bitmap = BitmapFactory.decodeFile(selected.getAbsolutePath());
//            Log.e("requestCode", "" + CODE);
                    bitmap = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth()  0.4), (int) (bitmap.getHeight()  0.4), true);
//            Log.e("requestCode", "" + CODE);
                    String rpath = getRealPathFromURI(getActivity(), getImageUri(getActivity(), bitmap), requestCode + "");
*//*
                    Bitmap bitmap;

                    try {
                        bitmap = android.provider.MediaStore.Images.Media.getBitmap(cr, selectedImage);
                        imgProfilePic.setImageBitmap(bitmap);

                        //Toast.makeText(getActivity(), selectedImage.toString(),Toast.LENGTH_LONG).show();

                    } catch (Exception e) {

                        Toast.makeText(getActivity(), "Failed to load", Toast.LENGTH_SHORT).show();
                        Log.e("Camera", e.toString());
                    }*//*
            }
        }*/
    }

    public static String getRealPathFromURI(Context context, Uri contentUri, String name) {
        Cursor cursor = null;
        try {
            //Log.e("name", name);
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            //Log.e("path", path);
            File dir = new File(Environment.getExternalStorageDirectory() + "/profileTempImg");
            if (!dir.exists())
                dir.mkdirs();
            String rpath = Environment.getExternalStorageDirectory() + "/profileTempImg/" + (name.contains("sign") ? name : "evidence" + name) + ".jpg";
            //Log.e("rpath", rpath);
            new File(path).renameTo(new File(rpath));
            return rpath;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public static Uri getImageUri(Context context, Bitmap inImage) {
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "profilePic", null);
        //Log.e("path", path);
        return Uri.parse(path);
    }

    private boolean validateInputData() {

        if (edt_username.getText().toString().length() == 0) {
            edt_username.requestFocus();
            edt_username.setError("Enter valid Username");
            return false;
        }
        if (!edt_email.getText().toString().matches(EMAIL_PATTERN)) {
            edt_email.requestFocus();
            edt_email.setError("Enter valid Email");
            return false;
        }
        if (!edt_password.getText().toString().matches(PASSWORD_PATTERN)) {
            edt_password.requestFocus();
            edt_password.setError("Enter valid Password");
            return false;
        }

        if (!edt_password.getText().toString().equals(edt_confirm_password.getText().toString())) {
            edt_confirm_password.requestFocus();
            edt_confirm_password.setError("Password do not match");
            return false;
        }

        if (edt_mobileNo.getText().toString().length() != 10) {
            edt_mobileNo.requestFocus();
            edt_mobileNo.setError("Enter valid mobile number");
            return false;
        }

        return true;
    }
}