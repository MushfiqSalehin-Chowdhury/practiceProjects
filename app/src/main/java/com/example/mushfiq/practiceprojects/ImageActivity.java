package com.example.mushfiq.practiceprojects;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class ImageActivity extends AppCompatActivity {
    int a=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
    }

    public void changePicture(View view) {

        ImageView iv= (ImageView) findViewById(R.id.imageView);
        ImageView iv2=(ImageView) findViewById(R.id.imageView2);

        if (a==0){ ;
            iv.animate().translationXBy(1000f).setDuration(1000);
            iv.animate().scaleX(0.5f).scaleY(0.5f).setDuration(1000);
            iv2.animate().alpha(1f).setDuration(1000);

            a=1;
        }
        else {
            iv.animate().scaleXBy(0.5f).scaleYBy(0.5f).setDuration(1000);
            iv.animate().translationXBy(-1000f).setDuration(1000);
            iv2.animate().alpha(0f).setDuration(1000);
            a=0;
        }
    }
}

