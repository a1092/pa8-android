package com.efrei.pa8.kylin.beta.json;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.util.Log;

import com.efrei.pa8.kylin.beta.contacts.model.Contact;
import com.efrei.pa8.kylin.beta.loans.model.Loan;
import com.efrei.pa8.kylin.beta.shoppingList.model.ShoppingItem;
import com.efrei.pa8.kylin.beta.shoppingList.model.ShoppingList;
import com.efrei.pa8.kylin.beta.todotasks.model.ToDoTask;
import com.efrei.pa8.kylin.beta.tools.Data;
import com.efrei.pa8.kylin.beta.tools.User;


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
		Data.getInstance().setId(Integer.parseInt(jObj.getString("id")));

		if(result.equals("success")){
			Log.d("égal ? : ", "oui");
			Data.getInstance().setId(Integer.parseInt(jObj.getString("id")));
			return true;
		}
		else{
			Log.d("égal ? : ", "non");
			return false;
		}
	}

	public static String toJsonId() {
		try {
			// Here we convert Java Object to JSON 
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("id", Data.getInstance().getId());

			return jsonObj.toString();
		}
		catch(JSONException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public void fromJsonToDataBaseGetContacts(String data) throws JSONException{

		JSONObject jObj = new JSONObject(data);		

		JSONArray jArr = jObj.getJSONArray("entities");
		
		Data.getInstance().setContactList(new ArrayList<Contact> ());
		for(int i = 0 ; i < jArr.length() ; i++){
			JSONObject jSONObject = jArr.getJSONObject(i);
			
			String contactName = jSONObject.getString("contactName");
			if(contactName.equals("null"))contactName = "";
			String contactEmail = jSONObject.getString("contactEmail");
			if(contactEmail.equals("null"))contactEmail = "";
			String contactAddress = jSONObject.getString("contactAddress");
			if(contactAddress.equals("null"))contactAddress = "";
			String contactHomePhoneNumber = jSONObject.getString("contactHomePhoneNumber");
			if(contactHomePhoneNumber.equals("null"))contactHomePhoneNumber = "";
			String contactMobilePhoneNumber = jSONObject.getString("contactMobilePhoneNumber");
			if(contactMobilePhoneNumber.equals("null"))contactMobilePhoneNumber = "";
			String contactOtherPhoneNumber = jSONObject.getString("contactOtherPhoneNumber");
			if(contactOtherPhoneNumber.equals("null"))contactOtherPhoneNumber = "";
			String contactCategory = jSONObject.getString("contactCategory");
			if(contactCategory.equals("null"))contactCategory = "";
			String contactRemark = jSONObject.getString("contactRemark");
			if(contactRemark.equals("null"))contactRemark = "";
			
			
			Data.getInstance().addContact(new Contact(jSONObject.getInt("contactId"),
					contactName,
					contactEmail,
					contactAddress,
					contactHomePhoneNumber,
					contactMobilePhoneNumber,
					contactOtherPhoneNumber,
					contactCategory,
					contactRemark
					));
			Log.d("name: ", jSONObject.getString("contactName"));
		}
	}
	
	public static String toJsonCreateContact(String name, String email, String address, String homePhoneNumber, String mobilePhoneNumber, String otherPhoneNumber,
			String remark, String category) {
		try {
			// Here we convert Java Object to JSON 
			JSONObject jsonObj = new JSONObject();
			Log.d("name name: ", name);
			Log.d("name email: ", email);
			Log.d("name address: ", address);
			Log.d("name homePhoneNumber: ", homePhoneNumber);
			jsonObj.put("id", String.valueOf(Data.getInstance().getId()));
			jsonObj.put("name", name);
			jsonObj.put("email", email);
			jsonObj.put("name", name);
			jsonObj.put("address", address);
			jsonObj.put("homePhoneNumber", homePhoneNumber);
			jsonObj.put("mobilePhoneNumber", mobilePhoneNumber);
			jsonObj.put("name", name);
			jsonObj.put("otherPhoneNumber", otherPhoneNumber);
			jsonObj.put("remark", remark);
			jsonObj.put("category", category);

			return jsonObj.toString();
		}
		catch(JSONException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static String toJsonModifyContact(int id, String name, String email, String address, String homePhoneNumber, String mobilePhoneNumber, String otherPhoneNumber,
			String remark, String category) {
		try {
			// Here we convert Java Object to JSON 
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("idUser", String.valueOf(Data.getInstance().getId()));
			jsonObj.put("idContact", String.valueOf(id));
			jsonObj.put("name", name);
			jsonObj.put("email", email);
			jsonObj.put("name", name);
			jsonObj.put("address", address);
			jsonObj.put("homePhoneNumber", homePhoneNumber);
			jsonObj.put("mobilePhoneNumber", mobilePhoneNumber);
			jsonObj.put("name", name);
			jsonObj.put("otherPhoneNumber", otherPhoneNumber);
			jsonObj.put("remark", remark);
			jsonObj.put("category", category);

			return jsonObj.toString();
		}
		catch(JSONException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static String toJsonDeleteContact(int id) {
		try {
			// Here we convert Java Object to JSON 
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("idUser", String.valueOf(Data.getInstance().getId()));
			jsonObj.put("idContact", String.valueOf(id));

			return jsonObj.toString();
		}
		catch(JSONException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	@SuppressLint("SimpleDateFormat")
	public void fromJsonToDataBaseGetShoppingLists(String data) throws JSONException{
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date deadline = new Date();
		int articleId;
		String articleName;
		String articleQuantity;
		
		// We create out JSONObject from the data

		JSONObject jObj = new JSONObject(data);
		
		JSONArray jArr = jObj.getJSONArray("entities");
		
		Data.getInstance().setShoppingListList(new ArrayList<ShoppingList> ());
		for(int i = 0 ; i < jArr.length() ; i++){
			JSONObject jSONObject = jArr.getJSONObject(i);

			int listId = jSONObject.getInt("listId");
			String listName = jSONObject.getString("listName");
			if(listName.equals("null"))listName = "";
			
			JSONObject jSONObjectDeadline = jSONObject.getJSONObject("listDeadline");
			try {
				deadline= outFormat.parse(jSONObjectDeadline.getString("date"));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			Data.getInstance().addShoppingList(new ShoppingList(listId, listName, deadline));
			
			
			JSONArray jSONArrayArticles = jSONObject.getJSONArray("articles");
			for(int j = 0 ; j < jSONArrayArticles.length() ; j++){
				JSONObject jSONArticle = jSONArrayArticles.getJSONObject(j);
				articleName = "";

				articleId = jSONArticle.getInt("articleId");
				articleName = jSONArticle.getString("articleName");
				articleQuantity = jSONArticle.getString("articleQuantity");
				
				Data.getInstance().getShoppingListList().get(i).addShoppingitem(new ShoppingItem(articleId, articleName, articleQuantity));

				Log.d("article: ", Data.getInstance().getShoppingListList().get(i).getShoppingItems().get(j).toString());
			}
			
			Log.d("name: ", jSONObject.getString("listName"));
		}
	}
	
	public static String toJsonCreateShoppingItem(String itemName, String itemQuantity) {
		try {
			// Here we convert Java Object to JSON 
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("idUser", String.valueOf(Data.getInstance().getId()));
			jsonObj.put("idShoppingList", String.valueOf(Data.getInstance().getShoppingListList().get(Data.getInstance().getListPosition()).getId()));
			jsonObj.put("shoppingItemName", itemName);
			jsonObj.put("shoppingItemQuantity", itemQuantity);

			return jsonObj.toString();
		}
		catch(JSONException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static String toJsonEditShoppingItem(String itemName, String itemQuantity) {
		try {
			// Here we convert Java Object to JSON 
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("idUser", String.valueOf(Data.getInstance().getId()));
			ShoppingList shoppingList= Data.getInstance().getShoppingListList().get(Data.getInstance().getListPosition());
			jsonObj.put("idShoppingList", String.valueOf(shoppingList.getId()));
			jsonObj.put("idShoppingItem", String.valueOf(shoppingList.getShoppingItems().get(Data.getInstance().getShoppingItemPosition()).getId()));
			jsonObj.put("shoppingItemName", itemName);
			jsonObj.put("shoppingItemQuantity", itemQuantity);

			return jsonObj.toString();
		}
		catch(JSONException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static String toJsonDeleteShoppingItem(int shoppingItemToDeletePosition) {		
		try {
			// Here we convert Java Object to JSON 
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("idUser", String.valueOf(Data.getInstance().getId()));
			jsonObj.put("idShoppingItem", String.valueOf(shoppingItemToDeletePosition));

			return jsonObj.toString();
		}
		catch(JSONException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public void fromJsonToDataBaseGetLoanList(String data) throws JSONException{
		// We create out JSONObject from the data

		JSONObject jObj = new JSONObject(data);

		Data.getInstance().setLoanList(new ArrayList<Loan>());
		Data.getInstance().setUserList(new ArrayList<User>());

		JSONArray jArrLoans = jObj.getJSONArray("tabLoans");
		for(int j = 0 ; j < jArrLoans.length() ; j++){

			JSONObject jSONObject = jArrLoans.getJSONObject(j);
			
			String object = jSONObject.getString("object");
			int idLender = Integer.parseInt(jSONObject.getString("idLender"));
			String nameLender = jSONObject.getString("nameLender");
			int idBorrower = Integer.parseInt(jSONObject.getString("idBorrower"));
			String nameBorrower = jSONObject.getString("nameBorrower");
			
			Data.getInstance().addloan(new Loan(object, idBorrower, nameBorrower, idLender, nameLender));
			
		}

		JSONArray jArrUsers = jObj.getJSONArray("tabUsers");
		for(int j = 0 ; j < jArrUsers.length() ; j++){

			JSONObject jSONObject = jArrUsers.getJSONObject(j);
			
			int userId = Integer.parseInt(jSONObject.getString("userId"));
			String userName = jSONObject.getString("userName");
			
			Data.getInstance().addUser(new User(userId, userName));
		}
	}
	
	public static String toJsonNewLoan(String loanObject, int positionLoanLender, int positionLoanBorrower) {
		try {
			// Here we convert Java Object to JSON 
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("userId", String.valueOf(Data.getInstance().getId()));
			jsonObj.put("loanObject", loanObject);
			jsonObj.put("idLender", Data.getInstance().getUserList().get(positionLoanLender).getId());
			jsonObj.put("idBorrower", Data.getInstance().getUserList().get(positionLoanBorrower).getId());

			return jsonObj.toString();
		}
		catch(JSONException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@SuppressLint("SimpleDateFormat")
	public void fromJsonToDataBaseGetToDoTasks(String data) throws JSONException{
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date deadline = new Date();
		String taskUserName;
		int taskUserId;

		JSONObject jObj = new JSONObject(data);
		JSONArray jArr = jObj.getJSONArray("tabToDoTasks");
		
		Data.getInstance().setUserList(new ArrayList<User>());
		Data.getInstance().setTodoTaskList(new ArrayList<ToDoTask>());
		
		for(int i = 0 ; i < jArr.length() ; i++){
			JSONObject jSONObject = jArr.getJSONObject(i);

			int taskId = jSONObject.getInt("taskId");
			String taskName = jSONObject.getString("taskName");
			String taskDescription = jSONObject.getString("taskDescription");
			JSONObject jSONObjectDeadline = jSONObject.getJSONObject("taskDeadline");
			try {
				deadline= outFormat.parse(jSONObjectDeadline.getString("date"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			Data.getInstance().addToDoTaks(new ToDoTask(taskId,taskName, deadline, taskDescription));
			
			JSONArray jSONArrayUsers = jSONObject.getJSONArray("users");
			for(int j = 0 ; j < jSONArrayUsers.length() ; j++){
				JSONObject jSONArticle = jSONArrayUsers.getJSONObject(j);

				taskUserId = jSONArticle.getInt("taskUserId");
				taskUserName = jSONArticle.getString("taskUserName");
				
				Data.getInstance().getTodoTaskList().get(i).addUser(new User(taskUserId, taskUserName));
			}
		}
		
		JSONArray jArrUsers = jObj.getJSONArray("tabUsers");
		for(int j = 0 ; j < jArrUsers.length() ; j++){

			JSONObject jSONObject = jArrUsers.getJSONObject(j);
			
			int userId = Integer.parseInt(jSONObject.getString("userId"));
			String userName = jSONObject.getString("userName");
			
			Data.getInstance().addUser(new User(userId, userName));
		}
	}
}
