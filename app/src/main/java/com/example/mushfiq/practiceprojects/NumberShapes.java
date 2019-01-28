package com.example.mushfiq.practiceprojects;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NumberShapes extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_shapes);
    }

        class TestNumber {
        int number,x;

            public boolean isTriangular() {
                int x = 1;
                int triangular = 1;

                while (triangular < number) {
                    x++;
                    triangular = triangular + x;
                }
                if (triangular == number) {
                    return true;
                } else
                    return false;

            }
            public boolean isSquare() {

                double squareRoot = Math.sqrt(number);

                if (squareRoot == Math.floor(squareRoot)) {

                    return true;

                } else {

                    return false;

                }

            }
        }

        public void isTriangular(View view) {

            EditText ed = (EditText) findViewById(R.id.editText1);

            if (ed.getText().toString().isEmpty()) {

                Toast.makeText(this, "Please Enter a number", Toast.LENGTH_SHORT).show();


            } else {

                TestNumber tn = new TestNumber();

                tn.number= Integer.parseInt(ed.getText().toString());

                if (tn.isTriangular()) {
                    if (tn.isSquare()) {
                        Toast.makeText(this, "Both", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(this, "Triangular but not Square", Toast.LENGTH_SHORT).show();

                } else if (tn.isSquare()) {
                    Toast.makeText(this, "Square but Not Triangular", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(this, "Neither Any", Toast.LENGTH_SHORT).show();


            }
        }

}



