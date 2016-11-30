package com.example.aluno.backendscreens.object;

import java.util.Comparator;

/**
 * Created by renan on 30/11/16.
 */

public class OrderByName implements Comparator<Student> {
    @Override
    public int compare(Student one, Student two){
        return one.getName().compareTo(two.getName());
    }
}
