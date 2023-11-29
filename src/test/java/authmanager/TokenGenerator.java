package authmanager;

import java.io.IOException;
import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.ConfigReader;

import static io.restassured.RestAssured.*;

public class TokenGenerator {
	
	
	public static String renewToken() throws IOException
	{
		ConfigReader configReader = new ConfigReader();
		
		HashMap<String, String> param = new HashMap<String, String>();
		
		param.put("grant_type", "refresh_token");
		param.put("refresh_token", configReader.readPropData("refreshtoken"));
		param.put("client_id", configReader.readPropData("client_id"));
		param.put("client_secret", configReader.readPropData("client_secret"));
		RestAssured.baseURI = "https://accounts.spotify.com";
		
		Response response = given()
		
		.contentType(ContentType.URLENC)
		
		.formParams(param)
		
		.when()
		
		.post("api/token")
		
		.then()
		
		.extract()
		
		.response();
		
		String accessToken = response.path("access_token");
		
		if(response.statusCode()!= 200)
		{
			throw new RuntimeException("Failed to generate the access token");
		}
		
		return accessToken;
		
	}
	

}
