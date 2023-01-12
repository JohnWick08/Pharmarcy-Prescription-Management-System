package seg3102.project.application.usecases

import seg3102.project.application.dto.queries.PrescriptionFillCreateDto
import seg3102.project.domain.prescription.repositories.PrescriptionFillRepository

interface PickUpMedcine {
    fun pickUpMedicine(prescriptionFill: PrescriptionFillCreateDto, summary:String, repository: PrescriptionFillRepository):Boolean
}