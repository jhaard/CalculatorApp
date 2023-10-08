
//Sebastian Svedberg
//Sara Sharif
//Okechukwu Nnoham
//Christopher Karlsson
//Juhee Kang Johansson
//Jörgen Hård
//Elin Andersson

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
    private EditText editTextNumber1; //Deklararer en field som pekar mot edit text Denna kan
    // användas i hela projektet för denna görs utanför måsvingarna. Skillnaden är att ett fält påverkar
    // allt i klassen och varje instans av den klassen. En variabel påverkar bara vad som finns i metoden
    // som den är definierad för. Med andra ord är deras omfattning avsevärt annorlunda.
    // Så kom ihåg att i Java betraktas alla fält som variabler men inte alla variabler är fält.
    private EditText editTextNumber2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //This activity is going to work with this xml-file

        //Initierar ett värde till denna varibel,
        // värdet här pekar mot editTextView i XML-filen:
        editTextNumber1 = findViewById(R.id.editTextNumber1);
        editTextNumber2 = findViewById(R.id.editTextNumber2);

        //Här deklarerar vi en spinner och initialiserar
        // på samma rad, detta pga att vi är i en metod, inom måsvingar. Denna pekar mot spinner i (XML)layout-filen:
        Spinner spinner = findViewById(R.id.spinner);

        //Spinnern behöver en adapter för att kunna visa olika values. Där gör vi en adapter här:
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.calculation_options, //Det är dessa värden values som spinnern kommer visa, calculation_option kommer från strings-filen.
                android.R.layout.simple_spinner_item //Här bestäms hur användaren kommer se spinnerlistan med multiplikation, roten ur etc
        );
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //detta behövs för när användaren väljer något, generellt
            
            //när användare väljer ett item (multiplikation etc) i spinner så körs denna metod:
            @Override 
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String calculationOption = parent.getItemAtPosition(position).toString();//what the user selected, valde, from the spinner
                switch (calculationOption) { //calculationOption är namnet på variabeln. Här gör vi en switch med den variabeln
                    case "Square Root":
                        editTextNumber2.setVisibility(View.INVISIBLE); //om man väljer roten ur så blir input 2 osyling
                        break;
                    case "Area of the Circle":
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
            public void onNothingSelected(AdapterView<?> parent) { //En listener kräver att man har två  override, även om den ena är tom. 
                //nothing to do
            }
        });

        Button calculateButton = findViewById(R.id.calculateButton); //Här gör vi att denna variabel pekar mot Button i (xml)layoutfilen

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
                            resultTextView.setText(R.string.cannot_divide_by_zero);
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
                            resultTextView.setText(R.string.invalid_input);
                            return;
                        }
                        break;
                    case "Procent":
                        if (num1 >= 0) {
                            result = num1 / 100;
                        } else {
                            resultTextView.setText(R.string.invalid_input);
                            return;
                        }
                        break;
                    case "Pythagorean Theorem":
                        result = Math.sqrt((num1 * num1) + (num2 * num2));
                        break;
                    case "Area of the Circle":
                        result = Math.PI * Math.pow(num1, 2);
                        break;
                    case "Cylinder Volume":
                        result = Math.PI * Math.pow(num1, 2) * num2;
                        break;
                    default: //This line should never being reached but added just for safety
                        break;
                }

                String resultText = getString(R.string.result) + result;
                resultTextView.setText(resultText);
            }
        });
    }
}
