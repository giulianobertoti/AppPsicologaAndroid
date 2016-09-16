package br.com.appbackend.Model;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.appbackend.Object.Competencie;
import br.com.appbackend.Object.Student;
import br.com.appbackend.REST.RestConnection;

/**
 * Created by Aluno on 10/08/2016.
 */
public class StudentModel
{
    private static List<Student> students = new ArrayList<>();
 
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
    
    
  //Método que enviará a requisição GET ao WebService e retornará um estudante com suas competências
    public static Student insertComment(long code, String comment)
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
            ObjectMapper mapper = new ObjectMapper();
            webStudent = mapper.readValue(response.toString(), Student.class);
            for (int i = 0; i < competencies.length(); i++)
            {
                JSONObject obj = competencies.getJSONObject(i);
                webStudent.getCompetencies().add(new Competencie(obj.getString("type"),obj.getInt("weight")));
            }
            
            
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
    
    //TODO Dado um curso, um ano e um semestre, retornar uma lista de alunos.
    //TODO na view 3 SPINNERS, 2 ESTATICOS, 1 DINAMICOS CASCATEADOS.
    
    public static List<Student> searchByCourseYearAndHalf(String instituition,String course, int year, int period)
    {
        /*
        * Conexão no webService para busca de estudantes.
        * */
        String url = "http://localhost:4567/alunos/"+instituition+"/"+course+"/"+year+"/"+period+"/";

        List<Student> webStudent = new ArrayList<>();

        try
        {
            JSONArray response = RestConnection.sendGetArray(url);
            
            //TODO iterar dentro do array JSON e converter para Student.
            
//            List<Student> alunos = new ArrayList<>();
//            for (int i = 0; i < response.length(); i++)
//            {
//                JSONObject obj = response.getJSONObject(i);
//                
//                Student aluno = new Student(obj.getString("name"), obj.getString("course"), obj.getString("institution")
//                        , null, obj.getInt("userCode"), obj.getInt("period"), obj.getInt("year"), obj.getLong("ra"));
//                if (aluno != null)
//                {
//                	alunos.add(aluno);
//                }
//            }
        }
        catch (JSONException e) {
            // handle exception
        }
        catch (Exception e)
        {
        	
        }

        return webStudent;
    }
    
    public static List<Student> getStudentCache()
    {
    	return students;
    }
}
