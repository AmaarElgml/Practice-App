package com.example.simpleapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.simpleapp.adapters.UsersAdapter;
import com.example.simpleapp.databinding.ActivityMainBinding;
import com.example.simpleapp.pojo.UserModel;
import com.example.simpleapp.viewmodels.UserViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private UserViewModel userViewModel;
    private UsersAdapter usersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ((BaseApplication) getApplication()).getAppComponent().inject(this);

        init();
        getAllUsers();
    }

    private void init() {
        userViewModel = new ViewModelProvider(this, viewModelFactory).get(UserViewModel.class);

        // bind RecyclerView
        RecyclerView recyclerView = activityMainBinding.usersRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        usersAdapter = new UsersAdapter();
        recyclerView.setAdapter(usersAdapter);
    }

    private void getAllUsers() {
        userViewModel.getAllUsersMutableLiveData().observe(this, new Observer<List<UserModel>>() {
            @Override
            public void onChanged(List<UserModel> userModels) {
                usersAdapter.setUsersList((ArrayList<UserModel>) userModels);
            }
        });
    }

}