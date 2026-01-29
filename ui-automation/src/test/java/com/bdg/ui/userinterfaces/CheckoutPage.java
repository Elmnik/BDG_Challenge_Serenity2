package com.bdg.ui.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CheckoutPage {
    public static final Target CART_ICON = Target.the("ícono del carrito").located(By.className("shopping_cart_link"));
    public static final Target CHECKOUT_BUTTON = Target.the("botón checkout").located(By.id("checkout"));
    
    // Formulario
    public static final Target FIRST_NAME = Target.the("nombre").located(By.id("first-name"));
    public static final Target LAST_NAME = Target.the("apellido").located(By.id("last-name"));
    public static final Target ZIP_CODE = Target.the("código postal").located(By.id("postal-code"));
    public static final Target CONTINUE_BUTTON = Target.the("botón continuar").located(By.id("continue"));
    public static final Target FINISH_BUTTON = Target.the("botón finalizar").located(By.id("finish"));
    
    public static final Target COMPLETE_HEADER = Target.the("encabezado de confirmación").located(By.className("complete-header"));
}