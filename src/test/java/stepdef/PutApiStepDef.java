package stepdef;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import util.TestBase;

public class PutApiStepDef extends TestBase {
	JSONObject jsonObj = new JSONObject();
	Response resp;

	@Given("^provide valid endpoint to update the user$")
	public void provide_valid_endpoint_to_update_user() throws IOException {
		RestAssured.baseURI = getPropertyValue("baseUrl");
		RestAssured.basePath = getPropertyValue("serviceApiUrl");
	}

	@When("^I send the request to the server with requestbody to update user$")
	public void I_send_the_request_to_the_server_with_requestbody_to_update_user() {

		jsonObj.put("name", "Ramesh Ch");
		jsonObj.put("job", "SDET");
		resp = RestAssured.given()
							.header("Accept", "application/json")
							.header("Content-Type", "application/json")
							.body(jsonObj.toString())
						  .when()
						  	.put("/2")
						  .then()
						  	.contentType("application/json").extract().response();

	}

	@Then("^I validate the update user response status code$")
	public void I_validate_the_update_user_response_statuscode(DataTable dt) {
		List<String> expStatCodeList = dt.asList();
		int respStatusCode = resp.getStatusCode();
		System.out.println("Response status code is:" + respStatusCode);
		Assertions.assertEquals(respStatusCode, Integer.parseInt(expStatCodeList.get(0)));

	}

	@Then("^validate the update user response body fields$")
	public void validate_the_update_user_response_body_fields() {

		String actname = resp.path("name");
		Assertions.assertEquals(actname, jsonObj.get("name"));

		String actjob = resp.path("job");
		Assertions.assertEquals(actjob, jsonObj.get("job"));

	}

	

}
