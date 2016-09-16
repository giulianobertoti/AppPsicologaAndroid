package br.com.appbackend.REST;
	
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Aluno on 10/08/2016.
 */public class RestConnection
 	{

    private static final String USER_AGENT = "Mozzilla/5.0";

    public static JSONArray sendGetArray(String url) throws IOException, JSONException
    {

        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);

        URL obj = new URL(url);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        con.setRequestProperty( "charset", "utf-8");
        
//        int responseCode = con.getResponseCode();
//
//        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return new JSONArray(response.toString());
    }

    public static JSONObject sendGetObject(String url) throws IOException, JSONException
    {
        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);

        URL obj = new URL(url);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        con.setRequestProperty( "charset", "utf-8");
//        int responseCode = con.getResponseCode();
//
//        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();


        return new JSONObject(response.toString());
    }

    
    public static JSONObject sendPostObject(String url, JSONObject params) throws IOException, JSONException
    {
        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);

        URL obj = new URL(url);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("POST");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        con.setRequestProperty( "Content-Type", "application/json");
        con.setRequestProperty( "charset", "utf-8");
        con.setDoOutput(true);
           
        byte[] postData = params.toString().getBytes( StandardCharsets.UTF_8 );
        int postDataLength = postData.length;

        con.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
        
        try( DataOutputStream wr = new DataOutputStream(con.getOutputStream())) 
        {
        	wr.write( postData );
    	}
        catch (Exception e) {
        	e.printStackTrace();
		}

//        int responseCode = con.getResponseCode();
//        System.out.println("Response Code : " + responseCode);
        
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();


        return new JSONObject(response.toString());
    }

    public static JSONArray sendPostArray(String url, JSONObject params) throws IOException, JSONException
    {
        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);

        URL obj = new URL(url);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("POST");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty( "Content-Type", "application/json");
        con.setRequestProperty( "charset", "utf-8");
        con.setDoOutput(true);
             
        byte[] postData = params.toString().getBytes( StandardCharsets.UTF_8 );
        int postDataLength = postData.length;

        con.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
        
        try( DataOutputStream wr = new DataOutputStream(con.getOutputStream())) 
        {
        	wr.write( postData );
    	}
        catch (Exception e) {
        	e.printStackTrace();
		}

//        int responseCode = con.getResponseCode();
//    	System.out.println("Response Code : " + responseCode);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        
        in.close();

        return new JSONArray(response.toString());
    }
    
}