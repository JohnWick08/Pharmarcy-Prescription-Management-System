Feature: Update a Patient Profile
  Scenario: Update a Patient Profile
    Given pharmacist is signed in
    And the patient has a profile in the system
    When the application command updatePatient is invoked
    Then the patient profile's information modified
