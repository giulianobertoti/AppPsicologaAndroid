package com.example.mari.menu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import model.Model;
import object.Student;
import okhttp3.ResponseBody;
import rest.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.ServiceGenerator;

public class DetalhesAluno extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Student student = new Student();

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


    private void setupViewPager() {


        if (student == null) {
            Intent main_intent = new Intent(this, PesquisarAluno.class);
            main_intent.putExtra("error", "Student not found!");
            startActivity(main_intent);
        }



        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


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

    public void retrofitConverter(int userCode) {


        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);

        Call<Student> call = service.converterUnidade(userCode);

        call.enqueue(new Callback<Student>() {

            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {

                if (response.isSuccessful()) {

                    Log.d("Debug", "onResponse: ENTROU NESSA APP");
                    //verifica aqui se o corpo da resposta não é nulo

                    student = response.body();

                    viewPager = (ViewPager) findViewById(R.id.viewpager);

                    setupViewPager();

                } else {

                    Toast.makeText(getApplicationContext(), "Resposta não foi sucesso", Toast.LENGTH_SHORT).show();
                    // segura os erros de requisição
                    ResponseBody errorBody = response.errorBody();
                }

            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
            }
        });

    }

}