Feature: Create a new Patient
  Scenario: Create a patient account
    Given that patient's information does not match an existing patient account
    And pharmacist is signed in
    When the application command registerPatient is invoked
    Then a new patient is created
    And the new patient profile is initialized filled with  the information given by the patient
