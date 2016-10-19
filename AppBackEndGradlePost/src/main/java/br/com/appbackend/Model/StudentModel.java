package br.com.appbackend.Model;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        
        
        // TODO alterar a rota; - Enviar via put somente o coment�rio, pois a rota j� realiza a consulta do aluno e posta o coment�rio no mesmo;
        
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
    
    //TODO Dado um curso, um ano e um semestre, retornar uma lista de alunos.
    //TODO na view 3 SPINNERS, 2 ESTATICOS, 1 DINAMICOS CASCATEADOS.
    
    public static List<Student> searchByCourseYearAndHalf(String instituition,String course, int year, int period)
    {
    	String url = "";
        /*
        * Conexão no webService para busca de estudantes.
        * */
    	try {
			
    		String instituicao = URLEncoder.encode(instituition, "ISO-8859-1");
			String curso = URLEncoder.encode(course, "ISO-8859-1");
	    	String ano = URLEncoder.encode(year+"", "ISO-8859-1");
	    	String periodo = URLEncoder.encode(period+"", "ISO-8859-1");
	    
	    	url = "http://localhost:4567/alunos/"+ instituicao+"/"+curso+"/"+ano+"/"+periodo;
	    	
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
   
        List<Student> webStudent = new ArrayList<>();

        try
        {
            JSONArray response = RestConnection.sendGetArray(url);
            
            //TODO iterar dentro do array JSON e converter para Student.
            
            List<Student> alunos = new ArrayList<>();
            for (int i = 0; i < response.length(); i++)
            {
                JSONObject obj = response.getJSONObject(i);
                
                Student aluno = new Student(obj.getString("name"), obj.getString("course"), obj.getString("institution")
                        , null, obj.getInt("userCode"), obj.getInt("period"), obj.getInt("year"), obj.getLong("ra"));
                if (aluno != null)
                {
                	alunos.add(aluno);
                }
            }
        }
        catch (JSONException e) {
            // handle exception
        	e.printStackTrace();
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }

        return webStudent;
    }
    
    public static List<Student> getStudentCache()
    {
    	return students;
    }
}
