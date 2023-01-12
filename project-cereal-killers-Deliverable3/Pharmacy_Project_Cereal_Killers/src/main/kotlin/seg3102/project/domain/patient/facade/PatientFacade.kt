package seg3102.project.domain.patient.facade

import seg3102.project.application.dto.queries.PatientCreateDto
import seg3102.project.application.dto.queries.PrescriptionCreateDto

interface PatientFacade {
    fun createPatient(patientInfo: PatientCreateDto):Boolean
    fun updatePatient(patientID: String, patientInfo: PatientCreateDto):Boolean
    fun addToPatient(patientID: String,prescription: PrescriptionCreateDto)
}