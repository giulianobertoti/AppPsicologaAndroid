package model;

import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import object.Competencie;
import object.Student;
import rest.RestConnection;

/**
 * Created by renan on 17/08/16.
 */
public class StudentModel {

    private static List<Student> students = new ArrayList<>();
    private static RestConnection con;


    static
    {
        con = new RestConnection();
    }

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


        // TODO alterar a rota; - Enviar via put somente o comentário, pois a rota já realiza a consulta do aluno e posta o comentário no mesmo;

        //http://teste-inacio.rhcloud.com/fatec/map/enrolls/comment?studentCode=15
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

    //Método que enviará a requisição GET ao WebService e retornará um estudante com suas competências
    public static Student searchByCode(long code)
    {
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
                    , lCompetencies, response.getInt("userCode"), response.getInt("period"), response.getInt("year"), response.getLong("ra"), response.getString("comments"));

            if (webStudent != null)
            {
                students.add(webStudent);
            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return webStudent;
    }



    public static List<String> courseReturn(int institution){
        List<String> courses = new LinkedList<>();

        String url = "http://teste-inacio.rhcloud.com/fatec/map/institution/find/all/courses";

        try
        {
            JSONArray response = RestConnection.sendGetArray(url);



            for (int i = 0; i < response.length(); i++)
            {
                JSONObject obj = response.getJSONObject(i);

                if(obj.getInt("code") == institution){
                    JSONArray coursesArray = obj.getJSONArray("courses");
                    for(int j = 0; j < coursesArray.length(); j++){
                        JSONObject json = coursesArray.getJSONObject(j);
                        courses.add(json.getString("name"));
                    }

                }

            }
        }
        catch (JSONException e) {
        }
        catch (Exception e)
        {
        }
        return courses;
    }

    public static ArrayList<Student> studentsReturn(int institution, int period, int year, String course){
        ArrayList<Student> students = new ArrayList<Student>();
        String url = "http://teste-inacio.rhcloud.com/fatec/map/enrolls/search/students/all/fatec?fatecCode=" + institution;

        try{
            JSONArray response = RestConnection.sendGetArray(url);

            for (int i = 0; i < response.length(); i++)
            {
                JSONObject obj = response.getJSONObject(i);
                if(obj.getString("course").equals(course) && obj.getInt("period") == period && obj.getInt("year") == year){
                    Student student = new Student();

                    JSONObject json = obj.getJSONObject("user");
                    student.setVerification(obj.getInt("verification"));
                    student.setUserCode(json.getInt("userCode"));
                    student.setName(json.getString("name").toUpperCase());

                    students.add(student);
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student one, Student two) {
                return one.getName().compareTo(two.getName());
            }
        });

        return students;
    }

    }
