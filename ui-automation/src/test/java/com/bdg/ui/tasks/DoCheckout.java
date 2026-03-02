package com.bdg.ui.tasks;

import com.bdg.ui.userinterfaces.CheckoutPage;
import com.bdg.ui.userinterfaces.InventoryPage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

public class DoCheckout implements Task {
    private final String firstName;
    private final String lastName;
    private final String zip;

    public DoCheckout(String firstName, String lastName, String zip) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.zip = zip;
    }

    public static DoCheckout withData(String firstName, String lastName, String zip) {
        return instrumented(DoCheckout.class, firstName, lastName, zip);
    }

    @Override
    @Step("{0} completa el proceso de compra para #firstName #lastName")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                // Checkout
                Click.on(InventoryPage.SHOPPING_CART_LINK),
                Click.on(CheckoutPage.CHECKOUT_BUTTON),

                // Wait to Load
                WaitUntil.the(CheckoutPage.FIRST_NAME, isVisible()).forNoMoreThan(5).seconds(),

                // Fill With Data
                Enter.theValue(firstName).into(CheckoutPage.FIRST_NAME),
                Enter.theValue(lastName).into(CheckoutPage.LAST_NAME),
                Enter.theValue(zip).into(CheckoutPage.ZIP_CODE),

                // End
                Click.on(CheckoutPage.CONTINUE_BUTTON),
                Click.on(CheckoutPage.FINISH_BUTTON)
        );
    }
}