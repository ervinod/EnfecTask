package com.ervinod.enfectask.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.ervinod.enfectask.models.Post;
import com.ervinod.enfectask.models.User;
import com.ervinod.enfectask.repository.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    UserRepository repository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
    }

    public LiveData<List<User>> getUserData() {
        //call api in repo which will return livedata as list
        return repository.getUserData();
    }

    public LiveData<List<Post>> getPostData() {
        //call api in repo which will return livedata as list
        return repository.getPostData();
    }

}


