package com.ervinod.enfectask.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.ervinod.enfectask.api.ApiRequestData;
import com.ervinod.enfectask.api.RetrofitClient;
import com.ervinod.enfectask.models.Post;
import com.ervinod.enfectask.models.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private MutableLiveData<List<Post>> postMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<User>> userMutableLiveData = new MutableLiveData<>();
    private Application application;

    public UserRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Post>> getPostData() {
        ApiRequestData apiService = RetrofitClient.getRetrofitServer();
        Call<List<Post>> call = apiService.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.body() != null) {
                    Log.d("API RESPONSE",""+response.body());
                    List<Post> userList = (List<Post>) response.body();
                    postMutableLiveData.setValue(userList);
                }
            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                //handle api error here like dismissing progress bar
            }
        });
        return postMutableLiveData;
    }


    public MutableLiveData<List<User>> getUserData() {
        ApiRequestData apiService = RetrofitClient.getRetrofitServer();
        Call<List<User>> call = apiService.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.body() != null) {
                    Log.d("API RESPONSE",""+response.body());
                    List<User> userList = (List<User>) response.body();
                    userMutableLiveData.setValue(userList);
                }
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                //handle api error here like dismissing progress bar
            }
        });
        return userMutableLiveData;
    }

}
