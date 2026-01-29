package com.bdg.ui.stepdefinitions;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

import com.bdg.ui.tasks.AddToCart;
import com.bdg.ui.tasks.DoCheckout;
import com.bdg.ui.tasks.Login;
import com.bdg.ui.userinterfaces.CheckoutPage;
import com.bdg.ui.userinterfaces.InventoryPage;
import com.bdg.ui.userinterfaces.LoginPage;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.questions.Text;
import net.thucydides.model.util.EnvironmentVariables;

public class SauceDemoStepDefinitions {

    private EnvironmentVariables environmentVariables;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("que el actor tiene acceso a la página {string}")
    public void navigateTo(String url) {
        theActorCalled("Actor").attemptsTo(Open.url(url));
    }

    @When("ingresa las credenciales válidas")
    public void loginWithValidCredentials() {
        String user = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("user.name");
        String pass = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("user.password");
        theActorInTheSpotlight().attemptsTo(Login.withCredentials(user, pass));
    }

    @When("ingresa el usuario {string} y la contraseña {string}")
    public void loginWithInvalidCredentials(String user, String pass) {
        theActorInTheSpotlight().attemptsTo(Login.withCredentials(user, pass));
    }

    @And("inicia sesión con credenciales válidas")
    public void loginPrecondition() {
        loginWithValidCredentials();
    }

    @Then("debería ver el título de {string} en la pantalla principal")
    public void verifyMainTitle(String expectedTitle) {
        theActorInTheSpotlight().should(
                seeThat("El título de la página", Text.of(InventoryPage.MAIN_TITLE), is(expectedTitle))
        );
    }

    @Then("debería ver el mensaje de error {string}")
    public void verifyErrorMessage(String expectedError) {
        theActorInTheSpotlight().should(
                seeThat("El mensaje de error", Text.of(LoginPage.ERROR_MESSAGE), containsString(expectedError))
        );
    }

    @When("añade el producto {string} al carrito")
    public void addProductToCart(String product) {
        theActorInTheSpotlight().attemptsTo(AddToCart.item(product));
    }

    @Then("el icono del carrito debería mostrar {string} unidad")
    public void verifyCartBadge(String count) {
        theActorInTheSpotlight().should(
                seeThat("Contador del carrito", Text.of(InventoryPage.SHOPPING_CART_BADGE), is(count))
        );
    }

    @When("completa la compra de {string} con los datos:")
    public void completePurchase(String product, io.cucumber.datatable.DataTable data) {
        Map<String, String> formData = data.asMaps().get(0);
        theActorInTheSpotlight().attemptsTo(
                AddToCart.item(product),
                DoCheckout.withData(formData.get("firstName"), formData.get("lastName"), formData.get("zipCode"))
        );
    }

    @Then("debería ver el mensaje de confirmación {string}")
    public void verifyConfirmation(String message) {
        theActorInTheSpotlight().should(
                seeThat("Mensaje de confirmación", Text.of(CheckoutPage.COMPLETE_HEADER), is(message))
        );
    }

    @When("añade los siguientes productos al carrito:")
    public void addMultipleProducts(List<String> products) {
        products.forEach(product -> 
            theActorInTheSpotlight().attemptsTo(AddToCart.item(product))
        );
    }

    @And("elimina el producto {string} del carrito")
    public void removeProduct(String product) {
        // Usamos el locator de REMOVE que definimos en InventoryPage
        theActorInTheSpotlight().attemptsTo(
                Click.on(InventoryPage.REMOVE_BUTTON.of(product))
        );
    }

    @And("finaliza la compra con los datos {string}, {string}, {string}")
    public void finalStepCheckout(String first, String last, String zip) {
        // Reutilizamos la tarea de Checkout
        theActorInTheSpotlight().attemptsTo(DoCheckout.withData(first, last, zip));
    }
}