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
