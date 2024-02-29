package com.example.simple_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv,solutionTv;
    MaterialButton buttonC,buttonBracketOpen,buttonBracketClose;
    MaterialButton buttonAdd,buttonSub,buttonDivide,buttonMulti,buttonEqual;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton  buttonAC,buttonDot;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

        assignID(buttonC,R.id.button_c);
        assignID(buttonBracketOpen,R.id.button_openbracket);
        assignID(buttonBracketClose,R.id.button_closebracket);
        assignID(buttonAdd,R.id.button_add);
        assignID(buttonSub,R.id.button_subs);
        assignID(buttonMulti,R.id.button_multiply);
        assignID(buttonDivide,R.id.button_divide);
        assignID(buttonEqual,R.id.button_equal);
        assignID(button0,R.id.button_zero);
        assignID(button1,R.id.button_one);
        assignID(button2,R.id.button_two);
        assignID(button3,R.id.button_three);
        assignID(button4,R.id.button_four);
        assignID(button5,R.id.button_five);
        assignID(button6,R.id.button_six);
        assignID(button7,R.id.button_seven);
        assignID(button8,R.id.button_eight);
        assignID(button9,R.id.button_nine);
        assignID(buttonDot,R.id.button_dot);
        assignID(buttonAC,R.id.button_allclear);
    }
void assignID(MaterialButton btn ,int id ){
        btn = findViewById(id);
        btn.setOnClickListener(this);
}


    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;

        }
        if ( buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);

        }else {
            dataToCalculate = dataToCalculate+buttonText;
        }

        solutionTv.setText(dataToCalculate);
        String finalresult = getResult(dataToCalculate);

        if(!finalresult.equals("Err")){
            resultTv.setText(finalresult);
        }

    }
    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult  =  context.evaluateString(scriptable,data,"javascript",1,null).toString();
         if (finalResult.endsWith(".0")){
             finalResult = finalResult.replace(".0","");
         }
            return finalResult;


        }catch(Exception e){
            return "Err";
        }

    }
}