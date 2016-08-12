package com.example.renan.appaluno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void searchStudentByRA(View view){
        EditText editRa = (EditText) findViewById(R.id.editText);
        String ra = String.valueOf(editRa.getText());
        TextView result = (TextView) findViewById(R.id.textView2);
        result.setText(ra);
    }
}
