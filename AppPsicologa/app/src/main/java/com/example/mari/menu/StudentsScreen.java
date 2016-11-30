package com.example.mari.menu;

import android.app.AlertDialog;
import android.content.DialogInterface;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import model.StudentModel;
import object.Color;
import object.ColorAdapter;
import object.CoursesDatas;
import object.Student;

public  class  StudentsScreen extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_screen);
        final ArrayList<Student> students = StudentModel.studentsReturn(CoursesDatas.institution, CoursesDatas.period, CoursesDatas.year, CoursesDatas.course);

        ArrayList<Color> color_data = new ArrayList<Color>();

        //Alerta no caso de vazio
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("ATENÇÃO");
        alertDialog.setMessage("Nenhum aluno foi encontrado com estes critérios de busca!");
        alertDialog.setButton("Continuar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Chamar a SpinerScreen
                trocarTela();
            }
        });
        //alertDialog.setIcon(R.drawable.icon); icone do alerta



        if(students.isEmpty()){
            // lista de estudantes vazia(nenhum estudante encontrado) mostrar um alerta e voltar para SpinnerScreen
            alertDialog.show();
        }

        for(Student std : students){

            if(std.getVerification() == 2){
                color_data.add(new Color(std.getName(), R.drawable.green));
            }

            if(std.getVerification() == 1){
                color_data.add( new Color(std.getName(), R.drawable.yellow));
            }
            if(std.getVerification() == 0){
                color_data.add( new Color(std.getName(), R.drawable.red));
            }
        }


        ColorAdapter adapter = new ColorAdapter(this,
                R.layout.list_view_row_students_screen, color_data);

        ListView listView = (ListView)findViewById(R.id.listView1);

        View header = (View)getLayoutInflater().inflate(R.layout.listview_header_row_students_screen, null);
        listView.addHeaderView(header);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                if(position > 0) {

                    Student student = students.get(position - 1);
                    if(student.getVerification() == 0) {
                        Toast.makeText(getBaseContext(), "Não é possível adicionar comentários, pois o status ainda não está ok", Toast.LENGTH_LONG).show();
                    }

                    if(student.getVerification() == 1) {
                        //TODO chamar a tela DetalhesTab para add comment
                        telaPsicologa(student);
                    }


                    if(student.getVerification() == 2) {
                        //TODO chamar a tela DetalhesTab somente para visualização
                        Toast.makeText(getBaseContext(), "Aluno já foi adicionado comentário", Toast.LENGTH_LONG).show();
                        telaPsicologa(student);
                    }

                }
            }
        });

    }


    public void trocarTela() {
        onBackPressed();
        this.finish();
    }

    public void telaPsicologa(Student student){
        //metodo para trocar a tela para a psicologa

        Intent intent = new Intent(this, DetalhesAluno.class);
        intent.putExtra("ra", String.valueOf(student.getUserCode()));
        intent.putExtra("verification", student.getVerification());
        startActivity(intent);

    }



}