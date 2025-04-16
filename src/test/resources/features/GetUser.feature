@smoke
Feature: Get user details
  Scenario: Verify single user details
    Given the API is up and running
    When I send a GET request to "/api/users/2"
    Then the response status code should be 200
    And the response should contain the user with id 2
