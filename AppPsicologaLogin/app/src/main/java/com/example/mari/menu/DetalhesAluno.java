package com.example.mari.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;

import java.util.ArrayList;

import model.Model;
import object.Competencie;
import object.Student;

/**
 * Created by Mari on 17/08/2016.
 */
public class DetalhesAluno extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhes_aluno);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        long ra = Long.parseLong(intent.getStringExtra("ra"));

        Model model = new Model();

        Student student = model.searchByCode(ra);

        if (student == null) {
            Intent main_intent = new Intent(this, PesquisarAluno.class);
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

        RadarData data = new RadarData(labels, dataset);
        bar.setData(data);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
        }

        return true;
    }


}
