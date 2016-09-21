package object;

/**
 * Created by 4º Semestre de 2016
 * Curso de Banco
 * Fatec SJC
 */


/**
 * Classe de competencia, que possui métodos get e toString para manipulação das informações de uma competência
 */
public class Competencie {

    private String type;
    private int weight;

    /**
     * Construtor
     */
    public Competencie(String name, int value){
        this.type = name;
        this.weight = value;
    }

    /**
     * método getType
     * @return type - nome (tipo) de uma competência
     */
    public String getType(){
        return type;
    }

    /**
     * método getWeight
     * @return weight - retorna o valor (peso) de uma competência
     */
    public int getWeight(){
        return weight;
    }

    /**
     * método toString - que manipula as informações de uma competência e monta uma string
     * @return str - string construída
     */
    @Override
    public String toString()
    {
        String str = "\nType:";
        str += this.type;
        str += "\nWeight:";
        str += this.weight;
        return str;
    }
}
