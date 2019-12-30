package com.example.fragmenttask.ui.android;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.net.URL;

public class AndroidPojo implements Parcelable {

    String name,team,createdby,publisher,imageurl,bio;

/*    public AndroidPojo(String name, String createdby, String imageurl) {
        this.name = name;
        this.createdby = createdby;
        this.imageurl = imageurl;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }


    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
