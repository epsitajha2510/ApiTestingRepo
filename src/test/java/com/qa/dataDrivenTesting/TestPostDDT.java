package com.qa.dataDrivenTesting;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestPostDDT 
{
		@Test(dataProvider ="empDataProvider")
		public void postNewEmployee(String ename, String esalary, String eage)
		{
			RestAssured.baseURI ="http://dummy.restapiexample.com/api/v1";
			
			RequestSpecification httprequest = RestAssured.given();
			
			JSONObject jObject = new JSONObject();
			jObject.put("name", ename);
			jObject.put("salary", esalary);
			jObject.put("age", eage);
			
			httprequest.header("Content-Type","application/json");
			
			httprequest.body(jObject.toJSONString());
			 
			Response response = httprequest.request(Method.POST,"/create");
			
			String responseBody =response.getBody().asString();
			
			System.out.println("The Response Body :"+ responseBody);
			
			//capure the Response body to do the Validation
			
			int statusCode =response.statusCode();
			Assert.assertEquals(statusCode, 200);
			
			Assert.assertEquals(responseBody.contains(ename), true);
			Assert.assertEquals(responseBody.contains(esalary), true);
			Assert.assertEquals(responseBody.contains(eage), true);

			
		}
		
		@DataProvider(name="empDataProvider")
		String[][] getemployeeData()
		{
			
			Xls_Reader reader = new Xls_Reader("D:\\CucumberWorkSpace\\RestAssured\\TestData\\EmployeeData.xlsx");
			
			int rowCount =reader.getRowCount("Sheet1");
			int ColCount = reader.getColumnCount("Sheet1");
			System.out.println(rowCount);
			System.out.println(ColCount);
			
			String empData[][] = new String[rowCount][ColCount];
			 
			 for(int i=1; i<=rowCount;i++)  
			 {
				 for(int j =0;j< ColCount;j++) 
				 {
					 empData[i-1][j] = reader.getCellData("Sheet1", j, i);
				 }
			 }
			//String empData [] [] = {{"Epsita Jha","567890","23"},{"Barun Das","675789","25"},{"Roshni Das","65345","21"}};
			 return empData;
			}
		
}	

