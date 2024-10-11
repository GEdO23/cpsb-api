# language: en

@Login
Feature: Login

  @LoginValido @SmokeTest
  Scenario: Login com credenciais validas
    Given i am in login screen
    When enter with valid credentials
    And click submit button
    Then view home screen

  @LoginInvalido
  Scenario: Login com username invalido
    Given i am in login screen
    When enter with invalid username
    And click submit button
    Then view login error message

  @LoginInvalido
  Scenario: Login com password invalido
    Given i am in login screen
    When enter with invalid password
    And click submit button
    Then view login error message

  @SignOff
  Scenario: Realizar o Sign Off
    Given i am in login screen
    When enter with valid credentials
    And click submit button
    Then view home screen
    When click sign off button
    Then view logout success message