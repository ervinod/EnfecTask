package com.ervinod.enfectask.api;

import com.ervinod.enfectask.models.Post;
import com.ervinod.enfectask.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRequestData {

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("users")
    Call<List<User>> getUsers();
}
