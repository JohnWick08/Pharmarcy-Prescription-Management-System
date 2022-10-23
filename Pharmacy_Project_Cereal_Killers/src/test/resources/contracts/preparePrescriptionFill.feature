Feature: Prepare Prescription Fill
  Scenario: A agent prepare prescription fill and no authorization required
    Given the pharmacist is signed in
    And medicine is in stock
    And the clinical check of the medication is verified
    And the refill number is non-zero
    When the application command preparePrescriptionDill is invoked
    Then record the lot number of medicine
    And record the expire date of medicine
    And set the fill as verified
    And print off the drug information and counseling documents
    And Contact the patient to inform the medicine is ready for pick-up
    And Decrease the number of remaining refill
    And set the prescription filling status as prepared

  Scenario: A agent prepare prescription fill and authorization is required
    Given the pharmacist is signed in
    And medicine is in stock
    And the clinical check of the medication is verified
    And the refill number is non-zero
    And refill is authorized
    When the application command preparePrescriptionDill is invoked
    Then record the lot number of medicine
    And record the expire date of medicine
    And set the fill as verified
    And print off the drug information and counseling documents
    And Contact the patient to inform the medicine is ready for pick-up
    And Decrease the number of remaining refill
    And set the prescription filling status as prepared




