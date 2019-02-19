package com.example.mushfiq.practiceprojects;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
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
    int a=1;
    private List <User> usersList= new ArrayList<>();
    private RecyclerView recyclerView;
    private UserAdapter mAdapter;
    private  final int space = 20;
    RecyclerView.LayoutManager mlayoutManager;

    DBHelper dbHelper;

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
        dbHelper= new DBHelper(this);
        tv= (TextView)findViewById(R.id.showdb);

    }
    public void SaveData(View view) {
        usersList.clear();
        a=1;
        String nam= name.getText().toString();
        String eml= email.getText().toString();

        if ( !isValidEmail(eml)){
            Toast.makeText(this, "Enter Valid Email", Toast.LENGTH_LONG).show();
        }

        else if (TextUtils.isEmpty(nam)){
            Toast.makeText(this, "Name Required ", Toast.LENGTH_LONG).show();
        }

        else{
            User user=  new User(nam,eml);
            dbref.push().setValue(user);
            Toast.makeText(this,"Saved", Toast.LENGTH_SHORT).show();

        }
    }
    public void ShowData(View view) {
        tv.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
      dbref.orderByKey().addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              for (final DataSnapshot child : dataSnapshot.getChildren()) {
                  String nm = child.child("name").getValue().toString();
                  String em = child.child("email").getValue().toString();
                  User usr = new User(nm,"Email : "+em+"\n");
                  usersList.add(usr);
                  mAdapter=new UserAdapter(usersList);
                  recyclerView.setAdapter(mAdapter);
                  mAdapter.notifyDataSetChanged();
              }
              recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                  @Override
                  public void onClick(View view, int position) {
                      User user = usersList.get(position);
                      Toast.makeText(getApplicationContext(), user.getName() + "\n" +"Tap and hold to delete", Toast.LENGTH_SHORT).show();
                  }
                  @Override
                  public void onLongClick(View view, int position) {
                      final User user = usersList.get(position);
                      dbref.orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
                          @Override
                          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                              for (final DataSnapshot ds : dataSnapshot.getChildren())
                              {
                                  if (ds.child("name").getValue().toString() == user.getName()) {
                                      ds.getRef().setValue(null);
                                      Toast.makeText(FirebaseActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                                      usersList.remove(user);
                                      mAdapter=new UserAdapter(usersList);
                                      recyclerView.setAdapter(mAdapter);
                                      mAdapter.notifyDataSetChanged();
                                      usersList.clear();
                                  }
                              }
                          }
                          @Override
                          public void onCancelled(@NonNull DatabaseError databaseError) {
                          }
                      });
                  }
              }));
          }
          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {
          }
      });
    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public void savetoDatabase(View view) {
        String nam= name.getText().toString();
        String eml= email.getText().toString();
        dbHelper.addData(nam,eml);
    }

    public void showFromDB(View view) {
        recyclerView.setVisibility(View.INVISIBLE);
        dbHelper.showData();
        tv.setVisibility(View.VISIBLE);
        tv.setText(dbHelper.a.toString());
    }
}
