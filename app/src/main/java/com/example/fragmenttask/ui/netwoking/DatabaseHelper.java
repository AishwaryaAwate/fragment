package com.example.fragmenttask.ui.netwoking;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper  extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "USER_DATABASE";
    public static final String TABLE_NAME = "USER";


    public static final String  COL_1 = "ID";
    public static final String  COL_2 = "PROFILE_PIC";
    public static final String  COL_3 = "USERNAME";
    public static final String  COL_4 = "EMAIL";
    public static final String  COL_5 = "PASSWORD";
    public static final String  COL_6 = "MOBILE_NO";
    public static final String  COL_7 = "GENDER";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {

            db.execSQL( "create table if not exists USER (\n" +
                    "ID int primary key,\n" +
                    "PROFILE_PIC text ,\n" +
                    "USERNAME text , \n" +
                    "EMAIL text  ,\n " +
                    "PASSWORD text ,\n " +
                    "MOBILE_NO text(10),\n " +
                    "GENDER text(10) " +
                    " );"
            );

            Log.d("test","database created :" );
        }
        catch (Exception e){
            e.printStackTrace();
            Log.d("test","database not created :" );

        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {


        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(NetworkPOJO networkPOJO){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,networkPOJO.getProfilePic_URL());
        contentValues.put(COL_3,networkPOJO.getUsername());
        contentValues.put(COL_4,networkPOJO.getEmail());
        contentValues.put(COL_5,networkPOJO.getPassword());
        contentValues.put(COL_6,networkPOJO.getMobileNo());
        contentValues.put(COL_7,networkPOJO.getGender());


        long result = database.insert(TABLE_NAME,null,contentValues);
        Log.i("test","Insert values :: "+result);

        if (result ==-1){
            return false;
        }

        return true;

    }





}
