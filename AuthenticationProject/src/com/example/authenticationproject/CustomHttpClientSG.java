package com.example.authenticationproject;

public class CustomHttpClientSG {
	private static CustomHttpClient customHttpClient;
	
	private CustomHttpClientSG(){
		customHttpClient = new CustomHttpClient();
	}
	
	public static CustomHttpClient getInstance(){
		if(customHttpClient == null){
			new CustomHttpClientSG();
		}
		return customHttpClient;
	}
}
