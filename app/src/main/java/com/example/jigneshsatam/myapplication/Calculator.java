package com.example.jigneshsatam.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Calculator extends AppCompatActivity implements View.OnClickListener{

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
    }

    @Override
    public void onClick(View v) {
        EditText result = (EditText) findViewById(R.id.calculate);
        int start = result.getSelectionStart();
        int end = result.getSelectionEnd();
        String replace = null;
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

            case R.id.clear :
                result.setText("");
                break;

        }
        if (replace == null) return;
        String exp = result.getText().toString();
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
}
