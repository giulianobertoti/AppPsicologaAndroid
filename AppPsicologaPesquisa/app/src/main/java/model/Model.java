package model;

/**
 * Created by 4º Semestre de 2016
 * Curso de Banco
 * Fatec SJC
 */

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rest.Connection;
import object.Competencie;
import object.Student;

/**
 * A classe Model manipula a conexão, recebendo o JSONArray contendo as informações dos estudantes que serão usadas pela nossa aplicação
 */

public class Model {


    private List<Student> students = new ArrayList<>();

    /**
     * Variável de instancia da conexão
     * Obs: static pois a conexão não será mudada depois de instanciada
     */

    private static Connection con;

    static
    {
        con = new Connection();
    }

    /**
     * Método que enviará a requisição GET ao WebService e retornará um estudante com suas competências
     * @param code - o código do estudante que será usado para a pesquisa correta das informações cadastradas sobre o estudante
     * @return webStudent - o método retorna um objeto estudante, que contém as informações retornadas pelo JSON
     * Obs: Para questão de estudo, utilize o modo debug para ver o retorno dos logs inseridos no código
     */
    public Student searchByCode(long code)
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

        try {
            //Envio do url com o código inserido pelo usuário, que trará o objeto
            //Obs: sendGetObject é um método da classe Connection
            JSONObject response = con.sendGetObject(url);
            //retorna a array de competencies, um dos valores da response
            JSONArray competencies = response.getJSONArray("competencies");

            /* Conteúdo da variável response para o code = 16
            * {"userCode":16,"name":"Hugo Almeida","ra":"1234567890","period":2,"year":2016,"course":"Banco de Dados","institution":"Fatec Jessen Vidal",
            * "competencies":[{"code":1,"type":"teste competência 1","weight":111,"situation":0},{"code":2,"type":"teste competência 2","weight":35,"situation":0},
            * {"code":3,"type":"teste competência 3","weight":37,"situation":0},{"code":4,"type":"teste competência 4.1","weight":19,"situation":0}]} */
            Log.d("response", response.toString());

            /*Conteúdo da váriavel competencies para o code = 16
            *[{"code":1,"type":"teste competência 1","weight":111,"situation":0},{"code":2,"type":"teste competência 2","weight":35,"situation":0},
            * {"code":3,"type":"teste competência 3","weight":37,"situation":0},{"code":4,"type":"teste competência 4.1","weight":19,"situation":0}*/
            Log.d("competencies", competencies.toString());

            List<Competencie> lCompetencies = new ArrayList<>();

            //Este for utilizará o JSONArray e quebrará a String inserindo as competencias (nome e valor de cada) no ArrayList lCompetencies
            for (int i = 0; i < competencies.length(); i++)
            {
                //Este comando pega do Array competencies a posição (i) das chaves do array
                //Ex: (i = 0) - primeira chave (obj) = {"code":1,"type":"teste competência 1","weight":111,"situation":0}
                //Ex: (i = 1) - segunda chave  (obj) = {"code":2,"type":"teste competência 2","weight":35,"situation":0}
                JSONObject obj = competencies.getJSONObject(i);
                Log.d("forModel "+i, obj.toString());

                //insere os valores "type" e "weight" na classe Competencie e atribui às posições do Array lCompetencies
                lCompetencies.add(new Competencie(obj.getString("type"),obj.getInt("weight")));
            }

            //Insere as informações do Student na variável webStudent, declarada mais acima
            webStudent = new Student(response.getString("name"), response.getString("course"), response.getString("institution")
                    , lCompetencies, response.getInt("userCode"), response.getInt("period"), response.getInt("year"), response.getLong("ra"));

            //insere o estudante na lista instanciada no inicio da classe Model se a variável não for nula
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

}
