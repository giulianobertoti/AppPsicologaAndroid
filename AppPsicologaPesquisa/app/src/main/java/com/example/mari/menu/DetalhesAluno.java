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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;

import java.util.ArrayList;

import object.Competencie;
import object.Student;
import okhttp3.ResponseBody;
import rest.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.ServiceGenerator;

/**
 * Created by Mari on 17/08/2016.
 */
public class DetalhesAluno extends AppCompatActivity {

    Student resposta = new Student();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        int userCode = Integer.parseInt(intent.getStringExtra("userCode"));
        retrofitConverter(userCode);

        super.onCreate(savedInstanceState);


        setContentView(R.layout.detalhes_aluno);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    public void buildChart(){
        if (resposta == null) {
            Intent main_intent = new Intent(this, PesquisarAluno.class);
            main_intent.putExtra("error", "Student not found!");
            startActivity(main_intent);
        }

        LinearLayout linearLayout = (LinearLayout ) findViewById(R.id.linearLayout);

        RadarChart bar = new RadarChart(getApplicationContext());

        ArrayList<Entry> entries = new ArrayList<>();
        int index = 0;
        for (Competencie competencie : resposta.getCompetencies()) {
            entries.add(new Entry(competencie.getWeight(), index));
            index++;
        }

        RadarDataSet dataset = new RadarDataSet(entries, "# of Calls");

        ArrayList<String> labels = new ArrayList<String>();
        for (Competencie competencie : resposta.getCompetencies()) {
            labels.add(competencie.getType());
        }

        dataset.setLineWidth(3);
        dataset.setDrawFilled(true);

        RadarData data = new RadarData(labels, dataset);
        bar.setData(data);

        linearLayout.addView(bar);
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


    public void retrofitConverter(int userCode) {


        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);

        Call<Student> call = service.converterUnidade(userCode);

        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {

                if (response.isSuccessful()) {


                    //verifica aqui se o corpo da resposta não é nulo

                    resposta = response.body();


                    buildChart();

                } else {

                    Toast.makeText(getApplicationContext(),"Resposta não foi sucesso", Toast.LENGTH_SHORT).show();
                    // segura os erros de requisição
                    ResponseBody errorBody = response.errorBody();
                }

            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

