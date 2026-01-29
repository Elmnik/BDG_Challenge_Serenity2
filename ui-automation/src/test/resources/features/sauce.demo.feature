Feature: Automatización del CRUD en SauceDemo

  @LoginExitoso
  Scenario: Inicio de sesión exitoso con un usuario válido
    Given que el actor tiene acceso a la página "https://www.saucedemo.com"
    When ingresa las credenciales válidas
    Then debería ver el título de "Products" en la pantalla principal

  @LoginFallido
  Scenario: Inicio de sesión fallido con un usuario inválido
    Given que el actor tiene acceso a la página "https://www.saucedemo.com"
    When ingresa el usuario "invalid_user" y la contraseña "secret_sauce"
    Then debería ver el mensaje de error "Epic sadface: Username and password do not match any user in this service"

  @AñadirProducto
  Scenario: Añadir un producto al carrito
    Given que el actor tiene acceso a la página "https://www.saucedemo.com"
    And inicia sesión con credenciales válidas
    When añade el producto "Sauce Labs Backpack" al carrito
    Then el icono del carrito debería mostrar "1" unidad

  @CompraExitosa
  Scenario: Finalizar una compra con un producto en el carrito
    Given que el actor tiene acceso a la página "https://www.saucedemo.com"
    And inicia sesión con credenciales válidas
    When completa la compra de "Sauce Labs Backpack" con los datos:
      | firstName | lastName | zipCode |
      | Elmer     | Perez    | 505     |
    Then debería ver el mensaje de confirmación "Thank you for your order!"

  @AñadirEliminarProductoCompra
  Scenario: Añadir múltiples productos, eliminar uno de ellos y luego finalizar la compra
    Given que el actor tiene acceso a la página "https://www.saucedemo.com"
    And inicia sesión con credenciales válidas
    When añade los siguientes productos al carrito:
      | Sauce Labs Backpack     |
      | Sauce Labs Bike Light   |
      | Sauce Labs Bolt T-Shirt |
    And elimina el producto "Sauce Labs Bike Light" del carrito
    And finaliza la compra con los datos "Elmer", "Perez", "505"
    Then debería ver el mensaje de confirmación "Thank you for your order!"