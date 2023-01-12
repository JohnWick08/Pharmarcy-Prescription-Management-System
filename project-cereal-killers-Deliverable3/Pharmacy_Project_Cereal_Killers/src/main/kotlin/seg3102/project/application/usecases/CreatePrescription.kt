package seg3102.project.application.usecases

import seg3102.project.application.dto.queries.PatientCreateDto
import seg3102.project.application.dto.queries.PrescriberCreateDto
import seg3102.project.application.dto.queries.PrescriptionCreateDto

interface CreatePrescription {
    fun createPrescription(prescriptionInfo: PrescriptionCreateDto):Boolean
}