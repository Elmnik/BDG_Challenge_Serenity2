package com.bdg.ui.userinterfaces;

import org.openqa.selenium.By;

import net.serenitybdd.screenplay.targets.Target;

public class InventoryPage {

    private InventoryPage(){}

    public static final Target MAIN_TITLE = Target.the("título de la página principal")
            .located(By.className("title"));
    
    public static final Target SHOPPING_CART_BADGE = Target.the("contador del carrito")
            .located(By.className("shopping_cart_badge"));

    // Añadir: Solo busca botones que digan "Add to cart"
    public static final Target ADD_TO_CART_BUTTON = Target.the("botón añadir al carrito de {0}")
            .locatedBy("//div[@class='inventory_item_label']//div[text()='{0}']/ancestor::div[@class='inventory_item_description']//button[contains(text(),'Add')]");

    // Remover: Solo busca botones que digan "Remove"
    public static final Target REMOVE_BUTTON = Target.the("botón eliminar del carrito de {0}")
            .locatedBy("//div[@class='inventory_item_label']//div[text()='{0}']/ancestor::div[@class='inventory_item_description']//button[contains(text(),'Remove')]");

    // Carrito para ir al Checkout
    public static final Target SHOPPING_CART_LINK = Target.the("ícono del carrito de compras")
            .located(By.className("shopping_cart_link"));
}