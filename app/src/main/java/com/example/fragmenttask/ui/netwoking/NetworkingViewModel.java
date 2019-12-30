package com.example.fragmenttask.ui.netwoking;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NetworkingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NetworkingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is networking fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}