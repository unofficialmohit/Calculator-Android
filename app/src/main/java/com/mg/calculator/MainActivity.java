package com.mg.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView resultTv,solutionTv;
    MaterialButton buttonC,buttonBrackOpen,buttonBrackClose;
    MaterialButton buttonDivide,buttonMultiply,buttonPlus,buttonMinus,buttonEquals;
    MaterialButton button1,button2,button3,button4,button5,button6,button7,button8,button9,button0;
    MaterialButton buttonAC,buttonDot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv=findViewById(R.id.result_tv);
        solutionTv=findViewById(R.id.solution_tv);
        buttonC=findViewById(R.id.button_c);
        buttonC.setOnClickListener(this);
        buttonBrackClose=findViewById(R.id.button_close_bracket);
        buttonBrackClose.setOnClickListener(this);
        buttonBrackOpen=findViewById(R.id.button_open_bracket);
        buttonBrackOpen.setOnClickListener(this);
        buttonDivide=findViewById(R.id.button_divide);
        buttonDivide.setOnClickListener(this);
        buttonMultiply=findViewById(R.id.button_multiply);
        buttonMultiply.setOnClickListener(this);
        buttonMinus=findViewById(R.id.button_minus);
        buttonMinus.setOnClickListener(this);
        buttonPlus=findViewById(R.id.button_plus);
        buttonPlus.setOnClickListener(this);
        buttonEquals=findViewById(R.id.button_equals);
        buttonEquals.setOnClickListener(this);
        button0=findViewById(R.id.button_0);
        button0.setOnClickListener(this);
        button1=findViewById(R.id.button_1);
        button1.setOnClickListener(this);
        button2=findViewById(R.id.button_2);
        button2.setOnClickListener(this);
        button3=findViewById(R.id.button_3);
        button3.setOnClickListener(this);
        button4=findViewById(R.id.button_4);
        button4.setOnClickListener(this);
        button5=findViewById(R.id.button_5);
        button5.setOnClickListener(this);
        button6=findViewById(R.id.button_6);
        button6.setOnClickListener(this);
        button7=findViewById(R.id.button_7);
        button7.setOnClickListener(this);
        button8=findViewById(R.id.button_8);
        button8.setOnClickListener(this);
        button9=findViewById(R.id.button_9);
        button9.setOnClickListener(this);
        buttonDot=findViewById(R.id.button_dot);
        buttonDot.setOnClickListener(this);
        buttonAC=findViewById(R.id.button_ac);
        buttonAC.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {
        MaterialButton button=(MaterialButton) view;
        String buttonText=button.getText().toString();
//        solutionTv.setText(buttonText);
        String dataToCalculate=solutionTv.getText().toString();
        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }
        if (buttonText.equals("C")) {
            if (!dataToCalculate.isEmpty() && dataToCalculate.length() > 1) {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            } else {
                dataToCalculate = "";
                resultTv.setText("0");
            }
            solutionTv.setText(dataToCalculate);

        } else {
            dataToCalculate = dataToCalculate + buttonText;
            solutionTv.setText(dataToCalculate);
        }


        solutionTv.setText(dataToCalculate);
        String finalResult=getResult(dataToCalculate);
        if(!finalResult.equals("ERROR"))
        {
            resultTv.setText(finalResult);
        }
    }
String getResult(String data){

       try{
           if (data.isEmpty()) {
               return "0"; // Return 0 if there's no data to calculate.
           }

           Context context= Context.enter();
           context.setOptimizationLevel(-1);
           Scriptable scriptable=context.initStandardObjects();
           String finalResult = context.evaluateString(scriptable,data,"javascript",1,null).toString();
           if(finalResult.endsWith(".0"))
           {
               finalResult=finalResult.replace(".0","");
           }
           return finalResult;
       }
       catch(Exception ae)
        {
        return "ERROR";
        }
}
}