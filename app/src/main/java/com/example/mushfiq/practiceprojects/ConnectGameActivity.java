package com.example.mushfiq.practiceprojects;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class ConnectGameActivity extends AppCompatActivity {

    int x=0,position;
    int tapped[]= {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    FrameLayout layout = (FrameLayout) findViewById(R.id.againId);
    TextView tv=(TextView)findViewById(R.id.playAgain);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_game);

    }
    public void show(View view) {
        ImageView counter = (ImageView) view;
       // System.out.println(counter.getTag().toString());
        position = Integer.parseInt(counter.getTag().toString());
        if (tapped[position] == 2) {
            tapped [position]=x;
            if (x == 0) {
                counter.setImageResource(R.drawable.circle);
                x = 1;
            } else {
                counter.setImageResource(R.drawable.cross);
                x = 0;
            }
        }
        for (int[] winningPosition : winningPositions) {
            if (tapped[winningPosition[0]] == tapped[winningPosition[1]] &&
                    tapped[winningPosition[1]] == tapped[winningPosition[2]] &&
                    tapped[winningPosition[0]] != 2) {
                if (tapped[winningPosition[0]]==0){
                   // Toast.makeText(this, "Circle Wins", Toast.LENGTH_SHORT).show();
                   layout.setVisibility(View.VISIBLE);
                    tv.setText("Circle Wins !!!");
                }
                else{
                   //Toast.makeText(this, "Cross wins", Toast.LENGTH_SHORT).show();
                    layout.setVisibility(View.VISIBLE);
                    tv.setText("Cross Wins !!!");
                }
            }
        }
    }
    public void playAgain(View view) {
        Intent it = getIntent();
        finish();
        startActivity(it);


    }
}
