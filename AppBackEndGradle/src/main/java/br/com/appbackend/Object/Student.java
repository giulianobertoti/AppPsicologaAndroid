package br.com.appbackend.Object;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.bind.v2.TODO;

/**
 * Created by Aluno on 10/08/2016.
 */
public class Student {

    private String name;
    private String course;
    private String institution;
    private List<Competencie> competencies = new ArrayList<>();;
    private int userCode;
    private int period;
    private int year;
    private long ra;
    private String comment;
    //private List<Comment> comments = new ArrayList<>();
    //TODO trocar o comentario para string (não vai ter mais de um comentario.)
    // poedemos remover a classe comment.

    public Student(String name, String course, String institution, List<Competencie> competencies, int userCode, int period, int year, long ra){

        this.name = name;
        this.course = course;
        this.institution = institution;
        this.competencies = competencies;
        this.userCode = userCode;
        this.period = period;
        this.year = year;
        this.ra = ra;

    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public String getInstitution() {
        return institution;
    }

    public List<Competencie> getCompetencies() {
        return competencies;
    }

    public int getUserCode() {
        return userCode;
    }

    public int getPeriod() {
        return period;
    }

    public int getYear() {
        return year;
    }

    public long getRa() {
        return ra;
    }
    
//    public List<Comment> getComments() {
//		return comments;
//	}

//	public void setComments(List<Comment> comments) {
//		this.comments = comments;
//	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
    public String toString()
    {
    	StringBuilder str = new StringBuilder();
    	
    	str.append("\nNome: ");
    	str.append(this.name);
    	str.append("\nCurso: ");
    	str.append(this.course);
    	str.append("\nInstituição: ");
    	str.append(this.institution);
    	str.append("\nUserCode: ");
    	str.append(this.userCode);
    	str.append("\nPeriodo: ");
    	str.append(this.period);
    	str.append("\nAno: ");
    	str.append(this.year);
    	str.append("\nRA: ");
    	str.append(this.ra);

    	str.append("\nCompetencias: ");
        
    	for(Competencie con : this.competencies)
    	{
    		str.append(con.toString());
    	}
    	
    	return str.toString();
    }
	
	public JSONObject toJsonObject() throws JsonProcessingException, JSONException
	{
		ObjectMapper mapper = new ObjectMapper();
		JSONObject asJson = new JSONObject(mapper.writeValueAsString(this));
		return asJson;
	}
	

}
