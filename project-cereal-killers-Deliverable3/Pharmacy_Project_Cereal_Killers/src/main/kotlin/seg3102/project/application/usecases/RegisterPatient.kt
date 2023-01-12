package seg3102.project.application.usecases

import seg3102.project.application.dto.queries.PatientCreateDto
import seg3102.project.domain.patient.entities.Patient

interface RegisterPatient {
    fun registerPatient(patientinfo: PatientCreateDto):Boolean;
}