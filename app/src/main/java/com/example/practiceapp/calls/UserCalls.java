package com.example.simpleapp.calls;

import com.example.simpleapp.pojo.UserModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface UserCalls {

    /* Get only user 2 from the API  */
    @GET("/users/2")
    Single<UserModel> getUserModel();

    /* Get All users from the API  */
    @GET("/users")
    Single<List<UserModel>> getAllUsers();

}
