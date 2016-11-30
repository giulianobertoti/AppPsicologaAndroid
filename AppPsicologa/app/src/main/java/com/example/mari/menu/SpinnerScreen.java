package com.example.mari.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;



import java.util.List;

import model.StudentModel;
import object.CoursesDatas;

public class SpinnerScreen extends Activity {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_screen);




        //TODO variavel institution deve ser defenida um atributo da psicologa
        //TODO informando a qual FATEC ela atende
        int institution = 1;
        //listCourse sendo armazenado com nomes de alunos apenas para teste
        List<String> listCourse = StudentModel.courseReturn(institution);
        final Integer[] arrayPeriod = new Integer[]{1,2};
        final Integer[] arrayYear = new Integer[]{2016,2017,2018,2019,2020};
        CoursesDatas.institution = institution;





        ArrayAdapter<String> adapterCourse =  new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listCourse);
        ArrayAdapter<Integer> adapterPeriod = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, arrayPeriod);
        ArrayAdapter<Integer> adapterYear = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, arrayYear);


        Spinner course = (Spinner) findViewById(R.id.courseSpinner);
        final Spinner period = (Spinner) findViewById(R.id.periodSpinner);
        Spinner year = (Spinner) findViewById(R.id.yearSpinner);

        course.setAdapter(adapterCourse);
        period.setAdapter(adapterPeriod);
        year.setAdapter(adapterYear);

        course.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                CoursesDatas.course = (String) parentView.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        period.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                CoursesDatas.period = (int) parentView.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                CoursesDatas.year = (int) parentView.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


    }



    public void callStudentsScreen(View view){
        Intent trocatela = new Intent(this, StudentsScreen.class);
        startActivity(trocatela);
    }




}