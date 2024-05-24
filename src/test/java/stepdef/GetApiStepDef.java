package stepdef;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import util.TestBase;

public class GetApiStepDef extends TestBase{

	Response resp;

	 @Given("^provide valid endpoint to fetch all users$")
	 public void provide_valid_endpoint_to_fetch_all_users() throws IOException {
		 RestAssured.baseURI=getPropertyValue("baseUrl");
		 RestAssured.basePath=getPropertyValue("serviceApiUrl");
	 }
	 @When("^send the request to the server$")
public void send_the_request_to_the_server() {
	resp=RestAssured.given()
					 .header("Accept", "application/json")
					 .header("Content-Type", "application/json")
					 .queryParam("page", 2)
				   .when()
				     .get()
				   .then()
				     .contentType("application/json")
				     .extract().response();
				    
	 }
	 
	 
	 @Then("^I validate the response statuscode 200$")
	 public void I_validate_the_response_statuscode() {
			int respStatusCode=resp.getStatusCode();
			System.out.println("Response status code is:"+respStatusCode);
			Assertions.assertEquals(respStatusCode,200);
		
		 }
	 
	 @When("^send the request to the server with page number as (.+)$")
	 public void send_the_request_to_the_server_with_page_number(int pageNo) {
		 resp=RestAssured.given()
				 .header("Accept", "application/json")
				 .header("Content-Type", "application/json")
				 .queryParam("page", pageNo)
			   .when()
			     .get()
			   .then()
			     .contentType("application/json")
			     .extract().response();
	 }
	 
	   @Then("^I verify the response statusCode (.+)$")
	   public void I_verify_the_response_statusCode(int statusCode) {
		   int respStatusCode=resp.getStatusCode();
			System.out.println("Response status code is:"+respStatusCode);
			Assertions.assertEquals(respStatusCode,statusCode);
		   
	   }
	   @Then("^I verify the first user record having email as \"([^\"]*)\"$")
 public void I_verify_the_response_statusCode(String email) {
		   String actEmail = resp.path("data[0].email");
		   Assertions.assertEquals(actEmail, email);
		   
	   }
	   @Given("^provide valid endpoint to fetch single user$")
	   public void provide_valid_endpoint_to_fetch_single_user() throws Throwable {
	   	// set the base url
	   	RestAssured.baseURI = getPropertyValue("baseUrl");
	   	RestAssured.basePath = getPropertyValue("serviceApiUrl");
	   }

	   @When("^send the request to the server to fetch single user$")
	   public void send_the_request_to_the_server_to_feth_single_user() throws Throwable {
	   	resp = RestAssured.given()
	               .header("Accept", "application/json")
	               .header("Content-Type", "application/json")
	               
	             .when()
	               .get("/2")
	             .then()
	                .contentType("application/json")
	                .extract().response();

	   }

	   @Then("^I validate the single user response statuscode 200$")
	   public void i_validate_the_single_user_response_statuscode_200() throws Throwable {
	   	int respCode=resp.getStatusCode();
	   	Assertions.assertEquals(respCode, 200); 
	   }

	   @Then("^I verify the response body fields$")
	   public void i_verify_the_rsponse_body_fields(DataTable dt) throws Throwable {
	      
	   List<List<String>>expData=dt.asLists();
	   	int expId=Integer.parseInt(expData.get(0).get(0));
	   	System.out.println("expected id from feature file:"+expId);
	   	String expEmail=expData.get(0).get(1);
	   	System.out.println("expected email from feature file:"+expEmail);
	   	String expFirstName=expData.get(0).get(2);
	   	System.out.println("expected firstname from feature file:"+expFirstName);
	   	String expLastName=expData.get(0).get(3);
	   	System.out.println("expected lastname from feature file:"+expLastName);
	   	int actId=resp.path("data.id");
	   	System.out.println("Act id from response:"+actId);
	   	Assertions.assertEquals(actId, expId); 
	   	 String actEmail=resp.path("data.email");
	   	System.out.println("Act email from response:"+actEmail);
	   	Assertions.assertEquals(actEmail, expEmail); 
	   	String actfName=resp.path("data.first_name");
	   	System.out.println("Act first name from response:"+actfName);
	   	Assertions.assertEquals(actfName, expFirstName);
	      String actlname=resp.path("data.last_name");
	   	System.out.println("Act last name from response:"+actlname);
	   	Assertions.assertEquals(actlname, expLastName);
	       }

	    @Given("^provide valid endpoint to fetch single user not found$")
	   public void provide_valid_endpoint_to_single_user_not_found() throws Throwable {
	   	// set the base url
	   				RestAssured.baseURI = getPropertyValue("baseUrl");
	   				RestAssured.basePath = getPropertyValue("serviceApiUrl");
	   }

	   @When("^send the request to the server to fetch single user not found$")
	   public void send_the_request_to_the_server_single_user_not_found() throws Throwable {
	   	resp = RestAssured.given()
	               .header("Accept", "application/json")
	               .header("Content-Type", "application/json")
	               
	             .when()
	               .get("/23")
	             .then()
	                .contentType("application/json")
	                .extract().response();
	   }

	   @Then("^I validate the single user not found response statuscode$")
	   public void i_validate_the_user_not_found_response_statuscode(DataTable dt) throws Throwable {
	      
	   	List<String>expData=dt.asList();
	   	
	   	int expStatusCode=Integer.parseInt(expData.get(0));
	   	Assertions.assertEquals(resp.getStatusCode(), expStatusCode);
	   }

	 
	 
	 
	 
	 
}
