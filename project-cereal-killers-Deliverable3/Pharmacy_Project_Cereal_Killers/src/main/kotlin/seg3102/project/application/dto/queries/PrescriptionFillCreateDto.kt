package seg3102.project.application.dto.queries

import seg3102.project.domain.patient.entities.Patient
import seg3102.project.domain.prescription.entities.Prescription
import java.util.*

class PrescriptionFillCreateDto (
    var id: UUID,
    var prescription: Prescription,
    var status: String,
    var verified: Boolean,
    var readyForPickup: Boolean,
    var summary: String,
    var patient: Patient
        ){

}