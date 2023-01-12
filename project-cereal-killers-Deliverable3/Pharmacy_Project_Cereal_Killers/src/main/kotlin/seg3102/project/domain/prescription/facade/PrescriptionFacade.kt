package seg3102.project.domain.prescription.facade

import seg3102.project.application.dto.queries.PatientCreateDto
import seg3102.project.application.dto.queries.PrescriberCreateDto
import seg3102.project.application.dto.queries.PrescriptionCreateDto
import seg3102.project.domain.prescription.entities.Prescription
import seg3102.project.domain.prescription.entities.PrescriptionFill
import seg3102.project.domain.user.entities.Prescriber
import java.util.*

interface PrescriptionFacade {
//    fun confirmPrescriber(prescriber: Prescriber): Boolean
//    fun confirmPrescription(prescription: Prescription): Boolean

//    fun createPrescription (patientInfo: PatientCreateDto, PrescriberInfo: PrescriberCreateDto)

    fun recordSummary (prescriptionFill: PrescriptionFill, summary: String): Boolean

    fun setPrescriptionStatus (prescriptionFill: Prescription, pickedUp: String): Boolean
}