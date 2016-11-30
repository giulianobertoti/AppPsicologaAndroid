package com.example.mari.menu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.interfaces.OnChartValueSelectedListener;

import java.util.ArrayList;

import object.Competencie;
import object.Student;

/**
 * Created by helen on 24/08/2016.
 */
public class GraficoTab extends Fragment implements OnChartValueSelectedListener {

    Student student = null;
    TextView textView = null;

    public GraficoTab() {
        // Required empty public constructor
    }

    public void setStudent(Student student) {

        this.student = student;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.grafico_tab, container, false);


        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.layoutChart);
        RadarChart radarChart = new RadarChart(view.getContext());

        textView = (TextView) view.findViewById(R.id.labelId);

        ArrayList<String> labels = new ArrayList<String>();
        ArrayList<Entry> entries = new ArrayList<>();

        int index = 0;
        for (Competencie competencie : student.getCompetencies()) {
            entries.add(new Entry(competencie.getWeight(), index, competencie.getType()));
            labels.add((competencie.getType().substring(0,7)+"..."));
            index++;
        }

        final RadarDataSet dataset = new RadarDataSet(entries, "Compentencias");

        dataset.setLineWidth(2);
        dataset.setColor(Color.rgb(63,81,181));

        radarChart.setClickable(true);
        radarChart.setOnChartValueSelectedListener(this);

        RadarData data = new RadarData(labels, dataset);

        radarChart.setData(data);
        relativeLayout.addView(radarChart);

        return view;

    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex) {

        textView.setText(e.getData().toString());
    }

    @Override
    public void onNothingSelected() {

    }
}
