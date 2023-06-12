package com.example.week32;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText unitsEditText;
    private Button calculateButton;
    private Button clearButton;
    private TextView resultTextView;
    private TextView rebateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unitsEditText = findViewById(R.id.unitsEditText);
        calculateButton = findViewById(R.id.calculateButton);
        clearButton = findViewById(R.id.clearButton);
        resultTextView = findViewById(R.id.resultTextView);
        rebateTextView = findViewById(R.id.rebateTextView);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String unitsString = unitsEditText.getText().toString();
                if (unitsString.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter units.", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    double units = Double.parseDouble(unitsString);
                    double billAmount = calculateBill(units);
                    double rebatePercentage = calculateRebate(units) * 100;

                    resultTextView.setText(String.format("Bill Amount: RM %.2f", billAmount));
                    rebateTextView.setText(String.format("Rebate: %.2f%%", rebatePercentage));
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Invalid input. Please enter a valid number.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unitsEditText.setText("");
                resultTextView.setText("");
                rebateTextView.setText("");
            }
        });
    }

    private double calculateBill(double units) {
        double rate;
        if (units <= 200) {
            rate = 0.218;
        } else if (units <= 300) {
            rate = 0.334;
        } else if (units <= 600) {
            rate = 0.516;
        } else {
            rate = 0.546;
        }

        double bill = units * rate;
        double rebate = calculateRebate(units) * bill;
        return bill - rebate;
    }

    private double calculateRebate(double units) {
        double rebatePercentage;
        if (units >= 0 && units <= 100) {
            rebatePercentage = 0.0;
        } else if (units <= 300) {
            rebatePercentage = 0.01;
        } else if (units <= 500) {
            rebatePercentage = 0.03;
        } else if (units <= 600) {
            rebatePercentage = 0.04;
        } else {
            rebatePercentage = 0.05;
        }
        return rebatePercentage;
    }
}
