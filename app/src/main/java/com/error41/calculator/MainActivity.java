package com.error41.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv, solutionTv;
    MaterialButton C_button, open_button, close_button;
    MaterialButton div_button, plus_button, minus_button, mul_button;
    MaterialButton $0_button, $1_button, $2_button, $3_button,
            $4_button, $5_button, $6_button, $7_button, $8_button, $9_button;
    MaterialButton Ac_button, Dot_button, equal_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);
      //  C_button = findViewById(R.id.c_button);
        assignId(R.id.c_button, C_button);
        assignId(R.id.firstbacket_button, open_button);
        assignId(R.id.secondbracket_button, close_button);
        assignId(R.id.division_button, div_button);
        assignId(R.id.plus_button, plus_button);
        assignId(R.id.minus_button, minus_button);
        assignId(R.id.multiple_button, mul_button);
        assignId(R.id.zero_button, $0_button);
        assignId(R.id.one_button, $1_button);
        assignId(R.id.two_button, $2_button);
        assignId(R.id.three_button, $3_button);
        assignId(R.id.four_button, $4_button);
        assignId(R.id.five_button, $5_button);
        assignId(R.id.six_button, $6_button);
        assignId(R.id.seven_button, $7_button);
        assignId(R.id.eight_button, $8_button);
        assignId(R.id.nine_button, $9_button);
        assignId(R.id.ac_button, Ac_button);
        assignId(R.id.dot_button, Dot_button);
        assignId(R.id.equial_button, equal_button);
    }

    void assignId(int id, MaterialButton btn) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();

        String dataToCalculate = solutionTv.getText().toString();


        if (buttonText.equals("cle")) {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }


        if (buttonText.equals("=")) {
            solutionTv.setText(resultTv.getText());
            return;
        }
        if (buttonText.equals("C")) {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        } else {
            dataToCalculate = dataToCalculate + buttonText;
        }
        solutionTv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if (!finalResult.equals("Err")) {
            resultTv.setText(finalResult);
        }
    }

    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();

            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        } catch (Exception e) {
            return "Err";
        }
    }
}
