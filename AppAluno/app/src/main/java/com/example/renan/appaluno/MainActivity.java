package com.example.renan.appaluno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import model.Model;
import object.Student;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        try {
            String error = intent.getStringExtra("error");
            TextView textview = (TextView) findViewById(R.id.textView2);
            textview.setText(error);
        } catch (Exception e) {

        }
    }

    public void searchStudentByRA(View view) {
        EditText editRa = (EditText) findViewById(R.id.editText);
        Intent intent = new Intent(this, StudentDetails.class);
        intent.putExtra("ra", String.valueOf(editRa.getText()));
        startActivity(intent);
    }
}
