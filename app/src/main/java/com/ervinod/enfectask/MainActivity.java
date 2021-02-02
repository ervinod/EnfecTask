package com.ervinod.enfectask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ervinod.enfectask.adapter.UserAdapter;
import com.ervinod.enfectask.databinding.ActivityMainBinding;
import com.ervinod.enfectask.models.Post;
import com.ervinod.enfectask.models.User;
import com.ervinod.enfectask.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    UserAdapter adapter;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    ArrayList<User> userArrayList;
    ArrayList<Post> postArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new UserAdapter();
        recyclerView.setAdapter(adapter);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        adapter.setOnItemClickListener(dog -> {

        });

        getUserData();

    }

    //getting user data from API
    private void getUserData() {
        //observer will listen to live data
        userViewModel.getUserData().observe(this, userList -> {
            if(userList!=null){
                progressBar.setVisibility(View.GONE);
                if(userList.size()>0){
                    userArrayList = (ArrayList<User>)userList;
                    getPostData();
                }else{
                    Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    //getting post data from API
    private void getPostData() {
        //observer will listen to live data
        userViewModel.getPostData().observe(this, postList -> {
            postArrayList  = (ArrayList<Post>)postList;
            getPostByUser();
        });
    }

    private void getPostByUser(){
        for(int i=0; i<userArrayList.size(); i++){
            User user = userArrayList.get(i);
            for(int j=0; j<postArrayList.size(); j++){
                    Post post = postArrayList.get(j);
                    if(user.getId() == post.getId()){
                        userArrayList.get(i).setTitle(post.getTitle());
                        userArrayList.get(i).setBody(post.getBody());
                    }
            }
            adapter.setUserList(userArrayList);

        }
    }
}