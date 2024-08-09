package com.example.basiccalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextResult;
    private String currentExpression = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextResult = findViewById(R.id.editTextResult);

        // Number Buttons
        int[] numberButtonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9
        };

        View.OnClickListener numberClickListener = v -> {
            Button button = (Button) v;
            currentExpression += button.getText().toString();
            editTextResult.setText(currentExpression);
        };

        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(numberClickListener);
        }

        // Operator Buttons
        int[] operatorButtonIds = {
                R.id.buttonAdd, R.id.buttonSubtract,
                R.id.buttonMultiply, R.id.buttonDivide
        };

        View.OnClickListener operatorClickListener = v -> {
            Button button = (Button) v;
            currentExpression += " " + button.getText().toString() + " ";
            editTextResult.setText(currentExpression);
        };

        for (int id : operatorButtonIds) {
            findViewById(id).setOnClickListener(operatorClickListener);
        }

        // Dot Button
        findViewById(R.id.buttonDot).setOnClickListener(v -> {
            currentExpression += ".";
            editTextResult.setText(currentExpression);
        });

        // Clear Button
        findViewById(R.id.buttonClear).setOnClickListener(v -> {
            currentExpression = "";
            editTextResult.setText(currentExpression);
        });

        // Backspace Button
        findViewById(R.id.buttonBackspace).setOnClickListener(v -> {
            if (currentExpression.length() > 0) {
                currentExpression = currentExpression.substring(0, currentExpression.length() - 1);
                editTextResult.setText(currentExpression);
            }
        });

        // Equals Button
        findViewById(R.id.buttonEquals).setOnClickListener(v -> {
            try {
                double result = evaluate(currentExpression);
                editTextResult.setText(String.valueOf(result));
                currentExpression = String.valueOf(result);
            } catch (Exception e) {
                editTextResult.setText("Error");
                currentExpression = "";
            }
        });
    }

    private double evaluate(String expression) {
        // Simple evaluation logic
        // Note: This is just for demonstration and doesn't handle all edge cases
        String[] tokens = expression.split(" ");
        double result = Double.parseDouble(tokens[0]);
        for (int i = 1; i < tokens.length; i += 2) {
            String operator = tokens[i];
            double nextValue = Double.parseDouble(tokens[i + 1]);
            switch (operator) {
                case "+":
                    result += nextValue;
                    break;
                case "-":
                    result -= nextValue;
                    break;
                case "*":
                    result *= nextValue;
                    break;
                case "/":
                    result /= nextValue;
                    break;
            }
        }
        return result;
    }
}
