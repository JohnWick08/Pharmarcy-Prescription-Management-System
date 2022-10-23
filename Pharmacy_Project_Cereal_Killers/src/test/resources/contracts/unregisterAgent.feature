Feature: Unregister User
  Scenario:Unregister an User(prescriber or a pharmacist)
    Given there is an existing user account for this target user
    And the goal is to delete this user account
    When the admin clicks on "unregister a user" on the menu
    And found the corresponding user by searching his/her lastname
    And click on delete this user
    Then this user is removed from the database and has been unregistered
    And an acknowledgment is displayed

