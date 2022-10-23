Feature: Update prescriber or pharmacist’s own information
  Scenario: update prescriber or pharmacist’s own information
    Given the prescriber or pharmacist is registered and has logged in
    When the user(includes prescriber & pharmacist) clicks to update his/her information on the menu
    And the system shows a form of all the information about this user
    And the user can edit the information shown on the form
    And the user clicks on update
    Then The System displays an acknowledgement message.
    And The information of the prescriber or a pharmacist is updated