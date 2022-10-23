Feature: Create an User Account
  Scenario: Create an User Account(including prescriber or a pharmacist)
  Given that user's information does not match an existing user
    And the user is not register yet by the Admin
    And the target user is either a prescriber or a pharmacist
    When the admin has clicked on register a new staff and directed to the page
    And entered the new user's information
    And has clicked on Create button
    Then a new staff's account is registered and has been given a temporary password
    And an acknowledgment is displayed
