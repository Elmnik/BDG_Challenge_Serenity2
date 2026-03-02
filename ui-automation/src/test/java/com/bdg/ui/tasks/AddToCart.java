package com.bdg.ui.tasks;

import com.bdg.ui.userinterfaces.InventoryPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.annotations.Step;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class AddToCart implements Task {
    private final String productName;

    public AddToCart(String productName) {
        this.productName = productName;
    }

    public static AddToCart item(String productName) {
        return instrumented(AddToCart.class, productName);
    }

    @Override
    @Step("{0} agrega el producto '#productName' al carrito")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                // Producto en Pantalla
                Scroll.to(InventoryPage.ADD_TO_CART_BUTTON.of(productName)),

                // Esperate que js lo habilite
                WaitUntil.the(InventoryPage.ADD_TO_CART_BUTTON.of(productName), isClickable())
                        .forNoMoreThan(5).seconds(),

                // Click
                Click.on(InventoryPage.ADD_TO_CART_BUTTON.of(productName))
        );
    }
}