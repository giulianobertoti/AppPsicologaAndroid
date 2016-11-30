package com.example.mari.menu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import object.Student;
import rest.Connection;


public class DetalhesTab extends Fragment {

    Student student = null;

    public DetalhesTab() {
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.detalhes_tab, container, false);

        final TextView textViewRa = (TextView) view.findViewById(R.id.lblRa);
        final TextView textViewNome = (TextView) view.findViewById(R.id.lblNome);
        final TextView textViewCurso = (TextView) view.findViewById(R.id.lblCurso);
        final TextView textViewComentario = (TextView) view.findViewById(R.id.comentario);

        Button btnSalvar = (Button) view.findViewById(R.id.btnSalvar);

        textViewRa.setText(textViewRa.getText() + " " + String.valueOf(student.getRa() + "987654"));
        textViewCurso.setText(textViewCurso.getText() + " " + String.valueOf(student.getCourse()));
        textViewNome.setText(String.valueOf(student.getName()));

        final String url = "http://localhost:4567/comentario";

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject req = new JSONObject();
                    req.put("ra", textViewRa.getText());
                    req.put("comentario", textViewComentario.getText().toString());
                    JSONObject response = Connection.sendPostObject(url, req);

                }catch(Exception ex){
                    ex.printStackTrace();
                }

            }
        });
        return view;


    }

}
