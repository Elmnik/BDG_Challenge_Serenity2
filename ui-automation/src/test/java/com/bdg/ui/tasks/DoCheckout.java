package com.bdg.ui.tasks;

import com.bdg.ui.userinterfaces.CheckoutPage;

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
    @Step("{0} completa el checkout")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(CheckoutPage.CART_ICON),
                Click.on(CheckoutPage.CHECKOUT_BUTTON),
                Enter.theValue(firstName).into(CheckoutPage.FIRST_NAME),
                Enter.theValue(lastName).into(CheckoutPage.LAST_NAME),
                Enter.theValue(zip).into(CheckoutPage.ZIP_CODE),
                Click.on(CheckoutPage.CONTINUE_BUTTON),
                Click.on(CheckoutPage.FINISH_BUTTON)
        );
    }
}