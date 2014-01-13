package com.example.authenticationproject;

public class JsonParserSG {
	private static JsonParser jsonParser;
	
	private JsonParserSG() {
		jsonParser = new JsonParser();
	}
	
	public static JsonParser getInstance(){
		if(jsonParser == null){
			new JsonParserSG();
		}
		return jsonParser;
	}
}
