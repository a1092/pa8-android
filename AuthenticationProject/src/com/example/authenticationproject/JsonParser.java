package com.example.authenticationproject;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


public class JsonParser {

	public static String toJsonConnexion(String userName, String password) {
	      try {
	        // Here we convert Java Object to JSON 
	        JSONObject jsonObj = new JSONObject();
	        jsonObj.put("userName", userName);
	        jsonObj.put("password", password);

	        return jsonObj.toString();
	    }
	    catch(JSONException ex) {
	        ex.printStackTrace();
	    }
	    return null;
	}
	
	public boolean fromJsonTobooleanConnexion(String data) throws JSONException{
		// We create out JSONObject from the data
		JSONObject jObj = new JSONObject(data);
		String result = jObj.getString("data");
		
		if(result.equals("success")){
			Log.d("égal ? : ", "oui");
			return true;
		}
		else{
			Log.d("égal ? : ", "non");
			return false;
		}
	}
}
