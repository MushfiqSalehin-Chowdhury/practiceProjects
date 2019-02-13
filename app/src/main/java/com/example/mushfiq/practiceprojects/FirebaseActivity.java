package com.example.mushfiq.practiceprojects;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    List <String> users;
    Random rand;
    ArrayList<String> arrayList;
    DatabaseReference dbref,myref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);
        users = new ArrayList<>();
        rand = new Random();
        name = (EditText)findViewById(R.id.Name);
        email =(EditText)findViewById(R.id.Email);
        tv= (TextView)findViewById(R.id.Details);
        dbref = FirebaseDatabase.getInstance().getReference();
        arrayList= new ArrayList<>();
       // myref= dbref.child();
    }
    public void SaveData(View view) {
        Map<String, User> value = new HashMap<>();
        value.put("User"+a++, new User(name.getText().toString(),email.getText().toString()));
        dbref.push().setValue(value);
        Toast.makeText(this,"Saved", Toast.LENGTH_SHORT).show();
        tv.setVisibility(View.INVISIBLE);
        //tv.setText(" ");
       // arrayList.clear();
    }
    public void ShowData(View view) {
      dbref.orderByKey().addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              for (DataSnapshot child : dataSnapshot.getChildren()){
                  String name = child.getValue().toString();
                  arrayList.add(name);
                 // tv.setText(name);
                  User usr =  child.getValue(User.class);
              }
              Log.i("Users",arrayList.toString());
          }
          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {
          }
      });
        tv.setVisibility(View.VISIBLE);
        tv.setText(arrayList.toString());
        arrayList.clear();
    }
}
