package com.example.jigneshsatam.myapplication;

import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView label = (TextView) findViewById(R.id.label_name);
        label.setText("Enter your name");

        Button showName = (Button) findViewById(R.id.show_name);
        showName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameTextField = (EditText) findViewById(R.id.name_text_field);
                String userName = String.valueOf(nameTextField.getText());
                if(userName.length() == 0) nameTextField.setError("Please enter your name");
                else {
                    Intent i = new Intent(MainActivity.this, ShowName.class);
                    i.putExtra("userName", userName);
                    startActivity(i);
                }
            }
        });
    }
}
