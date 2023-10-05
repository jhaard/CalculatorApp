package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText firstNumber,secondNumber;
    Button additionButton, substractButton, multipleButton, divisionButton, squareRootButton;
    TextView resultText;

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstNumber = findViewById(R.id.firstNumber);
        secondNumber = findViewById(R.id.secondNumber);
        additionButton = findViewById(R.id.additionButton);
        substractButton = findViewById(R.id.subtractionButton);
        multipleButton = findViewById(R.id.multiplicationButton);
        divisionButton = findViewById(R.id.divisionButton);
        squareRootButton = findViewById(R.id.squareRootButton);
        resultText = findViewById(R.id.textViewResult);

        // Addition
        additionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((firstNumber.getText().length()>0) && (secondNumber.getText().length()>0))
                {
                    double firstN = Double.parseDouble(firstNumber.getText().toString());
                    double secondN = Double.parseDouble(secondNumber.getText().toString());
                    double result = firstN + secondN;
                    resultText.setText(Double.toString(result));
                }
            }
        });

        //Subtraction
        substractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((firstNumber.getText().length()>0) && (secondNumber.getText().length()>0))
                {
                    double firstN = Double.parseDouble(firstNumber.getText().toString());
                    double secondN = Double.parseDouble(secondNumber.getText().toString());
                    double result = firstN - secondN;
                    resultText.setText(Double.toString(result));
                }
            }
        });

        // Multiplication
        multipleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((firstNumber.getText().length()>0) && (secondNumber.getText().length()>0))
                {
                    double firstN = Double.parseDouble(firstNumber.getText().toString());
                    double secondN = Double.parseDouble(secondNumber.getText().toString());
                    double result = firstN * secondN;
                    resultText.setText(Double.toString(result));
                }
            }
        });

        // Division (need to upgrade!)
        divisionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((firstNumber.getText().length()>0) && (secondNumber.getText().length()>0))
                {
                    double firstN = Double.parseDouble(firstNumber.getText().toString());
                    double secondN = Double.parseDouble(secondNumber.getText().toString());
                    double result = firstN / secondN;
                    resultText.setText(Double.toString(result));
                }
                else{
                    if((firstNumber.getText().length()<0) || (secondNumber.getText().length()<0)) {
                        Toast toast= Toast.makeText(MainActivity.this,
                                "Enter The Required Numbers",Toast.LENGTH_LONG);
                        toast.show();
                    }
                    Toast toast= Toast.makeText(MainActivity.this,
                            "Enter The Required Numbers",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        // Square root
        squareRootButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((firstNumber.getText().length()>0) || (secondNumber.getText().length()>0))
                {
                    double firstN = Double.parseDouble(firstNumber.getText().toString());
                    //double secondN = Double.parseDouble(secondNumber.getText().toString());
                    double result = Math.sqrt(firstN);
                    resultText.setText(Double.toString(result));
                }
            }
        });

        // Square root
        squareRootButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondNumber.setVisibility(View.INVISIBLE);

                if((firstNumber.getText().length()>0) || (secondNumber.getText().length()>0))
                {
                    double firstN = Double.parseDouble(firstNumber.getText().toString());
                    //double secondN = Double.parseDouble(secondNumber.getText().toString());
                    double result = Math.sqrt(firstN);
                    resultText.setText(Double.toString(result));
                }
            }
        });


    }
}



