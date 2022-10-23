Feature: Update a Patient Profile information
  Scenario: Update a Patient Profile
    Given System is ON
    And pharmacist has successfully logged in
    And the patient has a profile in the system
    When the application command updatePatient is invoked
    Then the patient profile's information modified
