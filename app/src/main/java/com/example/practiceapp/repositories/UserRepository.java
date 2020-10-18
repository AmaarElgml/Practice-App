package com.example.simpleapp.repositories;

import com.example.simpleapp.calls.UserCalls;
import com.example.simpleapp.pojo.UserModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class UserRepository {

    private UserCalls userCalls;

    @Inject
    public UserRepository(UserCalls userCalls) {
        this.userCalls = userCalls;
    }

    public Single<UserModel> getUserModelSingle() {
        return userCalls.getUserModel();
    }

    public Single<List<UserModel>> getAllUsers() {
        return userCalls.getAllUsers();
    }

}
