Feature: Pick up Medicine
  Scenario: Pharmacist pick up medicine for patient
    Given pharmacist is signed in
    And patient is registered in system
    And prescription fill status as waiting for pick up
    When the application command pickUpMedicine is invoked
    Then the summary of the discussion is recorded
    And prescription fill status set as picked up
