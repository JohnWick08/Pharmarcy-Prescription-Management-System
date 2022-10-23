Feature: Create a new Prescription
  Scenario: Create a new Precription
    Given prescriber is signed-in
    And patient is registered in system
    And prescriber is confirmed as valid
    And The Referred Medicine should be DIN listed in the Health Canada’s Drug Product Database
    When the application command createPrescription is invoked
    Then System validate the prescription information
    And assign a unique identification to prescription
    And save prescription into database
    And add prescription to patient's prescription