package com.example.authenticationproject.vue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.authenticationproject.CustomHttpClientSG;
import com.example.authenticationproject.R;

public class TestActivity extends Activity {

	EditText editText_connexion_userName;
	EditText editText_connexion_password;
	TextView error;
	Button btn_connexion_connect;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);

		editText_connexion_userName = (EditText) findViewById(R.id.editText_connexion_userName);
		editText_connexion_password = (EditText) findViewById(R.id.editText_connexion_password);
		btn_connexion_connect = (Button) findViewById(R.id.btn_connexion_connect);
		btn_connexion_connect.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					if ((editText_connexion_userName.getText().toString()
							.matches(""))
							|| (editText_connexion_password.getText()
									.toString().matches(""))) {
						if ((editText_connexion_userName.getText().toString()
								.matches(""))
								&& (!((editText_connexion_password.getText()
										.toString().matches(""))))) {

							Toast.makeText(TestActivity.this,
									"Vous n'avez pas entré de nom  -_-",
									Toast.LENGTH_SHORT).show();
						} else if ((editText_connexion_password.getText()
								.toString().matches(""))
								&& (!((editText_connexion_userName.getText()
										.toString().matches(""))))) {

							Toast.makeText(
									TestActivity.this,
									"Vous n'avez pas entré de mot de passe  -_-",
									Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(
									TestActivity.this,
									"Vous n'avez pas rempli un seul des deux champs  -_-",
									Toast.LENGTH_SHORT).show();
						}
					} else {

						if (CustomHttpClientSG.getInstance().connect(
								editText_connexion_userName.getText()
										.toString(),
								editText_connexion_password.getText()
										.toString())) {
							// The app launches the dashboard and passes the
							// username and password of the user.
							Intent intent = new Intent(TestActivity.this,
									DashboardActivity.class);

							// Send the username and the password into the
							// intent, for the nex activity to get them.
							Bundle bundle = new Bundle();
							bundle.putString("userName",
									editText_connexion_userName.getText()
											.toString());
							bundle.putString("password",
									editText_connexion_password.getText()
											.toString());
							intent.putExtras(bundle);

							startActivity(intent);
							// The current activity is closed because not useful
							// anymore.
							finish();
						} else {
							Toast.makeText(TestActivity.this,
									"Mauvais identifiants.", Toast.LENGTH_SHORT)
									.show();
						}
					}
				} catch (Exception e) {
					Toast.makeText(TestActivity.this, "Exception.",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}