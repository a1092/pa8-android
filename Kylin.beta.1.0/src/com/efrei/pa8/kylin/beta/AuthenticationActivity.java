package com.efrei.pa8.kylin.beta;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.efrei.pa8.kylin.beta.dashboard.view.DashboardActivity;
import com.efrei.pa8.kylin.beta.tools.Data;
import com.efrei.pa8.kylin.beta.web.CustomHttpClientSG;

public class AuthenticationActivity extends Activity {

	EditText editText_authentication_userName;
	EditText editText_authentication_password;
	Button btn_authentication_connect;
	Boolean connect;
	Boolean response;
	String login;
	String password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_authentication);
		
		response = false;

		editText_authentication_userName = (EditText) findViewById(R.id.editText_authentication_userName);
		editText_authentication_password = (EditText) findViewById(R.id.editText_authentication_password);
		btn_authentication_connect = (Button) findViewById(R.id.btn_authentication_connect);
		btn_authentication_connect.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					if ((editText_authentication_userName.getText().toString()
							.matches(""))
							|| (editText_authentication_password.getText()
									.toString().matches(""))) {
						if ((editText_authentication_userName.getText()
								.toString().matches(""))
								&& (!((editText_authentication_password
										.getText().toString().matches(""))))) {

							Toast.makeText(AuthenticationActivity.this,
									"Vous n'avez pas entré de nom  -_-",
									Toast.LENGTH_SHORT).show();
						} else if ((editText_authentication_password.getText()
								.toString().matches(""))
								&& (!((editText_authentication_userName
										.getText().toString().matches(""))))) {

							Toast.makeText(
									AuthenticationActivity.this,
									"Vous n'avez pas entré de mot de passe  -_-",
									Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(
									AuthenticationActivity.this,
									"Vous n'avez pas rempli un seul des deux champs  -_-",
									Toast.LENGTH_SHORT).show();
						}
					} else {
						Log.d("chaine:", editText_authentication_userName.getText().toString() + "/" + editText_authentication_password.getText().toString());
						login = editText_authentication_userName.getText().toString();
						password = editText_authentication_password.getText().toString();
						JSONAuthenticationTask jSONAuthenticationTask = new JSONAuthenticationTask();
						jSONAuthenticationTask.execute();
						
					}
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(AuthenticationActivity.this, "Exception.",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.authentication, menu);
		return true;
	}

	private class JSONAuthenticationTask extends AsyncTask<Void, Integer, Boolean>{

		@Override
		protected Boolean doInBackground(Void... arg0) {
			try {
				if(CustomHttpClientSG.getInstance().connect(login, password)){
					connect = true;
					response = true;
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			connect = false;
			response = true;
			return false;
		}

		
		@Override
			protected void onPostExecute(Boolean result) {			
				super.onPostExecute(result);
				if (result) {
					// The app launches the dashboard and passes the
					// username and password of the user.
					Intent intent = new Intent(
							AuthenticationActivity.this,
							DashboardActivity.class);
					
					Data.getInstance().setUsername(editText_authentication_userName.getText().toString());
					Data.getInstance().setPassword(editText_authentication_password.getText().toString());

					startActivity(intent);
					// The current activity is closed because not useful
					// anymore.
					finish();
				} else {
					Toast.makeText(AuthenticationActivity.this, "Mauvais identifiants.", Toast.LENGTH_SHORT).show();
				}
		}
	}
}
