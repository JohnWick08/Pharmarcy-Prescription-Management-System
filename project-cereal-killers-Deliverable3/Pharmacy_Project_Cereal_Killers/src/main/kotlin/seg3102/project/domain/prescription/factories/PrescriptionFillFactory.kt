package seg3102.project.domain.prescription.factories

import seg3102.project.application.dto.queries.PrescriptionFillCreateDto
import seg3102.project.domain.prescription.entities.PrescriptionFill

interface PrescriptionFillFactory {
    fun createPrescriptionFill(prescriptionInfo: PrescriptionFillCreateDto) : PrescriptionFill
}