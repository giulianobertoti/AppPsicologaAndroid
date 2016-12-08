package model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import object.User;
import object.UserType;
import rest.RestConnection;

/**
 * Created by Victor on 30/11/2016.
 */

public class UserModel {

    private static User userSession;

    public static String login(String username, String password){
        User ususarioLogado = null;
        String url = "http://teste-inacio.rhcloud.com/fatec/map/token";
        String responseText = "";
        try
        {
            JSONObject req = new JSONObject();
            req.put("userName", username);
            req.put("password", password);


            responseText = RestConnection.sendPostPlainText(url, req);

            JSONObject response = new JSONObject(responseText);

            if(response.getInt("userCode") > 0)
            {
                ususarioLogado = new User();
                ususarioLogado.setUserCode(response.getInt("userCode"));
                ususarioLogado.setName(response.getString("name"));
                ususarioLogado.setInstCode(response.getInt("instCode"));

                //TODO tratar para valores diferentes dos que estão no enum
                ususarioLogado.setType(UserType.getValue(response.getString("type")));

                ususarioLogado.setToken(response.getString("token"));

                //TODO atrelar um student ou um Psicologo ao usuario dependendo do tipo
            }

        }
        catch (JSONException e) {

            return  responseText;

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        userSession = ususarioLogado;
        return "OK";
    }

    public static User getUserSession()
    {
        return userSession;
    }

}