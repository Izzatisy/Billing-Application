package com.example.week32;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity {

    private EditText unitsEditText;
    private Button calculateButton;
    private Button clearButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        unitsEditText = findViewById(R.id.unitsEditText);
        calculateButton = findViewById(R.id.calculateButton);
        clearButton = findViewById(R.id.clearButton);
        resultTextView = findViewById(R.id.resultTextView);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String unitsString = unitsEditText.getText().toString();
                if (unitsString.isEmpty()) {
                    Toast.makeText(CalculatorActivity.this, "Please enter units.", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    double units = Double.parseDouble(unitsString);
                    double billAmount = calculateBill(units);
                    resultTextView.setText(String.format("Bill Amount: RM %.2f", billAmount));
                } catch (NumberFormatException e) {
                    Toast.makeText(CalculatorActivity.this, "Invalid input. Please enter a valid number.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unitsEditText.setText("");
                resultTextView.setText("");
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
        double rebate = 0.05 * bill;
        return bill - rebate;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate (R.menu.menu, menu);

        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                //Toast.makeText(this, "This is about",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.about:
                //Toast.makeText(this, "This is search",Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(this, AboutActivity2.class);
                startActivity(intent2);
                break;

            case R.id.calculator:
                //Toast.makeText(this, "This is search",Toast.LENGTH_LONG).show();
                Intent intent3 = new Intent(this, CalculatorActivity.class);
                startActivity(intent3);
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
