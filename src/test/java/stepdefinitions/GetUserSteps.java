package stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import utils.ConfigReader;

public class GetUserSteps {
    private Response response;

    @Given("the API is up and running")
    public void the_api_is_up_and_running() {
        ConfigReader.loadConfig(System.getProperty("env", "config"));
        baseURI = ConfigReader.getProperty("base.url");
    }

    @When("I send a GET request to \"/api/users/2\"")
    public void i_send_a_get_request() {
        response = given().when().get("/api/users/2");
    }

    @Then("the response status code should be 200")
    public void verify_status_code() {
        response.then().statusCode(200);
    }

    @Then("the response should contain the user with id 2")
    public void verify_user_id() {
        response.then().body("data.id", equalTo(2));
    }
}
