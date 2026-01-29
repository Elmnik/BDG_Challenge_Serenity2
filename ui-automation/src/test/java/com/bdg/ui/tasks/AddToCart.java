package com.bdg.ui.tasks;

import com.bdg.ui.userinterfaces.InventoryPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.annotations.Step;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class AddToCart implements Task {
    private final String productName;

    public AddToCart(String productName) {
        this.productName = productName;
    }

    public static AddToCart item(String productName) {
        return instrumented(AddToCart.class, productName);
    }

    @Override
    @Step("{0} adds #productName to the cart")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(InventoryPage.ADD_TO_CART_BUTTON.of(productName))
        );
    }
}