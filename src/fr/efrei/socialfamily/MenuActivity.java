package fr.efrei.socialfamily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import fr.efrei.symfony.User;

public class MenuActivity extends Activity {
	
	final User EXTRA_USER = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	
	public void Disconnect(View view) {
		Intent intent = new Intent(MenuActivity.this, MainActivity.class);
    	startActivity(intent);
	}
}
