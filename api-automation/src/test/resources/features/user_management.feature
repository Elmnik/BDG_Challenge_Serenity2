
Feature: User management in ReqRes

  @ListarUsuarios
  Scenario: List users and validate that the response contains at least one user
    Given the actor has access to the url "https://reqres.in"
    When he consults the list of users
    Then the response code should be 200
    And the response should contain at least one user with id 7

  @RegistrarUsuarios
  Scenario: Register a new user and validate the response
    Given the actor has access to the url "https://reqres.in"
    When he registers a user with email "eve.holt@reqres.in" and password "pistol"
    Then the response code should be 200
    And the response body should contain a token

  @RegistrosUsuariosFallidos
  Scenario: Attempt to register a user without mandatory data
    Given the actor has access to the url "https://reqres.in"
    When he attempts to register a user without password
    Then the response code should be 400
    And the error message should be "Missing password"

  @ActualizarUsuarios
  Scenario: Update existing user information
    Given the actor has access to the url "https://reqres.in"
    When he updates the name to "morpheus" and the job to "zion resident"
    Then the response code should be 200
    And the response body should contain the name "morpheus"

  @EliminarUsuarios
  Scenario: Delete a user
    Given the actor has access to the url "https://reqres.in"
    When he deletes the user with id 7
    Then the response code should be 204
