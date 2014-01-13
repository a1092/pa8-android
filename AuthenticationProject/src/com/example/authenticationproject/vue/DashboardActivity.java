package com.example.authenticationproject.vue;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.authenticationproject.R;

public class DashboardActivity extends Activity {
	String userName;
	String password;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
		
		Bundle bundle = getIntent().getExtras();
		this.userName = bundle.getString("userName");
		this.password = bundle.getString("password");
		
		TextView textView_dashboard_welcome = (TextView) findViewById(R.id.textView_dashboard_welcome);
		textView_dashboard_welcome.setText("Bienvenue sur la page d'accueil de l'application, " + this.userName + "!");
	}
	
	
}
