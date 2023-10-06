package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {

    EditText editTextNumber1;
    EditText editTextNumber2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumber1 = findViewById(R.id.editTextNumber1);
        editTextNumber2 = findViewById(R.id.editTextNumber2);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.calculation_options,
                android.R.layout.simple_spinner_item
        );
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        spinner.setAdapter(adapter);

        Button calculateButton = findViewById(R.id.calculateButton);

        /*
         * Suggestion to solve inputfield2 to invisisible.
         * Delete inputfield number 2 when selected specific operations in spinner,
         * that only requires one input field. Had to move EditText-Objects to reach them.
         */
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                
                String id = String.valueOf(spinner.getSelectedItem());

                // Compares the above string id with resource-string.
                // Not the best solution, but works.
                if(id.equals("Square Root") || id.equals("Procent")) {
                    int fillInput2 = 0;
                    editTextNumber2.setText(String.valueOf(fillInput2));
                    editTextNumber2.setVisibility(View.INVISIBLE);
                } else {
                    editTextNumber2.setVisibility(View.VISIBLE);
                }
            }

            // By default, both inputfields should show.
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                editTextNumber2.setVisibility(View.VISIBLE);
            }
        });


        calculateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                TextView resultTextView = findViewById(R.id.resultTextView);


                double num1 = Double.parseDouble(editTextNumber1.getText().toString());
                double num2 = Double.parseDouble(editTextNumber2.getText().toString());

                String calculationOption = spinner.getSelectedItem().toString();

                double result = 0.0;

                switch (calculationOption) {
                    case "Addition":
                        result = num1 + num2;
                        break;
                    case "Subtraction":
                        result = num1 - num2;
                        break;
                    case "Division":
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            resultTextView.setText("Cannot divide by zero");
                            return;
                        }
                        break;
                    case "Multiplication":
                        result = num1 * num2;
                        break;
                    case "Square Root":
                        //editTextNumber2.setVisibility(View.INVISIBLE);
                        if (num1 >= 0) {
                            result = Math.sqrt(num1); // Räkna ut kvadratroten av num1
                        } else {
                            resultTextView.setText("Invalid input");
                            return;
                        }
                        // Lägg till fler beräkningar här...
                        break;
                    case "Procent":
                        if (num1 >= 0) {
                            result = num1 / 100; //samma som (x/y)*100??
                        } else {
                            resultTextView.setText("Invalid input");
                            return;
                        }
                        break;
                    case "Pythagorean theorem":
                        //   result = (( num1 * num1) + (num2 * num2))^0.5;
                        //  double result = Math.sqrt(Math.pow(num1, 2) + Math.pow(num2, 2));
                        //Math.sqrt(result = (( num1 * num1) + (num2 * num2));
                        result = Math.sqrt((num1 * num1) + (num2 * num2));
                        break;
                    case "Area of the circle":
                        calculateCircleArea(resultTextView);
                        break;
                    default: //This line should never being reached but added just for safety
                        break;
                }
                resultTextView.setText("Resultat: " + result);
            }
        });
    }

    private void calculateCircleArea(TextView resultTextView) {
        BreakIterator radiusEditText = null;
        String radiusStr = radiusEditText.getText().toString();

        if (!radiusStr.isEmpty()) {
            double radius = Double.parseDouble(radiusStr);
            double area = Math.PI * Math.pow(radius, 2);
            resultTextView.setText("Cirkelns area är: " + area);
        } else {
            resultTextView.setText("Ange en radie för att beräkna cirkelns area.");
        }
    }
}
