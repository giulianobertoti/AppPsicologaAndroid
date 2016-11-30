package object;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by renan on 17/08/16.
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
    private int verification;
    //private List<Comment> comments = new ArrayList<>();
    //TODO trocar o comentario para string (não vai ter mais de um comentario.)
    // poedemos remover a classe comment.


    public Student() {
    }

    public Student(String name, String course, String institution, List<Competencie> competencies, int userCode, int period, int year, long ra, String comment) {
        this.name = name;
        this.course = course;
        this.institution = institution;
        this.competencies = competencies;
        this.userCode = userCode;
        this.period = period;
        this.year = year;
        this.ra = ra;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public List<Competencie> getCompetencies() {
        return competencies;
    }

    public void setCompetencies(List<Competencie> competencies) {
        this.competencies = competencies;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getRa() {
        return ra;
    }

    public void setRa(long ra) {
        this.ra = ra;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getVerification() {
        return verification;
    }

    public void setVerification(int verification) {
        this.verification = verification;
    }
}
