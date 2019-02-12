package com.example.mushfiq.practiceprojects;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class FirebaseActivity extends AppCompatActivity {

    EditText name,email;
    TextView tv;

    DatabaseReference dbref,myref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        name = (EditText)findViewById(R.id.Name);
        email =(EditText)findViewById(R.id.Email);
        tv= (TextView)findViewById(R.id.Details);
        dbref = FirebaseDatabase.getInstance().getReference();
       // myref= dbref.child();
    }

    public void SaveData(View view) {
        Map<String,String> value = new HashMap<>();

        value.put(name.getText().toString(),email.getText().toString());

        dbref.child("Demo").push().setValue(value);
        Toast.makeText(this,"Saved", Toast.LENGTH_SHORT).show();

    }

    public void ShowData(View view) {


      dbref.orderByKey().addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

              for (DataSnapshot child : dataSnapshot.getChildren()) {
                  String name= child.getValue().toString();
                 tv.setText(name);
              }
          }

          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {

          }
      });

    }
}
