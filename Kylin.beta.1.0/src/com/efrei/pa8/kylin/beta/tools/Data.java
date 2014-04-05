package com.efrei.pa8.kylin.beta.tools;


public class Data {

	private static DataProtected dataProtected;
	
	private Data(){
		dataProtected = new DataProtected();
	}
	
	public static DataProtected getInstance(){
		if(dataProtected == null){
			new Data();
		}
		return dataProtected;
	}
}
