package com.ervinod.enfectask;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ervinod.enfectask.adapter.UserAdapter;
import com.ervinod.enfectask.databinding.ActivityMainBinding;
import com.ervinod.enfectask.models.Post;
import com.ervinod.enfectask.models.User;
import com.ervinod.enfectask.viewmodel.UserViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    UserAdapter adapter;
    ArrayList<User> userArrayList;
    ArrayList<Post> postArrayList;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //binding view
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);

        adapter = new UserAdapter();
        binding.recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(user -> {
            //handle item click here
        });

        //get user list from API
        getUserData();

    }

    //getting user data from API
    private void getUserData() {
        //observer will listen to live data
        userViewModel.getUserData().observe(this, userList -> {
            if (userList != null) {
                binding.progress.setVisibility(View.GONE);
                if (userList.size() > 0) {
                    userArrayList = (ArrayList<User>) userList;
                    getPostData();
                } else {
                    Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    //getting post data from API
    private void getPostData() {
        //observer will listen to live data
        userViewModel.getPostData().observe(this, postList -> {
            postArrayList = (ArrayList<Post>) postList;
            getPostByUser();
        });
    }

    //getting matched post for each user
    private void getPostByUser() {
        for (int i = 0; i < userArrayList.size(); i++) {
            User user = userArrayList.get(i);
            for (int j = 0; j < postArrayList.size(); j++) {
                Post post = postArrayList.get(j);
                if (user.getId() == post.getId()) {
                    userArrayList.get(i).setTitle(post.getTitle());
                    userArrayList.get(i).setBody(post.getBody());
                }
            }
            adapter.setUserList(userArrayList);

        }
    }
}