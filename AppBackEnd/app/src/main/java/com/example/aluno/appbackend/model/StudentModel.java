package com.example.aluno.appbackend.model;

import com.example.aluno.appbackend.object.Competencie;
import com.example.aluno.appbackend.object.Student;
import com.example.aluno.appbackend.rest.RestConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentModel
{
    private static List<Student> students = new ArrayList<>();

    //MÃ©todo que enviarÃ¡ a requisiÃ§Ã£o GET ao WebService e retornarÃ¡ um estudante com suas competÃªncias
    public static Student searchByCode(long code)
    {
        /*
        * Varredura na memÃ³ria local para busca de estudantes jÃ¡ carregados.
        * */
        for (Student std : students)
        {
            if (std.getUserCode() == code)
                return std;
        }

        /*
        * ConexÃ£o no webService para busca de estudantes.
        * */
        String url = "http://teste-inacio.rhcloud.com/fatec/map/quiz/result/student?userCode="+code;

        Student webStudent = null;

        try
        {
            JSONObject response = RestConnection.sendGetObject(url);
            JSONArray competencies = response.getJSONArray("competencies");
            List<Competencie> lCompetencies = new ArrayList<>();

            for (int i = 0; i < competencies.length(); i++)
            {
                JSONObject obj = competencies.getJSONObject(i);
                lCompetencies.add(new Competencie(obj.getString("type"),obj.getInt("weight")));
            }

            webStudent = new Student(response.getString("name"), response.getString("course"), response.getString("institution")
                    , lCompetencies, response.getInt("userCode"), response.getInt("period"), response.getInt("year"), response.getLong("ra"));

            if (webStudent != null)
            {
                students.add(webStudent);
            }

        }
        catch (JSONException e) {
            // handle exception
        }
        catch (Exception e)
        {
        }

        return webStudent;
    }


    //MÃ©todo que enviarÃ¡ a requisiÃ§Ã£o GET ao WebService e retornarÃ¡ um estudante com suas competÃªncias
    public static Student insertComment(long code, String comment)
    {
        /*
        * Varredura na memÃ³ria local para busca de estudantes jÃ¡ carregados.
        * */
        for (Student std : students)
        {
            if (std.getUserCode() == code)
                return std;
        }

        /*
        * ConexÃ£o no webService para busca de estudantes.
        * */
        String url = "http://localhost:4567/comentario";

        Student webStudent = null;

        try
        {
            JSONObject req = new JSONObject();
            req.put("ra", code);
            req.put("comentario", comment);
            JSONObject response = RestConnection.sendPostObject(url, req);

            JSONArray competencies = response.getJSONArray("competencies");
            List<Competencie> lCompetencies = new ArrayList<>();

            for (int i = 0; i < competencies.length(); i++)
            {
                JSONObject obj = competencies.getJSONObject(i);
                lCompetencies.add(new Competencie(obj.getString("type"),obj.getInt("weight")));
            }

            webStudent = new Student(response.getString("name"), response.getString("course"), response.getString("institution")
                    , lCompetencies, response.getInt("userCode"), response.getInt("period"), response.getInt("year"), response.getLong("ra"));

            if (webStudent != null)
            {
                students.add(webStudent);
            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return webStudent;
    }

    //TODO Dado uma FATEC, um curso, um ano e um semestre, retornar uma lista de alunos.
    //TODO na view 4 SPINNERS, 2 ESTATICOS, 2 DINAMICOS CASCATEADOS.
}
