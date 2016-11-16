package object;

import java.util.List;

/**
 * Created by renan on 17/08/16.
 */
public class Student {

    private String name;
    private String course;
    private String institution;
    private List<Competencie> competencies;
    private int userCode;
    private int period;
    private int year;
    private String ra;
    private String comment;

    public Student(String name, String course, String institution, List<Competencie> competencies, int userCode, int period, int year, String ra, String comment) {
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

    public String getRa() {
        return ra;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
