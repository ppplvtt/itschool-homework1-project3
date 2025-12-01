package com.fbaskakov.homework1.project3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ConverterActivity extends AppCompatActivity {

    private EditText InputText;
    private Spinner spinnerIn, spinnerOut;
    private Button button;
    private ArrayAdapter<String> adapter;
    String[] uom = {"Сантиметры", "Метры", "Километры"};
    private String item_out, item_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_converter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        setupSpinners();
        setupListeners();
    }
    private void init() {
        InputText = findViewById(R.id.input_text);

        spinnerIn = findViewById(R.id.spinner_in);
        spinnerOut = findViewById(R.id.spinner_out);

        button = findViewById(R.id.button);

    }
    private void clickButton() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity();
            }
        });
    }
    private void startActivity() {
        String valueStr = InputText.getText().toString();
        double textNum = Double.parseDouble(valueStr);


        double result = measurements(textNum, item_in, item_out);

        Intent intent = new Intent(ConverterActivity.this, ResultActivity.class);
        intent.putExtra("INPUT_VALUE", valueStr);
        intent.putExtra("INPUT_UNIT", item_in);
        intent.putExtra("OUTPUT_UNIT", item_out);
        intent.putExtra("RESULT", result);

        startActivity(intent);
    }
    private  void setupListeners() {
        clickButton();
    }
    private void setupSpinners() {
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                uom
        );
        spinnerIn.setSelection(1); 
        spinnerOut.setSelection(1);
        
        spinnerIn.setAdapter(adapter);
        spinnerOut.setAdapter(adapter);

        spinnerIn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item_in = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        spinnerOut.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item_out = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

    }
    private double measurements(double text, String itemIn, String itemOut) {
        double num;
        switch (itemIn) {
            case "Сантиметры":
                num = text / 100.0;
                break;
            case "Метры":
                num = text;
                break;

            case "Километры":
                num = text*1000.0;
                break;
            default:
                num = text;
                break;
        }
        switch (itemOut) {
            case "Сантиметры":
                return  num * 100.0;
            case "Метры":
                return num;
            case "Километры":
                return num / 1000.0;
            default:
                return num;
        }
    }
}