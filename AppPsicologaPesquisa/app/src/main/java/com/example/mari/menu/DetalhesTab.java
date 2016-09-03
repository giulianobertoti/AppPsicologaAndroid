package com.example.mari.menu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import object.Student;


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

        TextView textEditRa = (TextView) view.findViewById(R.id.lblRa);
        TextView textEditNome = (TextView) view.findViewById(R.id.lblNome);
        textEditRa.setText(textEditRa.getText() + " " + String.valueOf(student.getRa()));
        textEditNome.setText(String.valueOf(student.getName()));

        return view;


    }

}
