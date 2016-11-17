package com.example.aluno.backendscreens.object;

/**
 * Created by Aluno on 31/08/2016.
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
