package com.bdg.api.stepdefinitions;

import com.bdg.api.interactions.GenericRest;
import com.bdg.api.models.UserData;
import io.cucumber.java.Before;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static net.serenitybdd.screenplay.actors.OnStage.*;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.*;

public class UserManagementStepDefinitions {

    private UserData responseModel;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("the actor has access to the url {string}")
    public void setBaseUrl(String baseUrl) {
        theActorCalled("Actor").whoCan(CallAnApi.at(baseUrl));
    }

    @When("he consults the list of users")
    public void getListUsers() {
        theActorInTheSpotlight().attemptsTo(
            GenericRest.execute("GET", "/api/users?page=2", null)
        );
    }

    @Then("the response should contain at least one user with id {int}")
    public void verifyUserInList(int id) {
        theActorInTheSpotlight().should(
            seeThatResponse("Verify user ID in list", res ->
                res.statusCode(200)
                   .body("data.id", hasItem(id))
            )
        );
    }

    @When("he registers a user with email {string} and password {string}")
    public void registerUser(String email, String password) {
        String body = String.format("{\"email\": \"%s\", \"password\": \"%s\"}", email, password);
        theActorInTheSpotlight().attemptsTo(
            GenericRest.execute("POST", "/api/register", body)
        );

    }

    @Then("the response body should contain a token")
    public void verifyToken() {
        theActorInTheSpotlight().should(
            seeThatResponse("Verify authentication token", res ->
                res.body("token", notNullValue())
            )
        );
    }

    @When("he attempts to register a user without password")
    public void registerWithoutPassword() {
        theActorInTheSpotlight().attemptsTo(
            GenericRest.execute("POST", "/api/register", "{\"email\": \"sydney@fife\"}")
        );
    }

    @Then("the error message should be {string}")
    public void verifyErrorMessage(String message) {
        theActorInTheSpotlight().should(
            seeThatResponse("Verify error message", res ->
                res.statusCode(400)
                   .body("error", equalTo(message))
            )
        );
    }

    @When("he updates the name to {string} and the job to {string}")
    public void updateUserInfo(String name, String job) {
        String body = String.format("{\"name\": \"%s\", \"job\": \"%s\"}", name, job);
        theActorInTheSpotlight().attemptsTo(
            GenericRest.execute("PUT", "/api/users/2", body)
        );
    }

    @Then("the response body should contain the name {string}")
    public void verifyNameInResponse(String name) {
        theActorInTheSpotlight().should(
            seeThatResponse("Verify updated name", res ->
                res.statusCode(200)
                   .body("name", is(name))
            )
        );
    }

    @When("he deletes the user with id {int}")
    public void deleteUser(int id) {
        theActorInTheSpotlight().attemptsTo(
            GenericRest.execute("DELETE", "/api/users/" + id, null)
        );
    }

    @Then("the response code should be {int}")
    public void verifyStatusCode(int code) {
        theActorInTheSpotlight().should(
            seeThatResponse("Verify HTTP status code", res -> res.statusCode(code))
        );
    }
}