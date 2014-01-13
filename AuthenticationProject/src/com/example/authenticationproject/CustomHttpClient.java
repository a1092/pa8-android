package com.example.authenticationproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class CustomHttpClient {
	
	
	private static String BASE_URL = "http://192.168.0.101/pa8/web/app_dev.php/android/";
	
	public boolean connect(String userName, String password)
			throws Exception {

		try {
			// instantiates httpclient to make request
			HttpClient httpclient = new DefaultHttpClient();

			// url with the post data
			HttpPost httpost = new HttpPost(BASE_URL + "connexion");

			JsonParserSG.getInstance();
			// passes the results to a string builder/entity
			StringEntity stringEntity = new StringEntity(
					JsonParser.toJsonConnexion(userName, password));
			
			Log.d("state : ", JsonParser.toJsonConnexion(userName, password));

			// sets the post request as the resulting string
			httpost.setEntity(stringEntity);

			HttpResponse httpResponse = httpclient.execute(httpost);

			HttpEntity httpEntity = httpResponse.getEntity();

			Log.d("Http Response:", httpEntity.toString());
			
			InputStream is = httpEntity.getContent();
			
			StringBuffer buffer = new StringBuffer();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			String line = null;
			
			while ((line = br.readLine()) != null) {
				buffer.append(line + "\r\n");
				Log.d("Line :", line);
			}
			
			is.close();
			
			Log.d("state : ", "sucess");
			return JsonParserSG.getInstance().fromJsonTobooleanConnexion(buffer.toString());

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// writing exception to log
			e.printStackTrace();
		} catch (IOException e) {
			// writing exception to log
			e.printStackTrace();

		}

		Log.d("state : ", "fail");
		return false;
	}
}
