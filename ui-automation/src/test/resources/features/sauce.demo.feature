Feature: Gestión de compras en SauceDemo

  Background:
    Given que el actor se encuentra en la página de inicio de sesión

  @LoginExitoso
  Scenario: Inicio de sesión exitoso con un usuario válido
    When ingresa las credenciales de "standard_user"
    Then debería ver el título de "Products" en la pantalla principal

  @LoginFallido @ignore
  Scenario: Inicio de sesión fallido con credenciales erróneas
    When ingresa el usuario "invalid_user" y la contraseña "wrong_password"
    Then debería ver el mensaje de error "Epic sadface: Username and password do not match any user in this service"

  @AñadirProducto
  Scenario: Añadir un producto al carrito
    And inicia sesión con credenciales válidas
    When añade el producto "Sauce Labs Backpack" al carrito
    Then el icono del carrito debería mostrar "1" unidad

  @FlujoCompletoDeCompra
  Scenario: Gestión dinámica de carrito y finalización de compra
    And inicia sesión con credenciales válidas
    When añade los siguientes productos al carrito:
      | Sauce Labs Backpack     |
      | Sauce Labs Bike Light   |
      | Sauce Labs Bolt T-Shirt |
    And elimina el producto "Sauce Labs Bike Light" del carrito
    And finaliza el proceso de compra con los datos:
      | firstName | lastName | zipCode |
      | Elmer     | Perez    | 505     |
    Then debería ver el mensaje de confirmación "Thank you for your order!"