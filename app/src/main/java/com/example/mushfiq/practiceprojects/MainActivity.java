package com.example.mushfiq.practiceprojects;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
        startActivity(new Intent(MainActivity.this, High_lowActivity.class));
    }
    public void NumberShape(View view) {
        startActivity(new Intent(MainActivity.this, NumberShapesActivity.class));
    }
    public void connectGame(View view) {
            startActivity(new Intent(MainActivity.this,ConnectGameActivity.class));
    }

    public void camera(View view) {
        startActivity(new Intent(MainActivity.this,cameraActivity.class));
    }

    public void playVideo(View view) {
        startActivity(new Intent(MainActivity.this,VideoActivity.class));
    }

    public void QuickCalc(View view) {
        startActivity(new Intent(MainActivity.this,QuickCalc.class));
    }

    public void Location(View view) {
        startActivity(new Intent(MainActivity.this,MapsActivity.class));
    }

    public void Download(View view) {
        startActivity(new Intent(MainActivity.this, FirebaseActivity.class));
    }
}
