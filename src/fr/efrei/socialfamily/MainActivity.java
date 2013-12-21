package fr.efrei.socialfamily;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import fr.efrei.symfony.User;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /*
        LinearLayout layout_login = new LinearLayout(this);
        
        TextView label_login = new TextView(this);
        label_login.setText("Login");
        layout_login.addView(label_login);
        
        EditText edit_login = new EditText(this);
        layout_login.addView(edit_login);
        
        
        LinearLayout layout_password = new LinearLayout(this);
        
        TextView label_password = new TextView(this);
        label_password.setText("Password");
        layout_password.addView(label_password);
        
        EditText edit_password = new EditText(this);
        layout_password.addView(edit_password);
        
        LinearLayout layout = new LinearLayout(this);
        layout.addView(layout_login);
        layout.addView(layout_password);
        

        Button btn_connexion = new Button(this);
        btn_connexion.setText("Connexion");
        layout.addView(btn_connexion);
        
        setContentView(layout);
        */
        
        setContentView(R.layout.activity_main);
    }

    public void Connect(View view) {
    	
    	
    	String login = ((EditText)this.findViewById(R.id.user_email)).getText().toString();
    	String password = ((EditText)this.findViewById(R.id.user_password)).getText().toString();
    	
    	if(login.length() == 0 || password.length() == 0) {
    		
    		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
    		alertDialog.setTitle("Connexion à SF");
            alertDialog.setMessage("Votre email ou mot de passe est vide.");
            alertDialog.show();
            
    		return;
    	}
    		
    	

        final User u = new User();
        u.Connect(login, password);

        if(!u.isAuthenticate()) {
        	AlertDialog alertDialog = new AlertDialog.Builder(this).create();
    		alertDialog.setTitle("Connexion à SF");
            alertDialog.setMessage("Vos identifiants ne sont pas valides.");
            alertDialog.show();
            
    		return;
        }
    	
        
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
    	startActivity(intent);
    	
    
    }
    

    
}
