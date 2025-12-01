package com.fbaskakov.homework1.project3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity  extends ConverterActivity{
    private TextView text;
    private String inputText, itemIn, itenOut;
    private double resualt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        resultText();
    }

    private void resultText() {
        text = findViewById(R.id.textResult);
        Intent intent = getIntent();
        inputText = intent.getStringExtra("INPUT_VALUE");
        itemIn = intent.getStringExtra("INPUT_UNIT");
        itenOut = intent.getStringExtra("OUTPUT_UNIT");
        resualt = intent.getDoubleExtra("RESULT", 0.0);

        String outText = inputText + " " + itemIn + " = " + resualt + " " + itenOut;
        text.setText(outText);
    }
}
