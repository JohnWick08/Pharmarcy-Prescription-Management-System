package seg3102.project.domain.prescription.entities

import seg3102.project.domain.patient.entities.Patient
import java.util.*

class PrescriptionFill (
    var id: UUID,
    var prescription: Prescription,
    var status: String,
    var verified: Boolean,
    var readyForPickup: Boolean,
    var summary: String,
    var patient: Patient
        ){

    fun updateSummary(updSummary: String) {
        summary = updSummary
    }

    fun updateStatus(updStatus: String) {
        status = updStatus
    }
    fun verifies() {
        verified = true
    }
}