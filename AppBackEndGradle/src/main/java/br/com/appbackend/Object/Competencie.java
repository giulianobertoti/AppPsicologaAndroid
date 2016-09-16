package br.com.appbackend.Object;

/**
 * Created by Aluno on 10/08/2016.
 */
public class Competencie {

    private String type;
    private int weight;

    public Competencie(String name, int value){
        this.type = name;
        this.weight = value;
    }

    public String getType(){
        return type;
    }

    public int getWeight(){
        return weight;
    }

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
