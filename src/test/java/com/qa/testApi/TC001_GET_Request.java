package com.qa.testApi;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_GET_Request 
{
		@Test
		public void getuserDetails()
		{
			// to Store the Base URI
			RestAssured.baseURI ="https://reqres.in";
			
			// to Create an Object of Request.
			RequestSpecification httpsRequest = RestAssured.given();
			
			//to Create an Object of Response
			
			Response response =httpsRequest.request(Method.GET, "/api/users/2");
			
			//to get the Body of the Response
			String responseBody =response.getBody().asString();
			
			// to Print the Response Body
			System.out.println("The Response Body :" + responseBody );
			
			Assert.assertEquals(responseBody.contains("Janet"), true);
			
			
			
			
			
			
				
			// to get the Status Code
			 int statusCode =response.getStatusCode();
			 System.out.println("The Status Code:" + statusCode);
			 Assert.assertEquals(statusCode, 200);
			 
			 // to get the StatusLine
			 String statusLine=response.getStatusLine();
			 System.out.println("The Status Line :"+ statusLine);
			 //Assert.assertEquals(statusLine, );
			 
			 
			 // to validate all the nodes of the JSON Body
			 
			 JsonPath jPath = response.jsonPath();
			  String actualID =jPath.get("email");
			  System.out.println("The ID of the User on the Response :" + actualID);
			
		}
}
