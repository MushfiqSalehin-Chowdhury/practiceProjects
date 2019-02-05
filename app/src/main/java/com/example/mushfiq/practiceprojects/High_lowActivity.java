package com.example.mushfiq.practiceprojects;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.EditText;
import android.widget.*;

import org.w3c.dom.Text;

import java.security.PublicKey;
import java.util.Random;

public class High_lowActivity extends AppCompatActivity {

    int number, number2;
    TextView tv, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_low);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv = (TextView) findViewById(R.id.tv);
        Random rand = new Random();
        number = rand.nextInt(50);
        number2 = rand.nextInt(100);
        tv.setText("ACTUAL NUMBER ; " + Integer.toString(number));
        tv2.setText("Test Number : " + Integer.toString(number2));
    }

    public void Higher(View view) {
        if (number < number2) {
            Toast.makeText(this, "Correct ", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Wrong!!! Try again... ", Toast.LENGTH_SHORT).show();
        Random rand = new Random();
        number = rand.nextInt(50);
        number2 = rand.nextInt(100);
        tv.setText("ACTUAL NUMBER ; " + Integer.toString(number));
        tv2.setText("Test Number : " + Integer.toString(number2));
    }

    public void Lower(View view) {
        if (number > number2) {
            Toast.makeText(this, "Correct ", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Wrong!!! Try again... ", Toast.LENGTH_SHORT).show();
        Random rand = new Random();
        number = rand.nextInt(50);
        number2 = rand.nextInt(100);
        tv.setText("ACTUAL NUMBER ; " + Integer.toString(number));
        tv2.setText("Test Number : " + Integer.toString(number2));
    }
}
