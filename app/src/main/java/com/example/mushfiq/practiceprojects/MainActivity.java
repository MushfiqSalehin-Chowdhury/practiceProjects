package com.example.mushfiq.practiceprojects;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ImageProject(View view) {
        startActivity(new Intent(MainActivity.this,ImageActivity.class));

    }

    public void highLow(View view) {

        startActivity(new Intent(MainActivity.this,High_low.class));
    }


    public void NumberShape(View view) {
        startActivity(new Intent(MainActivity.this,NumberShapes.class));
    }
}
