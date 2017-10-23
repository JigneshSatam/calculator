package com.example.jigneshsatam.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_name);
        Intent i = getIntent();
        final String userName = i.getStringExtra("userName");
        ((TextView) findViewById(R.id.user_name)).setText(userName);

        Button calculator = (Button) findViewById(R.id.start_calculator);
        calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShowName.this, Calculator.class);
                i.putExtra("userName", userName);
                startActivity(i);
            }
        });
    }
}
