package seg3102.project.domain.patient.factories

import seg3102.project.application.dto.queries.PatientCreateDto
import seg3102.project.domain.patient.entities.Patient

interface PatientFactory {
    fun createPatient(patientInfo: PatientCreateDto):Patient
}