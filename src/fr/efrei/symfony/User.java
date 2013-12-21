package fr.efrei.symfony;

import android.util.Log;


public class User extends SymfonyConnect {
	
	private String email = null;
	private String token = null;
	private boolean authenticate = false;
	
	public void Connect(String email, String password) {
		
		SRequest sr = new SRequest();
		sr.addParameters("login", email);
		sr.addParameters("password", password);
		
		
		
		try {
						
			if(sr.execute().equals("OK"))
				authenticate = true;
			else
				authenticate = false; 
		} catch (Exception e) {
			authenticate = false;
		}
	}
	
	public boolean isAuthenticate() {
		return authenticate;
	}
	
}
