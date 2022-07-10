@TestCase
Feature: Testing status code and load time and login failure

  @Status_Code
  Scenario: Checking status code
    Given navigate to url and get response status code

  @Load_Time
  Scenario: Checking load time
    Given navigate to url and get load time

  @Login_Checker
  Scenario: Checking the login functionality
    Given navigate to url and check login functionality
