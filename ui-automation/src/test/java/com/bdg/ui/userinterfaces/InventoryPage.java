package com.bdg.ui.userinterfaces;

import org.openqa.selenium.By;

import net.serenitybdd.screenplay.targets.Target;

public class InventoryPage {
    public static final Target MAIN_TITLE = Target.the("título de la página principal")
            .located(By.className("title"));
    
    public static final Target SHOPPING_CART_BADGE = Target.the("contador del carrito")
            .located(By.className("shopping_cart_badge"));

    // Selector dinámico para añadir productos por nombre
    public static final Target ADD_TO_CART_BUTTON = Target.the("botón añadir al carrito de {0}")
            .locatedBy("//div[text()='{0}']/ancestor::div[@class='inventory_item_description']//button");
            
    public static final Target REMOVE_BUTTON = Target.the("botón eliminar del carrito de {0}")
            .locatedBy("//div[text()='{0}']/ancestor::div[@class='inventory_item_description']//button");
}