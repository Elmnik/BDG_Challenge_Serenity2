package com.bdg.api.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.interactions.Put;

public class GenericRest implements Interaction {

    private final String method;
    private final String resource;
    private final String body;

    public GenericRest(String method, String resource, String body) {
        this.method = method;
        this.resource = resource;
        this.body = body;
    }

    public static GenericRest execute(String method, String resource, String body) {
        return instrumented(GenericRest.class, method, resource, body);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        switch (method.toUpperCase()) {
            case "GET":
                actor.attemptsTo(Get.resource(resource));
                break;
            case "POST":
                // Se usa .to() para POST y se asegura el header de JSON
                actor.attemptsTo(
                    Post.to(resource)
                        .with(request -> request.header("Content-Type", "application/json")
                        .body(body))
                );
                break;
            case "PUT":
                // Se usa .to() para PUT
                actor.attemptsTo(
                    Put.to(resource)
                        .with(request -> request.header("Content-Type", "application/json")
                        .body(body))
                );
                break;
            case "DELETE":
                // Se usa .from() para DELETE
                actor.attemptsTo(Delete.from(resource));
                break;
            default:
                throw new IllegalArgumentException("HTTP Method not supported: " + method);
        }
    }
}