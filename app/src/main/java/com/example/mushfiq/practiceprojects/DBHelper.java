package com.example.mushfiq.practiceprojects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DBHelper  extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users_db";

    SQLiteDatabase db;
    ArrayList <String> a = new ArrayList<>();

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, email VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    public void addData (String nam, String email){
        a.clear();

      try {
           db = this.getWritableDatabase();
           db.execSQL("INSERT INTO users (name,email) VALUES ('"+nam+"','"+email+"')");
           db.close();
      }catch (Exception e){
          Log.i("dberror",String.valueOf(e));
      }
    }
    public void showData (){
       try {
           db = this.getReadableDatabase();
           Cursor cursor = db.rawQuery("SELECT * FROM users",null);

           int nameIndex = cursor.getColumnIndex("name");
           int emailIndex = cursor.getColumnIndex("email");

           cursor.moveToFirst();
           while (cursor!=null){
               Log.i("nameeee",cursor.getString(nameIndex));
               Log.i("nameeee",cursor.getString(emailIndex));

               a.add(cursor.getString(nameIndex)+ "\n"+ cursor.getString(emailIndex)+"\n");
               cursor.moveToNext();
           }

           cursor.close();

       }catch (Exception e){
           Log.i("err",String.valueOf(e));
       }
    }
}
