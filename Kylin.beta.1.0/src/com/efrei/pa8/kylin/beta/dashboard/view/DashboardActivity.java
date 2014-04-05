package com.efrei.pa8.kylin.beta.dashboard.view;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.efrei.pa8.kylin.beta.R;
import com.efrei.pa8.kylin.beta.contacts.view.ContactsActivity;
import com.efrei.pa8.kylin.beta.loans.view.LoansActivity;
import com.efrei.pa8.kylin.beta.shoppingList.view.ChooseAShoppingListActivity;
import com.efrei.pa8.kylin.beta.todotasks.view.ToDoTaskActivity;
import com.efrei.pa8.kylin.beta.tools.Data;
import com.efrei.pa8.kylin.beta.web.CustomHttpClientSG;

public class DashboardActivity extends Activity {
	String userName;
	String password;
	Button btn_dashboard_open_contacts;
	Button btn_dashboard_open_shoppingList;
	Button btn_dashboard_open_loans;
	Button btn_dashboard_open_TODOList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);

		TextView textView_dashboard_welcome = (TextView) findViewById(R.id.textView_dashboard_welcome);
		textView_dashboard_welcome.setText("Bienvenue sur la page d'accueil de l'application, " + Data.getInstance().getUsername() + "!");

		btn_dashboard_open_contacts = (Button) findViewById(R.id.btn_dashboard_open_contacts);
		btn_dashboard_open_contacts.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				JSONRetrieveContactsTask jSONRetrieveContactsTask = new JSONRetrieveContactsTask();
				jSONRetrieveContactsTask.execute();
			}
		});

		btn_dashboard_open_shoppingList = (Button) findViewById(R.id.btn_dashboard_open_shoppingList);
		btn_dashboard_open_shoppingList.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				JSONRetrieveShoppingListsTask jSONRetrieveShoppingListsTask = new JSONRetrieveShoppingListsTask();
				jSONRetrieveShoppingListsTask.execute();
			}
		});
		
		btn_dashboard_open_loans = (Button) findViewById(R.id.btn_dashboard_open_loans);
		btn_dashboard_open_loans.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
				JSONRetrieveLoanListTask jSONRetrieveLoanListTask = new JSONRetrieveLoanListTask();
				jSONRetrieveLoanListTask.execute();
			}
		});

		btn_dashboard_open_TODOList = (Button) findViewById(R.id.btn_dashboard_open_TODOList);
		btn_dashboard_open_TODOList.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
				JSONRetrieveToDoTasksTask jSONRetrieveToDoTasksTask = new JSONRetrieveToDoTasksTask();
				jSONRetrieveToDoTasksTask.execute();
			}
		});
	}

	private class JSONRetrieveContactsTask extends AsyncTask<Void, Integer, Boolean>{

		@Override
		protected Boolean doInBackground(Void... arg0) {
			try {
				CustomHttpClientSG.getInstance().getContacts();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}

		@Override
		protected void onPostExecute(Boolean result) {			
			super.onPostExecute(result);
			Intent intent = new Intent(
					DashboardActivity.this,
					ContactsActivity.class);
			startActivity(intent);
		}
	}
	
	private class JSONRetrieveShoppingListsTask extends AsyncTask<Void, Integer, Boolean>{

		@Override
		protected Boolean doInBackground(Void... arg0) {
			try {
				CustomHttpClientSG.getInstance().getShoppingLists();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}

		@Override
		protected void onPostExecute(Boolean result) {			
			super.onPostExecute(result);
			Intent intent = new Intent(
					DashboardActivity.this,
					ChooseAShoppingListActivity.class);
			startActivity(intent);
		}
	}

	private class JSONRetrieveLoanListTask extends AsyncTask<Void, Integer, Boolean>{

		@Override
		protected Boolean doInBackground(Void... arg0) {
			try {
				CustomHttpClientSG.getInstance().getLoanList();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}

		@Override
		protected void onPostExecute(Boolean result) {			
			super.onPostExecute(result);
			Intent intent = new Intent(
					DashboardActivity.this,
					LoansActivity.class);
			startActivity(intent);
		}
	}

	private class JSONRetrieveToDoTasksTask extends AsyncTask<Void, Integer, Boolean>{

		@Override
		protected Boolean doInBackground(Void... arg0) {
			try {
				CustomHttpClientSG.getInstance().getToDoTasks();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}

		@Override
		protected void onPostExecute(Boolean result) {			
			super.onPostExecute(result);
			Intent intent = new Intent(
					DashboardActivity.this,
					ToDoTaskActivity.class);
			startActivity(intent);
		}
	}
}
