package com.bdg.ui.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginPage {
    public static final Target USERNAME_FIELD = Target.the("campo usuario")
            .located(By.id("user-name"));
    
    public static final Target PASSWORD_FIELD = Target.the("campo password")
            .located(By.id("password"));
    
    public static final Target LOGIN_BUTTON = Target.the("botón login")
            .located(By.id("login-button"));

    public static final Target ERROR_MESSAGE = Target.the("mensaje de error")
            .located(By.xpath("//h3[@data-test='error']"));
}