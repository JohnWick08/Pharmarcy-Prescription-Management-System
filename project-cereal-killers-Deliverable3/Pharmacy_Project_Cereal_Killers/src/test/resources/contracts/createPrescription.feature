Feature: Create a new Prescription
  Scenario: Create a new Prescription
    Given prescriber is signed in
    And patient is registered in system
    And prescriber is confirmed as valid
    And The Referred Medicine should be DIN listed in the Health Canada’s Drug Product Database
    When the application command createPrescription is invoked
    Then create a new prescription with a unique identification to prescription
    And save prescription into database
    And add prescription to patient's prescription