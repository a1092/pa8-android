package com.efrei.pa8.kylin.beta.web;

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
