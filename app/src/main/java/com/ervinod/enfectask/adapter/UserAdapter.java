package com.ervinod.enfectask.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ervinod.enfectask.R;
import com.ervinod.enfectask.databinding.ItemUserBinding;
import com.ervinod.enfectask.models.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private ArrayList<User> userArrayList;
    private OnItemClickListener listener;

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemUserBinding itemDogBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_user, parent, false);
        return new UserViewHolder(itemDogBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userArrayList.get(position);
        holder.itemUserBinding.setUser(user);
    }

    @Override
    public int getItemCount() {
        if (userArrayList != null) {
            return userArrayList.size();
        } else {
            return 0;
        }
    }

    //set user list
    public void setUserList(ArrayList<User> users) {
        this.userArrayList = users;
        notifyDataSetChanged();
    }

    public User getCurrentItemAt(int position) {
        return userArrayList.get(position);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private ItemUserBinding itemUserBinding;

        public UserViewHolder(@NonNull ItemUserBinding itemUserBinding) {
            super(itemUserBinding.getRoot());
            this.itemUserBinding = itemUserBinding;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        //handling user click
                        listener.onItemClick(getCurrentItemAt(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(User user);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
