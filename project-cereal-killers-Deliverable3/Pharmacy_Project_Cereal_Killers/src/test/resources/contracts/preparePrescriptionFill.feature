Feature: Prepare Prescription Fill
  Scenario: A agent prepare prescription fill and no authorization required
    Given pharmacist is signed in
    And medicine is in stock
    And the clinical check of the medication is verified
    And the refill number is not zero
    When the application command preparePrescriptionDill is invoked
    Then the lot number of medicine is recorded
    And the expire date of medicine is recorded
    And set the fill as verified
    And decrease the number of refill number
    And set the prescription filling status as prepared

  Scenario: A agent prepare prescription fill and authorization is required
    Given pharmacist is signed in
    And medicine is in stock
    And the clinical check of the medication is verified
    And the refill number is not zero
    And refill is authorized
    When the application command preparePrescriptionFill is invoked
    Then the lot number of medicine is recorded
    And the expire date of medicine is recorded
    And set the fill as verified
    And decrease the number of refill number
    And set the prescription filling status as prepared




