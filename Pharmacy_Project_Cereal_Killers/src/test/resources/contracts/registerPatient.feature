Feature: Create a new Patient
  Scenario: Create a new Patient who is not minor
    Given System is ON
    And pharmacist has successfully logged in
    And provided patient information doesn't match an existing patient profile
    And the patient signed the consent form
    When the application command createPatient is invoked
    Then a new patient profile is generated
    And the new patient profile is initialized from the information given by the patient
  Scenario: Create a new Patient who is minor
    Given System is ON and pharmacist has successfully logged in
    And provided patient information doesn't match an existing patient profile
    And the patient's guardian signed the consent form
    When the application command createPatient is invoked
    Then a new patient profile is generated
    And the new patient profile is initialized from the information given by the patient
