package seg3102.project.domain.prescription.factories

import seg3102.project.application.dto.queries.PrescriptionCreateDto
import seg3102.project.domain.prescription.entities.Prescription

interface PrescriptionFactory {
    fun createPrescription(prescriptionInfo:PrescriptionCreateDto) : Prescription
}