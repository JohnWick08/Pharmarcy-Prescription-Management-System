package seg3102.project.contracts.testStubs.factories

import seg3102.project.application.dto.queries.PrescriptionCreateDto
import seg3102.project.domain.prescription.entities.Prescription
import seg3102.project.domain.prescription.factories.PrescriptionFactory

class PrescriptionFactoryStub : PrescriptionFactory{
    override fun createPrescription(prescriptionInfo: PrescriptionCreateDto): Prescription {
        return Prescription(
            prescriptionInfo.id,
            prescriptionInfo. patient,
            prescriptionInfo. prescriber,
            prescriptionInfo. prescriptionFills,
            prescriptionInfo. date,
            prescriptionInfo. endDate,
            prescriptionInfo. medicineDIN,
            prescriptionInfo. medicineName,
            prescriptionInfo. medicineStrength,
            prescriptionInfo. medicineAmount,
            prescriptionInfo. methodOfAdministration,
            prescriptionInfo. frequencyOfAdministration,
            prescriptionInfo. optionConsideration,
            prescriptionInfo. refillable,
            prescriptionInfo. timesCanBeFilled,
            prescriptionInfo.totalTimesPickedUp,
            prescriptionInfo.authorization,
            prescriptionInfo. isVerified)
    }
}