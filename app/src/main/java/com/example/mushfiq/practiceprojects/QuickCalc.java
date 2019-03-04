package com.example.mushfiq.practiceprojects;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class QuickCalc extends AppCompatActivity {
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int positions,score=0;
    TextView tv1,tv2,tv3,tv4;
    Button button ;
    Button button1 ;
    Button button2 ;
    Button button3,button4 ;
    GridLayout grid;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_calc);
        tv1= findViewById(R.id.sum);
        tv2= findViewById(R.id.point);
        tv3=findViewById(R.id.correct);
        tv4=findViewById(R.id.timer);
         button = (Button)findViewById(R.id.btn);
         button1 = (Button)findViewById(R.id.btn1);
         button2 = (Button)findViewById(R.id.btn2);
        button3 = (Button)findViewById(R.id.btn3);
        button4 = (Button)findViewById(R.id.playAgain);
        grid = findViewById(R.id.grid);
        linearLayout= findViewById(R.id.linear);
        NewQuestionSet();

    }
    public void Start(View view) {
        linearLayout.setVisibility(View.INVISIBLE);
        grid.setVisibility(View.VISIBLE);
        tv1.setVisibility(View.VISIBLE);
        new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv4.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                button4.setVisibility(View.VISIBLE);
                tv3.setText( "YOUR SCORE IS : "+ score);
                grid.setVisibility(View.INVISIBLE);
            }
        }.start();
    }
    public void ChooseAnswer(View view) {
        if (view.getTag().toString().equals(Integer.toString(positions)))
        {
            score++;
            tv2.setText(Integer.toString(score));
            tv3.setText("Correct");
        }else {
            score--;
            tv2.setText(Integer.toString(score));
        tv3.setText("Wrong");
        }
        NewQuestionSet();
    }
    public void NewQuestionSet (){
        answers.clear();
        Random rand = new Random();
        int a= rand.nextInt(100);
        int b=  rand.nextInt(100);
        String arr[]={"+","-"};
        int select= rand.nextInt(arr.length);
        tv1.setText(Integer.toString(a)+ arr[select] + Integer.toString(b));
        positions = rand.nextInt(4);
        int incorrect ;
        for(int i=0; i<4 ; i++ ){

            if (i==positions){
                if(arr[select]=="+") {
                    answers.add(a + b);
                }
                if(arr[select]=="-") {
                    answers.add(a - b);
                }
            }else {
                incorrect = rand.nextInt(101);
                while (incorrect==a+b || incorrect==a-b){
                    incorrect= rand.nextInt(101);
                }
                answers.add(incorrect);
            }
        }
        button.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void PlayAgain(View view) {
        Intent it= getIntent();
        finish();;
        startActivity(it);

    }
}
