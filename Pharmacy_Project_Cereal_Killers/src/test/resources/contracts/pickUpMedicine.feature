Feature: Pick up Medicine
  Scenario: Pharmacist pick up medicine for patient
    Given System is ON
    And pharmacist has successfully logged in
    And the patient has a profile in the system
    And prescription fill status as waiting for pick-up
    And the pharmacist records a summary of the discussion and the use case ends
    When the application command pickUpMedicine is invoked
    Then prescription fill status set as picked-up
    And the summary of the discussion is recorded
