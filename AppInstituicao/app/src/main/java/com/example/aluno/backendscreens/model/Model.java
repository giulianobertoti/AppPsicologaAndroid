package com.example.aluno.backendscreens.model;

import com.example.aluno.backendscreens.object.Student;
import com.example.aluno.backendscreens.rest.RestConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Aluno on 24/08/2016.
 */
public class Model {

    public static List<String> courseReturn(int institution){
        List<String> courses = new LinkedList<>();

            String url = "http://teste-inacio.rhcloud.com/fatec/map/institution/find/all/courses";

            try
            {
                JSONArray response = RestConnection.sendGetArray(url);



                for (int i = 0; i < response.length(); i++)
                {
                    JSONObject obj = response.getJSONObject(i);

                    if(obj.getInt("code") == institution){
                        JSONArray coursesArray = obj.getJSONArray("courses");
                        for(int j = 0; j < coursesArray.length(); j++){
                            JSONObject json = coursesArray.getJSONObject(j);
                            courses.add(json.getString("name"));
                        }

                    }

                }
            }
            catch (JSONException e) {
            }
            catch (Exception e)
            {
            }
        return courses;
    }

    public static ArrayList<Student> studentsReturn(int institution, int period, int year, String course){
        ArrayList<Student> students = new ArrayList<Student>();
        String url = "http://teste-inacio.rhcloud.com/fatec/map/enrolls/search/students/all/fatec?fatecCode=" + institution;

        try{
            JSONArray response = RestConnection.sendGetArray(url);

            for (int i = 0; i < response.length(); i++)
            {
                JSONObject obj = response.getJSONObject(i);
                if(obj.getString("course").equals(course) && obj.getInt("period") == period && obj.getInt("year") == year){
                    Student student = new Student();

                    JSONObject json = obj.getJSONObject("user");
                    student.setVerification(obj.getInt("verification"));
                    student.setUserCode(json.getInt("userCode"));
                    student.setName(json.getString("name").toUpperCase());

                    students.add(student);
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return students;
    }

}
