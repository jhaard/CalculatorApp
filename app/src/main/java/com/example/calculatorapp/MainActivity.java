package com.example.calculatorapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editTextNumber1;
    private EditText editTextNumber2;

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


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //när ngn väljr ngt i spinenr
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {//när använadre väljer ett item i spinner så körs denna kod
                String calculationOption = parent.getItemAtPosition(position).toString();//får calculationOption som string
                switch (calculationOption) {
                    case "Square Root":
                        editTextNumber2.setVisibility(View.INVISIBLE); //om man väljer roten ur så blir input 2 osyling
                        break;
                    case "Area of the circle":
                        editTextNumber2.setVisibility(View.INVISIBLE);
                        break;
                    default:
                        //The rest of the calculations need both inputs
                        editTextNumber1.setVisibility(View.VISIBLE);
                        editTextNumber2.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //nothing to do
            }
        });

        Button calculateButton = findViewById(R.id.calculateButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TextView resultTextView = findViewById(R.id.resultTextView);

                String inputValue1 = "0";
                String inputValue2 = "0";

                inputValue1 = String.valueOf(editTextNumber1.getText());
                inputValue2 = String.valueOf(editTextNumber2.getText());

                double num1 = 0;
                if (!inputValue1.isEmpty()) {
                    num1 = Double.parseDouble(inputValue1);
                }

                double num2 = 0;
                if (!inputValue2.isEmpty()) {
                    num2 = Double.parseDouble(inputValue2);
                }

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
                        if (num1 >= 0) {
                            result = Math.sqrt(num1); // Räkna ut kvadratroten av num1
                        } else {
                            resultTextView.setText("Invalid input");
                            return;
                        }

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
                        result = Math.sqrt((num1 * num1) + (num2 * num2));
                    case "Area of the circle":
                        result = Math.PI * Math.pow(num1, 2);
                        break;
                    default: //This line should never being reached but added just for safety
                        break;
                    case "Cylinder Volym":
                        result = Math.PI * Math.pow(num1, 2) * num2;
                        break;



//                        break;
//                    case "Area of the circle":
//                        calculateCircleArea(resultTextView);
//                        break;
//                    default: //This line should never being reached but added just for safety
//                        break;
               }

                resultTextView.setText("Resultat: " + result);
            }
        });
    }


    private void calculateCircleArea(TextView resultTextView) {
        String radiusStr = editTextNumber1.getText().toString();

        if (!radiusStr.isEmpty()) {
            double radius = Double.parseDouble(radiusStr);
            double area = Math.PI * Math.pow(radius, 2);
            resultTextView.setText("Cirkelns area är: " + area);
        } else {
            resultTextView.setText("Ange en radie för att beräkna cirkelns area.");
        }

    }

}
