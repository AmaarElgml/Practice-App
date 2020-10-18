package com.example.simpleapp.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.simpleapp.pojo.UserModel;
import com.example.simpleapp.repositories.UserRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class UserViewModel extends ViewModel {
    private static final String TAG = "//UserViewModel";

    private UserRepository userRepository;
    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<UserModel> userModelMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<UserModel>> usersListMutableLiveData = new MutableLiveData<>();

    @Inject
    public UserViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public MutableLiveData<UserModel> getUserModelMutableLiveData() {
        loadUser();
        return userModelMutableLiveData;
    }

    public MutableLiveData<List<UserModel>> getAllUsersMutableLiveData() {
        loadAllUsers();
        return usersListMutableLiveData;
    }

    private void loadUser() {
        disposable.add(userRepository
                .getUserModelSingle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<UserModel>() {
                    @Override
                    public void onSuccess(UserModel userModel) {
                        getUserModelMutableLiveData().setValue(userModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError" + e.getMessage());
                    }
                })
        );
    }

    private void loadAllUsers() {
        userRepository.getAllUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<UserModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(List<UserModel> usersList) {
                        getAllUsersMutableLiveData().setValue(usersList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError " + e.getMessage());
                    }
                });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
        }
    }
}
