package com.example.cdiagno;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.StrictMode;

/* this class helps to get data from server*/
public class ClientServerInterface {
//input stream deals with bytes
static InputStream is = null;
static JSONObject phrase = null;
static JSONArray diagnosis = null;
static String json = "";
String sym_id = null;
String appid = "2b0c7398";
String appkey ="b53bfd4d57e2c49bc1a23d27dcd649cb";
//constructor
public ClientServerInterface(){
	
}
public String getPhrase(String x) throws JSONException{
	StringBuilder builder = new StringBuilder();
	
	
	
	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	StrictMode.setThreadPolicy(policy); 
	
	HttpClient client = new DefaultHttpClient();
	
	HttpGet httpGet = new HttpGet();

	 URI uri = null;
	try {
		uri = new URI("https://api.infermedica.com/v1/lookup?phrase="+x);
		//uri = new URI("https://api.infermedica.com/v1/lookup?phrase=headache");

	} catch (URISyntaxException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
        httpGet.setURI(uri);
        httpGet.addHeader("app_id",appid);
       httpGet.addHeader("app_key",appkey);

	try {
		HttpResponse response = client.execute(httpGet);
		System.out.println("1");
		StatusLine statusLine = response.getStatusLine();
		System.out.println("2");
		int statusCode = statusLine.getStatusCode();
		System.out.println("cozzzzzz:"+statusCode);
	
		if (statusCode == 200) {
			HttpEntity entity = response.getEntity();
			System.out.println("3");
			InputStream content = entity.getContent();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(content));
			String line;
			System.out.println("4");
			while ((line = reader.readLine()) != null) {
				builder.append(line);
				System.out.println(line);
				
				phrase = new JSONObject(line);
				sym_id = phrase.getString("id");
				return sym_id;
			}
		} else {
			System.out.println(
					"Failed to connect to Server");

		
		}
	} catch (ClientProtocolException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return sym_id;
	
}
public JSONArray getdiag(String sex,String age,String spm0,String spm1,String spm2,String spm3) throws JSONException{
    HttpParams httpParams = new BasicHttpParams();
    HttpClient client = new DefaultHttpClient(httpParams);
    HttpPost httpost = new HttpPost();
    URI uri = null;
	try {
		uri = new URI("https://api.infermedica.com/v1/diagnosis");
		//uri = new URI("https://api.infermedica.com/v1/lookup?phrase=headache");

	} catch (URISyntaxException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
        httpost.setURI(uri);
  //  httpost.setHeader("Accept", "application/json");
    httpost.addHeader("app_id",appid);
       httpost.addHeader("app_key",appkey);

    httpost.setHeader("Content-type", "application/json");
    System.out.println("2");
    JSONArray req = new JSONArray();
    JSONObject data = new JSONObject();
    if(spm0!=null){
    
    JSONObject sym0 = new JSONObject();
    sym0.put("id",spm0);
    sym0.put("choice_id","present");
    req.put(sym0);
    }
    if(spm1!=null){
    JSONObject sym1 = new JSONObject();
    sym1.put("id",spm1);
    sym1.put("choice_id","present");
    req.put(sym1);
    }
    if(spm2!=null){
    JSONObject sym2 = new JSONObject();
    sym2.put("id",spm2);
    sym2.put("choice_id","present");
    req.put(sym2);
    }
    if(spm3!=null){
    JSONObject sym3 = new JSONObject();
    sym3.put("id",spm3);
    sym3.put("choice_id","present");
    req.put(sym3);
    }
    HttpResponse response = null;
    try {
        data.put("sex",sex);
        data.put("age",age);
       
        data.put("evidence", req);
        System.out.println(data);
        StringEntity se = new StringEntity(data.toString());
        httpost.setEntity(se);
        try {
            response = client.execute(httpost);
            StatusLine statusLine = response.getStatusLine();
		System.out.println("2");
		int statusCode = statusLine.getStatusCode();
		System.out.println("cozzzzzz:"+statusCode);
            String res =EntityUtils.toString(response.getEntity());
            
            phrase = new JSONObject(res);
            String aar = phrase.getString("conditions");
            System.out.println(aar);
            diagnosis = new JSONArray(aar);
            return diagnosis;
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    } catch (JSONException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (UnsupportedEncodingException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	return diagnosis;
}
}
