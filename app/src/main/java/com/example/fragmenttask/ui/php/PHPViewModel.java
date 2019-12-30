package com.example.fragmenttask.ui.php;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PHPViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PHPViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is php fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}