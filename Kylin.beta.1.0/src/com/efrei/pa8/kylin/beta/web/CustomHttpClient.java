package com.efrei.pa8.kylin.beta.web;

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

import com.efrei.pa8.kylin.beta.json.JsonParser;
import com.efrei.pa8.kylin.beta.json.JsonParserSG;
import com.efrei.pa8.kylin.beta.tools.Data;

public class CustomHttpClient {

	private static String BASE_URL = "http://10.0.2.2/pa8/web/app_dev.php/android/";
	
	public boolean connect(String userName, String password)
			throws Exception {

		try {
			
			// instantiates httpclient to make request
			HttpClient httpclient = new DefaultHttpClient();
			
			// url with the post data
			HttpPost httpost = new HttpPost(BASE_URL + "connexion");

			JsonParserSG.getInstance();
			// passes the results to a string builder/entity
			StringEntity stringEntity = new StringEntity(JsonParser.toJsonConnexion(userName, password));

			Log.d("Line :", JsonParser.toJsonConnexion(userName, password));
			// sets the post request as the resulting string
			httpost.setEntity(stringEntity);
											
			HttpResponse httpResponse = httpclient.execute(httpost);			
								
			HttpEntity httpEntity = httpResponse.getEntity();
			
			InputStream is = httpEntity.getContent();
			StringBuffer buffer = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			
			while ((line = br.readLine()) != null) {
				buffer.append(line + "\r\n");
				Log.d("Line :", line);
			}
			is.close();
			Log.d("state : ", "sucess connexion");
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

		Log.d("state : ", "fail connexion");
		return false;
	}
	
	public boolean getContacts()
			throws Exception {

		try {
			
			// instantiates httpclient to make request
			HttpClient httpclient = new DefaultHttpClient();
			
			// url with the post data
			HttpPost httpost = new HttpPost(BASE_URL + "getContacts");

			JsonParserSG.getInstance();
			// passes the results to a string builder/entity
			StringEntity stringEntity = new StringEntity(JsonParser.toJsonId());
			
			// sets the post request as the resulting string
			httpost.setEntity(stringEntity);
			
			HttpResponse httpResponse = httpclient.execute(httpost);
			
								
			HttpEntity httpEntity = httpResponse.getEntity();

			InputStream is = httpEntity.getContent();
			StringBuffer buffer = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			
			while ((line = br.readLine()) != null) {
				buffer.append(line + "\r\n");
				Log.d("Line :", line);
			}
			is.close();
			
			JsonParserSG.getInstance().fromJsonToDataBaseGetContacts(buffer.toString());
			Log.d("state : ", "sucess getContacts");
			
			return true;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// writing exception to log
			e.printStackTrace();
		} catch (IOException e) {
			// writing exception to log
			e.printStackTrace();
		}

		Log.d("state : ", "fail getContacts");
		return false;
	}
	
	public boolean newContact(String name, String email, String address, String homePhoneNumber, String mobilePhoneNumber, String otherPhoneNumber,
			String remark, String category)
			throws Exception {
		try {
			
			// instantiates httpclient to make request
			HttpClient httpclient = new DefaultHttpClient();
			
			// url with the post data
			HttpPost httpost = new HttpPost(BASE_URL + "newContact");

			JsonParserSG.getInstance();
			// passes the results to a string builder/entity
			StringEntity stringEntity = new StringEntity(JsonParser.toJsonCreateContact(name, email, address, homePhoneNumber, mobilePhoneNumber, otherPhoneNumber, remark, category));
			
			Log.d("Line :", JsonParser.toJsonCreateContact(name, email, address, homePhoneNumber, mobilePhoneNumber, otherPhoneNumber, remark, category));
			// sets the post request as the resulting string
			httpost.setEntity(stringEntity);
											
			HttpResponse httpResponse = httpclient.execute(httpost);			
								
			HttpEntity httpEntity = httpResponse.getEntity();

			InputStream is = httpEntity.getContent();
			StringBuffer buffer = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			
			while ((line = br.readLine()) != null) {
				buffer.append(line + "\r\n");
				Log.d("Line :", line);
			}
			is.close();
			
			JsonParserSG.getInstance().fromJsonToDataBaseGetContacts(buffer.toString());
			Log.d("state : ", "sucess getContacts");
			
			return true;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// writing exception to log
			e.printStackTrace();
		} catch (IOException e) {
			// writing exception to log
			e.printStackTrace();
		}

		Log.d("state : ", "fail getContacts");
		return false;
	}
	
	public boolean modifyContact(int contactPosition, String name, String email, String address, String homePhoneNumber, String mobilePhoneNumber, String otherPhoneNumber,
			String remark, String category)
			throws Exception {
		try {
			
			// instantiates httpclient to make request
			HttpClient httpclient = new DefaultHttpClient();
			
			// url with the post data
			HttpPost httpost = new HttpPost(BASE_URL + "editContact");

			JsonParserSG.getInstance();
			// passes the results to a string builder/entity
			StringEntity stringEntity = new StringEntity(JsonParser.toJsonModifyContact(Data.getInstance().getContactList().get(contactPosition).getId(), name, email, address, homePhoneNumber, mobilePhoneNumber, otherPhoneNumber, remark, category));
			
			Log.d("Line :", JsonParser.toJsonModifyContact(Data.getInstance().getContactList().get(contactPosition).getId(), name, email, address, homePhoneNumber, mobilePhoneNumber, otherPhoneNumber, remark, category));
			// sets the post request as the resulting string
			httpost.setEntity(stringEntity);
											
			HttpResponse httpResponse = httpclient.execute(httpost);			
								
			HttpEntity httpEntity = httpResponse.getEntity();

			InputStream is = httpEntity.getContent();
			StringBuffer buffer = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			
			while ((line = br.readLine()) != null) {
				buffer.append(line + "\r\n");
				Log.d("Line :", line);
			}
			is.close();
			
			JsonParserSG.getInstance().fromJsonToDataBaseGetContacts(buffer.toString());
			Log.d("state : ", "sucess getContacts");
			
			return true;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// writing exception to log
			e.printStackTrace();
		} catch (IOException e) {
			// writing exception to log
			e.printStackTrace();
		}

		Log.d("state : ", "fail getContacts");
		return false;
	}
	
	public boolean deleteContact(int contactPosition)
			throws Exception {
		try {
			
			// instantiates httpclient to make request
			HttpClient httpclient = new DefaultHttpClient();
			
			// url with the post data
			HttpPost httpost = new HttpPost(BASE_URL + "deleteContact");

			JsonParserSG.getInstance();
			// passes the results to a string builder/entity
			StringEntity stringEntity = new StringEntity(JsonParser.toJsonDeleteContact(Data.getInstance().getContactList().get(contactPosition).getId()));
			
			// sets the post request as the resulting string
			httpost.setEntity(stringEntity);
											
			HttpResponse httpResponse = httpclient.execute(httpost);			
								
			HttpEntity httpEntity = httpResponse.getEntity();

			InputStream is = httpEntity.getContent();
			StringBuffer buffer = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			
			while ((line = br.readLine()) != null) {
				buffer.append(line + "\r\n");
				Log.d("Line :", line);
			}
			is.close();
			
			JsonParserSG.getInstance().fromJsonToDataBaseGetContacts(buffer.toString());
			Log.d("state : ", "sucess getContacts");
			
			return true;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// writing exception to log
			e.printStackTrace();
		} catch (IOException e) {
			// writing exception to log
			e.printStackTrace();
		}

		Log.d("state : ", "fail getContacts");
		return false;
	}
	
	public boolean getShoppingLists()
			throws Exception {

		try {
			
			// instantiates httpclient to make request
			HttpClient httpclient = new DefaultHttpClient();
			
			// url with the post data
			HttpPost httpost = new HttpPost(BASE_URL + "getShoppingLists");

			JsonParserSG.getInstance();
			// passes the results to a string builder/entity
			StringEntity stringEntity = new StringEntity(JsonParser.toJsonId());
			
			// sets the post request as the resulting string
			httpost.setEntity(stringEntity);
			
			HttpResponse httpResponse = httpclient.execute(httpost);
			
								
			HttpEntity httpEntity = httpResponse.getEntity();

			InputStream is = httpEntity.getContent();
			StringBuffer buffer = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			
			while ((line = br.readLine()) != null) {
				buffer.append(line + "\r\n");
				Log.d("Line :", line);
			}
			is.close();
			
			JsonParserSG.getInstance().fromJsonToDataBaseGetShoppingLists(buffer.toString());
			Log.d("state : ", "sucess getContacts");
			
			return true;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// writing exception to log
			e.printStackTrace();
		} catch (IOException e) {
			// writing exception to log
			e.printStackTrace();
		}

		Log.d("state : ", "fail getContacts");
		return false;
	}
	
	public boolean addShoppingItem(String itemName, String itemQuantity) throws Exception {

		try {
			
			// instantiates httpclient to make request
			HttpClient httpclient = new DefaultHttpClient();
			
			// url with the post data
			HttpPost httpost = new HttpPost(BASE_URL + "newShoppingItem");

			JsonParserSG.getInstance();
			// passes the results to a string builder/entity
			StringEntity stringEntity = new StringEntity(JsonParser.toJsonCreateShoppingItem(itemName, itemQuantity));
			
			// sets the post request as the resulting string
			httpost.setEntity(stringEntity);
			
			HttpResponse httpResponse = httpclient.execute(httpost);
			
								
			HttpEntity httpEntity = httpResponse.getEntity();

			InputStream is = httpEntity.getContent();
			StringBuffer buffer = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			
			while ((line = br.readLine()) != null) {
				buffer.append(line + "\r\n");
				Log.d("Line :", line);
			}
			is.close();
			
			JsonParserSG.getInstance().fromJsonToDataBaseGetShoppingLists(buffer.toString());
			Log.d("state : ", "sucess getContacts");
			
			return true;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// writing exception to log
			e.printStackTrace();
		} catch (IOException e) {
			// writing exception to log
			e.printStackTrace();
		}

		Log.d("state : ", "fail getContacts");
		return false;
	}
	
	public boolean editShoppingItem(String itemName, String itemQuantity) throws Exception {

		try {
			
			// instantiates httpclient to make request
			HttpClient httpclient = new DefaultHttpClient();
			
			// url with the post data
			HttpPost httpost = new HttpPost(BASE_URL + "editShoppingItem");

			JsonParserSG.getInstance();
			// passes the results to a string builder/entity
			StringEntity stringEntity = new StringEntity(JsonParser.toJsonEditShoppingItem(itemName, itemQuantity));
			
			// sets the post request as the resulting string
			httpost.setEntity(stringEntity);
			
			HttpResponse httpResponse = httpclient.execute(httpost);
			
								
			HttpEntity httpEntity = httpResponse.getEntity();

			InputStream is = httpEntity.getContent();
			StringBuffer buffer = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			
			while ((line = br.readLine()) != null) {
				buffer.append(line + "\r\n");
				Log.d("Line :", line);
			}
			is.close();
			
			JsonParserSG.getInstance().fromJsonToDataBaseGetShoppingLists(buffer.toString());
			Log.d("state : ", "sucess getContacts");
			
			return true;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// writing exception to log
			e.printStackTrace();
		} catch (IOException e) {
			// writing exception to log
			e.printStackTrace();
		}

		Log.d("state : ", "fail getContacts");
		return false;
	}
	
	public boolean deleteShoppingItem(int shoppingItemToDeletePosition) throws Exception {

		try {
			
			// instantiates httpclient to make request
			HttpClient httpclient = new DefaultHttpClient();
			
			// url with the post data
			HttpPost httpost = new HttpPost(BASE_URL + "deleteShoppingItem");

			JsonParserSG.getInstance();
			// passes the results to a string builder/entity
			StringEntity stringEntity = new StringEntity(JsonParser.toJsonDeleteShoppingItem(shoppingItemToDeletePosition));
			
			// sets the post request as the resulting string
			httpost.setEntity(stringEntity);
			
			HttpResponse httpResponse = httpclient.execute(httpost);
			
								
			HttpEntity httpEntity = httpResponse.getEntity();

			InputStream is = httpEntity.getContent();
			StringBuffer buffer = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			
			while ((line = br.readLine()) != null) {
				buffer.append(line + "\r\n");
				Log.d("Line :", line);
			}
			is.close();
			
			JsonParserSG.getInstance().fromJsonToDataBaseGetShoppingLists(buffer.toString());
			Log.d("state : ", "sucess getContacts");
			
			return true;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// writing exception to log
			e.printStackTrace();
		} catch (IOException e) {
			// writing exception to log
			e.printStackTrace();
		}

		Log.d("state : ", "fail getContacts");
		return false;
	}
	
	public boolean getLoanList()
			throws Exception {

		try {
			
			// instantiates httpclient to make request
			HttpClient httpclient = new DefaultHttpClient();
			
			// url with the post data
			HttpPost httpost = new HttpPost(BASE_URL + "getLoans");

			JsonParserSG.getInstance();
			// passes the results to a string builder/entity
			StringEntity stringEntity = new StringEntity(JsonParser.toJsonId());
			
			// sets the post request as the resulting string
			httpost.setEntity(stringEntity);
			
			HttpResponse httpResponse = httpclient.execute(httpost);
			
								
			HttpEntity httpEntity = httpResponse.getEntity();

			InputStream is = httpEntity.getContent();
			StringBuffer buffer = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			
			while ((line = br.readLine()) != null) {
				buffer.append(line + "\r\n");
				Log.d("Line :", line);
			}
			is.close();
			
			JsonParserSG.getInstance().fromJsonToDataBaseGetLoanList(buffer.toString());
			Log.d("state : ", "sucess getContacts");
			
			return true;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// writing exception to log
			e.printStackTrace();
		} catch (IOException e) {
			// writing exception to log
			e.printStackTrace();
		}

		Log.d("state : ", "fail getContacts");
		return false;
	}
	
	public boolean newLoan(String loanObject, int positionLoanLender, int positionLoanBorrower)
			throws Exception {

		try {
			
			// instantiates httpclient to make request
			HttpClient httpclient = new DefaultHttpClient();
			
			// url with the post data
			HttpPost httpost = new HttpPost(BASE_URL + "newLoan");

			JsonParserSG.getInstance();
			// passes the results to a string builder/entity
			StringEntity stringEntity = new StringEntity(JsonParser.toJsonNewLoan(loanObject, positionLoanLender, positionLoanBorrower));
			
			// sets the post request as the resulting string
			httpost.setEntity(stringEntity);
			
			HttpResponse httpResponse = httpclient.execute(httpost);
			
								
			HttpEntity httpEntity = httpResponse.getEntity();

			InputStream is = httpEntity.getContent();
			StringBuffer buffer = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			
			while ((line = br.readLine()) != null) {
				buffer.append(line + "\r\n");
				Log.d("Line :", line);
			}
			is.close();
			
			JsonParserSG.getInstance().fromJsonToDataBaseGetLoanList(buffer.toString());
			Log.d("state : ", "sucess getContacts");
			
			return true;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// writing exception to log
			e.printStackTrace();
		} catch (IOException e) {
			// writing exception to log
			e.printStackTrace();
		}

		Log.d("state : ", "fail getContacts");
		return false;
	}
	
	public boolean getToDoTasks()
			throws Exception {

		try {
			
			// instantiates httpclient to make request
			HttpClient httpclient = new DefaultHttpClient();
			
			// url with the post data
			HttpPost httpost = new HttpPost(BASE_URL + "getToDoTasks");

			JsonParserSG.getInstance();
			// passes the results to a string builder/entity
			StringEntity stringEntity = new StringEntity(JsonParser.toJsonId());
			
			// sets the post request as the resulting string
			httpost.setEntity(stringEntity);
			
			HttpResponse httpResponse = httpclient.execute(httpost);
			
								
			HttpEntity httpEntity = httpResponse.getEntity();

			InputStream is = httpEntity.getContent();
			StringBuffer buffer = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			
			while ((line = br.readLine()) != null) {
				buffer.append(line + "\r\n");
				Log.d("Line :", line);
			}
			is.close();
			
			JsonParserSG.getInstance().fromJsonToDataBaseGetToDoTasks(buffer.toString());
			Log.d("state : ", "sucess getContacts");
			
			return true;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// writing exception to log
			e.printStackTrace();
		} catch (IOException e) {
			// writing exception to log
			e.printStackTrace();
		}

		Log.d("state : ", "fail getContacts");
		return false;
	}
}
