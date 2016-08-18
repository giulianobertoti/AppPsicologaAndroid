package com.example.renan.appaluno;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;

import java.util.ArrayList;

import model.Model;
import object.Competencie;
import object.Student;

public class StudentDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent intent = getIntent();

        long ra = Long.parseLong(intent.getStringExtra("ra"));
        Model model = new Model();

        Student student = model.searchByCode(ra);

        if (student == null) {
            Intent main_intent = new Intent(this, MainActivity.class);
            main_intent.putExtra("error", "Student not found!");
            startActivity(main_intent);
        }

        RadarChart bar = (RadarChart) findViewById(R.id.chart);

        ArrayList<Entry> entries = new ArrayList<>();
        int index = 0;
        for (Competencie competencie : student.getCompetencies()) {
            entries.add(new Entry(competencie.getWeight(), index));
            index++;
        }

        RadarDataSet dataset = new RadarDataSet(entries, "# of Calls");

        ArrayList<String> labels = new ArrayList<String>();
        for (Competencie competencie : student.getCompetencies()) {
            labels.add(competencie.getType());
        }

        dataset.setLineWidth(3);
        dataset.setDrawFilled(true);

        RadarChart chart = new RadarChart(StudentDetails.this);
        setContentView(chart);
        RadarData data = new RadarData(labels, dataset);
        chart.setData(data);


    }

}
