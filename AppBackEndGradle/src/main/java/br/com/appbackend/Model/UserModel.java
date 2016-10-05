package br.com.appbackend.Model;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.appbackend.Object.User;
import br.com.appbackend.Object.UserType;
import br.com.appbackend.REST.RestConnection;

public class UserModel {
	
	public static User login(String username, String password){
		User ususarioLogado = new User();
		String url = "http://teste-inacio.rhcloud.com/fatec/map/token";

        try
        {
        	JSONObject req = new JSONObject();
        	req.put("userName", username);
        	req.put("password", password);
        	
        	JSONObject response = RestConnection.sendPostObject(url, req);
        	
        	System.out.println(response);
        	
        	if(response.getInt("userCode") > 0)
        	{
				ususarioLogado.setUserCode(response.getInt("userCode"));	
		    	ususarioLogado.setName(response.getString("name"));
		    	ususarioLogado.setInstCode(response.getInt("instCode"));
		    	
		    	//TODO tratar para valores diferentes dos que estão no enum
		    	ususarioLogado.setType(UserType.valueOf(response.getString("type").toUpperCase()));
		    	
		    	ususarioLogado.setToken(response.getString("token"));
		    	
		    	//TODO atrelar um student ou um Psicologo ao usuario dependendo do tipo
		    	
        	}
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        catch (IOException e)
        {
        	e.printStackTrace();
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
		
		return ususarioLogado;		
	}

}