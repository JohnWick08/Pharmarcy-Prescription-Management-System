Feature: Create an Agent Account
  Scenario: Create an Agent Account
    Given that user's information does not match an existing user
    And the user is either a prescriber or a pharmacist
    When the application command registerAgent is invoked
    Then entered the new user's information
    And a new Agent account is registered
    And an acknowledgment is displayed
