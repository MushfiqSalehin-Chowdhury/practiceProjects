package com.example.mushfiq.practiceprojects;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
    }

    public void changePicture(View view) {

        ImageView iv= (ImageView) findViewById(R.id.imageView);
        iv.setImageResource(R.drawable.car2);

    }
}

