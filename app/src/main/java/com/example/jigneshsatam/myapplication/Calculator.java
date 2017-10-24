package com.example.jigneshsatam.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Stack;

public class Calculator extends AppCompatActivity implements View.OnClickListener{

    HashMap<Character, Integer> operators = new HashMap<Character, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        String username = getIntent().getStringExtra("userName");
        if (username == null || username.length() <= 0) {username = "Calculator";}
        setTitle(username);

//        ((Button) findViewById(R.id.one)).setOnClickListener(this);
//        ((Button) findViewById(R.id.two)).setOnClickListener(this);
//        ((Button) findViewById(R.id.three)).setOnClickListener(this);
//        ((Button) findViewById(R.id.four)).setOnClickListener(this);
//        ((Button) findViewById(R.id.five)).setOnClickListener(this);
//        ((Button) findViewById(R.id.six)).setOnClickListener(this);
//        ((Button) findViewById(R.id.seven)).setOnClickListener(this);
//        ((Button) findViewById(R.id.eight)).setOnClickListener(this);
//        ((Button) findViewById(R.id.nine)).setOnClickListener(this);
//        ((Button) findViewById(R.id.zero)).setOnClickListener(this);

        operators.put('+', 1);
        operators.put('-', 1);
        operators.put('*', 2);
        operators.put('/', 2);
    }

    @Override
    public void onClick(View v) {
        EditText result = (EditText) findViewById(R.id.calculate);
        int start = result.getSelectionStart();
        int end = result.getSelectionEnd();
        String replace = null;
        String exp = result.getText().toString();
        switch (v.getId()){
            case R.id.one :
                replace = "1";
                break;

            case R.id.two :
                replace = "2";
                break;

            case R.id.three :
                replace = "3";
                break;

            case R.id.four :
                replace = "4";
                break;

            case R.id.five :
                replace = "5";
                break;

            case R.id.six :
                replace = "6";
                break;

            case R.id.seven :
                replace = "7";
                break;

            case R.id.eight :
                replace = "8";
                break;

            case R.id.nine :
                replace = "9";
                break;

            case R.id.zero :
                replace = "0";
                break;

            case R.id.add :
                replace = "+";
                break;

            case R.id.subtract :
                replace = "-";
                break;

            case R.id.multiply :
                replace = "*";
                break;

            case R.id.divide :
                replace = "/";
                break;

            case R.id.equal :
                String res = calculate(exp);
                result.setText(res);
                result.setSelection(res.length());
                break;

            case R.id.clear :
                result.setText("");
                break;

        }
        if (replace == null) return;
        String res = replaceAt(exp, replace, start, end);
//        Log.d("ABC_", "length => "+ exp.length());
//        Log.d("ABC_", "end => "+ end);
        result.setText(res);
        int postion =  (end > res.length()) ? res.length() : ++end;
        result.setSelection(postion);
    }

    String replaceAt(String original, String replacement, int from, int to){
//        Log.d("ABC_", "original => " + String.valueOf(original));
//        Log.d("ABC_", "replacement => " + String.valueOf(replacement));
//        Log.d("ABC_", "from => " + String.valueOf(from));
//        Log.d("ABC_", "to => " + String.valueOf(to));
        try {
            return (original.substring(0, from) + replacement + original.substring(to));
        }
        catch (Exception e){
            e.printStackTrace();
            return  original;
        }
    }

    String calculate(String exp){
        String result;
        Stack<Character> operatorsStack = new Stack();
        Stack<String> operandsStack = new Stack();
        char[] expArray = exp.toCharArray();
        int j = -1;
        for(int i = 0; i < expArray.length; i++){
            // Check if operand
            if (Character.isDigit(expArray[i]) && j == -1){
                j = i;
            }
            // Check if operator
            else if(operators.containsKey(expArray[i])) {
                String operand = exp.substring(j, i);
                operandsStack.push(operand);
                j = -1;
                try{
                    while (!operatorsStack.empty() && isHighPrecedence(operatorsStack.peek(), expArray[i])){
                        solve(operandsStack, operatorsStack);
                    }
                    operatorsStack.push(expArray[i]);
                }
                catch(Exception e){
                }

            }
        }

        if(j != -1){
            String operand = exp.substring(j, expArray.length);
            operandsStack.push(operand);
        }
        while(!operatorsStack.empty()){
            solve(operandsStack, operatorsStack);
        }
        return operandsStack.pop();
    }

    boolean isHighPrecedence(char topOperator, char newOperator){
        int topValue = 0, newValue = 0;
        topValue = operators.get(topOperator);
        newValue = operators.get(newOperator);
        return topValue > newValue;
    }

    void solve(Stack<String> operandsStack, Stack<Character> operatorsStack){
        try{
            char operator = operatorsStack.pop();
            int op1 = Integer.parseInt(operandsStack.pop());
            int op2 = Integer.parseInt(operandsStack.pop());
            int value = 0;
            switch(operator) {
                case '+':
                    value = op2 + op1;
                    break;
                case '-':
                    value = op2 - op1;
                    break;
                case '*':
                    value = op2 * op1;
                    break;
                case '/':
                    value = op2 / op1;
                    break;
            }
            operandsStack.push(String.valueOf(value));
        }catch (Exception e){

        }
    }
}
