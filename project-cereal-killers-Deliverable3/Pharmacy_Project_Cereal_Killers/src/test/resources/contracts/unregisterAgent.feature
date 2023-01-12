Feature: Unregister Agent
  Scenario:Unregister Agent(prescriber or a pharmacist)
    Given there is an existing user account for this target user
    When the application command unregisterAgent is invoked
    Then find the corresponding user
    And this user is removed from the database
    And an acknowledgment is displayed

