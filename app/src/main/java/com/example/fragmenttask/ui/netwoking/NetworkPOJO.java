package com.example.fragmenttask.ui.netwoking;

import com.example.fragmenttask.CircleImageView;

public class NetworkPOJO {
    String  profilePic_URL, username, email, password, confirm_password,mobileNo,gender;


    public NetworkPOJO(String profilePic_URL, String username, String email, String password, String confirm_password, String mobileNo,String gender) {
        this.profilePic_URL = profilePic_URL;
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirm_password = confirm_password;
        this.mobileNo = mobileNo;
        this.gender = gender;

    }


    public String getProfilePic_URL() {
        return profilePic_URL;
    }

    public void setProfilePic_URL(String profilePic_URL) {
        this.profilePic_URL = profilePic_URL;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
