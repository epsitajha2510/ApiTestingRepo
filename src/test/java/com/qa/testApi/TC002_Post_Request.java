package com.qa.testApi;

import java.io.IOException;
import org.apache.http.ParseException;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_Post_Request 
{
	
	JSONObject jObject ;
	
	@Test
		public void registernewUser() throws ParseException, IOException
		{
			RestAssured.baseURI ="https://reqres.in";
			
			RequestSpecification httpRequest  = RestAssured.given();
			
			jObject = new JSONObject();
			// Sending the Request Payload along with the Post Request
			jObject.put("name", "Epsita");
			jObject.put("job", "Test Analyst");
			
			httpRequest.body(jObject.toJSONString());
			
			Response response = httpRequest.request(Method.POST,"/api/users");
			
			String JsonBody =response.getBody().asString();
			System.out.println("The Json Body: "+ JsonBody);
			
			//jObject = new JSONObject();
			
			/*String ResponseJSONString =EntityUtils.toString((HttpEntity) response.getBody(),"UTF-8");
			System.out.println(ResponseJSONString);*/
			
			
			//System.out.println("The Json Body: "+ JsonBody);
			
			//status code validation
			 int statusCode=response.getStatusCode();
			 System.out.println("Status code is: "+statusCode);
			 Assert.assertEquals(statusCode, 201);
			 
			 
			  
			//success code validation
			 String successCode=response.jsonPath().get("SuccessCode");
			 System.out.println(successCode);
			 //Assert.assertEquals(successCode, "OPERATION_SUCCESS");
		}

}
