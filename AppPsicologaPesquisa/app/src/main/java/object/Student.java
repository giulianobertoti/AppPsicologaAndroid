package object;


/**
 * Created by 4º Semestre de 2016
 * Curso de Banco
 * Fatec SJC
 */

import java.util.List;


/**
 * Classe Student, com get e toString para manipular as informações do estudante
 */

public class Student {

    private String name;
    private String course;
    private String institution;
    private List<Competencie> competencies;
    private int userCode;
    private int period;
    private int year;
    private long ra;

    public Student(){

    }

    /**
     * Construtor da classe
     */
    public Student(String name, String course, String institution, List<Competencie> competencies, int userCode, int period, int year, long ra){

        this.setName(name);
        this.setCourse(course);
        this.setInstitution(institution);
        this.setCompetencies(competencies);
        this.setUserCode(userCode);
        this.setPeriod(period);
        this.setYear(year);
        this.setRa(ra);

    }


    /**
     * método getName
     * @return name - nome do estudante
     */
    public String getName() {
        return name;
    }

    /**
     * método getCourse
     * @return course - nome do curso do estudante
     */
    public String getCourse() {
        return course;
    }

    /**
     * método getInstitution
     * @return institution - nome da instituição do estudante
     */
    public String getInstitution() {
        return institution;
    }

    /**
     * método getCompetencies
     * @return competencies - lista de competencias
     */
    public List<Competencie> getCompetencies() {
        return competencies;
    }

    /**
     * método getUserCode
     * @return userCode - código do usuário
     */
    public int getUserCode() {
        return userCode;
    }

    /**
     * método getPeriod
     * @return period - periodo do estudante
     */
    public int getPeriod() {
        return period;
    }

    /**
     * método getYear
     * @return year - ano do estudante
     */
    public int getYear() {
        return year;
    }

    /**
     * método getRa
     * @return ra - ra do estudante
     */
    public long getRa() {
        return ra;
    }




    /**
     * método toString - transforma as informações do estudante em string
     * @return str - string construida
     */
    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();

        str.append("\nNome: ");
        str.append(this.name);
        str.append("\nCurso: ");
        str.append(this.course);
        str.append("\nInstitui��o: ");
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

        //loop, que irá dar append em todas as competencias do estudante
        for(Competencie con : this.competencies)
        {
            str.append(con.toString());
        }

        return str.toString();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public void setCompetencies(List<Competencie> competencies) {
        this.competencies = competencies;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setRa(long ra) {
        this.ra = ra;
    }
}
