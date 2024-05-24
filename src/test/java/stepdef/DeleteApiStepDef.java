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

public class DeleteApiStepDef extends TestBase {
	
	Response resp;

	@Given("^provide valid endpoint to delete the user$")
	public void provide_valid_endpoint_to_delete_user() throws IOException {
		RestAssured.baseURI = getPropertyValue("baseUrl");
		RestAssured.basePath = getPropertyValue("serviceApiUrl");
	}

	@When("^I send the request to the server to delete the user$")
	public void I_send_the_request_to_the_server_to_delete_user() {

		resp = RestAssured.given()
							.header("Accept", "application/json")
						.when()
						  	.delete("/2")
						  .then()
						  	.extract().response();

	}

	@Then("^I validate the delete user response status code$")
	public void I_validate_the_delete_user_response_statuscode(DataTable dt) {
		List<String> expStatCodeList = dt.asList();
		int respStatusCode = resp.getStatusCode();
		System.out.println("Response status code is:" + respStatusCode);
		Assertions.assertEquals(respStatusCode, Integer.parseInt(expStatCodeList.get(0)));

	}

	

	

}
