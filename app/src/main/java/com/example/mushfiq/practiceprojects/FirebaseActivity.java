package com.example.mushfiq.practiceprojects;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
public class FirebaseActivity extends AppCompatActivity{

    EditText name,email;
    TextView tv;
    int a=0;
    private List <User> usersList= new ArrayList<>();
    private RecyclerView recyclerView;
    private UserAdapter mAdapter;
    RecyclerView.LayoutManager mlayoutManager;
    Random rand;
    DatabaseReference dbref,myref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);
        recyclerView=(RecyclerView) findViewById(R.id.recycle);
        RecyclerView.LayoutManager mlayoutManager= new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        name = (EditText)findViewById(R.id.Name);
        email =(EditText)findViewById(R.id.Email);
        dbref = FirebaseDatabase.getInstance().getReference("Users");

    }
    public void SaveData(View view) {
        usersList.clear();
        a=0;
        User user=  new User(name.getText().toString(),email.getText().toString());
        dbref.push().setValue(user);
        Toast.makeText(this,"Saved", Toast.LENGTH_SHORT).show();
    }
    public void ShowData(View view) {
      dbref.orderByKey().addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              for (DataSnapshot child : dataSnapshot.getChildren()) {
                  User usr = new User("User"+a++,child.getValue().toString());
                  usersList.add(usr);
                  mAdapter=new UserAdapter(usersList);
                  recyclerView.setAdapter(mAdapter);
                  mAdapter.notifyDataSetChanged();
              }
          }
          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {
          }
      });

    }
}
