package com.example.mushfiq.practiceprojects;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyviewHolder> {
    private List<User> userList;
    public class MyviewHolder extends RecyclerView.ViewHolder{
        public TextView name,email;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.Namee);
            email=(TextView)itemView.findViewById(R.id.Emaill);
        }
    }
    public UserAdapter (List<User> userList){
        this.userList=userList;
    }
    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType ) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_list,viewGroup,false);
        return new MyviewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull MyviewHolder myviewHolder, int position) {

        User user = userList.get(position);
        myviewHolder.name.setText(user.getName());
        myviewHolder.email.setText(user.getEmail());
    }
    @Override
    public int getItemCount() {
        return userList.size();
    }


}
