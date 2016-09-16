package br.com.appbackend.run;
import static spark.Spark.*;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.appbackend.Model.StudentModel;
import br.com.appbackend.Object.Comment;
import br.com.appbackend.Object.Student;
import br.com.appbackend.REST.RestConnection;
import spark.Spark;

public class RestRoutes {

	public static void main(String[] args) {
		
		Spark.staticFileLocation("/public");
		
		get("/alunos/:inst/:course/:year/:period", (request, response) ->
		{			

			JSONArray alunos = new JSONArray();
            //TODO receber como pâmetros a psicologa, o curso, ano e semestre
			String course = request.params("course");		
			int year = 0;
			int period = 0;
			String inst = request.params("inst");
			
			try
            {
            	//atributo RA
				year  = Integer.parseInt(request.params("year"));
            	period = Integer.parseInt(request.params("period"));
            	
            	StudentModel.searchByCode(16l);
            	StudentModel.searchByCode(15l);
            	StudentModel.searchByCode(14l);
            	
            	for(Student student : StudentModel.getStudentCache())
            	{
            		if(student.getYear() == year && student.getCourse().equals(course)
            				&& student.getInstitution().equals(inst)
            				&& student.getPeriod() == period)
            		{
            			alunos.put(student.toJsonObject());
            		}
            	}
            	
            }
            catch(Exception e)
            {
            	e.printStackTrace();
            }
			
			
            return alunos;
		});
		
		post("/comentario", (request, response) ->
		{			
			Student student = null;
			JSONObject body = new JSONObject(request.body());
			JSONObject json = null;
			JSONArray comments = new JSONArray();
            //TODO receber como pâmetros o RA do aluno e o comentário da psicologa
			int ra = 0;
			String comentario = null;
			
			try
            {
            	//atributo RA
				ra  = body.getInt("ra");
            	comentario = body.getString("comentario");
            	student = StudentModel.searchByCode(ra);
            	
            	if(student != null)
            	{

        			//student.getComments().add(new Comment(ra,comentario));
        			student.setComment(comentario);
            	}
            	
            	String url = "http://teste-inacio.rhcloud.com/fatec/map/quiz/result/student?userCode="+ ra;
 				json = RestConnection.sendGetObject(url);
            }
            catch(Exception e)
            {
            	e.printStackTrace();
            }
			
			
			
			if (json == null)
			{
				return new JSONObject();
			}
			
			JSONObject comment = new JSONObject();
			
			try {
				comment.put("content",comentario);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			JSONObject obj1 = new JSONObject();
 			obj1.put("content", student.getComment());
			comments.put(comment);
			
//			for(Comment com : student.getComments())
//			{
//				JSONObject obj1 = new JSONObject();
//				obj1.put("ra", com.getRa());
//				obj1.put("content", com.getComment());
//				comments.put(comment);
//			}
			
			
			json.put("comments", comments);
			
            return json;
		});
		
	}

}
