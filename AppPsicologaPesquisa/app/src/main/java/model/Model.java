package model;

import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import object.Competencie;
import object.Student;
import rest.RestConnection;

/**
 * Created by renan on 17/08/16.
 */
public class Model {
    private List<Student> students = new ArrayList<>();
    private static RestConnection con;

    static
    {
        con = new RestConnection();
    }

    //Método que enviará a requisição GET ao WebService e retornará um estudante com suas competências
    public Student searchByCode(long code)
    {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        /*
        * Varredura na memória local para busca de estudantes já carregados.
        * */
        for (Student std : students)
        {
            if (std.getUserCode() == code)
                return std;
        }

        /*
        * Conexão no webService para busca de estudantes.
        * */
        String url = "http://teste-inacio.rhcloud.com/fatec/map/quiz/result/student?userCode=" + code;

        Student webStudent = null;

        try
        {
            JSONObject response = con.sendGetArray(url);
            JSONArray competencies = response.getJSONArray("competencies");
            List<Competencie> lCompetencies = new ArrayList<>();

            for (int i = 0; i < competencies.length(); i++)
            {
                JSONObject obj = competencies.getJSONObject(i);
                lCompetencies.add(new Competencie(obj.getString("type"),obj.getInt("weight")));
            }

            webStudent = new Student(response.getString("name"), response.getString("course"), response.getString("institution")
                    , lCompetencies, response.getInt("userCode"), response.getInt("period"), response.getInt("year"), response.getString("ra"), response.getString("comments"));

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

    public Student insertComment(long code, String comment)
    {

        for (Student std : students)
        {
            if (std.getUserCode() == code)
                return std;
        }


        String url = "http://teste-inacio.rhcloud.com/fatec/map/enrolls/comment?studentCode="+code;

        Student webStudent = null;

        try
        {
            String response = RestConnection.sendPutPlainText(url, comment);

            if(response.equals("true"))
            {

                webStudent = searchByCode(code);

                if (webStudent != null)
                {
                    if(students.contains(webStudent))
                    {
                        students.get(students.indexOf(webStudent)).setComment(comment);
                    }
                    else
                    {
                        students.add(webStudent);
                    }
                }
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

}
