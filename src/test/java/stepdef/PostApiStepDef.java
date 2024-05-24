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

public class PostApiStepDef extends TestBase {
	JSONObject jsonObj = new JSONObject();
	Response resp;

	@Given("^provide valid endpoint to create user$")
	public void provide_valid_endpoint_to_create_users() throws IOException {
		RestAssured.baseURI = getPropertyValue("baseUrl");
		RestAssured.basePath = getPropertyValue("serviceApiUrl");
	}

	@When("^I send the request to the server with requestbody$")
	public void I_send_the_request_to_the_server_with_requestbody() {

		jsonObj.put("name", "Ritwik");
		jsonObj.put("job", "Tester");
		resp = RestAssured.given().header("Accept", "application/json").header("Content-Type", "application/json")
				.body(jsonObj.toString()).when().post().then().contentType("application/json").extract().response();

	}

	@Then("^I validate the create user response status code$")
	public void I_validate_the_response_statuscode(DataTable dt) {
		List<String> expStatCodeList = dt.asList();
		int respStatusCode = resp.getStatusCode();
		System.out.println("Response status code is:" + respStatusCode);
		Assertions.assertEquals(respStatusCode, Integer.parseInt(expStatCodeList.get(0)));

	}

	@Then("^validate the create user response body fields$")
	public void validate_the_create_user_response_body_fields() {

		String actname = resp.path("name");
		Assertions.assertEquals(actname, jsonObj.get("name"));

		String actjob = resp.path("job");
		Assertions.assertEquals(actjob, jsonObj.get("job"));

	}

	@When("I send the request to the server with requestbody as {string} and {string}")
	public void send_the_request_to_the_server_with_page_number(String name, String job) {

		jsonObj.put("name", name);
		jsonObj.put("job", job);
		resp = RestAssured.given()
							.header("Accept", "application/json")
							.header("Content-Type", "application/json")
							.body(jsonObj.toString())
						.when()
							.post()
						.then()
							.contentType("application/json").extract().response();
	}


}
