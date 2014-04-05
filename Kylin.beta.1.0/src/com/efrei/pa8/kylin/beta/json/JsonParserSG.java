package com.efrei.pa8.kylin.beta.json;

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
