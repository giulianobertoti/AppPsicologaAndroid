package com.example.mari.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import model.StudentModel;
import object.Student;

public class DetalhesAluno extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Student student = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhes_aluno);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        long ra = Long.parseLong(intent.getStringExtra("ra"));

        student = StudentModel.searchByCode(ra);

        if (student == null) {
            Intent main_intent = new Intent(this, PesquisarAluno.class);
            main_intent.putExtra("error", "Student not found!");
            startActivity(main_intent);
        }
        else
        {
            student.setVerification(intent.getIntExtra("verification",1));
        }

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }


    private void setupViewPager(ViewPager viewPager) {
        GraficoTab grafico = new GraficoTab();
        grafico.setStudent(student);
        DetalhesTab detalhesTab = new DetalhesTab();
        detalhesTab.setStudent(student);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(detalhesTab, "Detalhes");
        adapter.addFragment(grafico, "Gráfico");
        viewPager.setAdapter(adapter);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
        }
        return true;
    }
}