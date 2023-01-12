Feature: Update agent's information
  Scenario: update agent’s information
    Given the agent is signed in
    And the update information is given
    When the application command updateAgent is invoked
    Then the agent's information is updated