package seg3102.project.contracts.testStubs.factories

import seg3102.project.application.dto.queries.PrescriptionFillCreateDto
import seg3102.project.domain.patient.entities.Patient
import seg3102.project.domain.prescription.entities.Prescription
import seg3102.project.domain.prescription.entities.PrescriptionFill
import seg3102.project.domain.prescription.factories.PrescriptionFillFactory
import java.util.*

class PrescriptionFillFactoryStub: PrescriptionFillFactory {
    override fun createPrescriptionFill(prescriptionInfo: PrescriptionFillCreateDto): PrescriptionFill {
        return PrescriptionFill(prescriptionInfo.id,
            prescriptionInfo.prescription,
            prescriptionInfo. status,
            prescriptionInfo. verified,
            prescriptionInfo. readyForPickup,
            prescriptionInfo. summary,
            prescriptionInfo. patient)

    }
}