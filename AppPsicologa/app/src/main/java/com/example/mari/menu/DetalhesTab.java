package com.example.mari.menu;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.inputmethod.InputMethodManager;

import model.StudentModel;
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
        final View view = inflater.inflate(R.layout.detalhes_tab, container, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final long studentCode = student.getUserCode();

        final TextView textViewRa = (TextView) view.findViewById(R.id.lblRa);
        final TextView textViewNome = (TextView) view.findViewById(R.id.lblNome);
        final TextView textViewCurso = (TextView) view.findViewById(R.id.lblCurso);
        final TextView textViewComentario = (TextView) view.findViewById(R.id.comentario);
        final EditText editComentario = (EditText) view.findViewById(R.id.txtComentario);

        Button btnSalvar = (Button) view.findViewById(R.id.btnSalvar);

        textViewRa.setText(textViewRa.getText() + " " + String.valueOf(student.getRa() ));
        textViewCurso.setText(textViewCurso.getText() + " " + String.valueOf(student.getCourse()));
        textViewNome.setText(String.valueOf(student.getName()));
        textViewComentario.setText(String.valueOf(textViewComentario.getText() + " " +student.getComment()));


        if(student.getVerification() != 1)
        {
            btnSalvar.setEnabled(false);
            editComentario.setEnabled(false);
            btnSalvar.setVisibility(View.INVISIBLE);
        }
        else
        {
            editComentario.setText(String.valueOf(student.getComment()));
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    final String cometary = String.valueOf(editComentario.getEditableText());

                    Student returnStudent = StudentModel.insertComment( studentCode ,cometary);
                    if(returnStudent!= null) {
                        Toast.makeText(v.getContext(), "Comentário add com sucesso.", Toast.LENGTH_LONG).show();
                        student.setComment(returnStudent.getComment());
                        textViewComentario.setText(String.valueOf("Comentário: " +returnStudent.getComment()));
                        editComentario.setText("");
                        InputMethodManager imm;
                        imm = (InputMethodManager) getActivity().getSystemService(v.getContext().INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(editComentario.getWindowToken(),
                                InputMethodManager.RESULT_UNCHANGED_SHOWN);
                    }
                    else
                    {
                        Toast.makeText(v.getContext(), "Não é possível adicionar comentário.", Toast.LENGTH_LONG).show();
                    }

                }catch(Exception ex){
                    ex.printStackTrace();
                }

            }
        });
        return view;


    }

}
