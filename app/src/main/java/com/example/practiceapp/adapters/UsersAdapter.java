package com.example.simpleapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpleapp.R;
import com.example.simpleapp.databinding.UserItemBinding;
import com.example.simpleapp.pojo.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {
    private List<UserModel> usersList = new ArrayList<>();

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserItemBinding userItemBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.user_item, parent, false);
        return new UsersViewHolder(userItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder usersViewHolder, int position) {
        UserModel userModel = usersList.get(position);
        usersViewHolder.userItemBinding.setUser(userModel);
    }

    @Override
    public int getItemCount() {
        if (usersList != null) {
            return usersList.size();
        } else {
            return 0;
        }
    }

    public void setUsersList(ArrayList<UserModel> users) {
        this.usersList = users;
        notifyDataSetChanged();
    }

    static class UsersViewHolder extends RecyclerView.ViewHolder {
        private UserItemBinding userItemBinding;

        public UsersViewHolder(@NonNull UserItemBinding userItemBinding) {
            super(userItemBinding.getRoot());

            this.userItemBinding = userItemBinding;
        }
    }

}